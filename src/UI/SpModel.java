/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import scatterplotgenerator.DataSet;

/**
 * scatterplot model, stores all main GUI elements and Main Dataset.
 * GUI elements will be hidden, but stored here
 * @author Joseph
 */
public class SpModel {
    private static DataSet mainDataSet;
    private static OpeningWindow opening;
    private static FileLoader fileLoader;
    private static CreationPane creationPane;
    private static SubsetSettings subsetSettings;
    private static DataEditor dataEditor;
    //private static MainGraph graph;

    /**
     * implemenets lazy instanciation
     * @param cp
     * @return 
     */
    public static DataEditor getDataEditor(CreationPane cp) {
        if (dataEditor == null) {
            System.out.println("Initialized data editor via lazy instanciation");
            dataEditor = new DataEditor(cp);
        }
        return dataEditor;
    }
    /**
     * does not implement lazy instaciation, may return null.
     * @return 
     */
    public static DataEditor getDataEditor() {
        return dataEditor;
    }

    public static void setDataEditor(DataEditor dataEditor) {
        SpModel.dataEditor = dataEditor;
    }

    public static DataSet getMainDataSet() {
        return mainDataSet;
    }

    public static CreationPane getCreationPane() {
        return creationPane;
    }

    public static void setCreationPane(CreationPane creationPane) {
        SpModel.creationPane = creationPane;
    }

    public static SubsetSettings getSubsetSettings() {
        return subsetSettings;
    }

    public static void setSubsetSettings(SubsetSettings subsetSettings) {
        SpModel.subsetSettings = subsetSettings;
    }

    public static void setMainDataSet(DataSet mainDataSet) {
        SpModel.mainDataSet = mainDataSet;
        if(opening!= null)opening.updateInfo();
    }

    public static OpeningWindow getOpening() {
        if(opening == null){
            System.out.println("Initialized opening via lazy instanciation");
            opening = new OpeningWindow();
        }
        return opening;
    }

    public static void setOpening(OpeningWindow opening) {
        SpModel.opening = opening;
    }

    public static FileLoader getFileLoader() {
        return fileLoader;
    }

    public static void setFileLoader(FileLoader fileLoader) {
        SpModel.fileLoader = fileLoader;
    }
}
