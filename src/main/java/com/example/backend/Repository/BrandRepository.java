package com.example.backend.Repository;

import com.example.backend.Model.Brand;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends MongoRepository<Brand, Long> {

    void deleteByGid(Long gid);

    @Cacheable(value = "brand", unless = "#result == null")
    List<Brand> findAllByGid(long gid);

    @Cacheable(value = "brand", unless = "#result == null")
    Brand findByName(String name);

    @Cacheable(value = "brand", unless = "#result == null")
    Brand findByNameContains(String name);

    @Cacheable(value = "brand", unless = "#result == null")
    List<Brand> findTop8ByRidAndPriority(long rid, String priority);

    @Cacheable(value = "brand", unless = "#result == null")
    List<Brand> findTop8ByGidAndRidAndPriority(long gid, long rid, String priority);
}
