package com.echo.fastdbproj.dao;

import com.echo.fastdbproj.entity.CustomerPayApproach;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (CustomerPayApproach)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-24 22:52:35
 */
public interface CustomerPayApproachDao {

    /**
     * 通过ID查询单条数据
     *
     * @param payApproachId 主键
     * @return 实例对象
     */
    CustomerPayApproach queryById(String payApproachId);

    /**
     * 查询指定行数据
     *
     * @param customerPayApproach 查询条件
     * @param pageable            分页对象
     * @return 对象列表
     */
    List<CustomerPayApproach> queryAllByLimit(CustomerPayApproach customerPayApproach, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param customerPayApproach 查询条件
     * @return 总行数
     */
    long count(CustomerPayApproach customerPayApproach);

    /**
     * 新增数据
     *
     * @param customerPayApproach 实例对象
     * @return 影响行数
     */
    int insert(CustomerPayApproach customerPayApproach);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CustomerPayApproach> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CustomerPayApproach> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CustomerPayApproach> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CustomerPayApproach> entities);

    /**
     * 修改数据
     *
     * @param customerPayApproach 实例对象
     * @return 影响行数
     */
    int update(CustomerPayApproach customerPayApproach);

    /**
     * 通过主键删除数据
     *
     * @param payApproachId 主键
     * @return 影响行数
     */
    int deleteById(String payApproachId);

}

