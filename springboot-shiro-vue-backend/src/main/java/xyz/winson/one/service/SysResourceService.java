package xyz.winson.one.service;

import xyz.winson.one.model.dto.SysResourceDto;
import xyz.winson.one.model.vo.ApiResult;
import xyz.winson.one.model.vo.SysResourceVo;

import java.util.List;

/**
 * @author : 温伟聪
 * @Description: 系统资源服务接口类
 * @date Date : 2019年09月30日 11:41
 */
public interface SysResourceService {

    /**
     * 分页查询系统资源
     * @return
     */
    ApiResult<List<SysResourceVo>> listAll();

    /**
     * 新增系统资源
     * @param sysResourceDto
     * @return
     */
    ApiResult<Void> add(SysResourceDto sysResourceDto);
}
