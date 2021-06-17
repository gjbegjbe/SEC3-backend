package com.example.backend.Service.Impl;

import com.example.backend.Model.Brand;
import com.example.backend.Model.Privilege;
import com.example.backend.Repository.BrandRepository;
import com.example.backend.Repository.PrivilegeRepository;
import com.example.backend.Service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private VipService vipService;

    @Autowired
    private QaService qaService;

    @Override
    public Brand getBrandByNameContains(String name) {
        Brand brand;
        try {
            brand = brandRepository.findByNameContains(name);
        } catch (Exception e) {
            brand = brandRepository.findByName(name);
        }

        return brand;
    }

    @Override
    public String getDetailByBrandName(String name) {
        Brand brand = getBrandByNameContains(name);
        if (brand == null)
            return "暂无相关信息。";
        List<Privilege> privilegeList = privilegeRepository.findAllByBidOrderByCheckoutAsc(brand.getId());
        if (privilegeList.isEmpty())
            return "暂无相关信息。";

        String detail = "";

        boolean isBegin = true;
        for (Privilege privilege : privilegeList) {
            String res = qaService.getDiscountByBrandAndVipAnswer(brand, vipService.getVipById(privilege.getVid()));
            res = res.replace('。', '，');
            res += "最晚" + privilege.getCheckout() + "退房，";
            if (privilege.getBreakfast() == 0) {
                res += "无免费早餐赠送。";
            } else {
                res += "赠送" + privilege.getBreakfast() + "份免费早餐。";
            }
            if (!isBegin)
                detail += "\n";
            detail += res;
            isBegin = false;
        }

        return detail;
    }

    @Override
    public String getLogoUrlByBrandName(String name) {
        Brand brand = getBrandByNameContains(name);
        if (brand == null)
            return "";
        return brand.getImgsrc();
    }
}
