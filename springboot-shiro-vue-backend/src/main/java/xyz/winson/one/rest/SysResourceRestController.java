package xyz.winson.one.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
public class SysResourceRestController {

    @PostMapping("/list")
    public ApiResult<List<SysResourceVo>> list() {
        return sysResourceService.listAll();
    }

    @PostMapping("/add")
    public ApiResult<Void> add(@RequestBody @Validated SysResourceDto sysResourceDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 直接返回提示
        }
        return sysResourceService.add(sysResourceDto);
    }

    @Autowired
    private SysResourceService sysResourceService;
}
