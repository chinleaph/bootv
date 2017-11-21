package com.bootx.bootv;

import com.bootx.bootv.bean.TA;
import com.bootx.bootv.config.AopConfig;
import com.bootx.bootv.service.DemoMethodService;
import com.bootx.bootv.service.DemoService;
import com.yeepay.shade.com.google.common.collect.Lists;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "boot start";
    }

    @RequestMapping(value = "/savedata",method = RequestMethod.POST)
    public String saveData(@RequestBody String msg, @Context HttpServletRequest request){
        System.out.println("body的参数：" + msg);
        System.out.println("header的参数：" + request.getHeader("x-code"));
        return request.getHeader("x-code");
    }

    @RequestMapping("/testList")
    public void testList(){
        List<String> firstList = Arrays.asList("about1", "after2", "coffee3", "drop4", "evening5", "float6", "gall7", "hot8", "image9", "jeneray10", "know11", "link12");
        List<List<String>> secondList = Lists.partition(firstList, 3);
        System.out.println(secondList.toString());
        List<List<List<String>>> thirdList = Lists.partition(secondList, 2);
        System.out.println(thirdList.toString());
    }

    @RequestMapping("/index")
    public void demo() throws ClassNotFoundException {
//        System.out.println(TA.UNI_NAME);
        ClassLoader cl = ClassLoader.getSystemClassLoader();
//        System.out.println("loadClass -");
//        cl.loadClass("com.bootx.bootv.bean.TA");
//        Class.forName("TA");
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url);
        }
    }

    @RequestMapping("/aop")
    public void aopDemo() {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
//        DemoService demoService = context.getBean(DemoService.class);
//        demoService.addTail();
//        DemoMethodService demoMethodService = context.getBean(DemoMethodService.class);
//        demoMethodService.addHat();
//        context.close();
    }
}
