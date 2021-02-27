package google.com.ortona.hashcode.qualification.io;

import google.com.ortona.hashcode.qualification.UtilsFile;
import google.com.ortona.hashcode.qualification.model.ProblemContainer;

public class ProblemReader {
	
	private static String FILE_LOC;
	
	
	public static ProblemContainer readProblem(String fileLocation) {
		FILE_LOC = fileLocation;
		UtilsFile uf = new UtilsFile(fileLocation);
		ProblemContainer pC = uf.getProblemContainer();
		return pC;
	}
	
	public static ProblemContainer readProblemForReset() {
		UtilsFile uf = new UtilsFile(FILE_LOC);
		ProblemContainer pC = uf.getProblemContainer();
		return pC;
	}

}
