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

	/*
	 * 모든 프로젝트 검색 entity와 dto 분리 필수 - ModelMapper 로 entity와 dto 호환
	 */
	public List<MemoDTO> getAllMemo() throws Exception {
		List<Memo> memoAll = memoDAO.findAll();

		if (memoAll == null) {
			throw new NotExistException("메모가 존재하지 않습니다");
		}
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

//	/*
//	 * 존재하는 데이터의 컬럼값만 변경(update) - Spring Data jpa - sql관점에서 영구 저장 및 복원 = 트렌잭션 처리로
//	 * 표현 : tx 가 transaction의 약어
//	 * 
//	 * @Transactional - @Modifying @Query 로 사용자 정의로 개발된 delete or update 메소드들 호출하는
//	 * service 메소드에 필수 - commit과 rollback 처리하는 AOP
//	 */
//	// update, delete는 tx 설정 필수
//	@Transactional
//	// 재능 기부자 수정 메소드[ActivistDAO의 updateActivist()]
//	public boolean updateActivist(String activistId, String major) throws Exception {
//
//		notExistActivist(activistId);
//
//		// 업데이트 적용한 row 수 반환
//		int result = activistDAO.updateActivistByIdMajor(activistId, major);
//
//		if (result == 0) {
//			throw new NotExistException("재능 기부자 정보 갱신 실패");
//		}
//		return true;
//	}

	// 재능 기부자 삭제 메소드[ ActivistDAO.deleteActivist()]
	public boolean deleteMemo(int memoId) throws Exception {
//		notExistActivist(memoId);
		memoDAO.deleteById(memoId);
//		if(!result){
//			throw new NotExistException("재능 기부자 정보 삭제 실패");
//		}
		return true;
	}
}