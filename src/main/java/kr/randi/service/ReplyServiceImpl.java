package kr.randi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.randi.domain.ReplyVO;
import kr.randi.mapper.ReplyMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Override
	public int writeReply(ReplyVO replyVO) {
		// TODO Auto-generated method stub
		return mapper.insert(replyVO);
	}
	
}
