package cn.edu.com.shiyouwangpan.service.impl;

import cn.edu.com.shiyouwangpan.dao.UserDao;
import cn.edu.com.shiyouwangpan.entity.User;
import cn.edu.com.shiyouwangpan.mapper.UserMapper;
import cn.edu.com.shiyouwangpan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    private UserMapper userMapper;

    /**
     *业务
     * @param user
     * @return
     */
    @Override
    public User findByUser(User user) {
        //此次业务逻辑  你们去完成。。。。。
        User user1 = userDao.findUser(user);
        return user1;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> all = userDao.findAll();
        return all;
    }

    @Override
    public void registerUser(User user) {
        userMapper.insertUser(user);
    }
}
