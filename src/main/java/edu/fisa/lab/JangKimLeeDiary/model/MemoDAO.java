package edu.fisa.lab.JangKimLeeDiary.model;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import edu.fisa.lab.JangKimLeeDiary.model.dto.MemoDTO;
import edu.fisa.lab.JangKimLeeDiary.model.entity.Memo;
@Repository
public interface MemoDAO extends JpaRepository<Memo, Integer> {
	
	@Query("SELECT a FROM Memo a WHERE a.memoDate = :date")
	List<Memo> memoByDate(@Param("date") String date);	
}