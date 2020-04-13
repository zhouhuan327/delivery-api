package com.zh.deliveryroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection="ExcelTemp")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExcelTemp<E> {
    private String type;
    private E data;
}
