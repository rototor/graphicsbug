package de.resolveit.graphicsbug;

import org.apache.poi.ss.usermodel.Sheet;

public class TestIdeaVarargMessage {

	static class BaseB extends BaseA {
	}

	public static class C extends BaseB {
		void doSomething() {
			Sheet dummy = null;
			deleteColumns(dummy, "A", "B", "C");
		}
	}

	public static void main(String[] args) {
		new C().doSomething();
	}
}
