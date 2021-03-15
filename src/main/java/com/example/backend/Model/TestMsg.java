package com.example.backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Document(collection = "TestMsg")
public class TestMsg {

	@Transient
	public static final String SEQUENCE_NAME = "testMsgs_sequence";

	@Id
	private long id;

	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	private String name;

	private List<String> stringList;

	private List<Object> obj;

	public TestMsg() {

	}

	public TestMsg(long id, @NotBlank @Size(max = 100) String name, List<String> stringList, List<Object> obj) {
		this.id = id;
		this.name = name;
		this.stringList = stringList;
		this.obj = obj;
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

	public List<String> getStringList() {
		return stringList;
	}

	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
	}

	public List<Object> getObj() {
		return obj;
	}

	public void setObj(List<Object> obj) {
		this.obj = obj;
	}
}
