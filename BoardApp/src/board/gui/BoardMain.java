package board.gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;

import db.DBManager;

public class BoardMain extends JFrame{
	//사용할 페이지 구성
	Page[] pageList = new Page[Pages.values().length]; //enum에 등록된 페이지 만큼 생성

	DBManager dbManager;
	
	public BoardMain() {
		//페이지 생성
		pageList[0]=new BoardList(this);
		pageList[1]=new BoardWrite(this);
		pageList[2]=new BoardContent(this);
		
		dbManager = new DBManager();
		
		setLayout(new FlowLayout()); //프레임에 여러개의 컴포넌트가 부착되려면 플로우로~
		//페이지들 부착
		for(Page page:pageList) {
			add(page);
			page.setVisible(false);	//일단 모두 안보이게 처리
			
		}
		
		showPage(Pages.valueOf("BoardList").ordinal());
		
		setSize(900,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void showPage(int viewPage) { //보여주고 싶은 페이지 인수로 전달
		for(int i=0;i<pageList.length;i++) {
			if(viewPage==i) {
				pageList[i].setVisible(true);
			}else {
				pageList[i].setVisible(false);
			}
		}
	}
	
	
	public static void main(String[] args) {
		new BoardMain();
	}

}
