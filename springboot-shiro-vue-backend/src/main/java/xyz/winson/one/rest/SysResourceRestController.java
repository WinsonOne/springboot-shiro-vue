package xyz.winson.one.rest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.winson.one.group.UpdateGroup;
import xyz.winson.one.model.dto.SysResourceDto;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.SysResourceVo;
import xyz.winson.one.service.SysResourceService;

import java.util.List;

/**
 * @author : 温伟聪
 * @Description: 系统资源接口类
 * @date Date : 2019年09月30日 14:01
 */
@RestController
@RequestMapping("/sys/resource")
public class SysResourceRestController extends BaseRestController {

    /**
     * 获取系统所有资源
     * @return
     */
    @PostMapping("/list")
    @RequiresPermissions("sys:resource:list")
    public ApiResult<List<SysResourceVo>> list() {
        return sysResourceService.listAll();
    }

    /**
     * 新增系统电子烟
     * @param sysResourceDto 待新增系统资源
     * @param bindingResult 参数校验结果
     * @return
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:resource:add")
    public ApiResult<Void> add(@RequestBody @Validated SysResourceDto sysResourceDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return error(bindingResult);
        }
        return sysResourceService.add(sysResourceDto);
    }

    /**
     * 修改系统资源
     * @param sysResourceDto 待修改系统资源
     * @param bindingResult 参数校验结果
     * @return
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:resource:update")
    public ApiResult<Void> update(@RequestBody @Validated(value = {UpdateGroup.class}) SysResourceDto sysResourceDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return error(bindingResult);
        }
        return sysResourceService.update(sysResourceDto);
    }

    /**
     * 逻辑删除系统资源
     * @param ids 待删除资源主键集合
     * @return
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys:resource:delete")
    public ApiResult<Void> delete(List<Long> ids) {
        return sysResourceService.delete(ids);
    }

    @Autowired
    private SysResourceService sysResourceService;
}
