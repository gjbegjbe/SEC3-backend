package com.example.backend.Service;

import com.example.backend.Model.Graph;

public interface IGraphService {

    /**
     * @param graph
     */
    void addGraph(Graph graph);

    /**
     * @return
     */
    Graph getLatestGraph();
}
