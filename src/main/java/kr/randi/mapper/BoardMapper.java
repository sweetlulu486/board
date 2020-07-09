package kr.randi.mapper;

import java.util.List;

import kr.randi.domain.BoardVO;

public interface BoardMapper {

	public List<BoardVO> getList();
	public void insertBoard(BoardVO board);
	public BoardVO readBoard(Long bno);
}
