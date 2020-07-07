package kr.randi.domain;

import java.util.Date;

public class BoardVO {

	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
	private Long viewcnt;
	
	@Override
	public String toString() {
		return title;
	}
}
