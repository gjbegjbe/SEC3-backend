package com.example.backend.Service.Impl;

import com.example.backend.BackendApplication;
import com.example.backend.Model.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BrandServiceTest {
    @Autowired
    BrandService brandService;

    @Test
    public void getBrandByNameContains1() {
        String name = "xxxxx";
        Brand brand = brandService.getBrandByNameContains(name);
        assert brand == null;
    }

    @Test
    public void getBrandByNameContains2() {
        String name = "7天";
        Brand brand = brandService.getBrandByNameContains(name);
        assert brand != null;

        System.out.println(brand.getName());
        assert brand.getName().equals("7天");
    }

    @Test
    public void getDetailByBrandName1() {
        String name = "xxxxx";
        String detail = brandService.getDetailByBrandName(name);
        assert detail.equals("暂无相关信息。");
    }

    @Test
    public void getDetailByBrandName2() {
        String name = "7天";
        String detail = brandService.getDetailByBrandName(name);
        assert !detail.equals("暂无相关信息。");
        System.out.println(detail);

        assert detail.contains(name);
    }
}