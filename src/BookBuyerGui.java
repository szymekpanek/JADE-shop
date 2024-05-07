package jadelab2;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.text.NumberFormatter;


// ex 2
/*
Assign the budget - certain amount of money - to the buyer agent. When the buyer makes a purchase
 (receives INFORM message confirming the successful purchase), budget decreases
 - money is withdrawn from the budget. Agent cannot spend more money than it owns.
 */


class BookBuyerGui extends JFrame {
	private BookBuyerAgent myAgent;

	private JTextField titleField;


	BookBuyerGui(BookBuyerAgent a) {
		super(a.getLocalName());

		myAgent = a;

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 3));

		p.add(new JLabel("Title:"));
		titleField = new JTextField(15);
		p.add(titleField);




		getContentPane().add(p, BorderLayout.CENTER);
		JButton addButton = new JButton("Search");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String title = titleField.getText().trim();
					myAgent.lookForTitle(title);
					titleField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(BookBuyerGui.this, "Invalid values. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);

		addWindowListener(new    WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );

		setResizable(false);
	}

	public void display() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 3;
		int centerY = (int)screenSize.getHeight() / 3;
		setLocation(centerX - getWidth() / 3, centerY - getHeight() / 3);
		setVisible(true);
	}
}
