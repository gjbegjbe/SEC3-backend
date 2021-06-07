package com.example.backend.Service.Impl;

import com.example.backend.Model.Privilege;
import com.example.backend.Repository.PrivilegeRepository;
import com.example.backend.Service.IPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService implements IPrivilegeService {
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public Privilege getPrivilegeByVidAndBid(long vid, long bid) {
        try {
            Privilege privilege = privilegeRepository.findByVidAndBid(vid, bid);
            return privilege;
        } catch (Exception e) {
            return null;
        }
    }
}
