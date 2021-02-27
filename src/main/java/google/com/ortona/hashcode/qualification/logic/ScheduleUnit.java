package google.com.ortona.hashcode.qualification.logic;

public class ScheduleUnit {
	String street;
	int duration;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String toString() {
		return street+"["+duration+"]";
	}
	
	

}
