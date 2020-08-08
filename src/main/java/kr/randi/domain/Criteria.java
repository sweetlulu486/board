package kr.randi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Criteria {
	
	private int idx;
	private int skipCount;
	private int pageNum;
	private int amount;
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.skipCount = 10;
		this.idx = (pageNum-1) * skipCount;
		
	}
	
	
}
