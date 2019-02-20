package co.yi.domain;

public class PageMaker {
	private int tottalCount; //�Խù� ��ü����
	private int startPage;	//���� ���̴� ���������۹�ȣ
	private int endPage; 	//���� ���̴� ������ ����ȣ
	private boolean prev;	//����10�����翩��
	private boolean next;	//���� 10�� ���翩��
	
	private int dispalyPageNum = 10;
	
	private Criteria cri; 
	 
	private void calcDate() {
		//ex)�Խù��� ��151 ��
		// page��ȣ��15����
		
		//���� �������� �� ��ȣ�� ���Ѵ�. ex) 15 / 10 => Math.ceil(1.5) -> 2*10 ->20
		endPage = (int) (Math.ceil(cri.getPage() / (double) dispalyPageNum) * dispalyPageNum);
		
		
		//������������ ���۹�ȣ�� ���Ѵ�. ex)20-10 +1 =11
		startPage = (endPage -dispalyPageNum) + 1;
		
		//cri.getPerPageNum() : ���信���� ������ �Խù� ����
		// ��ü �Խù��� 151 �̰� ���� �������� 15�϶� , ���ڸ� endPage�� 16���� ��Ÿ����.
		//ex) MAth.ceil(151/10) = 16
		int tempEndPage = (int)(Math.ceil(tottalCount / (double) cri.getPerPafeNum() ));
		
		
		//���� , �������� ��ȣ (EndPage)�� ���� ������ ����ȣ (tempEndPage) ����ũ�ٸ� �������ش�.
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
