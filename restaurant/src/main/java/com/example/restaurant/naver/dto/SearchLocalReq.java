package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalReq { //naver search를 요청할때 사용

    private String query = "";

    private int display = 1;

    private int start = 1;

    private String sort = "random";
}
