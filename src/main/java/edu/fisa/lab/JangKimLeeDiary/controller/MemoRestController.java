package edu.fisa.lab.JangKimLeeDiary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.fisa.lab.JangKimLeeDiary.model.MemoService;
import edu.fisa.lab.JangKimLeeDiary.model.dto.MemoDTO;

@RestController
public class MemoRestController {

	@Autowired
	MemoService memoService;

	// 모든 memo 검색 메소드
	@GetMapping("/memoAll")
	public List<MemoDTO> memoAll() throws Exception {
		return memoService.getAllMemo();
	}

	@GetMapping("/memoFindByDate")
	public List<MemoDTO> findMemoByDate(@RequestParam("date") String date) throws Exception {
		return memoService.getFindByDate(date); //json 포멧으로 client의 js 요청 영역으로 출력
	}

	@PostMapping("/memoInsert")
	protected boolean memoInsert(MemoDTO memo) throws Exception {
		return memoService.addMemo(memo);
	}
	
	@GetMapping("/memoDelete")
	public boolean memoDelete(int id) throws Exception{
		return memoService.deleteMemo(id);
	}
	
	@PostMapping("/memoUpdate")
	protected boolean memoUpdate(MemoDTO memo) throws Exception{
		return memoService.updateMemo(memo);
	}
}
