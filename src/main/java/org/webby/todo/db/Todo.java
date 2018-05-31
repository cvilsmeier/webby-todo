package org.webby.todo.db;

import java.time.Instant;

public class Todo {

	private final String id;
	private final String accountId; // -> Account.id
	private final Instant created;
	private final String title;
	private final String text;

	public Todo(String id, String accountId, Instant created, String title, String text) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.created = created;
		this.title = title;
		this.text = text;
	}
	
	public Todo withId(String id) {
		return new Todo(id, accountId, created, title, text);
	}
	
	public Todo withData(String title, String text) {
		return new Todo(id, accountId, created, title, text);
	}
	
	public String getId() {
		return id;
	}

	public String getAccountId() {
		return accountId;
	}

	public Instant getCreated() {
		return created;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

}
