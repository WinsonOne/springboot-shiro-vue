package xyz.winson.one.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.winson.one.mapper.SysResourceMapper;
import xyz.winson.one.model.dto.SysResourceDto;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.SysResourceVo;
import xyz.winson.one.service.SysResourceService;
import xyz.winson.one.util.ApiResultUtil;

import java.util.List;

/**
 * @author : 温伟聪
 * @Description: 系统资源服务接口实现类
 * @date Date : 2019年09月30日 11:41
 */
@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Override
    public ApiResult<List<SysResourceVo>> listAll() {
        return ApiResultUtil.success(sysResourceMapper.listAll());
    }

    @Override
    public ApiResult<Void> add(SysResourceDto sysResourceDto) {
        return null;
    }

    @Autowired
    private SysResourceMapper sysResourceMapper;
}
