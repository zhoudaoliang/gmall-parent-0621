package com.atguigu.gmall.all.controller;

import com.atguigu.gmall.item.client.ItemFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Daoliang Zhou
 * @create 2020-12-02 16:17
 */
@Controller
public class ItemController {

    @Autowired
    ItemFeignClient itemFeignClient;

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable("skuId") Long skuId,Model model){

        Map<String, Object> map = new HashMap<>();

        map = itemFeignClient.getItem(skuId);

        model.addAllAttributes(map);

        return "item/index";
    }

    /**
     *
     * @param
     * @return
     *
     * nested exception is java.lang.IllegalStateException: Ambiguous mapping. Cannot map 'itemController' method
     * controller中url映射出现重复
     */
//    @RequestMapping("{skuId}.html")t
//    public String item(@PathVariable("skuId") Long skuId){
//
//        HashMap<String, Object> map = new HashMap<>();
//
//        return "item/index";
//    }





    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("test")
    public String test(Model model){
        model.addAttribute("hello","hello thymeleaf");

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("元素" + i);
        }
        model.addAttribute("list",list);


//        model.addAttribute("num",3);
        model.addAttribute("num","动物世界");
        return "test";
    }

}

//package com.atguigu.gmall.all.controller;
//
//
//import com.atguigu.gmall.item.client.ItemFeignClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class ItemController {
//
//    @Autowired
//    ItemFeignClient itemFeignClient;
//
//    @RequestMapping("{skuId}.html")
//    public String item(@PathVariable("skuId") Long skuId,Model model){
//
//        Map<String,Object> map = new HashMap<>();
//
//        map = itemFeignClient.getItem(skuId);
//
//        model.addAllAttributes(map);
//
//        return "item/index";
//    }
//
//
//    @RequestMapping("/")
//    public String index(){
//
//        return "index";
//    }
//
//    @RequestMapping("test")
//    public String test(Model model){
//
//        model.addAttribute("hello","hello thymeleaf");
//
//        List<String> list = new ArrayList<>();
//
//        for (int i = 0; i < 5 ; i++) {
//            list.add("元素"+i);
//        }
//
//
//        model.addAttribute("list",list);
//
//
//        model.addAttribute("num","亡者联盟");
//        return "test";
//    }
//
//}

