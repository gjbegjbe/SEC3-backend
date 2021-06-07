package com.example.backend.Service.Impl;

import com.example.backend.Model.Rank;
import com.example.backend.Repository.RankRepository;
import com.example.backend.Service.IRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankService implements IRankService {
    @Autowired
    private RankRepository rankRepository;

    @Override
    public Rank getRankByNameContains(String name) {
        try {
            Rank rank = rankRepository.findByNameContains(name);
            return rank;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Rank getRankById(long id) {
        try {
            Rank rank = rankRepository.findById(id);
            return rank;
        } catch (Exception e) {
            return null;
        }
    }
}
