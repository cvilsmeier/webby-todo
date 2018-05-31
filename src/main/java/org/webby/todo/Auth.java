package org.webby.todo;

import org.webby.WebRequest;

public abstract class Auth {

	public static void putPrincipal(WebRequest req, Principal principal) {
		req.putSession("principal", principal);
	}

	public static Principal findPrincipal(WebRequest req) {
		return (Principal) req.getSession("principal", null);
	}

	public static Principal needPrincipal(WebRequest req) throws NotLoggedInException {
		Principal p = findPrincipal(req);
		if (p == null) {
			throw new NotLoggedInException();
		}
		return p;
	}
}
