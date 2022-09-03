package com.echo.fastdbproj.controller;

import com.echo.fastdbproj.entity.DriverCard;
import com.echo.fastdbproj.service.DriverCardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DriverCard)表控制层
 *
 * @author makejava
 * @since 2022-08-24 22:56:06
 */
@RestController
@RequestMapping("driverCard")
@CrossOrigin
public class DriverCardController {
    /**
     * 服务对象
     */
    @Resource
    private DriverCardService driverCardService;

    /**
     * 分页查询
     *
     * @param driverCard  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<DriverCard>> queryByPage(DriverCard driverCard, PageRequest pageRequest) {
        return ResponseEntity.ok(this.driverCardService.queryByPage(driverCard, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<DriverCard> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.driverCardService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param driverCard 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<DriverCard> add(DriverCard driverCard) {
        return ResponseEntity.ok(this.driverCardService.insert(driverCard));
    }

    /**
     * 编辑数据
     *
     * @param driverCard 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<DriverCard> edit(DriverCard driverCard) {
        return ResponseEntity.ok(this.driverCardService.update(driverCard));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.driverCardService.deleteById(id));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<DriverCard>> getAll() {
        return ResponseEntity.ok(driverCardService.getAll());
    }
}

