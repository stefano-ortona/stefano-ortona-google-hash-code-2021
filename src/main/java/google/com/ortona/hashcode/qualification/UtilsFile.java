package google.com.ortona.hashcode.qualification;

import google.com.ortona.hashcode.qualification.logic.Car;
import google.com.ortona.hashcode.qualification.logic.Intersection;
import google.com.ortona.hashcode.qualification.logic.Street;
import google.com.ortona.hashcode.qualification.model.ProblemContainer;
import jnr.ffi.annotations.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UtilsFile {

    /* If file structure is preserved (i.e. first line header, rest of the file data)
        Edit this file as following:
        1. define type of header items and data items
        2. generate setters and getters for header and data
        3. define logic of createHeader() and createData()
     */

    // 1. define type of header items and data items
    private int[] header;
    private char[][] data;
    int time;
    int intersectionAmount;
    int streetsAmount;
    int carsAmount;
    int bonus;
    List<Street> streets;
    List<Intersection> intersections;
    List<Car> cars;
    int carBonus;
    ProblemContainer problemContainer;

    // 2. generate setters and getters for header and data
    public void setHeader(int[] header) {
        this.header = header;
    }

    public void setData(char[][] data) {
        this.data = data;
    }

    public int[] getHeader() {
        return header;
    }

    public char[][] getData() {
        return data;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getIntersectionAmount() {
        return intersectionAmount;
    }

    public void setIntersectionAmount(int intersectionAmount) {
        this.intersectionAmount = intersectionAmount;
    }

    public int getStreetsAmount() {
        return streetsAmount;
    }

    public void setStreetsAmount(int streetsAmount) {
        this.streetsAmount = streetsAmount;
    }

    public int getCarsAmount() {
        return carsAmount;
    }

    public void setCarsAmount(int carsAmount) {
        this.carsAmount = carsAmount;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public void setIntersections(List<Intersection> intersections) {
        this.intersections = intersections;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public int getCarBonus() {
        return carBonus;
    }

    public void setCarBonus(int carBonus) {
        this.carBonus = carBonus;
    }

    public ProblemContainer getProblemContainer() {
        return problemContainer;
    }

    public void setProblemContainer(ProblemContainer problemContainer) {
        this.problemContainer = problemContainer;
    }

    //3. define logic of createHeader() and createData()

    public void createHeader() {
        String firstLine = getFirstLineOfFile();
        String[] split = splitString(firstLine, " ");
        int[] converted = convertArrayOfStringToArrayOfInt(split);

        this.setTime(converted[0]);
        this.setIntersectionAmount(converted[1]);
        this.setStreetsAmount(converted[2]);
        this.setCarsAmount(converted[3]);
        this.setBonus(converted[4]);
        this.setCarBonus(this.getBonus());

        // always finish with this.setHeader()
        this.setHeader(converted);
    }

    public void createData() {
        List<Street> streets = new ArrayList<>();
        List<Intersection> intersections = new ArrayList<>();
        List<Car> cars = new ArrayList<>();

        String[] file = this.getFile();
        int endStreets = this.getStreetsAmount();
        String[] streetsRaw = cloneArrayOfString(file, 1, endStreets);
        String[] carsRaw = cloneArrayOfString(file, endStreets + 1, this.file.length);
        Map<Integer, ArrayList<Street>> id2streets = new HashMap<>();
        Map<String, Street> name2street = new HashMap<>();

        for (int s=0; s<streetsRaw.length; s++){
            String[] streetRaw = splitString(streetsRaw[s], " ");
            Street street = new Street();
            streets.add(street);
            street.setName(streetRaw[2]);
            street.setLength(Integer.parseInt(streetRaw[3]));
            name2street.put(street.getName(), street);

            if (id2streets.containsKey(Integer.parseInt(streetRaw[1]))){
                ArrayList<Street> current = id2streets.get(Integer.parseInt(streetRaw[1]));
                current.add(street);
                id2streets.put(Integer.parseInt(streetRaw[1]),current );
            } else {
                ArrayList<Street>   current = new ArrayList<>();
                current.add(street);
                id2streets.put(Integer.parseInt(streetRaw[1]),current );
            }

        }

        for(int id: id2streets.keySet()) {
            Intersection intersection = new Intersection();
            intersection.setId(id);
            intersection.setIncomingStreets(id2streets.get(id));
            intersections.add(intersection);
        }

        for (int c=0; c<carsRaw.length; c++){
            Car car = new Car();
            car.setId(c);
            cars.add(car);

            String[] carRaw = splitString(carsRaw[c], " ");
            String[] pathRaw= cloneArrayOfString(file, 1, carRaw.length);
            ArrayList<Street> ss = new ArrayList<>();

            for (int pa=0; pa<pathRaw.length; pa++){
                Street str = name2street.get(pathRaw[pa]);
                ss.add(str);

            }
            car.setPath(ss);
        }

        this.setStreets(streets);
        this.setIntersections(intersections);
        this.setCars(cars);

        problemContainer = new ProblemContainer(this.getStreets(), this.getIntersections(), this.getCars(), this.getTime(), this.getBonus());

        this.setProblemContainer(problemContainer);

    }





    // ====== Do not change below here

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String RESOURCE_PATH = "src/main/resources/google/com/ortona/hashcode/qualification/";
    private String[] file;

    public void setFile(String[] file) {
        this.file = file;
    }

    public String[] getFile() {
        return file;
    }

    // Constructor
    public UtilsFile(String filepath) {

        try {
            File file = new File(RESOURCE_PATH + filepath);
            String absolutePath = file.getAbsolutePath();

            LOGGER.info("File absolute path:" + absolutePath);
            readFile(absolutePath);

            //LOGGER.info("Header creation: start");
            createHeader();
            //LOGGER.info("Header creation: done");

            //LOGGER.info("Data creation: start");
            createData();
            //LOGGER.info("Data creation: done");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // Utils
    public void readFile(String filepath) throws IOException {

        String line;
        List<String> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(filepath));

        while ((line = in.readLine()) != null) {
            list.add(line);  //Add line to file
        }

        //Cast List to Array of Array
        String[] stringArr = list.toArray(new String[0]);

        this.setFile(stringArr);

    }

    public String getFirstLineOfFile() {
        return this.file[0];
    }

    public String[] splitString(String string, String separator) {
        return string.split(separator);
    }

    public String[] cloneArrayOfString(String[] source, Integer start, Integer to) {
        return Arrays.copyOfRange(source, start, to);
    }

    public char[] convertStringToArrayOfChar(String string) {
        return string.toCharArray();
    }

    public char[][] convertArrayOfStringToArrayOfCharArrays(String[] dataRaw) {

        char[][] result = new char[dataRaw.length][dataRaw[0].length()];

        for (int i = 0; i < dataRaw.length; i++) {
            result[i] = convertStringToArrayOfChar(dataRaw[i]);
        }
        return result;
    }

    public String convertArrayOfChartToString(char[] a) {
        return new String(a);
    }

    public int[] convertArrayOfStringToArrayOfInt(String[] strings) {
        return Arrays.asList(strings).stream().mapToInt(Integer::parseInt).toArray();

    }

}
