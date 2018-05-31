package org.webby.todo;

import java.io.Serializable;

public class Principal implements Serializable {

	private static final long serialVersionUID = 1L;

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
