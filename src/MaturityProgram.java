/*
Author: Daniel Wester

Date: 04.05.12

Course: 2243-01, Spring 2012

Assignment: PGM3 Part 2

Description: This program will compute maturity on a deposit and the interest earned. It will
input the Amount, Year, and Interest. Checking for any errors with the input it will proceed
to calculate and give the user the new maturity and the interest gained.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MaturityProgram extends JFrame {
	
	JTextArea descriptionArea;
	JLabel depositLabel, yearsLabel, interestLabel, maturityLabel, interestGainedLabel;
	JTextField depositField, yearsField, interestField, maturityField, interestGainedField, errorField;
	JButton maturityButton, exitButton;
	JPanel northPanel, centerPanel, southPanel, southBottomPanel, southTopPanel;
	
	public MaturityProgram() {
		
		//Description of the Program
		descriptionArea = new JTextArea("Welcome,\nThis is a simple program to compute maturity on a deposit and interest earned.\nThank you for using this program.");
		descriptionArea.setLineWrap(true);
		descriptionArea.setEditable(false);
		descriptionArea.setBackground(null);
		
		//Input setup
		depositLabel = new JLabel("Amount deposited: ", SwingConstants.RIGHT);
		yearsLabel = new JLabel("Years: ", SwingConstants.RIGHT);
		interestLabel = new JLabel("Interest rate %: ", SwingConstants.RIGHT);
		depositField = new JTextField("", SwingConstants.LEFT);
		depositField.addActionListener(new MaturityButtonHandler());
		yearsField = new JTextField("", SwingConstants.LEFT);
		yearsField.addActionListener(new MaturityButtonHandler());
		interestField = new JTextField("", SwingConstants.LEFT);
		interestField.addActionListener(new MaturityButtonHandler());
		
		//Output setup
		maturityLabel = new JLabel("Maturity: ", SwingConstants.RIGHT);
		maturityField = new JTextField("", SwingConstants.CENTER);
		maturityField.setEditable(false);
		interestGainedLabel = new JLabel("Interest earned: ", SwingConstants.RIGHT);
		interestGainedField = new JTextField("", SwingConstants.CENTER);
		interestGainedField.setEditable(false);
		
		//Error setup
		errorField = new JTextField("", SwingConstants.CENTER);
		errorField.setForeground(Color.red);
		errorField.setEditable(false);
		
		//Buttons setup
		maturityButton = new JButton ("Mature Intrest");
		maturityButton.addActionListener(new MaturityButtonHandler());
		exitButton = new JButton ("Exit");
		exitButton.addActionListener(new ExitButtonHandler());
		
		//Building the GUI
		Container pane = getContentPane();
		northPanel = new JPanel(new GridLayout (1,1));
		centerPanel = new JPanel(new GridLayout (5,2));
		southPanel = new JPanel(new GridLayout (2,1));
		southTopPanel = new JPanel(new GridLayout (1,1));
		southBottomPanel = new JPanel(new GridLayout (1,2));
		northPanel.add(descriptionArea);
		centerPanel.add(depositLabel);
		centerPanel.add(depositField);
		centerPanel.add(yearsLabel);
		centerPanel.add(yearsField);
		centerPanel.add(interestLabel);
		centerPanel.add(interestField);
		centerPanel.add(maturityLabel);
		centerPanel.add(maturityField);
		centerPanel.add(interestGainedLabel);
		centerPanel.add(interestGainedField);
		southTopPanel.add(errorField);
		southBottomPanel.add(maturityButton);
		southBottomPanel.add(exitButton);
		southPanel.add(southTopPanel);
		southPanel.add(southBottomPanel);
		
		//Display the GUI
		setLayout(new BorderLayout());
		setSize(450,300);
		setTitle("Maturity Program");
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private class MaturityButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			double amountInput = 0, yearsInput = 0, interestInput = 0, newTotal = 0, interestGained = 0;
			String maturityOutput = "", interestOutput = "";
			
			//Checking for errors
			try {
				amountInput = Double.parseDouble(depositField.getText());
			} catch (NumberFormatException nfe) {
				errorField.setText(depositField.getText() + " is not a number, please enter only positive numbers in the amount field.");
				depositField.requestFocusInWindow();
				depositField.selectAll();
				return;
			} try {
				yearsInput = Double.parseDouble(yearsField.getText());
			} catch (NumberFormatException nfe) {
				errorField.setText(yearsField.getText() + " is not a number, please enter only positive numbers in the years field.");
				yearsField.requestFocusInWindow();
				yearsField.selectAll();
				return;
			} try {
				interestInput = Double.parseDouble(interestField.getText());
			} catch (NumberFormatException nfe) {
				errorField.setText(interestField.getText() + " is not a number, please enter only positive numbers in the interest field.");
				interestField.requestFocusInWindow();
				interestField.selectAll();
				return;
			} if (amountInput < 0) {
				errorField.setText("The deposit input, " + depositField.getText() + " must be a positive number.");
				depositField.requestFocusInWindow();
				depositField.selectAll();
				return;
			} else if (yearsInput < 0) {
				errorField.setText("The years input, " + yearsField.getText() + " must be a positive number.");
				yearsField.requestFocusInWindow();
				yearsField.selectAll();
				return;
			} else if (interestInput < 0) {
				errorField.setText("The interest input, " + interestField.getText() + " must be a positive number.");
				interestField.requestFocusInWindow();
				interestField.selectAll();
				return;
			}
			
			//Calculation
			newTotal = Math.pow((interestInput / 100) + 1, yearsInput);
			newTotal = newTotal * amountInput;
			interestGained = newTotal - amountInput;
			maturityOutput = String.format("%,.2f", newTotal);
			interestOutput = String.format("%,.2f", interestGained);
			
			//Output
			errorField.setText("");
			maturityField.setText(maturityOutput);
			interestGainedField.setText(interestOutput);
		}
	}
	
	//Exit button
	private class ExitButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			System.exit(0);
		}
	}
	
	//Main
	public static void main (String [] args) {
		new MaturityProgram();
	}
}