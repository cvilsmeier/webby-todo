package org.webby.todo;

import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.webby.WebRequest;
import org.webby.todo.db.Db;
import org.webby.todo.db.Todo;

public class TodoController {

	private final Logger logger = Logger.getLogger(getClass());
	private final Db db;
	
	public TodoController(Db db) {
		super();
		this.db = db;
	}

	public void serveTodos(WebRequest req) throws Exception {
		Principal principal = Auth.needPrincipal(req);
		List<Todo> todos = db.findTodosForAccount(principal.getAccountId());
		req.putModel("todos", todos);
		req.setTemplate("todos.html");
	}

	public void serveTodoEdit(WebRequest req) throws Exception {
		Principal principal = Auth.needPrincipal(req);
		Todo todo = db.findTodo(req.getParameter("todo", ""));
		if( todo != null ) {
			Must.beEqual(todo.getAccountId(), principal.getAccountId(), "todo does not belong to you!");
		}
		TodoForm form = new TodoForm();
		req.putModel("form", form);
		if( req.isGet() ) {
			form.from(todo);
			req.setTemplate("todoEdit.html");
			return;
		}
		form.from(req);
		List<String> errors = form.validate();
		req.putModel("errors", errors);
		if( ! errors.isEmpty() ) {
			req.setTemplate("todoEdit.html");
			return;
		}
		if( todo == null ) {
			todo = new Todo(null, principal.getAccountId(), Instant.now(), form.getTitle(), form.getText());
		} else {
			todo = todo.withData(form.getTitle(), form.getText());
		}
		String savedId = db.saveTodo(todo);
		logger.info("saved todo "+savedId);
		req.setRedirect("todos");
	}

	public void serveTodoDelete(WebRequest req) throws Exception {
		Principal principal = Auth.needPrincipal(req);
		if( req.isGet() ) {
			req.setTemplate("todoDelete.html");
			return;
		}
		Todo todo = db.findTodo(req.getParameter("todo"));
		if( todo == null ) {
			req.setRedirect("todos");
		}
		Must.beEqual(todo.getAccountId(), principal.getAccountId(), "todo does not belong to you!");
		logger.info("delete todo "+todo.getId());
		db.deleteTodo(todo.getId());
		req.setRedirect("todos");
	}

}
