package cn.edu.com.shiyouwangpan.dao;

import cn.edu.com.shiyouwangpan.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.sql.DriverManager;
import java.util.List;


@Mapper
@Component("UserDao")
public interface UserDao {
    //增加
    @Insert("insert into user(username,password) values(#{username},#{password}) ")
    int addUser(User user);

    //删
    @Delete("delete from user where userId=#{userId}")
    int deleteById(int userId);

    //改
    @Update("update user set password=#{password} where username=#{username}")
    int update(User user);

    //查  查找所有用户
    @Select("select * from user")
    List<User> findAll();

    //根据用户信息去查找用户
    @Select("select * from user where username=#{username} and password=#{password}")
    User findUser(User user);

    //查找用户是否存在
    @Select("select * from user where username=#{username}")
    User findUserByUserName(String username);
}
