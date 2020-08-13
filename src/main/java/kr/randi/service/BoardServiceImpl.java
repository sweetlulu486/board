package kr.randi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.randi.domain.BoardVO;
import kr.randi.domain.Criteria;
import kr.randi.mapper.BoardMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Override
	public void writeBoard(BoardVO board) {
		// TODO Auto-generated method stub
		mapper.insertBoard(board);
		board.setBno(mapper.getLastBno());
	}

	@Override
	public BoardVO getBoard(Long bno) {
		// TODO Auto-generated method stub
		return mapper.readBoard(bno);
	}

	@Override
	public boolean modifyBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return mapper.updateBoard(board) == 1;
	}

	@Override
	public boolean removeBoard(Long bno) {
		// TODO Auto-generated method stub
		return mapper.deleteBoard(bno) == 1;
	}

	@Override
	public List<BoardVO> getBoardList(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotalBoardCount(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getTotalBoardCount(cri);
	}

}
