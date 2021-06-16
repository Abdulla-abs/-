package querySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.NamAndPasRoo;

public class HireRoom {
	Connection con = null;
	PreparedStatement pStatement = null;
	public HireRoom() {
		try {
			con = new Meta().getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* ʹ��id�ⷿ */
	public boolean idHireRoom(String id) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			pStatement = con.prepareStatement("UPDATE `room` SET `hirepeople` = ?,`wannerhire`= \"��\" WHERE `roomid`=?");
			pStatement.setString(1, NamAndPasRoo.userid);
			pStatement.setString(2, id);
			result = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result!=0) {
			return true;
		}else {
			return false;
		}
	}
	
	/* ʹ�÷���id�˷� */
	public boolean idBackHireRoom(String roomId) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			pStatement = con.prepareStatement("UPDATE `room` SET `hirepeople` = NULL,`wannerhire`=\"��\" WHERE `roomid`=? AND `hirepeople`= ?");
			pStatement.setString(1, roomId);
			pStatement.setString(2, NamAndPasRoo.userid);
			result = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result!=0) {
			return true;
		}else {
			return false;
		}
	}
	
	/* ʹ�÷���id�� */
	public boolean idBuyRoom(String roomId) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			pStatement = con.prepareStatement("UPDATE `room` SET `owenr`=?,`wannersale`=\"��\",`hirepeople`=NULL WHERE `roomid` = ?");
			pStatement.setString(1, NamAndPasRoo.userid);
			pStatement.setString(2, roomId);
			result = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result!=0) {
			return true;
		}else {
			return false;
		}
	}
}
