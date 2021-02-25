package google.com.ortona.hashcode.qualification.logic;

import java.util.ArrayList;
import java.util.List;

public class Car {
	int id;
	List<Street> path = new ArrayList<>();
	int curIndex = 0;
	public int nextStreetAvailableTime = 0;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Street> getPath() {
		return path;
	}
	public void setPath(List<Street> path) {
		this.path = path;
	}
	public int getCurIndex() {
		return curIndex;
	}
	public void setCurIndex(int curIndex) {
		this.curIndex = curIndex;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + curIndex;
		result = prime * result + id;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
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
		Car other = (Car) obj;
		if (curIndex != other.curIndex)
			return false;
		if (id != other.id)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}
	
	
	public String toString() {
		String s = id+", path: \n";
		for(final Street street : this.path){
			s += street.getName() + " ";
		}
		s += "\n";
		return s;
	}

}
