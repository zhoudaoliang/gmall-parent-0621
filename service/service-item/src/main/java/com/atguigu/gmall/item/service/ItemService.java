package com.atguigu.gmall.item.service;

import java.util.Map;

/**
 * @author Daoliang Zhou
 * @create 2020-12-03 11:23
 */
public interface ItemService {
    Map<String, Object> getItem(Long skuId);
}
