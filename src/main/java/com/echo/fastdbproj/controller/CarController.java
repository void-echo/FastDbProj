package com.echo.fastdbproj.controller;

import com.echo.fastdbproj.entity.Car;
import com.echo.fastdbproj.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Car)表控制层
 *
 * @author makejava
 * @since 2022-08-11 14:08:10
 */
@RestController
@CrossOrigin
@RequestMapping("car")
public class CarController {
    /**
     * 服务对象
     */
    @Resource
    private CarService carService;

    /**
     * 分页查询
     *
     * @param car         筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Car>> queryByPage(Car car, PageRequest pageRequest) {
        return ResponseEntity.ok(this.carService.queryByPage(car, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Car> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.carService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param car 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Car> add(Car car) {
        return ResponseEntity.ok(this.carService.insert(car));
    }

    /**
     * 编辑数据
     *
     * @param car 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Car> edit(Car car) {
        return ResponseEntity.ok(this.carService.update(car));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.carService.deleteById(id));
    }


    @GetMapping("get-all")
    public ResponseEntity<List<Car>> getAll() {
        return ResponseEntity.ok(carService.getAll());
    }
}

