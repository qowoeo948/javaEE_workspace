package gui;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import blood.model.BloodAdvisor;


public class BloodForm extends JFrame{
	Choice ch;
	JButton bt;
	
	BloodAdvisor adviser = new BloodAdvisor();
	
	public BloodForm() {
		ch = new Choice();
		bt = new JButton("분석보기");
		
		//혈액형 유형 추가
		ch.add("A");
		ch.add("B");
		ch.add("O");
		ch.add("AB");
		
		setLayout(new FlowLayout());
		ch.setPreferredSize(new Dimension(120,30));
		add(ch);
		add(bt);
		
		bt.addActionListener((e)->{
			showResult();
			
		});
		
		setSize(400,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public void showResult() {
		String msg = adviser.getAdvice(ch.getSelectedItem());
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public static void main(String[] args) {
		new BloodForm();
	}
}
