package com.example.backend.Repository;

import com.example.backend.Model.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends MongoRepository<Brand, Long> {

    void deleteByGid(Long gid);
}
