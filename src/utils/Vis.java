package utils;

import java.awt.Component;

public class Vis {
	public Vis(Component ...components) {
		for (Component component : components) {
			component.setVisible(true);
		}
	}
}
