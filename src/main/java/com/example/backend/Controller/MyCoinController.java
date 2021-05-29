package com.example.backend.Controller;

import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Model.*;
import com.example.backend.Service.IMyCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
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
        myCoinService.deleteRelationByNodeId(node.getUuid());
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

    @PostMapping("/addGroup")
    public ResponseEntity<Map<String, Object>> addGroup(@Valid @RequestBody Group group) {
        long id = myCoinService.addGroup(group);
        String uuid = "group" + id;
        Map<String, Object> response = new HashMap<>();
        response.put("uuid", uuid);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/deleteGroupByUuid")
    public ResponseEntity<Map<String, Boolean>> deleteGroupByUuid(@Valid @RequestBody Map<String, Object> body) {
        String uuid = (String) body.get("uuid");
        long id = Long.parseLong(uuid.substring(5));
        boolean res = myCoinService.deleteGroupById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", res);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/addBrand")
    public ResponseEntity<Map<String, Object>> addBrand(@Valid @RequestBody Brand brand) {
        long id = myCoinService.addBrand(brand);
        String uuid = "brand" + id;
        Map<String, Object> response = new HashMap<>();
        response.put("uuid", uuid);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/deleteBrandByUuid")
    public ResponseEntity<Map<String, Boolean>> deleteBrandByUuid(@Valid @RequestBody Map<String, Object> body) {
        String uuid = (String) body.get("uuid");
        long id = Long.parseLong(uuid.substring(5));
        boolean res = myCoinService.deleteBrandById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", res);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getCoin")
    public ResponseEntity<Map<String, Object>> getCoin() {
        Map<String, Object> response = myCoinService.getCoin();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/updateCoin")
    public ResponseEntity<Map<String, Boolean>> updateCoin(@Valid @RequestBody KG kg) {
        myCoinService.updateCoin(kg);
        Map<String, Boolean> response = new HashMap<>();
        response.put("updated", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }
}