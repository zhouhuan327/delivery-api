package com.zh.deliveryroute;

import com.zh.deliveryroute.domain.NodeData;
import com.zh.deliveryroute.repository.NodeRepository;
import com.zh.deliveryroute.service.AlgorithService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    }
}
