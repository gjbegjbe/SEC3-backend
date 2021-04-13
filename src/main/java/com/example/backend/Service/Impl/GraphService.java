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
    GraphRepository graphRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void addGraph(Graph graph) {
        graph.setId(sequenceGeneratorService.generateSequence(Graph.SEQUENCE_NAME));
        graphRepository.save(graph);
    }

    @Override
    public Graph getLatestGraph() {
        return graphRepository.findTopByOrderByIdDesc();
    }
}
