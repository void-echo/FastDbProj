package com.echo.fastdbproj.service;

import com.echo.fastdbproj.entity.CreditCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (CreditCard)表服务接口
 *
 * @author makejava
 * @since 2022-08-24 22:38:35
 */
public interface CreditCardService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CreditCard queryById(String id);

    /**
     * 分页查询
     *
     * @param creditCard  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CreditCard> queryByPage(CreditCard creditCard, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param creditCard 实例对象
     * @return 实例对象
     */
    CreditCard insert(CreditCard creditCard);

    /**
     * 修改数据
     *
     * @param creditCard 实例对象
     * @return 实例对象
     */
    CreditCard update(CreditCard creditCard);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<CreditCard> getAll();
}
