package co.yi.domain;

public class PageMaker {
	private int tottalCount; //게시물 전체갯수
	private int startPage;	//현제 보이는 페이지시작번호
	private int endPage; 	//현제 보이는 페이지 끝번호
	private boolean prev;	//이전10개조재여부
	private boolean next;	//이후 10개 존재여부
	
	private int dispalyPageNum = 10;
	
	private Criteria cri; 
	 
	private void calcDate() {
		//ex)게시물이 총151 개
		// page번호를15선택
		
		//현제 페이지의 끝 번호를 구한다. ex) 15 / 10 => Math.ceil(1.5) -> 2*10 ->20
		endPage = (int) (Math.ceil(cri.getPage() / (double) dispalyPageNum) * dispalyPageNum);
		
		
		//현제페이지의 시작번호를 구한다. ex)20-10 +1 =11
		startPage = (endPage -dispalyPageNum) + 1;
		
		//cri.getPerPageNum() : 한페에지에 보여질 게시물 객숫
		// 전체 게시물이 151 이고 현제 페이지가 15일때 , 마자막 endPage는 16으로 나타나다.
		//ex) MAth.ceil(151/10) = 16
		int tempEndPage = (int)(Math.ceil(tottalCount / (double) cri.getPerPafeNum() ));
		
		
		//만약 , 끝페이지 번호 (EndPage)가 실제 구해진 끝번호 (tempEndPage) 보다크다면 변경해준다.
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		prev = (startPage == 1) ? false : true;
		
		next = (endPage * cri.getPerPafeNum() >= tottalCount) ? false : true;
		
		
	}
	

	public int getTottalCount() {
		return tottalCount;
	}

	public void setTottalCount(int tottalCount) {
		this.tottalCount = tottalCount;
		calcDate();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int stratPage) {
		this.startPage = stratPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDispalyPageNum() {
		return dispalyPageNum;
	}

	public void setDispalyPageNum(int dispalyPageNum) {
		this.dispalyPageNum = dispalyPageNum;
	}
  
	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	
	
}
