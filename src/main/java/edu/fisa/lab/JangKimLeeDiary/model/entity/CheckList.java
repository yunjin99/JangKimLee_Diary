package edu.fisa.lab.JangKimLeeDiary.model.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

@Table(name = "checklist")   
@Entity
public class CheckList {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "check_id")
	private int checkId;

	@Nonnull
	@Column(name = "check_date")
	private String checkDate;
	
	@Nonnull
	@Column(name = "check_status")
	private boolean checkStatus;

	@Nonnull
	@Column(name = "check_contents")
	private String checkContents;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("1. 체크리스트 번호 = ");
		builder.append(checkId);
		builder.append("2. 체크리스트 날짜 : ");
		builder.append(checkDate);
		builder.append("3. 실행 여부 : ");
		builder.append(checkStatus);
		builder.append("4. 체크리스트 내용 : ");
		builder.append(checkContents);
		return builder.toString();
	}

}
