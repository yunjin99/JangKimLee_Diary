package edu.fisa.lab.JangKimLeeDiary.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.fisa.lab.JangKimLeeDiary.model.entity.CheckList;

@Repository
public interface CheckListDAO extends JpaRepository<CheckList, Integer> {
	
	@Query("SELECT a FROM CheckList a WHERE a.checkDate = :date")
	List<CheckList> findByDate(@Param("date") String date);	
}