package com.zh.deliveryroute.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.zh.deliveryroute.domain.ExcelTemp;
import com.zh.deliveryroute.domain.NodeData;
import com.zh.deliveryroute.repository.NodeRepository;
import com.zh.deliveryroute.repository.TemplateResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class InitDataBase implements CommandLineRunner {
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private TemplateResponsitory templateResponsitory;
    @Override
    public void run(String... args) throws Exception {

        try {
            List<ExcelTemp> eList = templateResponsitory.findAll();
            List<NodeData> nodeData = nodeRepository.findAll();
            if(eList.size() == 0){
                System.out.println("初始化excel temp...");
                String str = readJsonFile("db/ExcelTemp.json");
                List<ExcelTemp> list = JSON.parseArray(str, ExcelTemp.class);
                for(int i =0;i<list.size();i++){
                    templateResponsitory.insert(list.get(i));
                }
                System.out.println("初始化excel temp 成功");
            }
            if(nodeData.size() == 0){
                System.out.println("初始化默认的地图数据...");
                String str = readJsonFile("db/NodeData.json");
                List<NodeData> list = JSON.parseArray(str,NodeData.class);
                for(int i =0;i<list.size();i++){
                    nodeRepository.insert(list.get(i));
                }
                System.out.println("初始化默认的地图数据成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            InputStream inputStream = new ClassPathResource(fileName).getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream , "UTF-8" );
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
