package querySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import propertyClass.Complain;
import utils.NamAndPasRoo;

public class complainQuery {
	Connection con = null;
	PreparedStatement pStatement = null;

	public complainQuery() {
		try {
			con = new Meta().getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 提交投诉
	public boolean submitComplain(String text) {
		boolean flag = false;
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String date = String.valueOf(calendar.get(Calendar.DATE));
		String cont = year + "-" + month + "-" + date;
		try {
			pStatement = con
					.prepareStatement("INSERT `complain` SET `year`=?,`month`=?,`date`=?,`fromid`=?,`content`=?");
			pStatement.setString(1, year);
			pStatement.setString(2, month);
			pStatement.setString(3, cont);
			pStatement.setString(4, NamAndPasRoo.userid);
			pStatement.setString(5, text);
			flag = pStatement.execute();
			// System.out.println(resultSet==null?"y":"f");
			 flag = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	// 获取所有投诉
	public List<Complain> allComplainQuery() {
		List<Complain> complainslist = new ArrayList<Complain>();
		ResultSet resultSet = null;
		try {
			pStatement = con.prepareStatement("SELECT * FROM `complain`");
			// pStatement.setString(1, roomId);
			// pStatement.setString(2, userid);
			resultSet = pStatement.executeQuery();
			System.out.println(resultSet == null ? "y" : "f");
			// flag = true;
			while (resultSet.next()) {
				Complain complain = new Complain();
				complain.setId(resultSet.getLong("id"));
				complain.setYear(resultSet.getInt("year"));
				complain.setMonth(resultSet.getInt("month"));
				complain.setDate(resultSet.getDate("date"));
				complain.setFromid(resultSet.getString("fromid"));
				complain.setContent(resultSet.getString("content"));
				complain.setProcessorid(resultSet.getString("processorid"));
				complain.setReply(resultSet.getString("reply"));
				complain.setIssolve(resultSet.getString("issolve"));
				complainslist.add(complain);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return complainslist;
	}

	// 根据id查历史投诉
	public List<Complain> idComplainQuery() {
		List<Complain> complainslist = new ArrayList<Complain>();
		ResultSet resultSet = null;
		try {
			pStatement = con.prepareStatement("SELECT * FROM `complain` WHERE `fromid`=?");
			pStatement.setString(1, NamAndPasRoo.userid);
			// pStatement.setString(2, userid);
			resultSet = pStatement.executeQuery();
			System.out.println(resultSet == null ? "y" : "f");
			while (resultSet.next()) {
				Complain complain = new Complain();
				complain.setId(resultSet.getLong("id"));
				complain.setYear(resultSet.getInt("year"));
				complain.setMonth(resultSet.getInt("month"));
				complain.setDate(resultSet.getDate("date"));
				complain.setFromid(resultSet.getString("fromid"));
				complain.setContent(resultSet.getString("content"));
				complain.setProcessorid(resultSet.getString("processorid"));
				complain.setReply(resultSet.getString("reply"));
				complain.setIssolve(resultSet.getString("issolve"));
				complainslist.add(complain);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return complainslist;
	}

	// 根据事件id查投诉
	public List<Complain> idQueryComplain(long id) {
		List<Complain> complainslist = new ArrayList<Complain>();
		ResultSet resultSet = null;
		try {
			pStatement = con.prepareStatement("SELECT * FROM `complain` WHERE `id`=?");
			pStatement.setLong(1, id);
			// pStatement.setString(2, userid);
			resultSet = pStatement.executeQuery();
			System.out.println(resultSet == null ? "y" : "f");
			while (resultSet.next()) {
				Complain complain = new Complain();
				complain.setId(resultSet.getLong("id"));
				complain.setYear(resultSet.getInt("year"));
				complain.setMonth(resultSet.getInt("month"));
				complain.setDate(resultSet.getDate("date"));
				complain.setFromid(resultSet.getString("fromid"));
				complain.setContent(resultSet.getString("content"));
				complain.setProcessorid(resultSet.getString("processorid"));
				complain.setReply(resultSet.getString("reply"));
				complain.setIssolve(resultSet.getString("issolve"));
				complainslist.add(complain);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return complainslist;
	}
	//查未通过的投诉
	public List<Complain> noPassComplainQuery() {
		List<Complain> complainslist = new ArrayList<Complain>();
		ResultSet resultSet = null;
		try {
			pStatement = con.prepareStatement("SELECT * FROM `complain` WHERE `issolve`=\"否\"");
			// pStatement.setString(1, roomId);
			// pStatement.setString(2, userid);
			resultSet = pStatement.executeQuery();
			System.out.println(resultSet == null ? "y" : "f");
			// flag = true;
			while (resultSet.next()) {
				Complain complain = new Complain();
				complain.setId(resultSet.getLong("id"));
				complain.setYear(resultSet.getInt("year"));
				complain.setMonth(resultSet.getInt("month"));
				complain.setDate(resultSet.getDate("date"));
				complain.setFromid(resultSet.getString("fromid"));
				complain.setContent(resultSet.getString("content"));
				complain.setProcessorid(resultSet.getString("processorid"));
				complain.setReply(resultSet.getString("reply"));
				complain.setIssolve(resultSet.getString("issolve"));
				complainslist.add(complain);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return complainslist;
	}
	//通过投诉
	public boolean submitComplainQuery(String reply,int id) {
		int resultSet = 0;
		try {
			pStatement = con.prepareStatement("UPDATE `complain` SET `reply`=?,`issolve`=\"是\" WHERE `id`=?");
			 pStatement.setString(1, reply);
			 pStatement.setInt(2, id);
			resultSet = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (resultSet >0) {
			return true;
		}else {
			return false;
		}
	}
	//根据年月查（已通过的）投诉
	public List<Complain> dateQueryPassComplains(int year,int month) {
		List<Complain> complainslist = new ArrayList<Complain>();
		ResultSet resultSet = null;
		try {
			pStatement = con.prepareStatement("SELECT * FROM `complain` WHERE `year`=? AND `month`=? HAVING `issolve` = \"是\"");
			 pStatement.setInt(1, year);
			 pStatement.setInt(2, month);
			resultSet = pStatement.executeQuery();
			System.out.println(resultSet == null ? "y" : "f");
			// flag = true;
			while (resultSet.next()) {
				Complain complain = new Complain();
				complain.setId(resultSet.getLong("id"));
				complain.setYear(resultSet.getInt("year"));
				complain.setMonth(resultSet.getInt("month"));
				complain.setDate(resultSet.getDate("date"));
				complain.setFromid(resultSet.getString("fromid"));
				complain.setContent(resultSet.getString("content"));
				complain.setProcessorid(resultSet.getString("processorid"));
				complain.setReply(resultSet.getString("reply"));
				complain.setIssolve(resultSet.getString("issolve"));
				complainslist.add(complain);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return complainslist;
	}
}
