package scatterplotgenerator;

import java.util.ArrayList;

/**
 * stores information for a single point on the plot
 *
 * @author Joseph
 */
public class Entry {
    /* FIELDS */

    /**
     * The Name of the entry, will display when moused over when viewed on
     * visual plot. leave null if you dont want it to show up in the mouseover list
     */
    public String tag = null;
    /**
     * The Xvalue when placed on the graph
     */
    public Double xValue = 0.0;
    /**
     * The Yvalue when placed on the graph
     */
    public Double yValue = 0.0;
    /**
     * Which subset it is a part of, each has a different color and are listed
     * in a separate manner. Max of 6 subsets per dataset. default is base
     */
    public String subset = "Base";

    /**
     * @param X x value on the chart
     * @param Y
     * @param tag
     * @param subset
     */
    public Entry(Double X, Double Y, String tag, String subset) {
        this.xValue = X;
        this.yValue = Y;
        this.tag = tag;
        this.subset = subset;
    }
    
    /**
     * turns an arraylist of entries and turns it into a 2d array.
     * first dimension are the entries, second is the 4 attributes:
     * x, y, tag, subset
     * @param input
     * @return 
     */
    public static Object[][] constructObjectArray(ArrayList<Entry> input){
        Object[][] output = new Object[input.size()][4];
        int i = 0;
        for(Entry e : input){
            output[i][0] = e.xValue;
            output[i][1] = e.yValue;
            output[i][2] = e.tag;
            output[i][3] = e.subset;
            i++;
        }
        return output;
    }
    
    /**
     * turns a properly formatted object array into an array of entries. see Entry.constructObjectArray for proper formatting
     * @param input
     * @return 
     */
    public static ArrayList<Entry> constructEntryList(Object[][] input){
        ArrayList<Entry> output = new ArrayList<>();
        for(int i = 0; i < input.length; i++){
          Entry e = new Entry(Double.parseDouble(input[i][0].toString()), Double.parseDouble(input[i][1].toString()), (String)input[i][2], (String)input[i][3]);
          output.add(e);
        }
        return output;
    }
    /**
     * used when importing, should be formatted
     * "X-value~~~Y-value~~~Name~~~Subset"
     * @param line
     * @throws scatterplotgenerator.CorruptFileException if we cannot make an
     * entry based on given string
     */
    public Entry(String line) throws CorruptFileException {
        String[] elements = line.split("~~~");
        if (elements.length != 4) {
            throw new CorruptFileException("Entry length is " + elements.length + ", not 4: " + line);
        }
        try {
            this.xValue = Double.parseDouble(elements[0]);
            this.yValue = Double.parseDouble(elements[1]);
            this.tag = elements[2];
            this.subset = elements[3];
        } catch (NumberFormatException e) {
            throw new CorruptFileException("Entry cannot detect two valid numbers for x/y values: " + line);
        }
    }
}
