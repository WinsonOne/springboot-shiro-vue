package xyz.winson.one.service;

import com.github.pagehelper.PageInfo;
import xyz.winson.one.model.dto.SysUserDTO;
import xyz.winson.one.model.entity.SysUser;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.PageQuery;
import xyz.winson.one.model.vo.SysResourceVO;
import xyz.winson.one.model.vo.SysUserVO;
import xyz.winson.one.shiro.JwtAccount;

import java.util.List;

/**
 * @author : 温伟聪
 * @Description: 系统用户服务接口类
 * @date Date : 2019年09月29日 18:31
 */
public interface SysUserService {

    /**
     * 查询系统用户列表
     * @param pageQuery 分页查询
     * @return
     */
    ApiResult<PageInfo<SysUserVO>> list(PageQuery pageQuery);

    /**
     * 新增系统用户
     * @param sysUserDto 待系统用户
     * @return
     */
    ApiResult<Void> add(SysUserDTO sysUserDto);

    /**
     * 修改系统用户
     * @param sysUserDto 待修改系统用户
     * @return
     */
    ApiResult<Void> update(SysUserDTO sysUserDto);

    /**
     * 用户登录
     * @param sysUser
     * @return
     */
    ApiResult<JwtAccount> login(SysUser sysUser);

    /**
     * 获取当前用户的资源
     * @return
     */
    ApiResult<List<SysResourceVO>> getUserResources();
}
