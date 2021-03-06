package google.com.ortona.hashcode.qualification.logic;

import java.util.List;
import java.util.Map;

import google.com.ortona.hashcode.qualification.model.ProblemContainer;
import google.com.ortona.hashcode.qualification.model.ScheduleInitaliser;

public class IntersectionScheduler {
	Map<Integer, List<ScheduleUnit>> intersection2schedules;
	public int totScore = 0;


	public IntersectionScheduler(ProblemContainer pC) {
		intersection2schedules = new ScheduleInitaliser().initalise(pC);
	}

	public boolean isGreen(Intersection inter, String streetName, int time) {
		int allIntersectionPeriod = this.intersection2schedules.get(inter.id).stream().mapToInt(s -> s.duration).sum();
		if(allIntersectionPeriod == 0) {
			//never a green
			return false;
		}
		int mod = time % allIntersectionPeriod;
		
		for(ScheduleUnit unit:this.intersection2schedules.get(inter.id)) {
			mod -= unit.duration;
			if(mod < 0) {
				return unit.street.equals(streetName);
			}
		}
		return false;
	}
	
	public Map<Integer, List<ScheduleUnit>> getIntersection2schedules() {
		return this.intersection2schedules;
	}
	
	public int getTotScore() {
		return this.totScore;
	}
}
