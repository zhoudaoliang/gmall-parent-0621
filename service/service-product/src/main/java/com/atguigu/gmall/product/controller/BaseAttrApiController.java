package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Daoliang Zhou
 * @create 2020-12-01 9:00
 */
@RequestMapping("admin/product")
@RestController
@CrossOrigin
public class BaseAttrApiController {
    @Autowired
    BaseAttrService baseAttrService;

    @RequestMapping("getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable Long attrId){
        List<BaseAttrValue> baseAttrValues=baseAttrService.getAttrValueList(attrId);
        return Result.ok(baseAttrValues);
    }

    @RequestMapping("saveAttrInfo")
    //springmvc接收json对象，加@requestBody注解
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        baseAttrService.saveAttrInfo(baseAttrInfo);
        return Result.ok();
    }


    @RequestMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result attrInfoList(@PathVariable("category3Id") Long category3Id){
        List<BaseAttrInfo> baseAttrInfos=baseAttrService.attrInfoList(category3Id);
        return Result.ok(baseAttrInfos);
    }
}
