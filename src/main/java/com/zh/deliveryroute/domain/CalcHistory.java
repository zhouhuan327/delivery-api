package com.zh.deliveryroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="CalcHistory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalcHistory<E> {
    private E resultData;
    private String sheetName;
    private String createTime;
}
