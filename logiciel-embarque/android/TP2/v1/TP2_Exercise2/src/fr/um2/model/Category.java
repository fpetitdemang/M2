package fr.um2.model;

public class Category {
	
	int id;
	String name;
	
	public Category() {
	}
	
	public Category(String name) {
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
