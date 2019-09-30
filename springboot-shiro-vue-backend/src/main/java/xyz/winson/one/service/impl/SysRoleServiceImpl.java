package xyz.winson.one.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.winson.one.mapper.SysRoleMapper;
import xyz.winson.one.model.dto.SysRoleDto;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.PageQuery;
import xyz.winson.one.model.vo.SysRoleVo;
import xyz.winson.one.service.SysRoleService;

/**
 * @author : 温伟聪
 * @Description: 系统角色服务接口实现类
 * @date Date : 2019年09月30日 10:16
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Override
    public ApiResult<PageInfo<SysRoleVo>> list(PageQuery pageQuery) {
        return null;
    }

    @Override
    public ApiResult<Void> add(SysRoleDto sysRoleDto) {
        return null;
    }

    @Override
    public ApiResult<Void> update(SysRoleDto sysRoleDto) {
        return null;
    }

    @Autowired
    private SysRoleMapper sysRoleMapper;
}
