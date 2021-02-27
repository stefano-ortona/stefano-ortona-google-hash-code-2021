package google.com.ortona.hashcode.qualification.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import google.com.ortona.hashcode.qualification.io.ProblemReader;
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
	private Map<String, Street> name2street;

	public ProblemContainer(List<Street> streets, List<Intersection> intersections, List<Car> cars, int time, int carBonus) {
		this.streets = streets;
		this.intersections = intersections;
		this.cars = cars;
		this.time = time;
		this.carBonus = carBonus;
		this.name2street = new HashMap<>();
		streets.forEach( s-> name2street.put(s.getName(), s));
	}
	
	public Street getStreet(String name) {
		return this.name2street.get(name);
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
	
	public ProblemContainer reset() {
		return ProblemReader.readProblemForReset();
	}
	
}
