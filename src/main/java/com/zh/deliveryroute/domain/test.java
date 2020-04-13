package com.zh.deliveryroute.domain;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection="test")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class test<T> {
    @Id
    private int id;
    private String name;
    private List< T > list;


}
