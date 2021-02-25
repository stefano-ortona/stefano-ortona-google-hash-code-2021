package google.com.ortona.hashcode.qualification.logic;

import java.util.List;

public class Street {
	public String name;
	public int length;
	public List<Car> currentCars;
	public int maxCarsWaiting = 0;
	public int totalCarsWaiting = 0;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public List<Car> getCurrentCars() {
		return currentCars;
	}
	public void setCurrentCars(List<Car> currentCars) {
		this.currentCars = currentCars;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Street other = (Street) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
