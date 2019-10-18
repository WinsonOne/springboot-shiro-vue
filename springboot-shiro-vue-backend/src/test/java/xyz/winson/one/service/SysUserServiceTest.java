package xyz.winson.one.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.winson.one.model.dto.SysUserDTO;
import xyz.winson.one.util.EncryptUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 温伟聪
 * @Description: TODO
 * @date Date : 2019年10月12日 9:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceTest {

    @Test
    public void add() {
        SysUserDTO sysUserDto = new SysUserDTO();
        sysUserDto.setUsername("admin");
        sysUserDto.setNickname("超级管理员");
        EncryptUtil encryptUtil = EncryptUtil.getInstance();
        sysUserDto.setPassword(encryptUtil.MD5("123456"));
        System.out.println(sysUserDto.getPassword());
        sysUserDto.setMobile("15988888888");
        sysUserDto.setEmail("admin@example.com");
        sysUserDto.setState(1);
        sysUserDto.setIsDelete(false);
        sysUserDto.setCreateUserId(1L);
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(1L);
        sysUserDto.setRoleIds(roleIds);
        sysUserService.add(sysUserDto);
    }

    @Autowired
    private SysUserService sysUserService;
}