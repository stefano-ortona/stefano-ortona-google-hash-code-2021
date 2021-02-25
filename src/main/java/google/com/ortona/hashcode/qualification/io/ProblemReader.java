package google.com.ortona.hashcode.qualification.io;

import google.com.ortona.hashcode.qualification.UtilsFile;
import google.com.ortona.hashcode.qualification.model.ProblemContainer;

public class ProblemReader {
	public ProblemContainer readProblem(String fileLocation) {
		UtilsFile uf = new UtilsFile("a.txt");
		ProblemContainer pC = uf.getProblemContainer();
		return pC;
	}

}
