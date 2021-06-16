package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class check {
	public boolean checkString(String cheakString) {
		// TODO Auto-generated constructor stub
		String regex = ".*[a-zA-Z]+.*";
		Matcher m = null;
		if ("".equals(cheakString)) {
			return true;
		} else {
			m = Pattern.compile(regex).matcher(cheakString);
			return m.matches();
		}

	}

//	@SuppressWarnings("serial")
//	class contian extends JFrame{
//		public contian() {
//			// TODO Auto-generated constructor stub
//			//add(jDialog);
//			setSize(250, 200);
//			setLocationRelativeTo(null);
//			setVisible(true);
//		}
//	}
}
