package com.example.backend.Service.Impl;

import com.example.backend.Model.Graph;
import com.example.backend.Repository.GraphRepository;
import com.example.backend.Service.IGraphService;
import com.example.backend.Service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<Graph> graphs = graphRepository.findAll();
        if (graphs.size() == 0)
            return null;
        int index = 0;
        for (int i = 0; i < graphs.size(); i++) {
            if (graphs.get(i).getId() > graphs.get(index).getId())
                index = i;
        }
        return graphs.get(index);
    }
}
