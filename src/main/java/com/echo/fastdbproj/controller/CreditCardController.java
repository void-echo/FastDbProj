package com.echo.fastdbproj.controller;

import com.echo.fastdbproj.entity.CreditCard;
import com.echo.fastdbproj.service.CreditCardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CreditCard)表控制层
 *
 * @author makejava
 * @since 2022-08-24 22:37:49
 */
@RestController
@RequestMapping("creditCard")
@CrossOrigin
public class CreditCardController {
    /**
     * 服务对象
     */
    @Resource
    private CreditCardService creditCardService;

    /**
     * 分页查询
     *
     * @param creditCard  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<CreditCard>> queryByPage(CreditCard creditCard, PageRequest pageRequest) {
        return ResponseEntity.ok(this.creditCardService.queryByPage(creditCard, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CreditCard> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.creditCardService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param creditCard 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<CreditCard> add(CreditCard creditCard) {
        return ResponseEntity.ok(this.creditCardService.insert(creditCard));
    }

    /**
     * 编辑数据
     *
     * @param creditCard 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<CreditCard> edit(CreditCard creditCard) {
        return ResponseEntity.ok(this.creditCardService.update(creditCard));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.creditCardService.deleteById(id));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<CreditCard>> getAll() {
        return ResponseEntity.ok(creditCardService.getAll());
    }
}

