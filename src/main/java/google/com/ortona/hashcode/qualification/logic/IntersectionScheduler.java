package google.com.ortona.hashcode.qualification.logic;

import java.util.List;
import java.util.Map;

import google.com.ortona.hashcode.qualification.model.ProblemContainer;
import google.com.ortona.hashcode.qualification.model.ScheduleInitaliser;

public class IntersectionScheduler {
	Map<Integer, List<ScheduleUnit>> intersection2schedules;
	
	
	public IntersectionScheduler(ProblemContainer pC) {
		intersection2schedules = new ScheduleInitaliser().initalise(pC);
	}

}
