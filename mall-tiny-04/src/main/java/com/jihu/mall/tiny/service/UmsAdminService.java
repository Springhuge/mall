package com.jihu.mall.tiny.service;

import com.jihu.mall.tiny.common.utils.JwtTokenUtil;
import com.jihu.mall.tiny.mbg.mapper.UmsAdminMapper;
import com.jihu.mall.tiny.mbg.mapper.UmsAdminRoleRelationMapper;
import com.jihu.mall.tiny.mbg.model.UmsAdmin;
import com.jihu.mall.tiny.mbg.model.UmsAdminExample;
import com.jihu.mall.tiny.mbg.model.UmsPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.Date;
import java.util.List;

@Service("umsAdminService")
public class UmsAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public UmsAdmin getAdminByUsername(String username){
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> admins = adminMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(admins)){
            return admins.get(0);
        }
        return null;
    }

    public List<UmsPermission> getPermissionList(Long adminId){
        return adminRoleRelationMapper.getPermissionList(adminId);
    }


    public String login(String username, String password) {

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            String token = jwtTokenUtil.generateToken(userDetails);
            return token;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return null;
    }

    public UmsAdmin register(UmsAdmin umsAdmin) {
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        UmsAdminExample example = new UmsAdminExample();
        List<UmsAdmin> admins = adminMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(admins)){
            return null;
        }
        //将密码加密
        String pasword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(pasword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }
}
