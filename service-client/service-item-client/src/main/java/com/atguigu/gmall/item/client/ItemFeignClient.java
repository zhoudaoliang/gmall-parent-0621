package com.atguigu.gmall.item.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author Daoliang Zhou
 * @create 2020-12-03 10:48
 */
@FeignClient(value = "service-item")
public interface ItemFeignClient {
    @RequestMapping("api/item/getItem/{skuId}")
    Map<String, Object> getItem(@PathVariable("skuId") Long skuId);
}
