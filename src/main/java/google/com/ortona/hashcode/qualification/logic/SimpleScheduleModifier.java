package google.com.ortona.hashcode.qualification.logic;

import java.util.ArrayList;
import java.util.List;

import google.com.ortona.hashcode.qualification.model.ProblemContainer;

public class SimpleScheduleModifier {

	private static final int MAX_TIME = 5;


	public IntersectionScheduler modify(ProblemContainer pC, IntersectionScheduler scheduler) {
		IntersectionScheduler newScheduler = new IntersectionScheduler(pC);


		for(int intersId:scheduler.intersection2schedules.keySet()){
			List<ScheduleUnit> units = scheduler.intersection2schedules.get(intersId);
			List<ScheduleUnit> newUnits = cloneUnits(units);

			int maxValue = units.stream().mapToInt(u -> (int) Math.ceil(pC.getStreet(u.street).totalCarsWaiting / (pC.time * 1.0))).max().getAsInt();

			for(ScheduleUnit oneUnit:newUnits) {
				Street s = pC.getStreet(oneUnit.street);
				if(s.totalCarsWaiting == 0) {
					oneUnit.duration = s.totCarsPassed > 0 ? 1 : 0;
				}
				else {
					int newValue = (int) Math.ceil((Math.ceil(s.totalCarsWaiting / (pC.time * 1.0)) / maxValue) * MAX_TIME);
					oneUnit.duration = newValue;
				}
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
			newUnits.add(newUnit);
		}
		return newUnits;
	}

}
