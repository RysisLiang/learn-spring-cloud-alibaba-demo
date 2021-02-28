package com.example.common.api;

import java.util.List;

/**
 * CRUDService
 *
 * @author liangxiao
 * @version 1.00
 * @Date 2020/8/8 16:45
 */
public interface CRUDService<T> {

    /**
     * 新增
     *
     * @param t
     * @return
     */
    T save(T t);

    /**
     * 获取全部信息
     *
     * @return
     */
    List<T> getAll();

    /**
     * 根据id查询信息
     *
     * @param id
     * @return
     */
    T findOneById(Long id);


}
