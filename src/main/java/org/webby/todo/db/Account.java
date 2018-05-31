package org.webby.todo.db;

public class Account {

	private final String id;
	private final String name;

	public Account(String id, String name) {
		super();
		this.id = id;
		this.name = name;

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
