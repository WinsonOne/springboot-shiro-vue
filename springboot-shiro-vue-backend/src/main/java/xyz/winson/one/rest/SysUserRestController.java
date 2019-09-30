package xyz.winson.one.rest;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.winson.one.group.UpdateGroup;
import xyz.winson.one.model.dto.SysUserDto;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.PageQuery;
import xyz.winson.one.model.vo.SysUserVo;
import xyz.winson.one.service.SysUserService;

/**
 * @author : 温伟聪
 * @Description: 系统用户接口
 * @date Date : 2019年09月30日 11:25
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserRestController {

    /**
     * 分页查询系统用户列表数据
     * @param pageQuery
     * @return
     */
    @PostMapping("/list")
    public ApiResult<PageInfo<SysUserVo>> list(@RequestBody PageQuery pageQuery) {
        return sysUserService.list(pageQuery);
    }

    @PostMapping("/add")
    public ApiResult<Void> add(@RequestBody @Validated SysUserDto sysUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 参数校验不通过，提示前端
        }
        return sysUserService.add(sysUserDto);
    }

    @PostMapping("/update")
    public ApiResult<Void> update(@RequestBody @Validated(value = {UpdateGroup.class}) SysUserDto sysUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 参数校验不通过，提示前端
        }
        return sysUserService.update(sysUserDto);
    }

    @Autowired
    private SysUserService sysUserService;
}
