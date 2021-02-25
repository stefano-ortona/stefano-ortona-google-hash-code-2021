package google.com.ortona.hashcode.qualification.logic;

import java.util.List;

public class Intersection {
	public List<Street> incomingStreets;
	int id;
	public List<Street> getIncomingStreets() {
		return incomingStreets;
	}
	public void setIncomingStreets(List<Street> incomingStreets) {
		this.incomingStreets = incomingStreets;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Intersection other = (Intersection) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	

}
