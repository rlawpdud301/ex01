package co.yi.domain;

public class Criteria {
	private int page; //선택한패잉지 번호저장
	private int perPafeNum; //페이지 당 몇게 읽을것인지 가지는 변수
	
	
	
	public Criteria(int page, int perPafeNum) {
		super();
		this.page = page;
		this.perPafeNum = perPafeNum;
	}

	public Criteria() {
		page =1;
		perPafeNum =10;
	}

	public int getPage() {
		
		return page;
	}

	public void setPage(int page) {
		if (page <= 0) {
			page =1;
		}
		this.page = page;
	}

	public int getPerPafeNum() {
		return perPafeNum;
	}

	public void setPerPafeNum(int perPafeNum) {
		if(perPafeNum <= 0 || perPafeNum > 100) {
			perPafeNum = 10;
		}
		this.perPafeNum = perPafeNum;
	}
	
	public int getPageSrart() {
		return (this.page -1) * perPafeNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPafeNum=" + perPafeNum + "]";
	}
	
	
}
