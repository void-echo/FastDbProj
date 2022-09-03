package com.echo.fastdbproj.service.impl;

import com.echo.fastdbproj.entity.DriverCard;
import com.echo.fastdbproj.dao.DriverCardDao;
import com.echo.fastdbproj.service.DriverCardService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DriverCard)表服务实现类
 *
 * @author makejava
 * @since 2022-08-24 22:56:06
 */
@Service("driverCardService")
public class DriverCardServiceImpl implements DriverCardService {
    @Resource
    private DriverCardDao driverCardDao;

    /**
     * 通过ID查询单条数据
     *
     * @param driverId 主键
     * @return 实例对象
     */
    @Override
    public DriverCard queryById(String driverId) {
        return this.driverCardDao.queryById(driverId);
    }

    /**
     * 分页查询
     *
     * @param driverCard  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<DriverCard> queryByPage(DriverCard driverCard, PageRequest pageRequest) {
        long total = this.driverCardDao.count(driverCard);
        return new PageImpl<>(this.driverCardDao.queryAllByLimit(driverCard, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param driverCard 实例对象
     * @return 实例对象
     */
    @Override
    public DriverCard insert(DriverCard driverCard) {
        this.driverCardDao.insert(driverCard);
        return driverCard;
    }

    /**
     * 修改数据
     *
     * @param driverCard 实例对象
     * @return 实例对象
     */
    @Override
    public DriverCard update(DriverCard driverCard) {
        this.driverCardDao.update(driverCard);
        return this.queryById(driverCard.getDriverId());
    }

    /**
     * 通过主键删除数据
     *
     * @param driverId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String driverId) {
        return this.driverCardDao.deleteById(driverId) > 0;
    }

    @Override
    public List<DriverCard> getAll() {
        return driverCardDao.getAll();
    }
}
