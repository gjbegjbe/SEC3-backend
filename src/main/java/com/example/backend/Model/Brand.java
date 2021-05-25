package com.example.backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Brand")
public class Brand {

    @Transient
    public static final String SEQUENCE_NAME = "brands_sequence";

    @Id
    private long id;

    private String name;

    private String priority;

    private String discount;

    private String checkout;

    private String breakfast;

    private long gid;

    private long rid;

    public Brand() {

    }

    public Brand(String name, String priority, String discount, String checkout, String breakfast) {
        this.name = name;
        this.priority = priority;
        this.discount = discount;
        this.checkout = checkout;
        this.breakfast = breakfast;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }
}
