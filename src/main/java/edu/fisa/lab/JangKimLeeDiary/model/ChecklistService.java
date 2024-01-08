package edu.fisa.lab.JangKimLeeDiary.model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import edu.fisa.lab.JangKimLeeDiary.exception.MessageException;
import edu.fisa.lab.JangKimLeeDiary.exception.NotExistException;
import edu.fisa.lab.JangKimLeeDiary.model.dto.ChecklistDTO;
import edu.fisa.lab.JangKimLeeDiary.model.entity.Checklist;
import jakarta.transaction.Transactional;

@Service
public class ChecklistService {

	@Autowired
	private ChecklistDAO ChecklistDAO;

	private ModelMapper mapper = new ModelMapper();

//	check date 값으로 해당 날짜의 checklist 정보 검색 service
//	entity -> dto로 변환
	
	public List<ChecklistDTO> getChecklistbyDate(String checkdate) throws Exception {
		List<Checklist> checkEntity = ChecklistDAO.findByDate(checkdate);
		List<ChecklistDTO> checklist = Arrays.asList(mapper.map(checkEntity, ChecklistDTO[].class));
		return checklist;
	}


	public List<ChecklistDTO> getAllChecklist() throws SQLException, NotExistException {
		List<Checklist> ChecklistAll = ChecklistDAO.findAll();

		if (ChecklistAll == null || ChecklistAll.isEmpty()) {
			throw new NotExistException("검색하는 체크리스트가 없습니다.");
		}

		List<ChecklistDTO> ChecklistDTOAll = Arrays.asList(mapper.map(ChecklistAll, ChecklistDTO[].class));

		return ChecklistDTOAll;
	}
	
	public void notExistChecklist(Integer ChecklistId) throws Exception {

		boolean result = ChecklistDAO.existsById(ChecklistId);

		if (result != true) {
			throw new NotExistException("검색하는 체크리스트가 업습니다.");
		}

	}

	@Transactional
	public boolean addChecklist(ChecklistDTO Checklist) throws MessageException {
		Checklist ChecklistEntity = mapper.map(Checklist, Checklist.class); 
		ChecklistEntity = ChecklistDAO.save(ChecklistEntity); 
		return true;
	}

	@Transactional
	public boolean updateChecklist(ChecklistDTO checklist) throws Exception {
		deleteChecklist(checklist.getCheckId());
	    addChecklist(checklist);
	    return true;
	}

	public boolean deleteChecklist(Integer ChecklistId) throws Exception {
		notExistChecklist(ChecklistId);
		ChecklistDAO.deleteById(ChecklistId);
		return true;
	}

	
}
