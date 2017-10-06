/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatterplotgenerator;

import UI.*;
import java.io.File;

/**
 *
 * @author Joseph
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    SpModel.setOpening(new OpeningWindow());
    }
    
    /**
     * used to test import/export
     */
    private static void testLoad(){
                File test = new File(System.getProperty("user.dir") + File.separator + "test.txt");
        System.out.println("test");
        try{
        DataSet ds = new DataSet(test);
        System.out.println("Dataset created: "  + ds.name+ "xname: " + ds.xName + " yname: " + ds.yName);
        for(Entry e : ds.data){
            System.out.println("Entry " +  e.tag + ": (" + e.xValue + "," + e.yValue + ") subset: " + e.subset);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
