package utils;

import java.awt.Component;
import java.awt.Font;

public class changeFont {
	
	public void changefontItalic(Component ...components) {
		for (Component component : components) {
			component.setFont(new Font("–±ÃÂ", Font.ITALIC, 20));
		}
	}
	public void changefontBold(Component ...components) {
		for (Component component : components) {
			component.setFont(new Font("¥÷ÃÂ", Font.BOLD, 20));
		}
	}
}
