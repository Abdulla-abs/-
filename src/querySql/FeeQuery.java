package querySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import propertyClass.Fee;

public class FeeQuery {
	
	Connection con = null;
	PreparedStatement pStatement = null;
	
	public FeeQuery() {
		try {
			con = new Meta().getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//≤È∑—”√
	public List<Fee> feeQuery(String roomId) {
		List<Fee> roomidlist = new ArrayList<Fee>();
		ResultSet resultSet = null;
		try {
			pStatement = con.prepareStatement("SELECT `property_costs`,`rent`,`parking`,`water`,`place` FROM `fee` WHERE `roomid`=?");
			pStatement.setString(1, roomId);
			//pStatement.setString(2, userid);
			resultSet = pStatement.executeQuery();
			System.out.println(resultSet==null?"y":"f");
			//flag = true;
			while (resultSet.next()) {
				Fee fee = new Fee();
				fee.setProperty_costs(resultSet.getInt("property_costs"));
				fee.setRent(resultSet.getInt("rent"));
				fee.setParking(resultSet.getInt("parking"));
				fee.setWater(resultSet.getInt("water"));
				fee.setPlace(resultSet.getInt("place"));
				roomidlist.add(fee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roomidlist;
	}
}
