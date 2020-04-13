package com.zh.deliveryroute.repository;

import com.zh.deliveryroute.domain.ExcelTemp;
import com.zh.deliveryroute.domain.NodeData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemplateResponsitory extends MongoRepository<ExcelTemp,Long> {
}
