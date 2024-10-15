package com.example.onlineexamsystem.utils;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.Core;

import java.util.Base64;

public class FaceCaptureUtil {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    /**
     * 捕获摄像头图像并返回Base64编码的图像字符串
     *
     * @return Base64编码的图像字符串，如果捕获失败则返回null
     */
    public static String captureImageAsBase64() {
        VideoCapture camera = new VideoCapture(0);

        if (!camera.isOpened()) {
            System.out.println("Error: Camera is not available.");
            return null;
        }

        Mat frame = new Mat();
        camera.read(frame);

        if (!frame.empty()) {
            String base64Image = matToBase64(frame);
            camera.release();
            return base64Image;
        } else {
            System.out.println("Error: No frame captured from camera.");
            camera.release();
            return null;
        }
    }

    /**
     * 将Mat对象转换为Base64编码的字符串
     *
     * @param image OpenCV Mat对象，表示捕获的图像
     * @return Base64编码的图像字符串
     */
    private static String matToBase64(Mat image) {
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", image, matOfByte);
        byte[] byteArray = matOfByte.toArray();
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
