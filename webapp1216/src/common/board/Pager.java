/*
 * paging 처리를 재사용성을 높인 클래스로 정의
 * 
 * */
package common.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Pager {
	private int totalRecord;	//총 레코드 수 
	private int pageSize=10;	// 페이지당 보여질 레코드 수 
	private int totalPage;
	private int blockSize=10; //블럭 당 보여질 페이지 수
	private int currentPage=1;
	private int firstPage;
	private int lastPage;
	
	int curPos;
	int num;
	
	//선언된 변수 초기화
	//실시간으로 변하는 값들은 생성자에서 바뀌게 만들어줘야함
	public void init(HttpServletRequest request, List list) {
		totalRecord = list.size();
		totalPage=(int)Math.ceil((float)totalRecord/pageSize);
		
		//페이지를 선택한 경우엔, 그 선택된 페이지로 대체
		if(request.getParameter("currentPage")!=null) {
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}

		firstPage=currentPage-(currentPage-1)%blockSize;
		lastPage=firstPage+(blockSize-1);

		curPos=(currentPage-1)*pageSize;	//페이지 당 List내에서의 시작 index
		num=totalRecord-curPos;	//페이지당 시작 번호
		
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getCurPos() {
		return curPos;
	}

	public void setCurPos(int curPos) {
		this.curPos = curPos;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	
	
	
	
}
