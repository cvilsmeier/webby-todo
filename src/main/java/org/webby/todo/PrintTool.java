package org.webby.todo;

import org.webby.Escaper;

public class PrintTool {

	public String html(Object o) {
		return Escaper.escapeHtml(o);
	}

}
