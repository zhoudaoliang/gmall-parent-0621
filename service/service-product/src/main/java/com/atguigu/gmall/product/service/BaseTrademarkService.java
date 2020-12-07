package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseTrademark;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author Daoliang Zhou
 * @create 2020-12-01 20:00
 */
public interface BaseTrademarkService {
//    IPage<BaseTrademark> baseTrademark(Page<BaseTrademark> page, Long category3Id);

    List<BaseTrademark> getTrademarkList(Long category3Id);
}
