package org.webby.todo;

public class Principal {

	private final String accountId;
	private final String name;

	public Principal(String accountId, String name) {
		super();
		this.accountId = accountId;
		this.name = name;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getName() {
		return name;
	}

}
