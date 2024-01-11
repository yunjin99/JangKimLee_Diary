package edu.fisa.lab.JangKimLeeDiary.model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fisa.lab.JangKimLeeDiary.exception.MessageException;
import edu.fisa.lab.JangKimLeeDiary.exception.NotExistException;
import edu.fisa.lab.JangKimLeeDiary.model.dto.CheckListDTO;
import edu.fisa.lab.JangKimLeeDiary.model.entity.CheckList;
import jakarta.transaction.Transactional;

@Service
public class CheckListService {

	@Autowired
	private CheckListDAO CheckListDAO;

	private ModelMapper mapper = new ModelMapper();

//	check date 값으로 해당 날짜의 checklist 정보 검색 service
//	entity -> dto로 변환
	
	public List<CheckListDTO> getCheckListbyDate(String checkdate) throws Exception {
		List<CheckList> checkEntity = CheckListDAO.findByDate(checkdate);
		List<CheckListDTO> checkList = Arrays.asList(mapper.map(checkEntity, CheckListDTO[].class));
		return checkList;
	}


	public List<CheckListDTO> getAllCheckList() throws SQLException, NotExistException {
		List<CheckList> CheckListAll = CheckListDAO.findAll();

		if (CheckListAll == null || CheckListAll.isEmpty()) {
			throw new NotExistException("검색하는 체크리스트가 없습니다.");
		}

		List<CheckListDTO> CheckListDTOAll = Arrays.asList(mapper.map(CheckListAll, CheckListDTO[].class));

		return CheckListDTOAll;
	}
	
	public void notExistCheckList(Integer CheckListId) throws Exception {

		boolean result = CheckListDAO.existsById(CheckListId);

		if (result != true) {
			throw new NotExistException("검색하는 체크리스트가 업습니다.");
		}

	}

	@Transactional
	public boolean addCheckList(CheckListDTO CheckList) throws MessageException {
		CheckList CheckListEntity = mapper.map(CheckList, CheckList.class); 
		CheckListEntity = CheckListDAO.save(CheckListEntity); 
		return true;
	}

	@Transactional
	public boolean updateCheckList(CheckListDTO checkList) throws Exception {
		deleteCheckList(checkList.getCheckId());
	    addCheckList(checkList);
	    return true;
	}

	public boolean deleteCheckList(Integer CheckListId) throws Exception {
		notExistCheckList(CheckListId);
		CheckListDAO.deleteById(CheckListId);
		return true;
	}

	
}
