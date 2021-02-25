package google.com.ortona.hashcode.qualification.model;

import google.com.ortona.hashcode.qualification.logic.IntersectionScheduler;

public class SolutionContainer {
	
	IntersectionScheduler scheduler;


	public SolutionContainer(IntersectionScheduler scheduler) {
		this.scheduler = scheduler;
	}

	@Override
	public String toString() {
		return "";
	}

	public int getScore() {
		return 0;
	}
}
