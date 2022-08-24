package com.echo.fastdbproj.service;

import com.echo.fastdbproj.entity.CustomerPayApproach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (CustomerPayApproach)表服务接口
 *
 * @author makejava
 * @since 2022-08-24 22:52:35
 */
public interface CustomerPayApproachService {

    /**
     * 通过ID查询单条数据
     *
     * @param payApproachId 主键
     * @return 实例对象
     */
    CustomerPayApproach queryById(String payApproachId);

    /**
     * 分页查询
     *
     * @param customerPayApproach 筛选条件
     * @param pageRequest         分页对象
     * @return 查询结果
     */
    Page<CustomerPayApproach> queryByPage(CustomerPayApproach customerPayApproach, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param customerPayApproach 实例对象
     * @return 实例对象
     */
    CustomerPayApproach insert(CustomerPayApproach customerPayApproach);

    /**
     * 修改数据
     *
     * @param customerPayApproach 实例对象
     * @return 实例对象
     */
    CustomerPayApproach update(CustomerPayApproach customerPayApproach);

    /**
     * 通过主键删除数据
     *
     * @param payApproachId 主键
     * @return 是否成功
     */
    boolean deleteById(String payApproachId);

}
