package kr.randi.mapper;

import java.util.List;

import kr.randi.domain.BoardVO;
import kr.randi.domain.Criteria;

public interface BoardMapper {

	public List<BoardVO> getList();
	public List<BoardVO> getListWithPaging(Criteria cri);
	public void insertBoard(BoardVO board);
	public BoardVO readBoard(Long bno);
	public int deleteBoard(Long bno);
	public int updateBoard(BoardVO board);
	public long getLastBno();
	public int getTotalBoardCount(Criteria cri);
}
