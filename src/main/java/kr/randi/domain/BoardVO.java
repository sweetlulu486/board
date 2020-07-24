package kr.randi.domain;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BoardVO {

	private Long bno;
	@Size(max = 200) @NotEmpty
	private String title;
	@Size(max = 65500) @NotEmpty
	private String content;
	@Size(max = 50) @NotEmpty
	private String writer;
	private Date regdate;
	private Date updatedate;
	private Long viewcnt;
	private Integer deleted;
	
}
