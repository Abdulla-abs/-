package querySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import propertyClass.RoomId;
import utils.NamAndPasRoo;

public class RoomQuery {
	
	Connection con = null;
	PreparedStatement pStatement = null;
	
	boolean flag;
	
	public RoomQuery() {
		try {
			con = new Meta().getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*��id��ѯ���÷�id
	 * 
	 * ���� userid
	 * ��� idӵ�е�������roomid
	*/
	public List<RoomId> IdQuery(String userid) {
		List<RoomId> roomidlist = new ArrayList<RoomId>();
		ResultSet resultSet = null;
		try {
			pStatement = con.prepareStatement("SELECT `roomid`  FROM `room` WHERE `room`.`hirepeople`=?");
			pStatement.setString(1, userid);
			resultSet = pStatement.executeQuery();
			flag = true;
			while (resultSet.next()) {
				RoomId rId = new RoomId();
				rId.setRoomId(resultSet.getString("roomid"));
				roomidlist.add(rId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon();
		}
		return roomidlist;
	}
	
	/*��id��ѯ���÷�id
	 * 
	 * ���� userid
	 * ��� idӵ�е�������roomid �� room��Ӧ�Ĵ�¥����
	*/
	public ResultSet IdBelongQuery(String userid) {
		//List<RoomId> roomidlist = new ArrayList<RoomId>();
		ResultSet resultSet = null;
		try {
			pStatement = con.prepareStatement("SELECT `roomid`,`buildingname` FROM `buidingall`,`room` WHERE `buildingid`= ANY (SELECT `belongbuilding` FROM `room` WHERE `hirepeople`=?) AND `roomid` = ANY(SELECT `roomid`  FROM `room` WHERE `room`.`hirepeople`=?)");
			pStatement.setString(1, userid);
			pStatement.setString(2, userid);
			resultSet = pStatement.executeQuery();
			System.out.println(resultSet==null?"y":"f");
			flag = true;
//			while (resultSet.next()) {
//				RoomId rId = new RoomId();
//				rId.setRoomId(resultSet.getString("roomid"));
//				rId.setRoombelong(resultSet.getString("buildingname"));
//				roomidlist.add(rId);
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/*��id��ѯ���÷�id
	 * 
	 * ���� userid
	 * ��� idӵ�е���ӵ��roomid �� room��Ӧ�Ĵ�¥����
	*/
	public List<RoomId> IdOwenrQuery(String userid) {
		List<RoomId> roomidlist = new ArrayList<RoomId>();
		ResultSet resultSet = null;
		try {
			pStatement = con.prepareStatement("SELECT `roomid`,`buildingname`,`height` FROM `buidingall`,`room` WHERE `buildingid`= ANY (SELECT `belongbuilding` FROM `room` WHERE `owenr`=?) AND `roomid` = ANY(SELECT `roomid`  FROM `room` WHERE `room`.`owenr`=?)");
			pStatement.setString(1, userid);
			pStatement.setString(2, userid);
			resultSet = pStatement.executeQuery();
			System.out.println(resultSet==null?"y":"f");
			flag = true;
			while (resultSet.next()) {
				RoomId rId = new RoomId();
				rId.setRoomId(resultSet.getString("roomid"));
				rId.setRoombelong(resultSet.getString("buildingname"));
				rId.setHeight(resultSet.getInt("height"));
				roomidlist.add(rId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon();
		}
		return roomidlist;
	}
	
	//��ѯ���г���ķ���
	public List<RoomId> hireRoomQuery(int limite) {
		List<RoomId> roomidlist = new ArrayList<RoomId>();
		ResultSet resultSet = null;
		StringBuffer sql = new StringBuffer("SELECT `roomid`,`buildingname`,`height` FROM `room`,`buidingall` WHERE `belongbuilding` = ANY (SELECT `belongbuilding` FROM `room`) AND `wannerhire` = ?"); 
		switch (limite) {
		case 0: {
			break;
			}
		case 1: {
			sql.append(" HAVING `height` = 1");
			break;
			}
		case 2: {
			sql.append(" HAVING `height` = 2");
			break;
			}
		}
		try {
			pStatement = con.prepareStatement(sql.toString());
			pStatement.setString(1, "��");
//			pStatement.setString(2, userid);
			resultSet = pStatement.executeQuery();
			System.out.println(resultSet==null?"y":"f");
			flag = true;
			while (resultSet.next()) {
				RoomId rId = new RoomId();
				rId.setRoomId(resultSet.getString("roomid"));
				rId.setRoombelong(resultSet.getString("buildingname"));
				rId.setHeight(resultSet.getInt("height"));
				roomidlist.add(rId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roomidlist;
	}
	
	//��ѯ���еķ��� ʹ��¥�����Ʋ�ѯ
	public List<RoomId> backHireQuery(int limite) {
		List<RoomId> roomidlist = new ArrayList<RoomId>();
		ResultSet resultSet = null;
		StringBuffer sql = new StringBuffer("SELECT `roomid`,`buildingname`,`height` FROM `buidingall`,`room` WHERE `buildingid`= ANY (SELECT `belongbuilding` FROM `room` WHERE `hirepeople`=?) AND`roomid` = ANY(SELECT `roomid`  FROM `room` WHERE `room`.`hirepeople`= ?)"); 
		switch (limite) {
		case 0: {
			break;
			}
		case 1: {
			sql.append(" HAVING `height` = 1");
			break;
			}
		case 2: {
			sql.append(" HAVING `height` = 2");
			break;
			}
		}
		try {
			pStatement = con.prepareStatement(sql.toString());
			pStatement.setString(1, NamAndPasRoo.userid);
			pStatement.setString(2, NamAndPasRoo.userid);
			resultSet = pStatement.executeQuery();
			System.out.println(resultSet==null?"y":"f");
			flag = true;
			while (resultSet.next()) {
				RoomId rId = new RoomId();
				rId.setRoomId(resultSet.getString("roomid"));
				rId.setRoombelong(resultSet.getString("buildingname"));
				rId.setHeight(resultSet.getInt("height"));
				roomidlist.add(rId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roomidlist;
	}
	
	//��������
	public List<RoomId> saleRoomQuery(int limite) {
		List<RoomId> roomidlist = new ArrayList<RoomId>();
		ResultSet resultSet = null;
		StringBuffer sql = new StringBuffer("SELECT `roomid`,`buildingname`,`height` FROM `room`,`buidingall` WHERE `wannersale`=\"��\""); 
		switch (limite) {
		case 0: {
			break;
			}
		case 1: {
			sql.append(" HAVING `height` = 1");
			break;
			}
		case 2: {
			sql.append(" HAVING `height` = 2");
			break;
			}
		}
		try {
			pStatement = con.prepareStatement(sql.toString());
			//pStatement.setString(1, NamAndPasRoo.userid);
			//pStatement.setString(2, NamAndPasRoo.userid);
			resultSet = pStatement.executeQuery();
			System.out.println(resultSet==null?"y":"f");
			flag = true;
			while (resultSet.next()) {
				RoomId rId = new RoomId();
				rId.setRoomId(resultSet.getString("roomid"));
				rId.setRoombelong(resultSet.getString("buildingname"));
				rId.setHeight(resultSet.getInt("height"));
				roomidlist.add(rId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roomidlist;
	}
	
	//id�����з���ӵ�з�
		public List<RoomId> hireAndOwenQuery(int limite) {
			List<RoomId> roomidlist = new ArrayList<RoomId>();
			ResultSet resultSet = null;
			StringBuffer sql = new StringBuffer("SELECT `roomid`,`buildingname`,`height` FROM `room`,`buidingall` WHERE `owenr`=? OR `hirepeople`=?"); 
			switch (limite) {
			case 0: {
				break;
				}
			case 1: {
				sql.append(" HAVING `height` = 1");
				break;
				}
			case 2: {
				sql.append(" HAVING `height` = 2");
				break;
				}
			}
			try {
				pStatement = con.prepareStatement(sql.toString());
				pStatement.setString(1, NamAndPasRoo.userid);
				pStatement.setString(2, NamAndPasRoo.userid);
				resultSet = pStatement.executeQuery();
				System.out.println(resultSet==null?"y":"f");
				flag = true;
				while (resultSet.next()) {
					RoomId rId = new RoomId();
					rId.setRoomId(resultSet.getString("roomid"));
					rId.setRoombelong(resultSet.getString("buildingname"));
					rId.setHeight(resultSet.getInt("height"));
					roomidlist.add(rId);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return roomidlist;
		}
	
	public void closeCon() {
		try {
			if(flag == false) {
				//���flag == 0 ��ʾû�е����κ���ɾ����е��κη�������ֱ�ӹر�Connection
				con.close();
			}
			if(flag == true) {
				//���flag == 1 ��ʾ��������ɾ����е�ĳһ����������Ҫ�ر�Connection��PreparedStatement
				pStatement.close();
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
