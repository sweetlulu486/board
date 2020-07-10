package kr.randi.service;

import java.util.List;

import kr.randi.domain.BoardVO;

public interface BoardService {
	public void writeBoard(BoardVO board);
	public BoardVO getBoard(Long bno);
	public boolean modifyBoard(BoardVO board);
	public boolean removeBoard(Long bno);
	public List<BoardVO> getBoardList();
}
