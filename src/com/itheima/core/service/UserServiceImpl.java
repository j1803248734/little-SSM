package com.itheima.core.service;
import com.itheima.core.service.UserService;
import com.itheima.core.dao.UserDao;
import com.itheima.core.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("UserService")
@Transactional //事务的注解
public class UserServiceImpl implements UserService {
    @Resource
    private  UserDao userDao;
    @Override
    public User findUser(String usercode, String password) {
        User user = userDao.findUser(usercode,password);
        return user;
    }
}
