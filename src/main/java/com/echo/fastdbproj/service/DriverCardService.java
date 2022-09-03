package com.echo.fastdbproj.service;

import com.echo.fastdbproj.entity.DriverCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (DriverCard)表服务接口
 *
 * @author makejava
 * @since 2022-08-24 22:56:06
 */
public interface DriverCardService {

    /**
     * 通过ID查询单条数据
     *
     * @param driverId 主键
     * @return 实例对象
     */
    DriverCard queryById(String driverId);

    /**
     * 分页查询
     *
     * @param driverCard  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<DriverCard> queryByPage(DriverCard driverCard, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param driverCard 实例对象
     * @return 实例对象
     */
    DriverCard insert(DriverCard driverCard);

    /**
     * 修改数据
     *
     * @param driverCard 实例对象
     * @return 实例对象
     */
    DriverCard update(DriverCard driverCard);

    /**
     * 通过主键删除数据
     *
     * @param driverId 主键
     * @return 是否成功
     */
    boolean deleteById(String driverId);

    List<DriverCard> getAll();

}
