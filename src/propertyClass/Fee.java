package propertyClass;

public class Fee {
	String roomid;
	int property_costs;
	int rent;
	int parking;
	int water;
	int place;
	public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	public int getProperty_costs() {
		return property_costs;
	}
	public void setProperty_costs(int property_costs) {
		this.property_costs = property_costs;
	}
	public int getRent() {
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	public int getParking() {
		return parking;
	}
	public void setParking(int parking) {
		this.parking = parking;
	}
	public int getWater() {
		return water;
	}
	public void setWater(int water) {
		this.water = water;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	
	public double[] feeCount() {
		// TODO Auto-generated method stub
		double[] count = new double[1];
		count[0] = this.property_costs+this.rent+this.parking+this.place+this.water;
		return count;
	}
}
