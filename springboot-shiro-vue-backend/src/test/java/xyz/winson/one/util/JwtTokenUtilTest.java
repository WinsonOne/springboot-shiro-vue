package xyz.winson.one.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.winson.one.model.vo.UserPerm;
import xyz.winson.one.shiro.JwtAccount;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author : 温伟聪
 * @Description: TODO
 * @date Date : 2019年10月10日 21:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTokenUtilTest {

    @Test
    public void issueJwt() {
    }

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
}