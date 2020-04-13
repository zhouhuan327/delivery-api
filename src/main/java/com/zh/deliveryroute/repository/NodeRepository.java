package com.zh.deliveryroute.repository;

        import com.zh.deliveryroute.domain.NodeData;
        import org.springframework.data.mongodb.repository.MongoRepository;

public interface NodeRepository extends MongoRepository<NodeData,Long> {
}
