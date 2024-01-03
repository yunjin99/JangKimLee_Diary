package edu.fisa.lab.JangKimLeeDiary.model;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fisa.lab.JangKimLeeDiary.exception.MessageException;
import edu.fisa.lab.JangKimLeeDiary.exception.NotExistException;
import edu.fisa.lab.JangKimLeeDiary.model.dto.MemoDTO;
import edu.fisa.lab.JangKimLeeDiary.model.entity.Memo;

@Service
public class MemoService {

	@Autowired
	private MemoDAO memoDAO;

	private ModelMapper mapper = new ModelMapper();

	// 메모 날짜로 검색 service
	public List<MemoDTO> getFindByDate(String date) throws Exception {
		List<Memo> memoEntity = memoDAO.memoByDate(date);
		List<MemoDTO> memo = Arrays.asList(mapper.map(memoEntity, MemoDTO[].class));
		return memo;
	}

	public List<MemoDTO> getAllMemo() throws Exception {
		List<Memo> memoAll = memoDAO.findAll();

		if (memoAll == null) {
			throw new NotExistException("메모가 존재하지 않습니다");
		}
		List<MemoDTO> memoDTOAll = Arrays.asList(mapper.map(memoAll, MemoDTO[].class));
		return memoDTOAll;
	}

//	@Transactional
	public boolean addMemo(MemoDTO memo) throws MessageException {
		Memo memoEntity = mapper.map(memo, Memo.class); // dto를 entity로 변환
		memoEntity = memoDAO.save(memoEntity); // db에 insert 또는 update 하는 메소드
		return true;
	}

	public boolean deleteMemo(int memoId) throws Exception {
//		notExistActivist(memoId);
		memoDAO.deleteById(memoId);
//		if(!result){
//			throw new NotExistException("재능 기부자 정보 삭제 실패");
//		}
		return true;
	}
	public boolean updateMemo(MemoDTO memo) throws Exception{
		deleteMemo(memo.getMemoId());
		addMemo(memo);
		return true;
	}
}