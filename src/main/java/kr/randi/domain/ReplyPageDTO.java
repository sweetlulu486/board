package kr.randi.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
public class ReplyPageDTO {

	private int replyCnt;
	private List<ReplyVO> list;
}
