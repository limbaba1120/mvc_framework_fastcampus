package org.example.food;

import java.util.List;

public class Menu {

	private final List<MenuItem> menuItem;

	public <E> Menu(List<MenuItem> menuItem) {
		this.menuItem = menuItem;
	}

	public MenuItem choose(String name) {
		return this.menuItem.stream()
			.filter(menuItem -> menuItem.matches(name))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못된 메뉴입니다."));
	}
}
