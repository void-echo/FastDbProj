package com.echo.fastdbproj.service.impl;

import com.echo.fastdbproj.entity.Driver;
import com.echo.fastdbproj.dao.DriverDao;
import com.echo.fastdbproj.service.DriverService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Driver)表服务实现类
 *
 * @author makejava
 * @since 2022-08-11 14:08:12
 */
@Service("driverService")
public class DriverServiceImpl implements DriverService {
    @Resource
    private DriverDao driverDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Driver queryById(String id) {
        return this.driverDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param driver      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Driver> queryByPage(Driver driver, PageRequest pageRequest) {
        long total = this.driverDao.count(driver);
        return new PageImpl<>(this.driverDao.queryAllByLimit(driver, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param driver 实例对象
     * @return 实例对象
     */
    @Override
    public Driver insert(Driver driver) {
        this.driverDao.insert(driver);
        return driver;
    }

    /**
     * 修改数据
     *
     * @param driver 实例对象
     * @return 实例对象
     */
    @Override
    public Driver update(Driver driver) {
        this.driverDao.update(driver);
        return this.queryById(driver.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.driverDao.deleteById(id) > 0;
    }
}
