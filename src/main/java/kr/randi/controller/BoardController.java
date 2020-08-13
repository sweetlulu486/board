package kr.randi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.randi.domain.BoardVO;
import kr.randi.domain.Criteria;
import kr.randi.domain.PageDTO;
import kr.randi.service.BoardService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/first/*")
public class BoardController {

	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Criteria cri , Model model) {
		model.addAttribute("list", service.getBoardList(cri));
		
		int total = service.getTotalBoardCount(cri);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}

	@PostMapping("/write")
	public String write(@Valid BoardVO board, BindingResult bindResult, RedirectAttributes attrs) {
		
		if (bindResult.hasErrors()) {
			return "redirect:/first/list";
		}
		
		service.writeBoard(board);
		attrs.addFlashAttribute("result", board.getBno());
		return "redirect:/first/list";
	}
	@GetMapping("/write")
	public void write() {
		
	}

	@GetMapping({"/read", "/modify"})
	public void read(@RequestParam("bno") Long bno,  @ModelAttribute("cri") Criteria cri, Model model) {
		model.addAttribute("board", service.getBoard(bno));
	}

	@PostMapping("/modify")
	public String modify(@Valid BoardVO board, Criteria cri, BindingResult bindResult, RedirectAttributes attrs) {

		if (bindResult.hasErrors()) {
			List<ObjectError> errors = bindResult.getAllErrors();
			errors.forEach(error -> log.info(error));
			return "redirect:/first/modify?bno="+board.getBno();
		}
		
		if (service.modifyBoard(board)) {
			attrs.addFlashAttribute("result", "success");
		}
		
		attrs.addAttribute("pageNum", cri.getPageNum());
		attrs.addAttribute("amount", cri.getAmount());
		
		return "redirect:/first/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @Valid BoardVO board, Criteria cri, BindingResult bindResult, RedirectAttributes attrs) {
		
		if (bindResult.hasErrors()) {
			List<ObjectError> errors = bindResult.getAllErrors();
			errors.forEach(error -> log.info(error));
			return "redirect:/first/modify?bno="+board.getBno();
		}
		
		if(service.removeBoard(bno)) {
			attrs.addFlashAttribute("result", "success");
		}
		
		attrs.addAttribute("pageNum", cri.getPageNum());
		attrs.addAttribute("amount", cri.getAmount());
		
		return"redirect:/first/list";
	}
}