package edu.fisa.lab.JangKimLeeDiary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.fisa.lab.JangKimLeeDiary.model.ChecklistService;
import edu.fisa.lab.JangKimLeeDiary.model.dto.ChecklistDTO;



@RestController
public class ChecklistRestController {
	@Autowired
	ChecklistService checklistService;

	@GetMapping("/checklistAll")
	public List<ChecklistDTO> ChecklistAll() throws Exception {
		return checklistService.getAllChecklist();
	}
	
//
//	@GetMapping("/checklistByDate")
//	public ChecklistDTO checklist(@RequestParam("date") String activistId) throws Exception {
//		return ChecklistService.getChecklist(checkdate);
//	}

	@PostMapping("/checklistInsert")
	protected String checklistInsert(ChecklistDTO checklist) throws Exception {
		boolean result = checklistService.addChecklist(checklist);
		return result ? "체크리스트 추가 완료" : "추가실패";
	}

}
