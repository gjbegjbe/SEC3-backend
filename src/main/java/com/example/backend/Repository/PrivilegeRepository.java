package com.example.backend.Repository;

import com.example.backend.Model.Privilege;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends MongoRepository<Privilege, Long> {

    Privilege findById(long id);

    Privilege findByVidAndBid(long vid, long bid);
}
