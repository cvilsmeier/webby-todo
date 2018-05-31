package org.webby.todo;

import org.webby.WebRequest;

public class IndexController {

	public void serveIndex(WebRequest req) throws Exception {
		req.setTemplate("index.html");
	}

}
