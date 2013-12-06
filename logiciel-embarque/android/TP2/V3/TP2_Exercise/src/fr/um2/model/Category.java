package fr.um2.model;

import java.util.ArrayList;


public class Category {
	
	
	int id;
	String name;
	
	public Category() {
	}
	
	public Category(String name) {
		this.name = name;
	}
	
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
