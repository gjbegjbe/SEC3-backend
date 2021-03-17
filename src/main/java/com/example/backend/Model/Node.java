package com.example.backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection = "Node")
public class Node {

    @Transient
    public static final String SEQUENCE_NAME = "nodes_sequence";

    @NotBlank
    @Size(max = 100)
    private String name;

    @Id
    private long uuid;

    private String imgsrc;


    public Node() {

    }

    public Node(@NotBlank @Size(max = 100) String name, long uuid, String imgsrc) {
        this.name = name;
        this.uuid = uuid;
        this.imgsrc = imgsrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }
}
