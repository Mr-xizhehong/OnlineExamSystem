//package com.example.onlineexamsystem;
//
//import com.example.onlineexamsystem.Service.UserService;
//import com.example.onlineexamsystem.utils.FaceCaptureUtil;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class OnlineExamSystemApplicationTests {
//
//    @Autowired
//    UserService userService;
//
//    @Test
//    void contextLoads() throws JSONException {
//        String faceBase64 = FaceCaptureUtil.captureImageAsBase64();
//        String result = userService.searchFace(faceBase64);
//        JSONObject jsonObject = new JSONObject(result);
//
//        // 获取 result 对象
//        JSONObject result2 = jsonObject.getJSONObject("result");
//
//        // 获取 user_list 数组
//        JSONArray userList = result2.getJSONArray("user_list");
//
//        // 获取数组中的第一个对象
//        JSONObject user = userList.getJSONObject(0);
//
//        // 获取 user_id 和 score
//        String userId = user.getString("user_id");
//        double score = user.getDouble("score");
//
//        // 输出结果
//        System.out.println("User ID: " + userId);
//        System.out.println("Score: " + score);
//        System.out.println(result2);
//        System.out.println(result);
//    }
//}
