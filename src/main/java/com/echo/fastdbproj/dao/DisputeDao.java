package com.echo.fastdbproj.dao;

import com.echo.fastdbproj.entity.Dispute;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Dispute)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-11 14:08:11
 */
public interface DisputeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dispute queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param dispute  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Dispute> queryAllByLimit(Dispute dispute, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param dispute 查询条件
     * @return 总行数
     */
    long count(Dispute dispute);

    /**
     * 新增数据
     *
     * @param dispute 实例对象
     * @return 影响行数
     */
    int insert(Dispute dispute);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dispute> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Dispute> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dispute> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Dispute> entities);

    /**
     * 修改数据
     *
     * @param dispute 实例对象
     * @return 影响行数
     */
    int update(Dispute dispute);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);


    List<Dispute> getAll();

    Dispute getOneByBillIdDispute(String billId);
}

