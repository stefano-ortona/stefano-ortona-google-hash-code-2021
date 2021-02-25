package google.com.ortona.hashcode.qualification.logic;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.qualification.model.ProblemContainer;
import google.com.ortona.hashcode.qualification.model.SolutionContainer;

public class ProblemSolver {
	private static Logger LOG = LoggerFactory.getLogger(ProblemSolver.class);

	IntersectionScheduler scheduler;
	Map<Integer, IntersectionScheduler> score2scheduler;
	
	final int iteration = 100;
	
	SimpleScheduleModifier modifier = new SimpleScheduleModifier();


	public SolutionContainer solve(ProblemContainer problem) {
		scheduler = new IntersectionScheduler(problem);
		
		for(int i = 0; i < iteration; i++) {
			performIteration(problem);
		}
		
		int bestScore = -1;
		IntersectionScheduler bestScheduler = null;
		for(int score: score2scheduler.keySet()) {
			if(score >= bestScore) {
				bestScore = score;
				bestScheduler = score2scheduler.get(score);
			}
			
		}
		
		return new SolutionContainer(bestScheduler);
	}
	
	
	private void performIteration(ProblemContainer pC) {
		pC.reset();
		
		for(int i = 0; i < pC.time; i++) {
			for(Intersection inter:pC.intersections) {
				for(Street street:inter.getIncomingStreets()) {
					performAction(pC, street, inter, i);
				}
			}
		}
		
		score2scheduler.put(scheduler.totScore, scheduler);
		scheduler = modifyScheduler(pC);		
	}
	
	private void performAction(ProblemContainer pC, Street street, Intersection inter, int time) {
		if(scheduler.isGreen(inter, street, time) && !street.currentCars.isEmpty() && street.currentCars.get(0).nextStreetAvailableTime >= time) {
			Car c = street.currentCars.remove(0);
			if(c.curIndex >= c.path.size()) {
				this.scheduler.totScore += pC.carBonus + pC.time - time;
			}
			else {
				c.curIndex++;
				c.path.get(c.curIndex).currentCars.add(c);
				c.nextStreetAvailableTime += c.path.get(c.curIndex).length;
			}
			
		} else {
			street.maxCarsWaiting = Math.max(street.maxCarsWaiting, street.currentCars.size());
			street.totalCarsWaiting += street.currentCars.size();
		}
	}
	
	private IntersectionScheduler modifyScheduler(ProblemContainer pC) {
		return this.modifier.modify(pC, scheduler);
		
	}

	public static void main(String[] args) {
		LOG.info("Hello World!");
	}

}
