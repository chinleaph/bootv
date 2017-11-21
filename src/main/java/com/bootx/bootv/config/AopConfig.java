package com.bootx.bootv.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.bootx.bootv")
@EnableAspectJAutoProxy
public class AopConfig {
}
