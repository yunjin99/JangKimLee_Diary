package edu.fisa.lab.JangKimLeeDiary.model.dto;

import edu.fisa.lab.JangKimLeeDiary.model.entity.CheckList;
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
public class CheckListDTO {

	private int checkId;  
	private String checkDate;
	private String checkContents;
	private boolean checkStatus;
	
	public CheckList toEntity() {
		return CheckList.builder().checkId(checkId).checkDate(checkDate).checkContents(checkContents).build();
	}
	
	 public void setCheckStatus(boolean checkStatus) {
	        this.checkStatus = checkStatus;
	}
}
