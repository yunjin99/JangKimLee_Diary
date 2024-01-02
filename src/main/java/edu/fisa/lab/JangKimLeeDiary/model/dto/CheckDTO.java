/*
CREATE TABLE probono (
       probono_id          	VARCHAR2(50) PRIMARY KEY,
       probono_name      VARCHAR2(50) NOT NULL,
       probono_purpose  	VARCHAR2(200) NOT NULL
);  */
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
public class CheckDTO {

	private String checkId;
	private Date checkDate;
	private String checkContents;
	private boolean checkStatus;
	
	public Checklist toEntity() {
		return Checklist.builder().checkId(checkId).checkDate(checkDate).checkContents(checkContents).build();
	}
}
