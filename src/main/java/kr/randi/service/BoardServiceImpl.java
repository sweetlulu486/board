package kr.randi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.randi.domain.BoardVO;
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
		
	}

	@Override
	public BoardVO getBoard(Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifyBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeBoard(Long bno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BoardVO> getBoardList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
