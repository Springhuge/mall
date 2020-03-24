package com.jihu.mall.tiny.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 用于配置需要动态生成的mapper接口的路径
 */
@Configuration
@MapperScan("com.jihu.mall.tiny.mbg.mapper")
public class MyBatisConfig {


}
