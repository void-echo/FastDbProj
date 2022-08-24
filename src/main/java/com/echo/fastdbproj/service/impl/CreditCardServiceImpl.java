package com.echo.fastdbproj.service.impl;

import com.echo.fastdbproj.entity.CreditCard;
import com.echo.fastdbproj.dao.CreditCardDao;
import com.echo.fastdbproj.service.CreditCardService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (CreditCard)表服务实现类
 *
 * @author makejava
 * @since 2022-08-24 22:38:35
 */
@Service("creditCardService")
public class CreditCardServiceImpl implements CreditCardService {
    @Resource
    private CreditCardDao creditCardDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CreditCard queryById(String id) {
        return this.creditCardDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param creditCard  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CreditCard> queryByPage(CreditCard creditCard, PageRequest pageRequest) {
        long total = this.creditCardDao.count(creditCard);
        return new PageImpl<>(this.creditCardDao.queryAllByLimit(creditCard, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param creditCard 实例对象
     * @return 实例对象
     */
    @Override
    public CreditCard insert(CreditCard creditCard) {
        this.creditCardDao.insert(creditCard);
        return creditCard;
    }

    /**
     * 修改数据
     *
     * @param creditCard 实例对象
     * @return 实例对象
     */
    @Override
    public CreditCard update(CreditCard creditCard) {
        this.creditCardDao.update(creditCard);
        return this.queryById(creditCard.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.creditCardDao.deleteById(id) > 0;
    }
}
