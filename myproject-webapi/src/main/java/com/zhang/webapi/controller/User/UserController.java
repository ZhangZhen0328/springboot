package com.zhang.webapi.controller.User;

import com.zhang.core.plugin.amqp.RabbitMqSender;
import com.zhang.data.service.UserService;
import com.zhang.webapi.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhen  2019-08-16 11:06
 * <p>
 * UserController
 **/
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private RabbitMqSender rabbitMqSender;

    @RequestMapping("/list")
    public String list(){
        rabbitMqSender.msg();
        return userService.list().toString();
    }
}
