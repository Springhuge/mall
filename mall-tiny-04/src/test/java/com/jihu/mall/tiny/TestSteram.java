package com.jihu.mall.tiny;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSteram {

    @Test
    public void TestStearm(){
        List<String> bigDatas = new ArrayList<String>();
        bigDatas.set(1,"document");
        bigDatas.set(2,"getElement");
        bigDatas.set(1,"ById");
        bigDatas.set(1,"find");
        System.out.println(bigDatas);
        List<SimpleGrantedAuthority> collect = bigDatas.stream()
                .filter(bigData -> bigData != null)
                .map(bigData -> new SimpleGrantedAuthority(bigData))
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    public static void main(String[] args) {
        List<String> bigDatas = new ArrayList<String>();
        bigDatas.add("document");
        bigDatas.add("getElement");
        bigDatas.add("ById");
        bigDatas.add("find");
        System.out.println(bigDatas);
        List<SimpleGrantedAuthority> collect = bigDatas.stream()
                .filter(bigData -> bigData != null)
                .map(bigData -> new SimpleGrantedAuthority(bigData))
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
