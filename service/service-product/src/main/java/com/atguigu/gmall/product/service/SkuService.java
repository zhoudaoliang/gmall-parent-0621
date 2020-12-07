package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Daoliang Zhou
 * @create 2020-12-04 11:37
 */
public interface SkuService {

    void saveSkuInfo(SkuInfo skuInfo);

    BigDecimal getPrice(Long skuId);

    SkuInfo getSkuInfoById(Long skuId);

    IPage<SkuInfo> skuList(Page<SkuInfo> page);

    void onSale(Long skuId);

    void cancelSale(Long skuId);
}
