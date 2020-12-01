package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Daoliang Zhou
 * @create 2020-12-01 9:39
 */
@Mapper
public interface BaseAttrInfoMapper extends BaseMapper<BaseAttrInfo>{
    List<BaseAttrInfo> selectAttrInfoList(@Param("category_level") int category_level, @Param("category_id")Long category3Id);
//    List<BaseAttrInfo> selectAttrInfoList(@Param("category_level") int category_level,
//                                          @Param("category_id") Long category_id);
}
