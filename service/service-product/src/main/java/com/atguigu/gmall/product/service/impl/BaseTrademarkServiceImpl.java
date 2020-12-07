package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.mapper.BaseTrademarkMapper;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Daoliang Zhou
 * @create 2020-12-01 20:01
 */
@Service
public class BaseTrademarkServiceImpl implements BaseTrademarkService {
    @Autowired
    BaseTrademarkMapper baseTrademarkMapper;

//    @Override
//    public IPage<BaseTrademark> baseTrademark(Page<BaseTrademark> page, Long category3Id) {
//        QueryWrapper<BaseTrademark> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("category3_id",category3Id);
//        IPage<BaseTrademark> baseTrademarkIPage = baseTrademarkMapper.selectPage(page, queryWrapper);
//        return baseTrademarkIPage;
//    }

    @Override
    public List<BaseTrademark> getTrademarkList(Long category3Id) {
        List<BaseTrademark> baseTrademarkList = baseTrademarkMapper.selectList(null);
        return baseTrademarkList;
    }
}
