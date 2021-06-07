package com.example.backend.Service.Impl;

import com.example.backend.Model.Graph;
import com.example.backend.Repository.GraphRepository;
import com.example.backend.Service.IGraphService;
import com.example.backend.Service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphService implements IGraphService {
    @Autowired
    private GraphRepository graphRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public long addGraph(Graph graph) {
        long id = sequenceGeneratorService.generateSequence(Graph.SEQUENCE_NAME);
        graph.setId(id);
        graphRepository.save(graph);
        return id;
    }

    @Override
    public Graph getLatestGraph() {
        return graphRepository.findTopByOrderByIdDesc();
    }

    @Override
    public void deleteGraphById(long id) {
        graphRepository.deleteById(id);
    }
}
