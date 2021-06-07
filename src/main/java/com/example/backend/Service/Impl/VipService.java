package com.example.backend.Service.Impl;

import com.example.backend.Model.Vip;
import com.example.backend.Repository.VipRepository;
import com.example.backend.Service.IVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VipService implements IVipService {
    @Autowired
    private VipRepository vipRepository;

    @Override
    public Vip getVipByNameContains(String name) {
        try {
            Vip vip = vipRepository.findByNameContains(name);
            return vip;
        } catch (Exception e) {
            return null;
        }
    }
}
