package com.zhang.data.service;

import com.zhang.data.dao.UserMapper;
import com.zhang.data.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhen  2019-08-16 12:41
 * <p>
 * UserService
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public List<User> list(){
        return userMapper.selectAll();
    }

}
