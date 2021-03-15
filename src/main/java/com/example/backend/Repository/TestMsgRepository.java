package com.example.backend.Repository;

import com.example.backend.Model.TestMsg;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMsgRepository extends MongoRepository<TestMsg, Long> {

}
