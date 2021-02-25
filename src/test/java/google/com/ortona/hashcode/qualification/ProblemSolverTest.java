package google.com.ortona.hashcode.qualification;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.qualification.io.ProblemReader;
import google.com.ortona.hashcode.qualification.io.ProblemWriter;
import google.com.ortona.hashcode.qualification.logic.ProblemSolver;
import google.com.ortona.hashcode.qualification.model.ProblemContainer;
import google.com.ortona.hashcode.qualification.model.SolutionContainer;
/**
 *
 */
public class ProblemSolverTest {

	private final static ProblemSolver SOLVER = new ProblemSolver();
	private final static ProblemReader READER = new ProblemReader();
	private final static ProblemWriter WRITER = new ProblemWriter();

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Test
	public void firstTest() {
		LOG.info("----------------------");
		LOG.info("First test is starting");
		Assert.assertTrue(true);
		
	}

	@Test
	public void testFirstInput() throws IOException {
		//final ProblemContainer p = READER.readProblem("a_example");
		final ProblemContainer p = READER.readProblem("b_little_bit_of_everything.in");
		//final ProblemContainer p = READER.readProblem("c_many_ingredients.in");
		//final ProblemContainer p = READER.readProblem("d_many_pizzas.in");
		//final ProblemContainer p = READER.readProblem("e_many_teams.in");

		LOG.info(p.toString());

		final SolutionContainer sC = SOLVER.solve(p);
		WRITER.writeProblem("output_file", sC);
		LOG.info("test1 solution: " + sC.toString() + "\nScore: " + sC.getScore());
	}

}
