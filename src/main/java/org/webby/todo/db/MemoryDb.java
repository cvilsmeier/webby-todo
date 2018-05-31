package org.webby.todo.db;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MemoryDb implements Db {

	private static Comparator<Account> ACCOUNT_COMPARATOR = new Comparator<Account>() {
		@Override
		public int compare(Account a, Account b) {
			int r = a.getName().compareTo(b.getName());
			if (r == 0) {
				r = a.getId().compareTo(b.getId());
			}
			return r;
		}
	};
	
	private static Comparator<Todo> TODO_COMPARATOR = new Comparator<Todo>() {
		@Override
		public int compare(Todo a, Todo b) {
			int r = a.getCreated().compareTo(b.getCreated());
			if (r == 0) {
				r = a.getId().compareTo(b.getId());
			}
			return r;
		}
	};
	
	private final Map<String, Account> accounts = new HashMap<>();
	private final Map<String, Todo> todos = new HashMap<>();

	public MemoryDb() {
		super();
		Account alice = new Account(uuid(), "Alice");
		accounts.put(alice.getId(), alice);
		Account bob = new Account(uuid(), "Bob");
		accounts.put(bob.getId(), bob);
	}

	@Override
	public synchronized List<Account> findAccounts() {
		List<Account> list = new ArrayList<>(accounts.values());
		list.sort(ACCOUNT_COMPARATOR);
		return list;
	}

	@Override
	public synchronized Account findAccountByName(String name) {
		for (Account account : accounts.values()) {
			if (account.getName().equals(name)) {
				return account;
			}
		}
		return null;
	}

	@Override
	public synchronized Todo findTodo(String id) {
		return todos.get(id);
	}

	@Override
	public synchronized List<Todo> findTodosForAccount(String accountId) {
		List<Todo> list = new ArrayList<>();
		for (Todo todo : todos.values()) {
			if (todo.getAccountId().equals(accountId)) {
				list.add(todo);
			}
		}
		list.sort(TODO_COMPARATOR);
		return list;
	}

	@Override
	public synchronized String saveTodo(Todo todo) {
		if (todo.getId() == null) {
			todo = todo.withId(uuid());
		}
		todos.put(todo.getId(), todo);
		return todo.getId();
	}

	@Override
	public synchronized void deleteTodo(String id) {
		todos.remove(id);
	}

	private String uuid() {
		return UUID.randomUUID().toString();
	}

}
