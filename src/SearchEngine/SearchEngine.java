package SearchEngine;

import models.BM25Model;
import models.BooleanModel;
import models.Model;
import models.TFIDFModel;
import shared.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchEngine extends javax.swing.JFrame {

    private ArrayList<Model> models;

    /**
     * Creates new form Form
     */
    public SearchEngine() {
        this.models = new ArrayList<>();
        this.models.add( new BooleanModel() );
        this.models.add( new TFIDFModel() );
        this.models.add( new BM25Model() );

        initComponents();
    }

    private void initComponents() {
        this.jPanel1 = new javax.swing.JPanel();
        this.searchLabel = new javax.swing.JLabel();
        this.modelLabel = new javax.swing.JLabel();
        this.modelsComboBox = new javax.swing.JComboBox();
        this.queryTextField = new javax.swing.JTextField();
        this.searchButton = new javax.swing.JButton();
        this.jSeparator1 = new javax.swing.JSeparator();
        this.jPanel2 = new javax.swing.JPanel();
        this.jScrollPane1 = new javax.swing.JScrollPane();
        this.resultsTable = new javax.swing.JTable();
        this.statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        this.searchLabel.setText("Search");

        this.modelLabel.setText("Model");

        this.modelsComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.getModelNames()));

        this.queryTextField.setText("");

        this.searchButton.setText("Search!");
        this.searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                searchButtonPressed();
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(searchButton)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(searchLabel)
                                .addComponent(modelLabel))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(modelsComboBox, 0, 271, Short.MAX_VALUE)
                                .addComponent(queryTextField))))
                    .addContainerGap(37, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator1)
                    .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchLabel)
                        .addComponent(queryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(modelLabel)
                        .addComponent(modelsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(searchButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        this.resultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{
                {null, null, null}, {null, null, null}, {null, null, null}, {null, null, null}, {null, null, null},
                {null, null, null}, {null, null, null}, {null, null, null}, {null, null, null}, {null, null, null},
                {null, null, null}, {null, null, null}, {null, null, null}, {null, null, null}, {null, null, null}
            },
            new String[]{"#", "Document ID", "Score"}
        ));
        this.resultsTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        this.resultsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.resultsTable.getColumnModel().getColumn(2).setPreferredWidth(120);

        this.jScrollPane1.setViewportView(resultsTable);


        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(13, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
        );

        this.statusLabel.setText("Idle");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(statusLabel))
        );
        pack();
    }

    /**
     * Returns a string array containing the model names available.
     * @return a string array containing the model names available.
     */
    private String[] getModelNames() {
        String[] ret = new String[this.models.size()];
        int i = 0;
        for (Model m : this.models) { ret[i++] = m.toString(); }
        return ret;
    }

    /**
     * Returns the selected model from the models container.
     * @return the selected model from the models container.
     */
    private Model getSelectedModel() {
        int i = this.modelsComboBox.getSelectedIndex();
        return this.models.get(i);
    }

    private void searchButtonPressed() {
        if (!this.inputIsValid()) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
            return;
        }
        String query = this.queryTextField.getText();
        Model m = getSelectedModel();

        outputResults(m.getRanking(query).getDocuments());
    }

    /**
     * Returns true if all the user-provided input is valid and retrieval can begin. (Extend if more input fields are
     * introduced.)
     * @return True if all the user-provided input is valid and retrieval can begin.
     */
    private boolean inputIsValid() {
        return this.queryTextField.getText().length() > 0;
    }

    /**
     * Outputs the results to the user.
     * @param ranking An arraylist of documents.
     */
    private void outputResults(ArrayList<Document> ranking) {
        String [] columnNames = {"#", "Document ID", "Score"};
        Object[][] tableData = new Object[ranking.size()][3];
        int index = 0;
        for (Document d : ranking) {
            String id = d.getID();
            Double score = d.getScore();
            tableData[index][0] = index + 1;
            tableData[index][1] = id;
            tableData[index][2] = score;
            index++;
        }
        DefaultTableModel model = new DefaultTableModel(tableData, columnNames);
        this.resultsTable.setModel(model);
        this.resultsTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        this.resultsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.resultsTable.getColumnModel().getColumn(2).setPreferredWidth(120);

        this.statusLabel.setText("Search returned " + ranking.size() + " results");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            SearchEngine se = new SearchEngine();
            se.setVisible(true);
            se.setResizable(false);
            }
        });
    }

    // window components
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox modelsComboBox;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JLabel modelLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable resultsTable;
    private javax.swing.JTextField queryTextField;
}
