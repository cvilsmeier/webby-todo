package org.webby.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webby.App;
import org.webby.AppContext;
import org.webby.WebRequest;
import org.webby.todo.db.Db;
import org.webby.todo.db.MemoryDb;

public class TodoApp implements App {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Db db;
	private final IndexController indexController;
	private final LoginController loginController;
	private final TodoController todoController;

	public TodoApp(AppContext appContext) throws Exception {
		logger.info("app startup");
		// initialize database
		this.db = new MemoryDb();
		// create controllers
		this.indexController = new IndexController();
		this.loginController = new LoginController(db);
		this.todoController = new TodoController(db);
	}

	@Override
	public void serve(WebRequest req) throws Exception {
		String path = req.getPath();
		req.putModel("p", new PrintTool());
		req.putModel("principal", Auth.findPrincipal(req));
		try {
			switch (path) {
			case "/":
				indexController.serveIndex(req);
				break;
			case "/login":
				loginController.serveLogin(req);
				break;
			case "/logout":
				loginController.serveLogout(req);
				break;
			case "/todos":
				todoController.serveTodos(req);
				break;
			case "/todoEdit":
				todoController.serveTodoEdit(req);
				break;
			case "/todoDelete":
				todoController.serveTodoDelete(req);
				break;
			}
		} catch (NotLoggedInException e) {
			req.setRedirect("login?reason=notLoggedIn");
		}
	}

	@Override
	public void destroy() {
		logger.info("app shutdown");
		// nothing to do
		//
		// in real-world apps, we could close the database
		// connection (or the connection pool) here.
	}

}
