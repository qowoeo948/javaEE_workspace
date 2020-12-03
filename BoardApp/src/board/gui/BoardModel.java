package board.gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import board.model.Notice;

public class BoardModel extends AbstractTableModel{
	String[] column= {"notice_id","�ۼ���","����","�����","��ȸ��"};
	//������ ������ ������ ����
	ArrayList<Notice>list = new ArrayList<Notice>();
	
	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return column.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return column[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Notice notice = list.get(row); //���濡 �ִ� vo�� ����
		String obj = null;
		if(col==0) {
			obj = Integer.toString(notice.getNotice_id());
		}else if(col==1) {
			obj = notice.getAuthor();
		}else if(col==2) {
			obj = notice.getTitle();
		}else if(col==3) {
			obj = notice.getRegdate();
		}else if(col==4) {
			obj = Integer.toString(notice.getHit());
		}
		
		return obj;
	}

}
