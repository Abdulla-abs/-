package propertyClass;

import java.sql.Date;

public class Complain {
	long id;
	int year;
	int month;
	Date date;
	String fromid;
	String content;
	String processorid;
	String reply;
	String issolve;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFromid() {
		return fromid;
	}
	public void setFromid(String fromid) {
		this.fromid = fromid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getProcessorid() {
		return processorid;
	}
	public void setProcessorid(String processorid) {
		this.processorid = processorid;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getIssolve() {
		return issolve;
	}
	public void setIssolve(String issolve) {
		this.issolve = issolve;
	}
}
