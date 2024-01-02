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

//	// 메모 날짜로 검색 service
//	// entity -> dto로 변환
//	public MemoDTO getActivist(int memoId) throws Exception {
//		Optional<Memo> memoEntity = memoDAO.findById(memoId);
//
//		if (memoEntity.get() == null) {
//			throw new NotExistException("검색하는 재능 기부자가 미 존재합니다.");
//		}
//
//		MemoDTO memo = mapper.map(memoEntity.get(), MemoDTO.class);
//
//		return memo;
//	}

	/*
	 * 모든 프로젝트 검색 entity와 dto 분리 필수 - ModelMapper 로 entity와 dto 호환
	 */
	public List<MemoDTO> getAllMemo() throws Exception {
		List<Memo> memoAll = memoDAO.findAll();

//		if (memoAll == null) {
//			throw new NotExistException("메모가 존재하지 않습니다");
//		}
		List<MemoDTO> memoDTOAll = Arrays.asList(mapper.map(memoAll, MemoDTO[].class));
		return memoDTOAll;
	}

	/*
	 * client가 입력한 데이터로 dto로 자동 생성 -> db에 insert시에 entity로 변환필요 (service에서 작업)
	 */
//	@Transactional
	public boolean addMemo(MemoDTO memo) throws MessageException {
		Memo memoEntity = mapper.map(memo, Memo.class); // dto를 entity로 변환
		memoEntity = memoDAO.save(memoEntity); // db에 insert 또는 update 하는 메소드
		return true;
	}


	// memo 삭제 메소드
	public boolean deleteMemo(int memoId) throws Exception {
//		notExistActivist(memoId);
		memoDAO.deleteById(memoId);
//		if(!result){
//			throw new NotExistException("재능 기부자 정보 삭제 실패");
//		}
		return true;
	}
}
