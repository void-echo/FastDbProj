package com.echo.fastdbproj.service;

import com.echo.fastdbproj.entity.ReserveBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (ReserveBill)表服务接口
 *
 * @author makejava
 * @since 2022-08-27 21:46:12
 */
public interface ReserveBillService {

    /**
     * 通过ID查询单条数据
     *
     * @param billId 主键
     * @return 实例对象
     */
    ReserveBill queryById(String billId);

    /**
     * 分页查询
     *
     * @param reserveBill 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<ReserveBill> queryByPage(ReserveBill reserveBill, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param reserveBill 实例对象
     * @return 实例对象
     */
    ReserveBill insert(ReserveBill reserveBill);

    /**
     * 修改数据
     *
     * @param reserveBill 实例对象
     * @return 实例对象
     */
    ReserveBill update(ReserveBill reserveBill);

    /**
     * 通过主键删除数据
     *
     * @param billId 主键
     * @return 是否成功
     */
    boolean deleteById(String billId);

    List<ReserveBill> getAll();

}
