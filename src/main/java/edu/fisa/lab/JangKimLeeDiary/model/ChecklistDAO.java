package edu.fisa.lab.JangKimLeeDiary.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.fisa.lab.JangKimLeeDiary.model.entity.Checklist;

@Repository
public interface ChecklistDAO extends JpaRepository<Checklist, Integer> {

}
