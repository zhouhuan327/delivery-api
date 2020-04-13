package com.zh.deliveryroute.repository;

import com.zh.deliveryroute.domain.CalcHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CalcHistoryRepository extends MongoRepository<CalcHistory,Long> {
}
