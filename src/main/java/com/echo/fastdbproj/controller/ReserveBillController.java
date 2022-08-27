package com.echo.fastdbproj.controller;

import com.echo.fastdbproj.entity.ReserveBill;
import com.echo.fastdbproj.service.ReserveBillService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ReserveBill)表控制层
 *
 * @author makejava
 * @since 2022-08-27 21:46:10
 */
@RestController
@RequestMapping("reserveBill")
@CrossOrigin
public class ReserveBillController {
    /**
     * 服务对象
     */
    @Resource
    private ReserveBillService reserveBillService;

    /**
     * 分页查询
     *
     * @param reserveBill 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ReserveBill>> queryByPage(ReserveBill reserveBill, PageRequest pageRequest) {
        return ResponseEntity.ok(this.reserveBillService.queryByPage(reserveBill, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ReserveBill> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.reserveBillService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param reserveBill 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ReserveBill> add(ReserveBill reserveBill) {
        return ResponseEntity.ok(this.reserveBillService.insert(reserveBill));
    }

    /**
     * 编辑数据
     *
     * @param reserveBill 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ReserveBill> edit(ReserveBill reserveBill) {
        return ResponseEntity.ok(this.reserveBillService.update(reserveBill));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.reserveBillService.deleteById(id));
    }

}

