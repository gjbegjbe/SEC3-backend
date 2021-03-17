package com.example.backend.Controller;

import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Model.Link;
import com.example.backend.Model.Node;
import com.example.backend.Repository.LinkRepository;
import com.example.backend.Repository.NodeRepository;
import com.example.backend.Service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class MyCoinController {
    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @PostMapping("/addNode")
    public ResponseEntity<Map<String, Boolean>> addNode(@Valid @RequestBody Node node) {
        nodeRepository.save(node);
        Map<String, Boolean> response = new HashMap<>();
        response.put("added", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/deleteNode")
    public ResponseEntity<Map<String, Boolean>> deleteNode(@Valid @RequestBody Node node) throws ResourceNotFoundException {
        Node resNode = nodeRepository.findById(node.getUuid())
                .orElseThrow(() -> new ResourceNotFoundException("Node not found for this id : " + node.getUuid()));
        nodeRepository.delete(resNode);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/addRelation")
    public ResponseEntity<Map<String, Boolean>> addRelation(@Valid @RequestBody Link link) {
        linkRepository.save(link);
        Map<String, Boolean> response = new HashMap<>();
        response.put("added", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/deleteRelation")
    public ResponseEntity<Map<String, Boolean>> deleteRelation(@Valid @RequestBody Link link) throws ResourceNotFoundException {
        Link resLink = linkRepository.findById(link.getUuid())
                .orElseThrow(() -> new ResourceNotFoundException("Relation not found for this id : " + link.getUuid()));
        linkRepository.delete(resLink);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }
}