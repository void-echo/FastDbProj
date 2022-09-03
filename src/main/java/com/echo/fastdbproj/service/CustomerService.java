package com.echo.fastdbproj.service;

import com.echo.fastdbproj.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Customer)表服务接口
 *
 * @author makejava
 * @since 2022-08-11 14:08:11
 */
public interface CustomerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Customer queryById(String id);

    /**
     * 分页查询
     *
     * @param customer    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Customer> queryByPage(Customer customer, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    Customer insert(Customer customer);

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    Customer update(Customer customer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<Customer> getAll();


}
