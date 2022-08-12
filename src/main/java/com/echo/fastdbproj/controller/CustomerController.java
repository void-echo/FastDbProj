package com.echo.fastdbproj.controller;

import com.echo.fastdbproj.entity.Customer;
import com.echo.fastdbproj.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Customer)表控制层
 *
 * @author makejava
 * @since 2022-08-11 14:08:10
 */
@RestController
@CrossOrigin
@RequestMapping("customer")
public class CustomerController {
    /**
     * 服务对象
     */
    @Resource
    private CustomerService customerService;

    /**
     * 分页查询
     *
     * @param customer    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Customer>> queryByPage(Customer customer, PageRequest pageRequest) {
        return ResponseEntity.ok(this.customerService.queryByPage(customer, pageRequest));
    }

//    @RequestMapping("")
//    public String doLogin() {
//
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("get/{id}")
    public ResponseEntity<Customer> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.customerService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param customer 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Customer> add(Customer customer) {
        return ResponseEntity.ok(this.customerService.insert(customer));
    }

    /**
     * 编辑数据
     *
     * @param customer 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Customer> edit(Customer customer) {
        return ResponseEntity.ok(this.customerService.update(customer));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.customerService.deleteById(id));
    }

}

