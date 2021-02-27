package google.com.ortona.hashcode.qualification.logic;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.qualification.model.ProblemContainer;
import google.com.ortona.hashcode.qualification.model.SolutionContainer;

public class ProblemSolver {
	private static Logger LOG = LoggerFactory.getLogger(ProblemSolver.class);

	IntersectionScheduler scheduler;
	Map<Integer, IntersectionScheduler> score2scheduler = new HashMap<>();

	final int iteration = 100;

	SimpleScheduleModifier modifier = new SimpleScheduleModifier();
	
	private final static double MAX_POSSIBLE_TIME = 60.0*20; //2 minutes


	public SolutionContainer solve(ProblemContainer problem) {
		scheduler = new IntersectionScheduler(problem);

		long start = System.currentTimeMillis();
		for(int i = 0; i < iteration; i++) {
			LOG.info("Starting iteration {}...",i);
			performIteration(problem);
			double passedSeconds = (System.currentTimeMillis()-start)/1000.0;
			if(passedSeconds > MAX_POSSIBLE_TIME) {
				break;
			}
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
		pC = pC.reset();

		for(int i = 0; i < pC.time; i++) {
			for(Intersection inter:pC.intersections) {
				for(Street street:inter.getIncomingStreets()) {
					performAction(pC, street, inter, i);
				}
			}
		}

		LOG.info("New iteration with score: {}", scheduler.totScore);

		score2scheduler.put(scheduler.totScore, scheduler);
		scheduler = modifyScheduler(pC);		
	}

	private void performAction(ProblemContainer pC, Street street, Intersection inter, int time) {
		if(scheduler.isGreen(inter, street.getName(), time) && !street.currentCars.isEmpty() && street.currentCars.get(0).nextStreetAvailableTime <= time) {
			Car c = street.currentCars.remove(0);
			c.curIndex++;
			int newTime = time + c.path.get(c.curIndex).length;
			if(c.curIndex >= c.path.size() - 1) {
				if(newTime <= pC.time) {
					this.scheduler.totScore += pC.carBonus + pC.time - newTime;
				}
			}
			else {
				c.path.get(c.curIndex).currentCars.add(c);
				c.path.get(c.curIndex).totCarsPassed++;
				c.nextStreetAvailableTime = newTime;
			}

		} else {
			//street.maxCarsWaiting = Math.max(street.maxCarsWaiting, street.currentCars.size());
			street.totalCarsWaiting += street.currentCars.stream().filter(c -> c.nextStreetAvailableTime <= time).count();
		}
	}

	private IntersectionScheduler modifyScheduler(ProblemContainer pC) {
		return this.modifier.modify(pC, scheduler);

	}

	public static void main(String[] args) {
		LOG.info("Hello World!");
	}

}
