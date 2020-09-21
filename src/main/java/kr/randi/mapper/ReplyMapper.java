package kr.randi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.randi.domain.Criteria;
import kr.randi.domain.ReplyVO;

public interface ReplyMapper {

	public int insert(ReplyVO vo);
	public ReplyVO read(Long rno);
	public int delete(Long rno);
	public int update(ReplyVO reply);
	public List<ReplyVO> getReplyListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno, @Param("curReplyPage") int curReplyPage);
	public int getCountByBno(Long bno);
}
