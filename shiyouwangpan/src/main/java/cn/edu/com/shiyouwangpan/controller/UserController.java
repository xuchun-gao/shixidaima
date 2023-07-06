package cn.edu.com.shiyouwangpan.controller;

import cn.edu.com.shiyouwangpan.entity.ResultModel;
import cn.edu.com.shiyouwangpan.entity.User;
import cn.edu.com.shiyouwangpan.service.UserService;
import cn.edu.com.shiyouwangpan.utils.HDFSUtils;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;



@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public ResultModel<User> getLogin(@RequestBody User user){
        System.out.println(user);
        //验证用户名与密码是否正确
        User byUser = userService.findByUser(user);
        ResultModel<User> result=null;
        //判断
        if(byUser!=null){
           result = ResultModel.success("登录成功",user);
        }else{
            result = ResultModel.error("用户名或密码错误");
        }

        return result;
    }
    @RequestMapping("/findAllUsers")
    @ResponseBody
    public ResultModel<List<User>> getfindAllUsers(){
        List<User> allUsers = userService.findAllUsers();
        ResultModel<List<User>> result=null;
        if(allUsers!=null){
            result = ResultModel.success("获取成功",allUsers);
        }else{
            result = ResultModel.error("获取失败");
        }
        return result;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok().build();
    }




}
