package com.ssafy.myroutine.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.myroutine.model.dao")
public class DBConfig {

}
