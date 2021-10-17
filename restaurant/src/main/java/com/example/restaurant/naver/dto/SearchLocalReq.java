package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalReq { //naver search를 요청할때 사용

    private String query = "";
    private int display = 1;
    private int start = 1;
    private String sort = "random";

    //위의 변수를 queryparameter에 하나씩 넣기 번거로워 메서드를 하나 만든 후 map을 key, value의 형태로 만들어서 사용
    public MultiValueMap<String, String> toMultiValueMap() { //Map<key, value> 의 형태로 queryPamameter가 들어가는 형식에 맞춰줌.

        var map = new LinkedMultiValueMap<String, String>(); //var는 타입추론이라 따로 타입을 써주지 않아도 컴파일러가 자동으로 타입 추론

        map.add("query", query);
        map.add("display", String.valueOf(display)); // display는 int타입인데, value의 형태를 넣을땐 String으로 넣어줘야 하기 때문에 형변환
        map.add("start", String.valueOf(start));
        map.add("sort", sort);

        return map;
    }
}
