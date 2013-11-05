package SearchEngine;

import shared.Document;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchEngine {

    private JFrame frame;
    private JTable resultTable = new JTable();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SearchEngine window = new SearchEngine();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public SearchEngine() {
        initialize();
    }

    /**
     * Takes care of the buttonPressed event.
     */
    private void searchButtonPressed() {
        // TODO read all inputs.
        String query = "";  // jTextfield
        String method = ""; // jDropdown

        // for now create a arraylist that contains dummy documents
        // eventually will be something like : model.getRanking();
        ArrayList<Document> result = new ArrayList<>();

        populateResults(result);
    }

    /**
     * Populates the result section's jTable given an arraylist of documents.
     * @param ranking An arraylist of documents.
     */
    private void populateResults(ArrayList<Document> ranking) {
        for (Document d : ranking) {
            String id = d.getID();
            Double score = d.getScore();
            // TODO
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 455, 358);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //creating hashmap with toy data
        final HashMap<String, Double> results = new HashMap<String, Double>();
        results.put("doc1", 0.67);
        results.put("doc2", 0.246);
        results.put("doc3", 0.00054);
        results.put("doc4", 0.00000356);
        final String [] columnNames = {"Document", "Score"};
        final Object[][] tableData = new Object[results.keySet().size()][results.keySet().size()];

        JPanel panel_1 = new JPanel();

        final JTextArea queryTxtField = new JTextArea("query");
        queryTxtField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                queryTxtField.setText("");
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
        queryTxtField.setAlignmentX(Component.LEFT_ALIGNMENT);
        queryTxtField.setTabSize(1);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int index = 0;
                for (String key : results.keySet()) {
                    tableData[index][0] = key;
                    tableData[index][1] = results.get(key);
                    index++;
                }
                DefaultTableModel model = new DefaultTableModel(tableData, columnNames);
                resultTable.setModel(model);
                //resultTable.repaint();

            }
        });

        JComboBox methodsScrollBar = new JComboBox();

        JSeparator separator = new JSeparator();

        JLabel lblQuery = new JLabel("Query");

        JLabel lblMethods = new JLabel("Methods");
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addComponent(separator, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGap(25)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addComponent(methodsScrollBar, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMethods)
                        .addComponent(lblQuery)
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addComponent(queryTxtField, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)))
                    .addGap(80))
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addComponent(lblQuery)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(searchButton)
                                        .addComponent(queryTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lblMethods)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(methodsScrollBar, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE))
        );
        panel_1.setLayout(gl_panel_1);

        JPanel panel = new JPanel();

        JLabel lblResults = new JLabel("Results");

        JScrollBar scrollBar = new JScrollBar();

        DefaultTableModel model = new DefaultTableModel(tableData, columnNames);

        resultTable.setModel(model);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblResults, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                        .addComponent(resultTable, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(45))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(lblResults)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(resultTable, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addGap(14))
                        .addGroup(gl_panel.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)))
                    .addGap(0))
        );
        panel.setLayout(gl_panel);
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(10)
                    .addComponent(panel, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                    .addGap(10))
                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                .addGap(18)
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                .addGap(2))
        );
        frame.getContentPane().setLayout(groupLayout);
        searchButton.requestFocusInWindow();
    }
}
