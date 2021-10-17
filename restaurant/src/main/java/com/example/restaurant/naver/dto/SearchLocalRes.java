package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchLocalRes {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<SearchLocalItem> items; // 밑에 내부 클래스의 이름을 맞춰줌.

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchLocalItem { // item/items는 List items에 item이 들어있다는 의미로 내부 클래스로 선언

        private String title;
        private String link;
        private String category;
        private String description;
        private String telephone;
        private String address;
        private String roadAddress;
        private int mapx;
        private int mapy;
    }
}
