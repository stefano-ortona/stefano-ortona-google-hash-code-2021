package google.com.ortona.hashcode.qualification.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import google.com.ortona.hashcode.qualification.model.ProblemContainer;

public class SimpleScheduleModifier {
	
	private static final int MAX_TIME = 10;
	
	
	public IntersectionScheduler modify(ProblemContainer pC, IntersectionScheduler scheduler) {
		IntersectionScheduler newScheduler = new IntersectionScheduler(pC);
		
		
		for(int intersId:scheduler.intersection2schedules.keySet()){
			List<ScheduleUnit> units = scheduler.intersection2schedules.get(intersId);
			List<ScheduleUnit> newUnits = cloneUnits(units);
			
			int maxValue = newUnits.stream().mapToInt(u -> (int) Math.ceil(u.street.totalCarsWaiting / pC.time)).max().getAsInt();
			
			for(ScheduleUnit oneUnit:newUnits) {
				int newValue = (int) Math.ceil((Math.ceil(oneUnit.street.totalCarsWaiting / pC.time) / maxValue) * MAX_TIME);
				oneUnit.duration = newValue;
			}
			newScheduler.intersection2schedules.put(intersId, newUnits);
		}
		
		
		return newScheduler;
	}
	
	private List<ScheduleUnit> cloneUnits(List<ScheduleUnit> units){
		List<ScheduleUnit> newUnits = new ArrayList<>();
		for(ScheduleUnit sU: units) {
			ScheduleUnit newUnit = new ScheduleUnit();
			newUnit.street = sU.street;
			newUnit.duration = sU.duration;
		}
		return newUnits;
	}

}
