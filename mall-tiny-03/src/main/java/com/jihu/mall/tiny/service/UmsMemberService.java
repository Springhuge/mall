package com.jihu.mall.tiny.service;

import com.jihu.mall.tiny.common.api.CommonResult;
import com.jihu.mall.tiny.common.api.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

@Service("umsMemberService")
public class UmsMemberService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    /**
     * 生成验证码
     * @param telephone
     * @return
     */
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i =0; i < 6; i++ ){
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone,sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone,AUTH_CODE_EXPIRE_SECONDS);
        return new CommonResult(ResultCode.SUCCESS,"获取验证码成功");
    }

    /**
     * 判断验证码和手机号码是否匹配
     * @param telephone
     * @param authCode
     * @return
     */
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if(StringUtils.isEmpty(authCode)){
            return new CommonResult(ResultCode.FAILED);
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        if(authCode.equals(realAuthCode)){
            return new CommonResult(ResultCode.TRUECODE);
        }else{
            return new CommonResult(ResultCode.FAILCODE);
        }
    }
}
