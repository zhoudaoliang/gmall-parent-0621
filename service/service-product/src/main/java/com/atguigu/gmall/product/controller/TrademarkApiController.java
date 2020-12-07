package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Daoliang Zhou
 * @create 2020-12-01 19:47
 */
@RestController
@RequestMapping("admin/product")
@CrossOrigin
public class TrademarkApiController {
    @Autowired
    BaseTrademarkService baseTrademarkService;

    @RequestMapping("baseTrademark/getTrademarkList")
    public Result getTrademarkList(Long category3Id){
        List<BaseTrademark> baseTrademarkList = baseTrademarkService.getTrademarkList(category3Id);
        return Result.ok(baseTrademarkList);
    }

//    @RequestMapping("baseTrademark/{pageNo}/{size}")
//    public Result baseTrademark(@PathVariable("pageNo") Long pageNo,@PathVariable("size") Long size,Long category3Id){
//        Page<BaseTrademark> page = new Page<>();
//        page.setSize(size);
//        page.setCurrent(pageNo);
//
//        IPage<BaseTrademark> baseTrademarkList= baseTrademarkService.baseTrademark(page,category3Id);
//        return Result.ok(baseTrademarkList);
//    }
}
