package querySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import propertyClass.Area;
import propertyClass.Complain;

public class AreaMassageQuery {
	Connection con = null;
	PreparedStatement pStatement = null;

	public AreaMassageQuery() {
		try {
			con = new Meta().getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//查小区信息
	public List<Area> areaQuery() {
		List<Area> complainslist = new ArrayList<Area>();
		ResultSet resultSet = null;
		try {
			pStatement = con.prepareStatement("SELECT * FROM `areamassage` WHERE `areaid`=\"001\"");
			resultSet = pStatement.executeQuery();
			System.out.println(resultSet == null ? "y" : "f");
			while (resultSet.next()) {
				Area area = new Area();
				area.setAreaid(resultSet.getString("areaid"));
				area.setName(resultSet.getString("name"));
				area.setHavingall(resultSet.getInt("havingall"));
				area.setBuilding(resultSet.getInt("building"));
				area.setVilla(resultSet.getInt("villa"));
				complainslist.add(area);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return complainslist;
	}
	
	//改小区信息
	public boolean changeAreaQuery(String areaid,String name,int havingall,int building,int villa) {
		ResultSet resultSet = null;
		try {
			pStatement = con.prepareStatement("UPDATE `areamassage` SET `name`=?,`havingall`=?,`building`=?,`villa`=? WHERE `areaid`=?");
			//pStatement.setString(1, areaid);
			pStatement.setString(1, name);
			pStatement.setInt(2, havingall);
			pStatement.setInt(3, building);
			pStatement.setInt(4, villa);
			pStatement.setString(5, areaid);
			boolean flag = pStatement.execute();
			if (flag) {
				return true;
			}else {
				return false;
			}
			//System.out.println(resultSet == null ? "y" : "f");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
