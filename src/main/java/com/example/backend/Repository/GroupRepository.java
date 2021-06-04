package com.example.backend.Repository;

import com.example.backend.Model.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends MongoRepository<Group, Long> {

    Group findByName(String name);
}
