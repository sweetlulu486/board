package kr.randi.persistence;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.randi.domain.BoardVO;
import kr.randi.domain.Criteria;
import kr.randi.mapper.BoardMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}

	@Test
	public void testInsertBoard() {
		BoardVO board = new BoardVO();
		board.setTitle("hello world");
		board.setContent("new content");
		board.setWriter("newbie");
		mapper.insertBoard(board);

		log.info(board);
	}
	
	@Test
	public void testReadBoard() {
		BoardVO board = mapper.readBoard(5L);
		
		log.info(board);
	}
	
	@Test
	public void testDeleteBoard() {
		log.info("DELETE COUNT: " + mapper.deleteBoard(4L));
	}
	
	@Test
	public void testUpdateBoard() {
		
		BoardVO board = new BoardVO();
		board.setBno(5L);
		board.setTitle("수정하였음");
		board.setContent("수정한 내용");
		board.setWriter("user99");
		
		int count = mapper.updateBoard(board);
		log.info("UPDATE COUNT: " + count);
	}
	
	@Test
	public void testPaging() {
		Criteria cri = new Criteria();
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board->log.info(board));
	}
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		
		cri.setKeyword("수정");
		cri.setType("TC");
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));
	}
	
}