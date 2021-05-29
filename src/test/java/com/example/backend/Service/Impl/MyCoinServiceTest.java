//package com.example.backend.Service.Impl;
//
//import com.example.backend.BackendApplication;
//import com.example.backend.Model.*;
//import com.example.backend.Repository.*;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class MyCoinServiceTest {
//
//    @Autowired
//    private MyCoinService myCoinService;
//
//    @Autowired
//    private LinkRepository linkRepository;
//
//    @Autowired
//    private NodeRepository nodeRepository;
//
//    @Test
//    public void addNode() {
//        Node node = new Node();
//        node.setName("n1");
//        node.setUuid(10001);
//        node.setImgsrc("n1");
//
//        boolean res = myCoinService.addNode(node);
//
//        assert res;
//    }
//
//    @Test
//    public void deleteNodeById() {
//        Node node = new Node();
//        node.setName("n1");
//        node.setUuid(10001);
//        node.setImgsrc("n1");
//        boolean res = myCoinService.addNode(node);
//        assert res;
//
//        res = myCoinService.deleteNodeById(node.getUuid());
//        assert res;
//    }
//
//    @Test
//    public void addRelation() {
//        Link link = new Link(10001, 10002, "r1", 20001);
//
//        boolean res = myCoinService.addRelation(link);
//
//        assert res;
//    }
//
//    @Test
//    public void deleteRelationById() {
//        Link link = new Link(10001, 10002, "r1", 20001);
//        boolean res = myCoinService.addRelation(link);
//        assert res;
//
//        res = myCoinService.deleteRelationById(link.getUuid());
//        assert res;
//    }
//
//    @Test
//    public void deleteRelationByNodeId() {
//        Node node = new Node();
//        node.setName("n1");
//        node.setUuid(10001);
//        node.setImgsrc("n1");
//        myCoinService.addNode(node);
//
//        Link link = new Link(10001, 10002, "r1", 20001);
//        boolean res = myCoinService.addRelation(link);
//
//        myCoinService.deleteRelationByNodeId(node.getUuid());
//
//        res = linkRepository.existsById(link.getUuid());
//        assert !res;
//    }
//
//    @Before
//    public void beforeOp() {
//        nodeRepository.save(new Node("n1", 10001, "n1"));
//        nodeRepository.save(new Node("n2", 10002, "n2"));
//        nodeRepository.save(new Node("n3", 10003, "n3"));
//        linkRepository.save(new Link(10001, 10002, "r1", 20001));
//        linkRepository.save(new Link(10002, 10003, "r2", 20002));
//        linkRepository.save(new Link(10003, 10001, "r3", 20003));
//    }
//
//    @After
//    public void afterOp() {
//        nodeRepository.delete(new Node("n1", 10001, "n1"));
//        nodeRepository.delete(new Node("n2", 10002, "n2"));
//        nodeRepository.delete(new Node("n3", 10003, "n3"));
//        linkRepository.deleteById(20001L);
//        linkRepository.deleteById(20002L);
//        linkRepository.deleteById(20003L);
//    }
//}