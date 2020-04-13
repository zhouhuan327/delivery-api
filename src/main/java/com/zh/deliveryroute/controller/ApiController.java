package com.zh.deliveryroute.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zh.deliveryroute.domain.CoNode;
import com.zh.deliveryroute.domain.ExcelTemp;
import com.zh.deliveryroute.domain.NodeData;
import com.zh.deliveryroute.domain.Result;
import com.zh.deliveryroute.repository.MongoRes;
import com.zh.deliveryroute.repository.NodeRepository;
import com.zh.deliveryroute.repository.TemplateResponsitory;
import com.zh.deliveryroute.service.AlgorithService;
import com.zh.deliveryroute.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Node;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {
    @Autowired
    NodeRepository nodeRepository;
    @Autowired
    TemplateResponsitory templateResponsitory;
    @Autowired
    AlgorithService algorithService;
    @GetMapping("/getNodeList")
    public ServerResponse getNodeList() {
       List<NodeData> list = nodeRepository.findAll();

        return ServerResponse.createBySuccess("查询成功", list);
    }
    @PostMapping("/calc")
    public ServerResponse calc(@RequestBody JSONObject jsonObject){
        Long id = jsonObject.getLong("id");
        int n = 0;//客户数
        int m = 0;
        int q = 0;
        int maxroad = 0;
        int sizepop = 0;
        int maxgen = 0;
        try {
            n = jsonObject.getInteger("n");
            m = jsonObject.getInteger("m");
            q = jsonObject.getInteger("q");
            maxroad = jsonObject.getInteger("maxroad");
            sizepop = jsonObject.getInteger("sizepop");
            maxgen = jsonObject.getInteger("maxgen");
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError("参数出错",e.toString());
        }
        NodeData nodeData = null;
        try {
            Optional<NodeData> obj = nodeRepository.findById(id);
            nodeData = obj.get();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError("节点数据获取失败",e.toString());
        }
        Result res = null;
        try {
            res = new Result();
            if(nodeData.getType().equals("coodinate")){
                res = algorithService.getRoteByCoo(nodeData,n,m,q,maxroad,sizepop,maxgen);
            }else if(nodeData.getType().equals("distance")){
                res = algorithService.getRouteByDis(nodeData,n,m,q,maxroad,sizepop,maxgen);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError("计算出错",e.toString());
        }

        return ServerResponse.createBySuccess("计算成功",res);
    }

    @PostMapping("/addNode")
    public ServerResponse addNode(@RequestBody List<NodeData> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                long nextId = getNextId(); //生成id
                list.get(i).setId(nextId);
                list.get(i).setCreateTime(getTime());
                nodeRepository.insert(list.get(i));
            }
        } catch (Exception e) {
            return ServerResponse.createBySuccess("Error\n"+e,null);
        }
        return ServerResponse.createBySuccess("保存成功", null);
    }
    @PostMapping("/getNode")
    public ServerResponse getNode(@RequestBody JSONObject jsonObject){
        Long id = jsonObject.getLong("id");
        Optional<NodeData> obj = nodeRepository.findById(id);

//        List<double[]> distance = obj.get().getDisData();
//
//        double[] qt = obj.get().getQtdata();
//        Optional<NodeData> obj = nodeRepository.findById(0L);
//        List<double[]> distance = obj.get().getDisData();
//        double [][]distanceArray = new double[distance.size()][];
//        for(int i =0;i<distance.size();i++){
//            distanceArray[i] = distance.get(i);
//        }
//        double [] qtArray = obj.get().getQtdata();
//        JSONObject jsonObject = new JSONObject();
        return ServerResponse.createBySuccess("id:"+id,obj.get());
    }
    @PostMapping("/deleteNode")
    public ServerResponse deleteNode(@RequestBody JSONObject jsonObject){
        Long id = jsonObject.getLong("id");
        try {
            nodeRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError(e.toString(),null);
        }

        return ServerResponse.createBySuccess("删除成功",id);
    }
//    @PostMapping("/addTemp")
//    public ServerResponse addExcelTemp(@RequestBody ExcelTemp excelTemp){
//
//        templateResponsitory.insert(excelTemp);
//
//        return ServerResponse.createBySuccess("success",null);
//    }
    @GetMapping("/getTemp")
    public ServerResponse getTemp(){
        List<ExcelTemp> Template;
        try {
            Template = templateResponsitory.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError(e.toString(),null);
        }
        return ServerResponse.createBySuccess("成功",Template);

    };

    public long getNextId(){
        List<NodeData> list = nodeRepository.findAll();
        long id = 0;
        if(list.size() == 0){
            return id;
        }else{
            id =list.get(list.size()-1).getId();
            return ++id;
        }
    }
    public String getTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }



}
