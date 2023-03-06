package com.framework.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.framework.spring")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringConfig {
}
