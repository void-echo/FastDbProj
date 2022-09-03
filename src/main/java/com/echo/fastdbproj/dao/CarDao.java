package com.echo.fastdbproj.dao;

import com.echo.fastdbproj.entity.Car;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Car)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-11 14:08:10
 */
public interface CarDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Car queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param car      查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Car> queryAllByLimit(Car car, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param car 查询条件
     * @return 总行数
     */
    long count(Car car);

    /**
     * 新增数据
     *
     * @param car 实例对象
     * @return 影响行数
     */
    int insert(Car car);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Car> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Car> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Car> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Car> entities);

    /**
     * 修改数据
     *
     * @param car 实例对象
     * @return 影响行数
     */
    int update(Car car);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    List<Car> getAll();

}

