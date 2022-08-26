package com.echo.fastdbproj.dao;

import com.echo.fastdbproj.entity.Driver;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Driver)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-11 14:08:11
 */
public interface DriverDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Driver queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param driver   查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Driver> queryAllByLimit(Driver driver, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param driver 查询条件
     * @return 总行数
     */
    long count(Driver driver);

    /**
     * 新增数据
     *
     * @param driver 实例对象
     * @return 影响行数
     */
    int insert(Driver driver);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Driver> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Driver> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Driver> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Driver> entities);

    /**
     * 修改数据
     *
     * @param driver 实例对象
     * @return 影响行数
     */
    int update(Driver driver);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

//    @Select("select * from bill where driver_id = #{driverId}")
//    @Results({
//            @Result(property = "id", column = "id", javaType = String.class),
//            @Result(property = "time", column = "time", javaType = Timestamp.class),
//            @Result(property = "money", column = "money", javaType = String.class),
//            @Result(property = "score", column = "score", javaType = Integer.class),
//            @Result(property = "driverId", column = "driver_id", javaType = String.class),
//            @Result(property = "status", column = "status", javaType = String.class),
//            @Result(property = "duration", column = "duration", javaType = String.class),
//            @Result(property = "fromPlace", column = "from_place", javaType = String.class),
//            @Result(property = "toPlace", column = "to_place", javaType = String.class),
//            @Result(property = "customerId", column = "customer_id", javaType = String.class),
//    })
    @Select("select * from driver")
    @Results({
            @Result(property = "id", column = "id", javaType = String.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "tel", column = "tel", javaType = String.class),
            @Result(property = "mail", column = "mail", javaType = String.class),
            @Result(property = "runTimes", column = "run_times", javaType = Integer.class),
            @Result(property = "preferPlace", column = "prefer_place", javaType = String.class),
            @Result(property = "score", column = "score", javaType = Double.class),
    })
    List<Driver> getAll();

}

