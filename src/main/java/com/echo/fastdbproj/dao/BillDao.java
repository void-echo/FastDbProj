package com.echo.fastdbproj.dao;

import com.echo.fastdbproj.entity.Bill;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

/**
 * (Bill)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-24 15:54:15
 */
public interface BillDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Bill queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param bill     查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Bill> queryAllByLimit(Bill bill, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param bill 查询条件
     * @return 总行数
     */
    long count(Bill bill);

    /**
     * 新增数据
     *
     * @param bill 实例对象
     * @return 影响行数
     */
    int insert(Bill bill);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Bill> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Bill> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Bill> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Bill> entities);

    /**
     * 修改数据
     *
     * @param bill 实例对象
     * @return 影响行数
     */
    int update(Bill bill);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);


    @Select("select avg(score) from bill where driver_id = #{driverId}")
    double getAverageScoreOfDriver(String driverId);

    /*
    * @Select("SELECT * FROM users WHERE id = #{id}")
	@Results({
		@Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
		@Result(property = "nickName", column = "nick_name")
	})
	*
	*
	* id;
        l.Timestamp ti
        money;
         score;
        driverId;
        customerId;
        status;
        duration;
        fromPlace;
        toPlace;
    * */
    @Select("select * from bill where customer_id = #{customerId}")
    @Results({
            @Result(property = "id", column = "id", javaType = String.class),
            @Result(property = "time", column = "time", javaType = Timestamp.class),
            @Result(property = "money", column = "money", javaType = String.class),
            @Result(property = "score", column = "score", javaType = Integer.class),
            @Result(property = "driverId", column = "driver_id", javaType = String.class),
            @Result(property = "status", column = "status", javaType = String.class),
            @Result(property = "duration", column = "duration", javaType = String.class),
            @Result(property = "fromPlace", column = "from_place", javaType = String.class),
            @Result(property = "customerId", column = "customer_id", javaType = String.class),
            @Result(property = "toPlace", column = "to_place", javaType = String.class),
    })
    List<Bill> selectAllByCustomerIdList(String customerId);


    @Select("select * from bill where driver_id = #{driverId}")
    @Results({
            @Result(property = "id", column = "id", javaType = String.class),
            @Result(property = "time", column = "time", javaType = Timestamp.class),
            @Result(property = "money", column = "money", javaType = String.class),
            @Result(property = "score", column = "score", javaType = Integer.class),
            @Result(property = "driverId", column = "driver_id", javaType = String.class),
            @Result(property = "status", column = "status", javaType = String.class),
            @Result(property = "duration", column = "duration", javaType = String.class),
            @Result(property = "fromPlace", column = "from_place", javaType = String.class),
            @Result(property = "toPlace", column = "to_place", javaType = String.class),
            @Result(property = "customerId", column = "customer_id", javaType = String.class),
    })
    List<Bill> selectAllByDriverIdList(String driverId);


    List<Bill> getAll();
}

