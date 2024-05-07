package jadelab2;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class BookSellerGui extends JFrame {	
	private BookSellerAgent myAgent;
	
	private JTextField titleField, priceField, shipField;
	
	BookSellerGui(BookSellerAgent a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 3));

		//title place
		p.add(new JLabel("Title:"));
		titleField = new JTextField(15);
		p.add(titleField);
		//price place
		p.add(new JLabel("Price:"));
		priceField = new JTextField(15);
		p.add(priceField);
		//ship cost
		p.add(new JLabel("Ship cost:"));
		shipField = new JTextField(15);
		p.add(shipField);

		getContentPane().add(p, BorderLayout.CENTER);
		JButton addButton = new JButton("Add");

		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String title = titleField.getText().trim();
					String price = priceField.getText().trim();
					String ship = shipField.getText().trim();  // dodałem tutaj ship

					myAgent.updateCatalogue(title, Integer.parseInt(price), Integer.parseInt(ship)); // tutaj dodany ship
					titleField.setText("");
					priceField.setText("");
					shipField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(BookSellerGui.this, "Invalid values. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	
	public void display() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		setVisible(true);
	}	
}
