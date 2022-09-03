package com.echo.fastdbproj.service.impl;

import com.echo.fastdbproj.entity.Dispute;
import com.echo.fastdbproj.dao.DisputeDao;
import com.echo.fastdbproj.service.DisputeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Dispute)表服务实现类
 *
 * @author makejava
 * @since 2022-08-11 14:08:11
 */
@Service("disputeService")
public class DisputeServiceImpl implements DisputeService {
    @Resource
    private DisputeDao disputeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Dispute queryById(String id) {
        return this.disputeDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param dispute     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Dispute> queryByPage(Dispute dispute, PageRequest pageRequest) {
        long total = this.disputeDao.count(dispute);
        return new PageImpl<>(this.disputeDao.queryAllByLimit(dispute, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param dispute 实例对象
     * @return 实例对象
     */
    @Override
    public Dispute insert(Dispute dispute) {
        this.disputeDao.insert(dispute);
        return dispute;
    }

    /**
     * 修改数据
     *
     * @param dispute 实例对象
     * @return 实例对象
     */
    @Override
    public Dispute update(Dispute dispute) {
        this.disputeDao.update(dispute);
        return this.queryById(dispute.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.disputeDao.deleteById(id) > 0;
    }

    @Override
    public List<Dispute> getAll() {
        return disputeDao.getAll();
    }
}
