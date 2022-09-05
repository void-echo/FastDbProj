package com.echo.fastdbproj.service;

import com.echo.fastdbproj.entity.Dispute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Dispute)表服务接口
 *
 * @author makejava
 * @since 2022-08-11 14:08:11
 */
public interface DisputeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dispute queryById(String id);

    /**
     * 分页查询
     *
     * @param dispute     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Dispute> queryByPage(Dispute dispute, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param dispute 实例对象
     * @return 实例对象
     */
    Dispute insert(Dispute dispute);

    /**
     * 修改数据
     *
     * @param dispute 实例对象
     * @return 实例对象
     */
    Dispute update(Dispute dispute);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<Dispute> getAll();

    Dispute getOneByBillId(String billId);

}
