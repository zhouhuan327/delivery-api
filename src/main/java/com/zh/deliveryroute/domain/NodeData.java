package com.zh.deliveryroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Document(collection="NodeData")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NodeData<E> {
    @Id
    private long id;
    private String sheetName;
    private String type;
    private List<double[]> disData;
    private List<CoNode> cooData;
    private double[] qtdata;
    private String createTime;
    private E wb; //excel数据
}
