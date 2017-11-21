package com.bootx.bootv.service;

import com.bootx.bootv.aop.Action;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    @Action(name = "注解式拦截的方法-add")
    public String addTail() {
        return " ---- 啦啦啦啦啦，我是小尾巴。";
    }

}
