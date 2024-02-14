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
import jakarta.transaction.Transactional;

@Service
public class MemoService {

	@Autowired
	private MemoDAO memoDAO;

	private ModelMapper mapper = new ModelMapper();

	public List<MemoDTO> getFindByDate(String date) throws Exception {
		List<Memo> memoEntity = memoDAO.memoByDate(date);
		List<MemoDTO> memo = Arrays.asList(mapper.map(memoEntity, MemoDTO[].class));
		return memo;
	}

	public List<MemoDTO> getAllMemo() throws Exception {
		List<Memo> memoAll = memoDAO.findAll();
		if (memoAll == null) {
			throw new NotExistException("고른 날짜의 메모를 불러오는데 실패했습니다");
		}
		List<MemoDTO> memoDTOAll = Arrays.asList(mapper.map(memoAll, MemoDTO[].class));
		return memoDTOAll;
	}
	
	public void notExistMemo(Integer memoId) throws Exception {
		if (!memoDAO.existsById(memoId)) {
			throw new NotExistException("검색하는 체크리스트가 업습니다.");
		}
	}

	@Transactional
	public boolean addMemo(MemoDTO memo) throws Exception {
		try {
			memoDAO.save(mapper.map(memo, Memo.class));
		} catch(Exception e) {
			throw new MessageException("메모 삽입에 실패했습니다.");
		}
		return true;
	}
    
	@Transactional
	public boolean deleteMemo(int memoId) throws Exception {
		notExistMemo(memoId);
		try {
			memoDAO.deleteById(memoId);
		} catch(Exception e) {
			throw new MessageException("메모 삭제에 실패했습니다.");
		}
		return true;
	}
	
	@Transactional
	public boolean updateMemo(MemoDTO memo) throws Exception{
		notExistMemo(memo.getMemoId());
		try {
			memoDAO.findById(memo.getMemoId()).get().setMemoContents(memo.getMemoContents());
		} catch(Exception e) {
			throw new MessageException("메모 수정에 실패했습니다.");
		}
		return true;
	}
}