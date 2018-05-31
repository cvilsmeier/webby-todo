package org.webby.todo;

import java.util.ArrayList;
import java.util.List;

import org.webby.WebRequest;
import org.webby.todo.db.Todo;

public class TodoForm {

	private String title = "";
	private String text = "";

	public TodoForm() {
		super();
	}

	public void from(Todo todo) {
		if (todo != null) {
			title = todo.getTitle();
			text = todo.getText();
		}
	}

	public void from(WebRequest req) {
		title = req.getParameter("title").trim();
		text = req.getParameter("text").trim();
	}

	public List<String> validate() {
		List<String> errors = new ArrayList<>();
		if( title.isEmpty() ) {
			errors.add("Title must not be empty");
		}
		return errors;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

}
