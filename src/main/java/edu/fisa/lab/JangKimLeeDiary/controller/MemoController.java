package edu.fisa.lab.JangKimLeeDiary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.fisa.lab.JangKimLeeDiary.model.MemoService;


/* client 요청시 각 메소드의 반환 타입으로 JSON 으로 응답하지 않고(@RestController)
 * jsp로 응답하려 할 경우 구현하는 방식의 controller 
 * 
 * application.yml 파일의 jsp 관련 설정 확인 후 이해하기
 */
@Controller
public class MemoController {

	/*
	 * ProbonoService probonoService = new probonoService();
	 * 만일 probonoService가 interface인 경우 하위 클래스로 자동 객체 생성
	 * spring bean 객체 생성, 관리도 spring이 하겠다는 의미
	 * -> singleton 방식으로 객체 생성
	 */
	@Autowired
	MemoService memoService;

	@RequestMapping(path = "/activistAllView", method = RequestMethod.GET)
	public ModelAndView activistAll() throws Exception {
		System.out.println("---------");
		List<ActivistDTO> all = probonoService.getAllActivists();
		
		//HttpServletRequest와 흡사한 기능의 객체
		//데이터인 model과 화면 view
		ModelAndView md = new ModelAndView();
		
		md.addObject("activistAll", all); //setAttribute("activistAll", all)
		md.setViewName("activist/activistList");
		return md;
	}
	
	/* forward로 jsp파일 실행하는 코드
	 * 예외 발생시 forward방식으로 jsp에게 응답하게 하는 방식
	 * 
	 * 주요 API
	 * Model - HttpServletRequest 객체와 동일
	 * addAttribute() : setAttribute()와 동일
	 */
	@ExceptionHandler
	public String exceptionHandler(Exception e, Model m) {
		m.addAttribute("errorMsg", "발생된 이슈 " + e.getMessage());
		e.printStackTrace();
		return "forward:showError.jsp";
	}
}
