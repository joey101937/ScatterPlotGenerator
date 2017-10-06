package scatterplotgenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Stores all entries of a dataset as well as other pertenent information about
 * a dataset to graph
 *
 * @author Joseph
 */
public class DataSet {
    /* FIELDS */

    /**
     * Stores all entries
     */
    public ArrayList<Entry> data = new ArrayList<>();
    /**
     * Raw Data of the DataSet saved file
     */
    public ArrayList<String> rawData = new ArrayList<>();
    public String name = "Unnamed DataSet";
    /**
     * Number of subsets this dataset contains, set in constructor
     */
    public ArrayList<String> subsets = new ArrayList<>();
    /**
     * Name of the x Axis, set in constructor
     */
    public String xName = null;
    /**
     * Name of y axis, set in constructor
     */
    public String yName = null;

    /**
     * Create a new Dataset based on a previously saved dataset
     *
     * @param file dataset to import
     */
    public DataSet(File file) throws FileNotFoundException, IOException, CorruptFileException {
        ArrayList<String> output = new ArrayList<>(); //output is where we store all the lines from the given file
        InputStream ips = new FileInputStream(file);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String line;
        while ((line = br.readLine()) != null) {
            output.add(line);
        }
        if (output.size() < 3) {
            throw new CorruptFileException("Too Few Lines");
        }
        this.name = output.get(0);
        this.xName = output.get(1);
        this.yName = output.get(2);
        for (int i = 3; i < output.size(); i++) {
            try {
                data.add(new Entry(output.get(i)));
            } catch (CorruptFileException cfe) {
                throw cfe;
            } catch (Exception e) {
                throw e;
            }
        }
        boolean subsetAccountedFor = false;
        for (Entry e : data) {
            subsetAccountedFor = false;
            for (String s : subsets) {
                if (s.equals(e.subset)) {
                    subsetAccountedFor = true;
                }
            }
            if (!subsetAccountedFor) {
                subsets.add(e.subset);// add all new subsets to the list of subsets
            }
        }
    }

    /**
     * @param inputData the data for the chart
     * @param name the name of the chart
     * @param xName the name of the x axis
     * @param yName the name of the Y axis
     */
    public DataSet(ArrayList<Entry> inputData, String name, String xName, String yName) {
        data = inputData;
        boolean subsetAccountedFor = false;
        this.name = name;
        this.xName = xName;
        this.yName = yName;
        for (Entry e : inputData) {
            subsetAccountedFor = false;
            for (String s : subsets) {
                if (s.equals(e.subset)) {
                    subsetAccountedFor = true;
                }
            }
            if (!subsetAccountedFor) {
                subsets.add(e.subset);// add all new subsets to the list of subsets
            }
        }
    }

    /**
     * returns a file corresponding to this dataset which we can later import to
     * recreate this dataset
     *
     * @param pathName name of the file
     * @return
     */
    public File export(String pathName) {
        File output = new File(pathName);
        ArrayList<String> lines = new ArrayList<>(); //holds all the lines of the file to write
        /*
         we are now going to use lines to store the contents of the file to write, we must organize it in such a way that we can later go back and read the save
         and reproduce this dataset. 
         the first 3 lines are name, xname, yname respectively. after that we read all the data, each entry is represented on a single line
         entries are sorted xValue, yValue, tag, subset in that order and use a regular expression "~~~" to separate fields
         */
        lines.add(this.name);
        lines.add(this.xName);
        lines.add(this.yName);
        for (Entry e : data) {
            lines.add(e.xValue + "~~~" + e.yValue + "~~~" + e.tag + "~~~" + e.subset);
        }
        try {
            FileWriter fw = new FileWriter(output, false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String s : lines) {
                bw.write(s);
                System.out.println("writing... " + s);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

}
