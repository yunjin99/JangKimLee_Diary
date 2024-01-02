package edu.fisa.lab.JangKimLeeDiary.model.dto;

import java.sql.Date;

import edu.fisa.lab.JangKimLeeDiary.model.entity.Checklist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChecklistDTO {

	private int checkId;
	private Date checkDate;
	private String checkContents;
	private boolean checkStatus;
	
	public Checklist toEntity() {
		return Checklist.builder().checkId(checkId).checkDate(checkDate).checkContents(checkContents).build();
	}
}
