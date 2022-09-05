package com.echo.fastdbproj.controller;

import com.echo.fastdbproj.entity.Dispute;
import com.echo.fastdbproj.service.DisputeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Dispute)表控制层
 *
 * @author makejava
 * @since 2022-08-11 14:08:11
 */
@RestController
@CrossOrigin
@RequestMapping("dispute")
public class DisputeController {
    /**
     * 服务对象
     */
    @Resource
    private DisputeService disputeService;

    /**
     * 分页查询
     *
     * @param dispute     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Dispute>> queryByPage(Dispute dispute, PageRequest pageRequest) {
        return ResponseEntity.ok(this.disputeService.queryByPage(dispute, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Dispute> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.disputeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param dispute 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Dispute> add(Dispute dispute) {
        return ResponseEntity.ok(this.disputeService.insert(dispute));
    }

    /**
     * 编辑数据
     *
     * @param dispute 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Dispute> edit(Dispute dispute) {
        return ResponseEntity.ok(this.disputeService.update(dispute));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.disputeService.deleteById(id));
    }


    @GetMapping("get-all")
    public ResponseEntity<List<Dispute>> getAll() {
        return ResponseEntity.ok(disputeService.getAll());
    }


    @GetMapping("get-1-by-bill-id")
    public ResponseEntity<Dispute> getOneByBillId(String billId) {
        return ResponseEntity.ok(disputeService.getOneByBillId(billId));
    }
}

