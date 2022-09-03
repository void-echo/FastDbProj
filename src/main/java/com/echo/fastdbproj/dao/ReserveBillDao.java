package com.echo.fastdbproj.dao;

import com.echo.fastdbproj.entity.ReserveBill;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (ReserveBill)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-27 21:46:11
 */
public interface ReserveBillDao {

    /**
     * 通过ID查询单条数据
     *
     * @param billId 主键
     * @return 实例对象
     */
    ReserveBill queryById(String billId);

    /**
     * 查询指定行数据
     *
     * @param reserveBill 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<ReserveBill> queryAllByLimit(ReserveBill reserveBill, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param reserveBill 查询条件
     * @return 总行数
     */
    long count(ReserveBill reserveBill);

    /**
     * 新增数据
     *
     * @param reserveBill 实例对象
     * @return 影响行数
     */
    int insert(ReserveBill reserveBill);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReserveBill> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ReserveBill> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReserveBill> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ReserveBill> entities);

    /**
     * 修改数据
     *
     * @param reserveBill 实例对象
     * @return 影响行数
     */
    int update(ReserveBill reserveBill);

    /**
     * 通过主键删除数据
     *
     * @param billId 主键
     * @return 影响行数
     */
    int deleteById(String billId);


    List<ReserveBill> getAll();
}

