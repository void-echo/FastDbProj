package com.echo.fastdbproj.service.impl;

import com.echo.fastdbproj.entity.Car;
import com.echo.fastdbproj.dao.CarDao;
import com.echo.fastdbproj.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Car)表服务实现类
 *
 * @author makejava
 * @since 2022-08-11 14:08:10
 */
@Service("carService")
public class CarServiceImpl implements CarService {
    @Resource
    private CarDao carDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Car queryById(String id) {
        return this.carDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param car         筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Car> queryByPage(Car car, PageRequest pageRequest) {
        long total = this.carDao.count(car);
        return new PageImpl<>(this.carDao.queryAllByLimit(car, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param car 实例对象
     * @return 实例对象
     */
    @Override
    public Car insert(Car car) {
        this.carDao.insert(car);
        return car;
    }

    /**
     * 修改数据
     *
     * @param car 实例对象
     * @return 实例对象
     */
    @Override
    public Car update(Car car) {
        this.carDao.update(car);
        return this.queryById(car.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.carDao.deleteById(id) > 0;
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }
}
