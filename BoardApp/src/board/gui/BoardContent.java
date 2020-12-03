package board.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import board.model.Notice;

public class BoardContent extends Page{
	JTextField t_author;
	JTextField t_title;
	JTextArea area;
	JScrollPane scroll;
	JButton bt_list;
	JButton bt_edit;
	JButton bt_del;
	Notice notice;
	
	public BoardContent(BoardMain boardMain) {
		super(boardMain);

		t_author = new JTextField();
		t_title = new JTextField();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		bt_list = new JButton("�������");
		bt_edit = new JButton("����");
		bt_del = new JButton("����");
		
		//��Ÿ��
		t_author.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10,35));
		t_title.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10,35));
		scroll.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10,500));
		
		//����
		add(t_author);
		add(t_title);
		add(scroll);
		add(bt_list);
		add(bt_edit);
		add(bt_del);
		
	}
	
	//������Ʈ�� ������ ä���ֱ� 
	//�̸޼��带 ȣ���ϴ� �ڴ� �Ѱ��� �����͸� VO�� ��Ƽ� ȣ���ϸ� �ȴ�.
	public void setData(Notice notice) {
		this.notice = notice; //���߿� ���� ���!
		
		t_author.setText(notice.getAuthor());
		t_title.setText(notice.getTitle());
		area.setText(notice.getContent());
	}
}
