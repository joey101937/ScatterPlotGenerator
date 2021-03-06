/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import scatterplotgenerator.CorruptFileException;
import scatterplotgenerator.DataSet;

/**
 *
 * @author Joseph
 */
public class FileLoader extends javax.swing.JFrame {
    /**Does the Dataset Directory have saved datasets in it?*/
    public boolean filesFound = false;
    public ArrayList<File> dataSets = new ArrayList<>();
    /**
     * Creates new form FileLoader
     */
    public FileLoader() {
        initComponents();
        this.setVisible(true);
        this.directoryLabel.setText("Checking: " + System.getProperty("user.dir") + File.separator + "DataSets" + File.separator);
        int numSets = 0;
        File dir = new File(System.getProperty("user.dir") + File.separator + "DataSets" + File.separator); //where we store the dataset files
        if(dir.exists()) System.out.println("found dir");
        else dir.mkdir();
        for(File f : dir.listFiles()){
            if(!f.getName().endsWith(".ds"))continue; //skip it if it isnt a .ds
            filesFound = true;
            dataSets.add(f);
        }
        if(filesFound)comboBox.removeAllItems(); //if we have files to display, get rid of that NONE message
        for(File f : dataSets){
            comboBox.addItem(f.getName());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboBox = new javax.swing.JComboBox();
        titleLabel = new javax.swing.JLabel();
        directoryLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No .ds files found in DataSet directory" }));

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        titleLabel.setText("Select Dataset To Load");

        directoryLabel.setText("<directory to look in>");

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(directoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(okButton))
                            .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(258, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(directoryLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(okButton))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * check for valid selection then send that selection to the model
     * @param evt 
     */
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
       if(comboBox.getSelectedItem().toString().endsWith(".ds")){
           //if we hit OK to a .ds file
           File selectedFile = dataSets.get(comboBox.getSelectedIndex());
           try {
               DataSet dataToGive = new DataSet(selectedFile);
               SpModel.setMainDataSet(dataToGive);
               this.dispose();
           }catch (CorruptFileException cfe){
               cfe.printStackTrace();
               JOptionPane.showMessageDialog(null, "Selected file could not be read properly.");
               return;
           }catch (Exception e){
               e.printStackTrace();
               JOptionPane.showMessageDialog(null, "Error: " + e.toString());
               return;
           }
       }else{
        //if we hit OK to "no files found"
        this.dispose();
    }
    }//GEN-LAST:event_okButtonActionPerformed

    @Override
    public void dispose() {
        super.dispose();
        SpModel.getOpening().setEnabled(true);
        SpModel.getOpening().requestFocus();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboBox;
    private javax.swing.JLabel directoryLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
