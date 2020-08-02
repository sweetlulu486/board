package kr.randi.persistence;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.randi.domain.BoardVO;
import kr.randi.domain.Criteria;
import kr.randi.mapper.BoardMapper;
import kr.randi.service.BoardService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}

	@Test
	public void testBoardWrite() {
		
		BoardVO board = new BoardVO();
		board.setTitle("서비스 오픈");
		board.setContent("시ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ작 합니다");
		board.setWriter("테스터");
		service.writeBoard(board);
		
		log.info("생성된 게시물의 번호: " + board.getBno());
	}
	
	@Test
	public void testBoardList() {
		service.getBoardList(new Criteria()).forEach(board -> log.info(board));
	}
	
	@Test
	public void testReadBoard() {
		log.info(service.getBoard(7L));
	}
	
	@Test
	public void testRemoveBoard() {
		log.info(service.removeBoard(3L));
	}

	@Test
	public void testUpdateBoard() {
		BoardVO board = service.getBoard(2L);
		
		board.setTitle("수정합니다우아우");
		log.info(service.modifyBoard(board));
	}
	
}
