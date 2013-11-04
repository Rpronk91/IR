package SearchEngine;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JScrollBar;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import java.awt.event.FocusListener;

public class SearchEngine {

	private JFrame frame;
	private JTable table;

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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 455, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_1 = new JPanel();
		
		final JTextArea queryTxtField = new JTextArea("query");
		queryTxtField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				queryTxtField.setText("");
			}
		});
		queryTxtField.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	System.out.println("e1");
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
		
		table = new JTable();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblResults, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(table, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))
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
							.addComponent(table, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
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
	}
}
