package com.example.backend.Controller;

import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Model.Link;
import com.example.backend.Model.Node;
import com.example.backend.Service.IMyCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class MyCoinController {
    @Autowired
    private IMyCoinService myCoinService;

    @PostMapping("/addNode")
    public ResponseEntity<Map<String, Boolean>> addNode(@Valid @RequestBody Node node) {
        myCoinService.addNode(node);
        Map<String, Boolean> response = new HashMap<>();
        response.put("added", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/deleteNode")
    public ResponseEntity<Map<String, Boolean>> deleteNode(@Valid @RequestBody Node node) throws ResourceNotFoundException {
        myCoinService.deleteNodeById(node.getUuid());
        myCoinService.deleteRelationByNodeId((int) node.getUuid());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/addRelation")
    public ResponseEntity<Map<String, Boolean>> addRelation(@Valid @RequestBody Link link) {
        myCoinService.addRelation(link);
        Map<String, Boolean> response = new HashMap<>();
        response.put("added", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/deleteRelation")
    public ResponseEntity<Map<String, Boolean>> deleteRelation(@Valid @RequestBody Link link) {
        myCoinService.deleteRelationById(link.getUuid());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getCoin")
    public ResponseEntity<Map<String, Object>> getCoin() {
        Map<String, Object> response = myCoinService.getCoin();
        return ResponseEntity.ok().body(response);
    }
}