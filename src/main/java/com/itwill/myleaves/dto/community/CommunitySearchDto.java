package com.itwill.myleaves.dto.community;
import lombok.Data;

@Data
public class CommunitySearchDto {

	 // request parameter의 name과 동일하게 작성
    private String type;  // t, c, a,..
    private String keyword;
    private String userId;
}



