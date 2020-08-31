package kr.randi.persistence;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.randi.domain.ReplyVO;
import kr.randi.mapper.ReplyMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test
	public void createReply() {
		IntStream.rangeClosed(1, 10).forEach(i ->{
		
			ReplyVO vo = new ReplyVO();
			
			vo.setBno((long) i);
			vo.setReplyContent("테스트");
			vo.setReplyer("test");
		
			mapper.insert(vo);
		});
	}
	
	
	@Test
	public void testRead() {
		Long targetRno = 5L;
		ReplyVO replyVO = mapper.read(targetRno);
		log.info(replyVO);
	}
	
	@Test
	public void testDelete() { 
		Long targetRno = 4L;
		int result = mapper.delete(targetRno);
		log.info(result);
	}
	
	@Test
	public void testUpdate() {
		Long targetRno = 2L;
		ReplyVO replyVO = mapper.read(targetRno);
		replyVO.setReplyContent("하이 ... ");
		int count = mapper.update(replyVO);
		log.info("update count: " + count);
		
	}
}
