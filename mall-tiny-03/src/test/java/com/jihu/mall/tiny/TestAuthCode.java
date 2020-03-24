package com.jihu.mall.tiny;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAuthCode {

    @Test
    public void TestAuthCode(){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for(int i = 0; i < 6; i++ ){
            sb.append(random.nextInt(10));
        }
        System.out.println(sb.toString());
    }
}
