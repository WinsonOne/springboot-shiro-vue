package xyz.winson.one.model.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author : 温伟聪
 * @Description: 分页查询模型
 * @date Date : 2019年09月29日 19:55
 */
@Data
public class PageQuery {

    /**
     * 页码
     */
    private int pageNum;

    /**
     * 每页查询记录数
     */
    private int pageSize;

    /**
     * 查询条件
     */
    private Map query;
}
