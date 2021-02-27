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
	private final static ProblemWriter WRITER = new ProblemWriter();

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Test
	public void firstTest() {
		LOG.info("----------------------");
		LOG.info("First test is starting");
		final ProblemContainer p = ProblemReader.readProblem("a.txt");
		LOG.info(p.toString());
		final SolutionContainer solution = SOLVER.solve(p);
		Assert.assertNotNull(solution);
		LOG.info("test1 solution: " + solution.toString() + "\nScore: " + solution.getScore());
		Assert.assertTrue(true);
		
	}

	@Test
	public void testFirstInput() throws IOException {
//		final ProblemContainer p = ProblemReader.readProblem("a.txt");
//		final ProblemContainer p = ProblemReader.readProblem("b.txt");
//		final ProblemContainer p = ProblemReader.readProblem("c.txt");
		final ProblemContainer p = ProblemReader.readProblem("d.txt");
//		final ProblemContainer p = ProblemReader.readProblem("e.txt");
//		final ProblemContainer p = ProblemReader.readProblem("f.txt");

//		LOG.info(p.toString());

		final SolutionContainer sC = SOLVER.solve(p);
		WRITER.writeProblem("output_file", sC);
		LOG.info("test1 Final Score: " + sC.getScore());
	}

}
