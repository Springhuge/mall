package com.jihu.mall.tiny;

import com.jihu.mall.tiny.common.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJwt {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void testGetJwt(){
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MyIsImNyZWF0ZWQiOjE1ODUxMzY4MzIwMTcsImV4cCI6MTU4NTc0MTYzMn0.q1h_yDridAGorXOLtaLuzeE6fnWgF9H2G_2CEevDpJ34U8ZIkzV_w9rB7twX_UOO9zJszH1mex78BjYEooIyuw";
        Claims claimsFromToken = jwtTokenUtil.getClaimsFromToken(token);
        System.out.println(claimsFromToken);
    }
}
