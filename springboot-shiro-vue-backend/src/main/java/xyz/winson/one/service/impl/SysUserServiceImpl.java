package xyz.winson.one.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.winson.one.mapper.SysUserMapper;
import xyz.winson.one.model.dto.SysUserDto;
import xyz.winson.one.model.entity.SysUser;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.ApiResultCodeEnum;
import xyz.winson.one.model.vo.PageQuery;
import xyz.winson.one.model.vo.SysUserVo;
import xyz.winson.one.service.SysUserService;
import xyz.winson.one.util.ApiResultUtil;

import java.util.List;

/**
 * @author : 温伟聪
 * @Description: 系统用户服务接口类实现
 * @date Date : 2019年09月29日 18:31
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Override
    public ApiResult<PageInfo<SysUserVo>> list(PageQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        List<SysUserVo> sysUserVoList = sysUserMapper.list(pageQuery.getQuery());
        PageInfo<SysUserVo> pageInfo = new PageInfo<>(sysUserVoList);
        return ApiResultUtil.success(pageInfo);
    }

    @Override
    public ApiResult<Void> add(SysUserDto sysUserDto) {
        SysUser dbSysUser = sysUserMapper.selectByUsername(sysUserDto.getUsername());
        if (dbSysUser != null) {
            return ApiResultUtil.buildResult(ApiResultCodeEnum.NOT_UNIQUE_DATA, "用户名已存在，请修改。");
        }
        return null;
    }

    @Override
    public ApiResult<Void> update(SysUserDto sysUserDto) {
        return null;
    }

    @Autowired
    private SysUserMapper sysUserMapper;
}
