package com.echo.fastdbproj.controller;

import com.echo.fastdbproj.entity.CustomerPayApproach;
import com.echo.fastdbproj.service.CustomerPayApproachService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CustomerPayApproach)表控制层
 *
 * @author makejava
 * @since 2022-08-24 22:52:35
 */
@RestController
@RequestMapping("customerPayApproach")
@CrossOrigin
public class CustomerPayApproachController {
    /**
     * 服务对象
     */
    @Resource
    private CustomerPayApproachService customerPayApproachService;

    /**
     * 分页查询
     *
     * @param customerPayApproach 筛选条件
     * @param pageRequest         分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<CustomerPayApproach>> queryByPage(CustomerPayApproach customerPayApproach, PageRequest pageRequest) {
        return ResponseEntity.ok(this.customerPayApproachService.queryByPage(customerPayApproach, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CustomerPayApproach> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.customerPayApproachService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param customerPayApproach 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<CustomerPayApproach> add(CustomerPayApproach customerPayApproach) {
        return ResponseEntity.ok(this.customerPayApproachService.insert(customerPayApproach));
    }

    /**
     * 编辑数据
     *
     * @param customerPayApproach 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<CustomerPayApproach> edit(CustomerPayApproach customerPayApproach) {
        return ResponseEntity.ok(this.customerPayApproachService.update(customerPayApproach));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.customerPayApproachService.deleteById(id));
    }


    @GetMapping("get-all")
    public ResponseEntity<List<CustomerPayApproach>> getAll() {
        return ResponseEntity.ok(customerPayApproachService.getAll());
    }
}

