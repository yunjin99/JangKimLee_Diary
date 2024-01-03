package edu.fisa.lab.JangKimLeeDiary.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.fisa.lab.JangKimLeeDiary.model.entity.Checklist;
import edu.fisa.lab.JangKimLeeDiary.model.entity.Memo;

@Repository
public interface ChecklistDAO extends JpaRepository<Checklist, Integer> {
	@Modifying
	@Query("update Checklist a set a.checkContents=:checkContents where a.id=:id")
	int updateChecklistByIdcheckContents(@Param("id") Integer id, @Param("checkContents") String checkContents);
	
	@Query("SELECT a FROM Checklist a WHERE a.checkDate = :date")
	List<Checklist> findByDate(@Param("date") String date);	
	
	@Modifying
	@Query("update Checklist a set a.checkStatus=:checkStatus where a.id=:id")
	int updateChecklistByIdcheckStatus(@Param("id") Integer id, @Param("checkStatus") boolean checkStatus);
}