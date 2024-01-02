package edu.fisa.lab.JangKimLeeDiary.model.dto;

import edu.fisa.lab.JangKimLeeDiary.model.entity.Memo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Builder
public class MemoDTO {

	private String memoId;
	private Date memoDate;
	private String memoContents;

	public Memo toEntity() {
		return Memo.builder().memoId(memoId).memoDate(memoDate).memoContents(memoContents).build();
	}
}
