package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author Daoliang Zhou
 * @create 2020-12-01 18:57
 */
public interface SpuService {
    IPage<SpuInfo> spuList(Page<SpuInfo> page, Long category3Id);

    List<BaseSaleAttr> baseSaleAttrList();


    void saveSpuInfo(SpuInfo spuInfo);

    List<SpuSaleAttr> spuSaleAttrList(Long spuId);

    List<SpuImage> spuImageList(Long spuId);
}
