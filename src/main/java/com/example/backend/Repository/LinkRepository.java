package com.example.backend.Repository;

import com.example.backend.Model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends MongoRepository<Link, Long> {
    public List<Link> findBySourceid(long sourceid);

    public List<Link> findByTargetid(long targetid);
}
