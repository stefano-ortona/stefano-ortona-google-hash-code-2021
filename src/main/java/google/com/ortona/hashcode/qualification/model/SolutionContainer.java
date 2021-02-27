package google.com.ortona.hashcode.qualification.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import google.com.ortona.hashcode.qualification.logic.IntersectionScheduler;
import google.com.ortona.hashcode.qualification.logic.ScheduleUnit;

public class SolutionContainer {
	
	IntersectionScheduler scheduler;

	public SolutionContainer(IntersectionScheduler scheduler) {
		this.scheduler = scheduler;
	}

	@Override
	public String toString() {
		String s = "";
		Map<Integer, List<ScheduleUnit>> filterSchedule = modifyScheduler(this.scheduler.getIntersection2schedules());
		s += filterSchedule.keySet().size();
		s += "\n";
		for(int id: filterSchedule.keySet()) {
			s += id + "\n";
			s += filterSchedule.get(id).size() + "\n";
			
			for(ScheduleUnit unit:filterSchedule.get(id)) {
				s += unit.getStreet() + " " + unit.getDuration() + "\n";
			}
		}
		return s;
	}
	
	private Map<Integer, List<ScheduleUnit>> modifyScheduler(Map<Integer, List<ScheduleUnit>> schedule){
		Map<Integer, List<ScheduleUnit>> newSchedule = new HashMap<>();
		for(int intersId:schedule.keySet()) {
			List<ScheduleUnit> newS = schedule.get(intersId).stream().filter(u -> u.getDuration() > 0).collect(Collectors.toList());
			if(newS.size() > 0) {
				newSchedule.put(intersId, newS);
			}
		}
		return newSchedule;
		
	}

	public int getScore() {
		return this.scheduler.getTotScore();
	}
}
