package com.echo.fastdbproj.service.impl;

import com.echo.fastdbproj.entity.Bill;
import com.echo.fastdbproj.dao.BillDao;
import com.echo.fastdbproj.service.BillService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Bill)表服务实现类
 *
 * @author makejava
 * @since 2022-08-24 15:54:16
 */
@Service("billService")
public class BillServiceImpl implements BillService {
    @Resource
    private BillDao billDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Bill queryById(String id) {
        return this.billDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param bill        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Bill> queryByPage(Bill bill, PageRequest pageRequest) {
        long total = this.billDao.count(bill);
        return new PageImpl<>(this.billDao.queryAllByLimit(bill, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param bill 实例对象
     * @return 实例对象
     */
    @Override
    public Bill insert(Bill bill) {
        this.billDao.insert(bill);
        return bill;
    }

    /**
     * 修改数据
     *
     * @param bill 实例对象
     * @return 实例对象
     */
    @Override
    public Bill update(Bill bill) {
        this.billDao.update(bill);
        return this.queryById(bill.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.billDao.deleteById(id) > 0;
    }


    @Override
    public double getAverScoreOfDriver(String driverId) {
        return billDao.getAverageScoreOfDriver(driverId);
    }

    @Override
    public List<Bill> selectAllByCustomerId(String customerId) {
        return billDao.selectAllByCustomerIdList(customerId);
    }

    @Override
    public List<Bill> selectAllByDriverId(String driverId) {
        return billDao.selectAllByDriverIdList(driverId);
    }
}
