package google.com.ortona.hashcode.qualification.model;

import java.util.List;

import google.com.ortona.hashcode.qualification.logic.Car;
import google.com.ortona.hashcode.qualification.logic.Intersection;
import google.com.ortona.hashcode.qualification.logic.IntersectionScheduler;
import google.com.ortona.hashcode.qualification.logic.Street;

public class ProblemContainer {
	
	public List<Street> streets;
	public List<Intersection> intersections;
	public List<Car> cars;
	int time;

	public ProblemContainer(List<Street> streets, List<Intersection> interesctions, List<Car> cars, int time) {
	}

	@Override
	public String toString() {
		return "";
	}
	
	public void reset() {
		
	}
	
}
