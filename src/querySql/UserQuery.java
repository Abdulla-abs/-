package querySql;

import java.sql.*;
//import querySql.Meta;

public class UserQuery {
	Connection con = null;
	PreparedStatement pStatement = null;
	
	public UserQuery() {
		try {
			con = new Meta().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//µÇÂ½Ê¹ÓÃ
	public ResultSet login(String id,String password){
		ResultSet result = null;
		try {
			pStatement = con.prepareStatement("SELECT `username`,`userid`,`password`,`rootid`,`sex` FROM `user` WHERE `userid`=? AND `password`=?");
			pStatement.setString(1, id);
			pStatement.setString(2, password);
			result = pStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return result;		
	}
	
	public ResultSet queryPersonal(String id){
		ResultSet result = null;
		try {
			pStatement = con.prepareStatement("SELECT * FROM `user` WHERE `userid`=?");
			pStatement.setString(1, id);
			result = pStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return result;		
	}
	
}
