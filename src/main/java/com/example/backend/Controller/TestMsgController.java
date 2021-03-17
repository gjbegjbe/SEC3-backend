package com.example.backend.Controller;

import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Model.TestMsg;
import com.example.backend.Repository.TestMsgRepository;
import com.example.backend.Service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/testapi")
class TestMsgController {
    @Autowired
    private TestMsgRepository testMsgRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/testmsgs")
    public List<TestMsg> getAllTestMsgs() {
        return testMsgRepository.findAll();
    }

    @GetMapping("/testmsgs/{id}")
    public ResponseEntity<TestMsg> getTestMsgById(@PathVariable(value = "id") Long testMsgId)
            throws ResourceNotFoundException {
        TestMsg testMsg = testMsgRepository.findById(testMsgId)
                .orElseThrow(() -> new ResourceNotFoundException("TestMsg not found for this id :: " + testMsgId));
        return ResponseEntity.ok().body(testMsg);
    }

    @PostMapping("/testmsgs")
    public TestMsg createTestMsg(@Valid @RequestBody TestMsg testMsg) {
        testMsg.setId(sequenceGeneratorService.generateSequence(TestMsg.SEQUENCE_NAME));
        return testMsgRepository.save(testMsg);
    }

    @PutMapping("/testmsgs/{id}")
    public ResponseEntity<TestMsg> updateTestMsg(@PathVariable(value = "id") Long testMsgId,
                                                 @Valid @RequestBody TestMsg testMsgDetails) throws ResourceNotFoundException {
        TestMsg testMsg = testMsgRepository.findById(testMsgId)
                .orElseThrow(() -> new ResourceNotFoundException("TestMsg not found for this id :: " + testMsgId));

        testMsg.setName(testMsgDetails.getName());
        testMsg.setStringList(testMsgDetails.getStringList());
        testMsg.setObj(testMsgDetails.getObj());
        final TestMsg updatedTestMsg = testMsgRepository.save(testMsg);
        return ResponseEntity.ok(updatedTestMsg);
    }

    @DeleteMapping("/testmsgs/{id}")
    public Map<String, Boolean> deleteTestMsg(@PathVariable(value = "id") Long testMsgId)
            throws ResourceNotFoundException {
        TestMsg testMsg = testMsgRepository.findById(testMsgId)
                .orElseThrow(() -> new ResourceNotFoundException("TestMsg not found for this id :: " + testMsgId));

        testMsgRepository.delete(testMsg);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}