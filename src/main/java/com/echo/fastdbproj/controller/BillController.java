package com.echo.fastdbproj.controller;

import com.echo.fastdbproj.entity.Bill;
import com.echo.fastdbproj.service.BillService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Bill)表控制层
 *
 * @author makejava
 * @since 2022-08-11 14:08:09
 */
@RestController
@CrossOrigin
@RequestMapping("bill")
public class BillController {
    /**
     * 服务对象
     */
    @Resource
    private BillService billService;

    /**
     * 分页查询
     *
     * @param bill        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Bill>> queryByPage(Bill bill, PageRequest pageRequest) {
        return ResponseEntity.ok(this.billService.queryByPage(bill, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Bill> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.billService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param bill 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Bill> add(Bill bill) {
        return ResponseEntity.ok(this.billService.insert(bill));
    }

    /**
     * 编辑数据
     *
     * @param bill 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Bill> edit(Bill bill) {
        return ResponseEntity.ok(this.billService.update(bill));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.billService.deleteById(id));
    }

}

