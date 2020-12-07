package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import com.atguigu.gmall.product.mapper.BaseAttrValueMapper;
import com.atguigu.gmall.product.service.BaseAttrService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Daoliang Zhou
 * @create 2020-12-01 9:23
 */
@Service
public class BaseAttrServiceImpl implements BaseAttrService {
    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;


    @Override
    public List<BaseAttrInfo> attrInfoList(Long category3Id) {
        List<BaseAttrInfo> baseAttrInfos=baseAttrInfoMapper.selectAttrInfoList(3,category3Id);
//        QueryWrapper<BaseAttrInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("category_level", 3);
//        queryWrapper.eq("category_id", category3Id);
//        List<BaseAttrInfo> baseAttrInfos = baseAttrInfoMapper.selectList(queryWrapper);
//        for (BaseAttrInfo baseAttrInfo : baseAttrInfos) {
//            Long attr_id = baseAttrInfo.getId();
//            QueryWrapper<BaseAttrValue> wrapper = new QueryWrapper<>();
//            wrapper.eq("attr_id", attr_id);
//            List<BaseAttrValue> baseAttrValues = baseAttrValueMapper.selectList(wrapper);
//            baseAttrInfo.setAttrValueList(baseAttrValues);
//        }
        return baseAttrInfos;
    }

    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        Long id = baseAttrInfo.getId();
        if (null == id && id <= 0) {
            //保存
            int insert = baseAttrInfoMapper.insert(baseAttrInfo);
            Long attr_id = baseAttrInfo.getId();
            id = attr_id;
        } else {
            //更新
            baseAttrInfoMapper.updateById(baseAttrInfo);
            QueryWrapper<BaseAttrValue> wrapper = new QueryWrapper<>();
            wrapper.eq("attr_id", id);
            baseAttrValueMapper.delete(wrapper);

        }
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        for (BaseAttrValue baseAttrValue : attrValueList) {
            baseAttrValue.setAttrId(id);
            baseAttrValueMapper.insert(baseAttrValue);
        }

    }

    @Override
    public List<BaseAttrValue> getAttrValueList(Long attrId) {
        QueryWrapper<BaseAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_id", attrId);
        List<BaseAttrValue> baseAttrValues = baseAttrValueMapper.selectList(wrapper);
        return baseAttrValues;
    }
}
