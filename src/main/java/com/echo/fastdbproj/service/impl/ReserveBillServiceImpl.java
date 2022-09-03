package com.echo.fastdbproj.service.impl;

import com.echo.fastdbproj.entity.ReserveBill;
import com.echo.fastdbproj.dao.ReserveBillDao;
import com.echo.fastdbproj.service.ReserveBillService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ReserveBill)表服务实现类
 *
 * @author makejava
 * @since 2022-08-27 21:46:12
 */
@Service("reserveBillService")
public class ReserveBillServiceImpl implements ReserveBillService {
    @Resource
    private ReserveBillDao reserveBillDao;

    /**
     * 通过ID查询单条数据
     *
     * @param billId 主键
     * @return 实例对象
     */
    @Override
    public ReserveBill queryById(String billId) {
        return this.reserveBillDao.queryById(billId);
    }

    /**
     * 分页查询
     *
     * @param reserveBill 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<ReserveBill> queryByPage(ReserveBill reserveBill, PageRequest pageRequest) {
        long total = this.reserveBillDao.count(reserveBill);
        return new PageImpl<>(this.reserveBillDao.queryAllByLimit(reserveBill, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param reserveBill 实例对象
     * @return 实例对象
     */
    @Override
    public ReserveBill insert(ReserveBill reserveBill) {
        this.reserveBillDao.insert(reserveBill);
        return reserveBill;
    }

    /**
     * 修改数据
     *
     * @param reserveBill 实例对象
     * @return 实例对象
     */
    @Override
    public ReserveBill update(ReserveBill reserveBill) {
        this.reserveBillDao.update(reserveBill);
        return this.queryById(reserveBill.getBillId());
    }

    /**
     * 通过主键删除数据
     *
     * @param billId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String billId) {
        return this.reserveBillDao.deleteById(billId) > 0;
    }

    @Override
    public List<ReserveBill> getAll() {
        return reserveBillDao.getAll();
    }
}
