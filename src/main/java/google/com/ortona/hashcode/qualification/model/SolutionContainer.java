package google.com.ortona.hashcode.qualification.model;

import google.com.ortona.hashcode.qualification.logic.Intersection;
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
		s += this.scheduler.getIntersection2schedules().keySet().size();
		s += "\n";
		for(int id: this.scheduler.getIntersection2schedules().keySet()) {
			s += id + "\n";
			s += this.scheduler.getIntersection2schedules().get(id).size() + "\n";
			
			for(ScheduleUnit unit:this.scheduler.getIntersection2schedules().get(id)) {
				s += unit.getStreet().getName() + " " + unit.getDuration() + "\n";
			}
		}
		return s;
	}

	public int getScore() {
		return this.scheduler.getTotScore();
	}
}
