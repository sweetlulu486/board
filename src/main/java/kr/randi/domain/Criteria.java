package kr.randi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Criteria {
	
	private int idx;
	private int skipCount = 10;
	private int pageNum;
	private int amount;
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.skipCount = 10; // 한 페이지당 보여줄 게시글 수
		this.idx = (pageNum-1) * skipCount;
		
	}

	public void setIdx(int pageNum) {
		this.idx = (pageNum-1) * getSkipCount();
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		setIdx(pageNum);
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
