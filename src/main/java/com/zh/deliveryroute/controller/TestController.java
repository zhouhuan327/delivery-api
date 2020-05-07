package com.zh.deliveryroute.controller;


import com.alibaba.fastjson.JSONObject;
import com.zh.deliveryroute.domain.CoNode;
import com.zh.deliveryroute.domain.test;
import com.zh.deliveryroute.repository.MongoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@CrossOrigin
public class TestController {
    @Autowired
    MongoRes mon;

    @GetMapping("/helloBoot")
    public List<?> helloBoot(){
        List<CoNode> nodelist = new ArrayList<>();
//        nodelist.add(new node(1,2));
//        nodelist.add(new node(4,2));
//        mon.insert(new test(5,"33",nodelist));
//        mon.insert(new test(6,"33",nodelist));
//        CoNode c = new CoNode("1",12,23,23);
//        nodelist.add(c);
//        mon.insert(new test(8,"33",nodelist));
        List<test> list = mon.findAll();

        return list;
    }
    @PostMapping("/addtest")
    public int addtest(@RequestBody JSONObject jsonObject){
        List<CoNode> nodelist = new ArrayList<>();
        return 0;


    }
}
