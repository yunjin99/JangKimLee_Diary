package edu.fisa.lab.JangKimLeeDiary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/checklistByDate")
	public List<ChecklistDTO> checklist(@RequestParam("date") String checkDate) throws Exception {
		return checklistService.getChecklistbyDate(checkDate);
	}

	@PostMapping("/insertChecklist")
	protected String insertChecklist(ChecklistDTO checklist) throws Exception {
		boolean result = checklistService.addChecklist(checklist);
		return result ? "체크리스트 추가 완료" : "추가실패";
	}
	
	@PostMapping("/updateChecklist")
	public String updateChecklist(ChecklistDTO checklist) throws Exception {
	    boolean result = checklistService.updateChecklist(checklist);
	    return result ? "체크리스트 수정 완료" : "수정 실패";
	}
	
	@GetMapping("/deleteChecklist")
	public boolean deleteChecklist(int id) throws Exception{
		return checklistService.deleteChecklist(id);
	}
}
