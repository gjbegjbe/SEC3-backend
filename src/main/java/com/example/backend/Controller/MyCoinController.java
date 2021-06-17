package com.example.backend.Controller;

import com.example.backend.Model.Brand;
import com.example.backend.Model.Group;
import com.example.backend.Service.IMyCoinService;
import com.example.backend.Service.Impl.BrandService;
import com.example.backend.Service.Impl.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MyCoinController {
    @Autowired
    private IMyCoinService myCoinService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private BrandService brandService;

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

    @GetMapping("/getGroupNameList")
    public ResponseEntity<List<String>> getGroupNameList() {
        return ResponseEntity.ok().body(groupService.getGroupNameList());
    }

    @PostMapping("/getGraphByGroupName")
    public ResponseEntity<Map<String, Object>> getGraphByGroupName(@Valid @RequestBody Map<String, Object> body) {
        String name = (String) body.get("groupName");
        Map<String, Object> response = groupService.getGraphByGroupName3Level(name);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/getDetailByBrandName")
    public ResponseEntity<Map<String, Object>> getDetailByBrandName(@Valid @RequestBody Map<String, Object> body) {
        String name = (String) body.get("brandName");
        System.out.println("getDetailByBrandName " + name);
        Map<String, Object> response = new HashMap<>();
        response.put("detail", brandService.getDetailByBrandName(name));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/getDetailByGroupName")
    public ResponseEntity<Map<String, Object>> getDetailByGroupName(@Valid @RequestBody Map<String, Object> body) {
        String name = (String) body.get("groupName");
        System.out.println("getDetailByGroupName " + name);
        Map<String, Object> response = new HashMap<>();
        response.put("detail", groupService.getDetailByGroupName(name));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/getPicByBrandName")
    public ResponseEntity<Map<String, Object>> getPicByBrandName(@Valid @RequestBody Map<String, Object> body) {
        String name = (String) body.get("brandName");
        Map<String, Object> response = new HashMap<>();
        response.put("detail", brandService.getLogoUrlByBrandName(name));
        return ResponseEntity.ok().body(response);
    }
}