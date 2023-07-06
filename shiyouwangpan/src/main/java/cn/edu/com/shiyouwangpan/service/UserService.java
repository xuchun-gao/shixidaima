package cn.edu.com.shiyouwangpan.service;

import cn.edu.com.shiyouwangpan.entity.User;

import java.util.List;


public interface UserService {

    /**
     * 验证  用户是否存在的业务
     */
    User  findByUser(User user);


    /**
     * 获取所有的用户
     */
    List<User> findAllUsers();


    void registerUser(User user);
}
