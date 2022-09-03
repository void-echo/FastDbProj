package com.echo.fastdbproj.service.impl;

import com.echo.fastdbproj.entity.CustomerPayApproach;
import com.echo.fastdbproj.dao.CustomerPayApproachDao;
import com.echo.fastdbproj.service.CustomerPayApproachService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CustomerPayApproach)表服务实现类
 *
 * @author makejava
 * @since 2022-08-24 22:52:35
 */
@Service("customerPayApproachService")
public class CustomerPayApproachServiceImpl implements CustomerPayApproachService {
    @Resource
    private CustomerPayApproachDao customerPayApproachDao;

    /**
     * 通过ID查询单条数据
     *
     * @param payApproachId 主键
     * @return 实例对象
     */
    @Override
    public CustomerPayApproach queryById(String payApproachId) {
        return this.customerPayApproachDao.queryById(payApproachId);
    }

    /**
     * 分页查询
     *
     * @param customerPayApproach 筛选条件
     * @param pageRequest         分页对象
     * @return 查询结果
     */
    @Override
    public Page<CustomerPayApproach> queryByPage(CustomerPayApproach customerPayApproach, PageRequest pageRequest) {
        long total = this.customerPayApproachDao.count(customerPayApproach);
        return new PageImpl<>(this.customerPayApproachDao.queryAllByLimit(customerPayApproach, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param customerPayApproach 实例对象
     * @return 实例对象
     */
    @Override
    public CustomerPayApproach insert(CustomerPayApproach customerPayApproach) {
        this.customerPayApproachDao.insert(customerPayApproach);
        return customerPayApproach;
    }

    /**
     * 修改数据
     *
     * @param customerPayApproach 实例对象
     * @return 实例对象
     */
    @Override
    public CustomerPayApproach update(CustomerPayApproach customerPayApproach) {
        this.customerPayApproachDao.update(customerPayApproach);
        return this.queryById(customerPayApproach.getPayApproachId());
    }

    /**
     * 通过主键删除数据
     *
     * @param payApproachId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String payApproachId) {
        return this.customerPayApproachDao.deleteById(payApproachId) > 0;
    }

    @Override
    public List<CustomerPayApproach> getAll() {
        return customerPayApproachDao.getAll();
    }
}
