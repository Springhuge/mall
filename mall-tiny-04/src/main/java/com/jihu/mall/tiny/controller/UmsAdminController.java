package com.jihu.mall.tiny.controller;

import com.jihu.mall.tiny.common.api.CommonResult;
import com.jihu.mall.tiny.common.api.ResultCode;
import com.jihu.mall.tiny.mbg.model.UmsAdmin;
import com.jihu.mall.tiny.mbg.model.UmsPermission;
import com.jihu.mall.tiny.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("登录后返回token")
    @PostMapping("/login")
    public CommonResult login(@RequestBody UmsAdmin umsAdmin){
        String token = adminService.login(umsAdmin.getUsername(),umsAdmin.getPassword());
        if(StringUtils.isEmpty(token)){
            return new CommonResult(ResultCode.ERRORUSERNAMEPASSWORD);
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return new CommonResult(ResultCode.SUCCESS,tokenMap);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public CommonResult register(@RequestBody UmsAdmin umsAdminParam){
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if(!Optional.ofNullable(umsAdmin).isPresent()){
            return new CommonResult(ResultCode.ERRORREGISTER);
        }
        return new CommonResult(ResultCode.SUCCESS,umsAdmin);
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @GetMapping("/permission/{adminid}")
    public CommonResult getPermissionList(@PathVariable("adminid") Long adminid){
        List<UmsPermission> permissionList = adminService.getPermissionList(adminid);
        return new CommonResult(ResultCode.SUCCESS,permissionList);
    }
}
