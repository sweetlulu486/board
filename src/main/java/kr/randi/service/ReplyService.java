package kr.randi.service;

import java.util.List;

import kr.randi.domain.Criteria;
import kr.randi.domain.ReplyVO;

public interface ReplyService {
	public int writeReply(ReplyVO replyVO);	
	public List<ReplyVO> getReplyList(Criteria cri, Long bno);
	public ReplyVO read(Long rno);
	public int remove(Long rno);
	public int modify(ReplyVO replyVO);
}
