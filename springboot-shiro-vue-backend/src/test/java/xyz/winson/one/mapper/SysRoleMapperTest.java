package xyz.winson.one.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.winson.one.model.entity.SysRole;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * @author : 温伟聪
 * @Description: TODO
 * @date Date : 2019年09月27日 19:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleMapperTest {

    @Test
    public void insert() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试角色");
        sysRole.setState(1);
        sysRole.setIsDelete(false);
        sysRole.setCreateTime(Calendar.getInstance().getTime());
        sysRole.setCreateUserId(1L);
        sysRoleMapper.insert(sysRole);
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Autowired
    private SysRoleMapper sysRoleMapper;
}