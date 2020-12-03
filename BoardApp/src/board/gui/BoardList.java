package board.gui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import board.model.Notice;
import board.model.NoticeDAO;

public class BoardList extends Page{
	JTable table;
	JScrollPane scroll;
	JButton bt;
	BoardModel model;
	NoticeDAO noticeDAO;
	ArrayList<Notice> boardList; 
	
	public BoardList(BoardMain boardMain) {
		super(boardMain);
		
		//����
		table = new JTable(model = new BoardModel()); //jtable�� �𵨰�ü�� ����
		scroll = new JScrollPane(table);
		bt = new JButton("�۵��");
		noticeDAO = new NoticeDAO();
		
		//��Ÿ��
		scroll.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth(),600));
//		this.setBackground(Color.YELLOW);
		
		//����
		add(scroll);
		add(bt);
		
		getList();
		table.updateUI();
		
		bt.addActionListener((e)->{
			boardMain.showPage(Pages.valueOf("BoardWrite").ordinal());
			
		});
		
		//���̺� �� �ϳ��� ���ڵ带 �����ϸ� �󼼺��� �����ֱ�!
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				//�󼼺���� �������� notice_id�� ���� �����ؾ� �Ѵ�.
				int col = 0;
				int row = table.getSelectedRow();
				
				Notice notice = boardList.get(row);
				
				BoardContent boardContent = (BoardContent)boardMain.pageList[Pages.valueOf("BoardContent").ordinal()];
				boardContent.setData(notice);
				boardMain.showPage(Pages.valueOf("BoardContent").ordinal());
			}
		});
		
	}
	
	//DAO�� �̿��Ͽ� ������ ��������!!
	public void getList() {
		boardList = noticeDAO.selectAll();
		model.list = boardList;
		
	}

}
