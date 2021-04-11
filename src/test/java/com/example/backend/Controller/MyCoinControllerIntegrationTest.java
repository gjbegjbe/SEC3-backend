package com.example.backend.Controller;

import com.example.backend.BackendApplication;
import com.example.backend.Model.Link;
import com.example.backend.Model.Node;
import com.example.backend.Repository.LinkRepository;
import com.example.backend.Repository.NodeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyCoinControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private NodeRepository nodeRepository;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void addNode() {
        Node node = new Node();
        node.setName("n1");
        node.setUuid(10001);
        node.setImgsrc("n1");

        ResponseEntity<Map> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/addNode", node, Map.class);
        Map<String, Boolean> m = postResponse.getBody();

        assert m != null;
        assert m.get("added");
    }

    @Test
    public void deleteNode() {
        Node node = new Node();
        node.setName("n1");
        node.setUuid(10001);
        node.setImgsrc("n1");

        ResponseEntity<Map> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/deleteNode", node, Map.class);
        Map<String, Boolean> m = postResponse.getBody();

        assert m != null;
        assert m.get("deleted");
    }

    @Test
    public void addRelation() {
        Link link = new Link(10001, 10002, "r1", 20001);

        ResponseEntity<Map> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/addRelation", link, Map.class);
        Map<String, Boolean> m = postResponse.getBody();

        assert m != null;
        assert m.get("added");
    }

    @Test
    public void deleteRelation() {
        Link link = new Link(10001, 10002, "r1", 20001);

        ResponseEntity<Map> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/deleteRelation", link, Map.class);
        Map<String, Boolean> m = postResponse.getBody();

        assert m != null;
        assert m.get("deleted");
    }

    @Test
    public void getCoin() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Map> response = restTemplate.exchange(getRootUrl() + "/api/getCoin",
                HttpMethod.GET, entity, Map.class);

        assertNotNull(response.getBody());
    }

    @Before
    public void beforeOp() {
        nodeRepository.save(new Node("n1", 10001, "n1"));
        nodeRepository.save(new Node("n2", 10002, "n2"));
        nodeRepository.save(new Node("n3", 10003, "n3"));
        linkRepository.save(new Link(10001, 10002, "r1", 20001));
        linkRepository.save(new Link(10002, 10003, "r2", 20002));
        linkRepository.save(new Link(10003, 10001, "r3", 20003));
    }

    @After
    public void afterOp() {
        nodeRepository.delete(new Node("n1", 10001, "n1"));
        nodeRepository.delete(new Node("n2", 10002, "n2"));
        nodeRepository.delete(new Node("n3", 10003, "n3"));
        linkRepository.deleteById(20001L);
        linkRepository.deleteById(20002L);
        linkRepository.deleteById(20003L);
    }
}
