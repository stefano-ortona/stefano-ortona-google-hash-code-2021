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
	public int time;
	public int carBonus;

	public ProblemContainer(List<Street> streets, List<Intersection> intersections, List<Car> cars, int time, int carBonus) {
		this.streets = streets;
		this.intersections = intersections;
		this.cars = cars;
		this.time = time;
		this.carBonus = carBonus;
	}

	@Override
	public String toString() {
		String s = "streets: ";
		for(final Street street : this.streets){
			s += street.getName() + " ";
		}
		s += "\n intersections: \n";
		for(final Intersection intersection : this.intersections){
			s += intersection.toString() + " ";
		}
		s += "\n cars: ";
		for(final Car car : this.cars){
			s += car.toString() + " ";
		}
		s += "\n time: " + this.time;
		s += "\n carBonus: " + this.carBonus;
		return s;
	}
	
	public void reset() {
		
	}
	
}
