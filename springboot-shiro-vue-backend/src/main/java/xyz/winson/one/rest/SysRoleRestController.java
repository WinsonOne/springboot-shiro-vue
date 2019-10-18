package xyz.winson.one.rest;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.winson.one.group.UpdateGroup;
import xyz.winson.one.model.dto.SysRoleDTO;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.PageQuery;
import xyz.winson.one.model.vo.SysRoleVO;
import xyz.winson.one.service.SysRoleService;

import java.util.List;

/**
 * @author : 温伟聪
 * @Description: 系统角色restful controller
 * @date Date : 2019年10月04日 8:03
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleRestController extends BaseRestController {

    /**
     * 分页查询系统角色
     * @param pageQuery 分页查询参数
     * @return 系统角色集合
     */
    @PostMapping("/list")
    @RequiresPermissions("sys:role:list")
    public ApiResult<PageInfo<SysRoleVO>> list(@RequestBody PageQuery pageQuery) {
        return sysRoleService.list(pageQuery);
    }

    /**
     * 新增系统角色
     * @param sysRoleDto 待新增系统角色信息
     * @param bindingResult 参数校验结果
     * @return
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:role:add")
    public ApiResult<Void> add(@RequestBody @Validated SysRoleDTO sysRoleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return error(bindingResult);
        }
        return sysRoleService.add(sysRoleDto);
    }

    /**
     * 修改系统角色
     * @param sysRoleDto 待修改系统角色
     * @param bindingResult 参数校验结果
     * @return
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:role:update")
    public ApiResult<Void> update(@RequestBody @Validated(value = {UpdateGroup.class}) SysRoleDTO sysRoleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return error(bindingResult);
        }
        return sysRoleService.update(sysRoleDto);
    }

    /**
     * 逻辑删除系统角色
     * @param ids 待删除系统角色ID集合
     * @return
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public ApiResult<Void> delete(List<Long> ids) {
        return sysRoleService.delete(ids);
    }

    @Autowired
    private SysRoleService sysRoleService;
}
