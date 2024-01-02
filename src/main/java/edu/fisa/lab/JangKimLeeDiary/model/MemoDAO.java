package edu.fisa.lab.JangKimLeeDiary.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.fisa.lab.JangKimLeeDiary.model.entity.Memo;

@Repository
public interface MemoDAO extends JpaRepository<Memo, Integer> {
}