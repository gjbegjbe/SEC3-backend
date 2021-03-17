package com.example.backend.Repository;

import com.example.backend.Model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends MongoRepository<Link, Long> {

}
