package org.webby.todo;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webby.WebRequest;
import org.webby.todo.db.Account;
import org.webby.todo.db.Db;

public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Db db;

	public LoginController(Db db) {
		super();
		this.db = db;
	}

	public void serveLogin(WebRequest req) throws Exception {
		if (req.isGet()) {
			req.putModel("reason", req.getParameter("reason", ""));
			req.setTemplate("login.html");
			return;
		}
		String name = req.getParameter("name");
		Account account = db.findAccountByName(name);
		if (account == null) {
			req.putModel("errors", Arrays.asList("Account '" + name + "' not found"));
			req.setTemplate("login.html");
			return;
		}
		Principal principal = new Principal(account.getId(), account.getName());
		Auth.putPrincipal(req, principal);
		logger.info(principal.getName() + " has logged in");
		req.setRedirect("todos");
	}

	public void serveLogout(WebRequest req) throws Exception {
		Principal principal = Auth.findPrincipal(req);
		if (principal != null) {
			logger.info(principal.getName() + " has logged out");
		}
		Auth.putPrincipal(req, null);
		req.killSession();
		req.setRedirect("login?reason=logout");
	}

}
