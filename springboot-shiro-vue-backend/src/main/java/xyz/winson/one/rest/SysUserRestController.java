package xyz.winson.one.rest;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.winson.one.group.UpdateGroup;
import xyz.winson.one.model.dto.SysUserDto;
import xyz.winson.one.model.entity.SysUser;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.PageQuery;
import xyz.winson.one.model.vo.SysUserVo;
import xyz.winson.one.service.SysUserService;
import xyz.winson.one.shiro.JwtAccount;

/**
 * @author : 温伟聪
 * @Description: 系统用户接口
 * @date Date : 2019年09月30日 11:25
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserRestController extends BaseRestController {

    /**
     * 分页查询系统用户列表数据
     * @param pageQuery
     * @return
     */
    @PostMapping("/list")
    @RequiresPermissions("sys:user:list")
    public ApiResult<PageInfo<SysUserVo>> list(@RequestBody PageQuery pageQuery) {
        return sysUserService.list(pageQuery);
    }

    /**
     *
     * @param sysUserDto 待新增用户信息
     * @param bindingResult 参数校验结果
     * @return
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:user:add")
    public ApiResult<Void> add(@RequestBody @Validated SysUserDto sysUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 参数校验不通过，提示前端
            return error(bindingResult);
        }
        return sysUserService.add(sysUserDto);
    }

    /**
     *
     * @param sysUserDto 待修改用户信息
     * @param bindingResult 参数校验结果
     * @return
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public ApiResult<Void> update(@RequestBody @Validated(value = {UpdateGroup.class}) SysUserDto sysUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 参数校验不通过，提示前端
            return error(bindingResult);
        }
        return sysUserService.update(sysUserDto);
    }

    /**
     * 登录
     * @param sysUser
     * @return
     */
    @PostMapping("/login")
    public ApiResult<JwtAccount> login(@RequestBody SysUser sysUser) {
        return sysUserService.login(sysUser);
    }

    @Autowired
    private SysUserService sysUserService;
}
