package kr.randi.persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

//Test for Controller
@WebAppConfiguration
	
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@Log4j
public class BoardControllerTests {

	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	//@Test 실행 전에 실행 되는 메소드
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testBoardList() throws Exception {
		
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
				);
		
	}
	
	@Test
	public void testWriteBoard() throws Exception {
		
		String resultPage = mockMvc.perform(
				MockMvcRequestBuilders.post("/board/write")
				.param("title", "BoardController 새글")
				.param("content", "BoardController 새내용")
				.param("writer", "tester")
				).andReturn()
				.getModelAndView()
				.getViewName();
		
		log.info(resultPage);
		
	}
	
	@Test
	public void testReadBoard() throws Exception {
		log.info(mockMvc.perform(
					MockMvcRequestBuilders.get("/board/read")
					.param("bno", "2"))
					.andReturn()
					.getModelAndView()
					.getModelMap()
				);
	}
	
	public void testRemoveBoard() throws Exception {
		String resultPage = mockMvc.perform(
				MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "3")
				).andReturn()
				.getModelAndView()
				.getViewName();
		
		log.info(resultPage);
	}
}
