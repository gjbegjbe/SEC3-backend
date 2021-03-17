package com.example.backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection = "Link")
public class Link {

    @Transient
    public static final String SEQUENCE_NAME = "nodes_sequence";

    private int sourceid;
    private int targetid;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Id
    private long uuid;


    public Link() {

    }

    public Link(int sourceid, int targetid, @NotBlank @Size(max = 100) String name, long uuid) {
        this.sourceid = sourceid;
        this.targetid = targetid;
        this.name = name;
        this.uuid = uuid;
    }

    public int getSourceid() {
        return sourceid;
    }

    public void setSourceid(int sourceid) {
        this.sourceid = sourceid;
    }

    public int getTargetid() {
        return targetid;
    }

    public void setTargetid(int targetid) {
        this.targetid = targetid;
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
}