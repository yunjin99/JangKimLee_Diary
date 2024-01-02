package edu.fisa.lab.JangKimLeeDiary.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.fisa.lab.JangKimLeeDiary.model.entity.Checklist;

@Repository
public interface ChecklistDAO extends JpaRepository<Checklist, Integer> {
	@Modifying
	@Query("update Checklist a set a.checkContents=:checkContents where a.id=:id")
	int updateChecklistByIdcheckContents(@Param("id") Integer id, @Param("checkContents") String checkContents);
}