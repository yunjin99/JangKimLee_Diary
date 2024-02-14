package edu.fisa.lab.JangKimLeeDiary.model;

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
	private CheckListDAO checkListDAO;

	private ModelMapper mapper = new ModelMapper();

	public List<CheckListDTO> getCheckListbyDate(String checkdate) throws Exception {
		List<CheckList> checkEntity = checkListDAO.findByDate(checkdate);
		List<CheckListDTO> checkList = Arrays.asList(mapper.map(checkEntity, CheckListDTO[].class));
		return checkList;
	}

	public List<CheckListDTO> getAllCheckList() throws Exception {
		List<CheckList> checkListAll = checkListDAO.findAll();
		if (checkListAll == null) {
			throw new NotExistException("고른 날짜의 체크리스트를 불러오는데 실패했습니다.");
		}
		List<CheckListDTO> checkListDTOAll = Arrays.asList(mapper.map(checkListAll, CheckListDTO[].class));
		return checkListDTOAll;
	}
	
	public void notExistCheckList(Integer checkListId) throws Exception {
		if (!checkListDAO.existsById(checkListId)) {
			throw new NotExistException("ID에 해당하는 체크리스트가 없습니다.");
		}
	}

	@Transactional
	public boolean addCheckList(CheckListDTO checkList) throws Exception {
		try {   
			checkListDAO.save(mapper.map(checkList, CheckList.class)); 
		}catch(Exception e){
			throw new MessageException("체크리스트 삽입에 실패했습니다.");
		}
		return true;
	}

	@Transactional
	public boolean updateCheckList(CheckListDTO checkList) throws Exception {
		notExistCheckList(checkList.getCheckId());
		try{
			CheckList cl = checkListDAO.findById(checkList.getCheckId()).get();
			cl.setCheckContents(checkList.getCheckContents());
			cl.setCheckStatus(checkList.isCheckStatus());
		} catch(Exception e) {
			throw new MessageException("체크리스트 수정에 실패했습니다.");
		}
		return true;
	}

	@Transactional
	public boolean deleteCheckList(Integer checkListId) throws Exception {
		notExistCheckList(checkListId);
		try {
			checkListDAO.deleteById(checkListId);
		} catch(Exception e) {
			throw new MessageException("체크리스트 삭제에 실패했습니다.");
		}
		return true;
	}

	
}
