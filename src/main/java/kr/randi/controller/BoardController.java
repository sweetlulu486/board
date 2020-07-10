package kr.randi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.randi.domain.BoardVO;
import kr.randi.service.BoardService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {

	@Setter(onMethod_ = @Autowired)
	private BoardService service;

	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list", service.getBoardList());
	}

	@PostMapping("/write")
	public String write(BoardVO board, RedirectAttributes attrs) {
		service.writeBoard(board);
		attrs.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";
	}

	@GetMapping("/read")
	public void get(@RequestParam("bno") Long bno, Model model) {
		model.addAttribute("board", service.getBoard(bno));
	}

	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes attrs) {

		if (service.modifyBoard(board)) {
			attrs.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes attrs) {
		if(service.removeBoard(bno)) {
			attrs.addFlashAttribute("result", "success");
		}
		return"redirect:/board/list";
	}
}