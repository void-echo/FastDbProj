package com.echo.fastdbproj.dao;

import com.echo.fastdbproj.entity.DriverCard;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (DriverCard)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-24 22:56:06
 */
public interface DriverCardDao {

    /**
     * 通过ID查询单条数据
     *
     * @param driverId 主键
     * @return 实例对象
     */
    DriverCard queryById(String driverId);

    /**
     * 查询指定行数据
     *
     * @param driverCard 查询条件
     * @param pageable   分页对象
     * @return 对象列表
     */
    List<DriverCard> queryAllByLimit(DriverCard driverCard, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param driverCard 查询条件
     * @return 总行数
     */
    long count(DriverCard driverCard);

    /**
     * 新增数据
     *
     * @param driverCard 实例对象
     * @return 影响行数
     */
    int insert(DriverCard driverCard);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DriverCard> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DriverCard> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DriverCard> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<DriverCard> entities);

    /**
     * 修改数据
     *
     * @param driverCard 实例对象
     * @return 影响行数
     */
    int update(DriverCard driverCard);

    /**
     * 通过主键删除数据
     *
     * @param driverId 主键
     * @return 影响行数
     */
    int deleteById(String driverId);

}

