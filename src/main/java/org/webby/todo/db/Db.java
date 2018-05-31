package org.webby.todo.db;

import java.util.List;

public interface Db {

	// Accounts
	
	List<Account> findAccounts();

	Account findAccountByName(String name);

	// Todos

	Todo findTodo(String id);

	List<Todo> findTodosForAccount(String accountId);

	String saveTodo(Todo todo);

	void deleteTodo(String id);

}
