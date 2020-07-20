package kr.randi.mapper;

import java.util.List;

import kr.randi.domain.BoardVO;

public interface BoardMapper {

	public List<BoardVO> getList();
	public void insertBoard(BoardVO board);
	public BoardVO readBoard(Long bno);
	public int deleteBoard(Long bno);
	public int updateBoard(BoardVO board);
	public long getLastBno();
}
