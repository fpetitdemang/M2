package fr.um2.model;

public class Task {

	int id;
	String name;
	String date;
	String startTime;
	String endTime;
	int category;
	
	public Task() {
	}

	public Task(String name) {
		this.name = name;
	}

	public Task(String name, String date) {
		this.name = name;
		this.date = date;
	}

	public Task(String name, String date, String startTime, String endTime) {
		this.name = name;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public Task(String name, String date, String startTime, String endTime, int category) {
		this.name = name;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.category = category;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getCategory() {
		return category;
	}
}
