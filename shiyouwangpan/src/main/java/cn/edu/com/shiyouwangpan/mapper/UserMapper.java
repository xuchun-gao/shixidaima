package cn.edu.com.shiyouwangpan.mapper;

import cn.edu.com.shiyouwangpan.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Class Name :UserMapper
 * Package :cn.edu.com.shiyouwangpan.mapper
 * Description:
 *
 * @Author: Mr.chunxugao
 * Create: 2023/7/4- 11:29
 * @Version:v1.0
 */
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user (username, password) VALUES (#{username}, #{password})")
    void insertUser(User user);
}
