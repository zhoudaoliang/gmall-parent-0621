package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.constant.RedisConst;
import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.mapper.SkuAttrValueMapper;
import com.atguigu.gmall.product.mapper.SkuImageMapper;
import com.atguigu.gmall.product.mapper.SkuInfoMapper;
import com.atguigu.gmall.product.mapper.SkuSaleAttrValueMapper;
import com.atguigu.gmall.product.service.SkuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Daoliang Zhou
 * @create 2020-12-04 11:38
 */
@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    SkuInfoMapper skuInfoMapper;

    @Autowired
    SkuImageMapper skuImageMapper;

    @Autowired
    SkuAttrValueMapper skuAttrValueMapper;

    @Autowired
    SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        skuInfoMapper.insert(skuInfo);
        Long sku_id = skuInfo.getId();
        List<SkuImage> skuImages = skuInfo.getSkuImageList();
        if (null != skuImages) {
            for (SkuImage skuImage : skuImages) {
                skuImage.setSkuId(sku_id);
                skuImageMapper.insert(skuImage);
            }
        }
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        if (null != skuSaleAttrValueList) {
            for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
                skuSaleAttrValue.setSkuId(sku_id);
                skuSaleAttrValue.setSpuId(skuInfo.getSpuId());
                skuSaleAttrValueMapper.insert(skuSaleAttrValue);
            }
        }

        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        if(null!=skuAttrValueList){
            for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setSkuId(sku_id);
                skuAttrValueMapper.insert(skuAttrValue);
            }
        }
    }


    @Override
    public BigDecimal getPrice(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        return skuInfo.getPrice();
    }

    @Override
    public SkuInfo getSkuInfoById(Long skuId) {
        long currentTimeMillisStart = System.currentTimeMillis();
        SkuInfo skuInfo = null;
        // 访问nosql
        skuInfo = (SkuInfo) redisTemplate.opsForValue().get(RedisConst.SKUKEY_PREFIX+skuId+RedisConst.SKUKEY_SUFFIX);
        if(null==skuInfo){
            // 访问db
            skuInfo = getSkuInfoByIdFromDb(skuId);

            // 同步缓存
            redisTemplate.opsForValue().set(RedisConst.SKUKEY_PREFIX+skuId+RedisConst.SKUKEY_SUFFIX,skuInfo);
        }

        long currentTimeMillisEnd = System.currentTimeMillis();
        System.out.println(currentTimeMillisEnd-currentTimeMillisStart+"毫秒");
        return skuInfo;
    }

    private SkuInfo getSkuInfoByIdFromDb(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);

        QueryWrapper<SkuImage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sku_id",skuId);
        List<SkuImage> skuImages = skuImageMapper.selectList(queryWrapper);
        skuInfo.setSkuImageList(skuImages);
        return skuInfo;
    }

    @Override
    public IPage<SkuInfo> skuList(Page<SkuInfo> page) {
        QueryWrapper<SkuInfo> queryWrapper = new QueryWrapper<>();
        page.setSize(50);
        IPage<SkuInfo> skuInfoIPage = skuInfoMapper.selectPage(page,null);
        return skuInfoIPage;
    }

    @Override
    public void onSale(Long skuId) {
        //mysql 上架
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setIsSale(1);
        skuInfo.setId(skuId);
        skuInfoMapper.updateById(skuInfo);
        //写入nosql
        System.out.println("同步搜索引擎");
    }

    @Override
    public void cancelSale(Long skuId) {
        //mysql 下架
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setIsSale(0);
        skuInfo.setId(skuId);
        skuInfoMapper.updateById(skuInfo);
        //清理nosql
        System.out.println("同步搜索引擎");
    }
}
