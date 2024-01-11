package edu.fisa.lab.JangKimLeeDiary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.fisa.lab.JangKimLeeDiary.model.CheckListService;
import edu.fisa.lab.JangKimLeeDiary.model.dto.CheckListDTO;



@RestController
public class CheckListRestController {
	@Autowired
	CheckListService checkListService;

	@GetMapping("/checkListAll")
	public List<CheckListDTO> checkListAll() throws Exception {
		return checkListService.getAllCheckList();
	}
	
	@GetMapping("/checkListByDate")
	public List<CheckListDTO> checkListByDate(@RequestParam("date") String checkDate) throws Exception {
		return checkListService.getCheckListbyDate(checkDate);
	}

	@PostMapping("/checkListInsert")
	protected String checkListInsert(CheckListDTO checkList) throws Exception {
		boolean result = checkListService.addCheckList(checkList);
		return result ? "체크리스트 추가 완료" : "추가실패";
	}
	
	@PostMapping("/checkListUpdate")
	public String checkListUpdate(CheckListDTO checkList) throws Exception {
	    boolean result = checkListService.updateCheckList(checkList);
	    return result ? "체크리스트 수정 완료" : "수정 실패";
	}
	
	@GetMapping("/checkListDelete")
	public boolean checkListDelete(int id) throws Exception{
		return checkListService.deleteCheckList(id);
	}
}
