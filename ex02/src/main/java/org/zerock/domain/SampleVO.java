package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
//비어있는 생성자를 만들때 사용하는 어노테이션(비어있는 생성자는 기본생성자를 의)
@NoArgsConstructor
public class SampleVO {
	private	int mno; 
	private String firstName;
	private String lastName;
}
