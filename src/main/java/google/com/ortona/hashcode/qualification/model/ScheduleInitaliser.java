package google.com.ortona.hashcode.qualification.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import google.com.ortona.hashcode.qualification.logic.Intersection;
import google.com.ortona.hashcode.qualification.logic.ScheduleUnit;
import google.com.ortona.hashcode.qualification.logic.Street;

public class ScheduleInitaliser {
	
	private final static int INITIAL_VAUE = 1;
	
	public Map<Integer, List<ScheduleUnit>> initalise(ProblemContainer pC) {
		Map<Integer, List<ScheduleUnit>> finalMap = new HashMap<>();
		for(Intersection inter:pC.intersections) {
			for(Street s:inter.incomingStreets) {
				ScheduleUnit sU = new ScheduleUnit();
				sU.setStreet(s);
				sU.setDuration(INITIAL_VAUE);
			}
		}
		return finalMap;
	}

}
