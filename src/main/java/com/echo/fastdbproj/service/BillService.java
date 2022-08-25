package com.echo.fastdbproj.service;

import com.echo.fastdbproj.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Bill)表服务接口
 *
 * @author makejava
 * @since 2022-08-24 15:54:16
 */
public interface BillService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Bill queryById(String id);

    /**
     * 分页查询
     *
     * @param bill        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Bill> queryByPage(Bill bill, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param bill 实例对象
     * @return 实例对象
     */
    Bill insert(Bill bill);

    /**
     * 修改数据
     *
     * @param bill 实例对象
     * @return 实例对象
     */
    Bill update(Bill bill);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    double getAverScoreOfDriver(String driverId);

    List<Bill> selectAllByCustomerId(String customerId);
    List<Bill> selectAllByDriverId(String driverId);

}
