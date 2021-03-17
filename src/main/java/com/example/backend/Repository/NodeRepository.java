package com.example.backend.Repository;

import com.example.backend.Model.Node;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends MongoRepository<Node, Long> {

}
