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

    private long sourceid;
    private long targetid;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Id
    private long uuid;


    public Link() {

    }

    public Link(long sourceid, long targetid, @NotBlank @Size(max = 100) String name, long uuid) {
        this.sourceid = sourceid;
        this.targetid = targetid;
        this.name = name;
        this.uuid = uuid;
    }

    public long getSourceid() {
        return sourceid;
    }

    public void setSourceid(long sourceid) {
        this.sourceid = sourceid;
    }

    public long getTargetid() {
        return targetid;
    }

    public void setTargetid(long targetid) {
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