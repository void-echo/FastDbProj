package com.echo.fastdbproj.service;

import com.echo.fastdbproj.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Driver)表服务接口
 *
 * @author makejava
 * @since 2022-08-11 14:08:12
 */
public interface DriverService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Driver queryById(String id);

    /**
     * 分页查询
     *
     * @param driver      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Driver> queryByPage(Driver driver, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param driver 实例对象
     * @return 实例对象
     */
    Driver insert(Driver driver);

    /**
     * 修改数据
     *
     * @param driver 实例对象
     * @return 实例对象
     */
    Driver update(Driver driver);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
