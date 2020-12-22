package gui;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChatClient extends JFrame{
	private JComponent p_north;	
	private JComponent t_ip;	//ip작성
	private JComponent bt;
	private JComponent area;
	private JComponent scroll;
	private JComponent t_input;	//메시지 입력창 
	
	
	public JComponent getP_north() {
		return p_north;
	}
	public void setP_north(JComponent p_north) {
		this.p_north = p_north;
	}
	public JComponent getT_ip() {
		return t_ip;
	}
	public void setT_ip(JComponent t_ip) {
		this.t_ip = t_ip;
	}
	public JComponent getBt() {
		return bt;
	}
	public void setBt(JComponent bt) {
		this.bt = bt;
	}
	public JComponent getArea() {
		return area;
	}
	public void setArea(JComponent area) {
		this.area = area;
	}
	public JComponent getScroll() {
		return scroll;
	}
	public void setScroll(JComponent scroll) {
		this.scroll = scroll;
	}
	public JComponent getT_input() {
		return t_input;
	}
	public void setT_input(JComponent t_input) {
		this.t_input = t_input;
	}
	
	//조립
	public void init() {
		p_north.add(t_ip);
		p_north.add(bt);
		add(p_north,BorderLayout.NORTH);
		add(scroll);
		add(t_input,BorderLayout.SOUTH);
		
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

}
