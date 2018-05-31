package org.webby.todo;

public abstract class Must {

	public static void beEqual(Object a, Object b, String msg) {
		if( a == null && b == null ) {
			return;
		}
		if( a == null || b == null ) {
			throw new RuntimeException(msg);
		}		
		if( ! a.equals(b) ) {
			throw new RuntimeException(msg);
		}		
	}
	
}
