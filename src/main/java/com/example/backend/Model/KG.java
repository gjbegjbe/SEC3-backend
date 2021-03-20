package com.example.backend.Model;

import java.util.List;

public class KG {
    private List<Node> node;
    private List<Link> relationship;

    public List<Node> getNode() {
        return node;
    }

    public void setNode(List<Node> node) {
        this.node = node;
    }

    public List<Link> getRelationship() {
        return relationship;
    }

    public void setRelationship(List<Link> relationship) {
        this.relationship = relationship;
    }
}
