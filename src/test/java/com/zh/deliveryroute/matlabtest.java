package com.zh.deliveryroute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zh.deliveryroute.domain.NodeData;
import com.zh.deliveryroute.repository.NodeRepository;
import com.zh.deliveryroute.service.AlgorithService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.*;
import java.util.List;
import java.util.Optional;
@SpringBootTest
public class matlabtest {
    @Autowired
    AlgorithService algorithService;
    @Autowired
    NodeRepository nodeRepository;
    @Test
    public void calc(){
//        Optional<NodeData> coo = nodeRepository.findById(6L);
//        algorithService.getRoteByCoo(coo.get(),20,5,8,50,100,1000);

//        Optional<NodeData> dis = nodeRepository.findById(2L);
//        algorithService.getRouteByDis(dis.get(),8,3,8,50,50,100);

//        String res = readJsonFile("src/main/resources/db/res.json");
//
//        List<NodeData> nodeData = JSON.parseArray(res,NodeData.class);
//        for(int i =0;i<nodeData.size();i++){
//            long id = nodeData.get(i).getId();
//            Optional<NodeData> obj = nodeRepository.findById(id);
//
//            if(obj.isPresent()){
//                System.out.println("you");
//            }else{
//                nodeRepository.insert(nodeData.get(i));
//            }
//        }
//
//        System.out.println(nodeData.get(0).getId());
    }
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
