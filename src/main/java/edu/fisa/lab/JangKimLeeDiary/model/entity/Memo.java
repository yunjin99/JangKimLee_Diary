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
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor     
@Builder

@Table(name = "memo")
@Entity
public class Memo {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "memo_id")
	private int memoId;

	@Nonnull
	@Column(name = "memo_date")
	private String memoDate;

	@Nonnull
	@Column(name = "memo_contents")
	private String memoContents;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("1. 메모 번호 = ");
		builder.append(memoId);
		builder.append("2. 메모 날짜 : ");
		builder.append(memoDate);
		builder.append("3. 메모 내용 : ");
		builder.append(memoContents);
		return builder.toString();
	}
}
