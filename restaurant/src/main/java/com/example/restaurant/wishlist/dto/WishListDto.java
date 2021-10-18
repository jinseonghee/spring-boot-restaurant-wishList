package com.example.restaurant.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListDto { //db의 Entity가 변경되면, Frontend 까지고 변수명을 변경해야 하는 영향을 끼친다.
                           //그렇지 않고 중간에 변환하는 과정만 있으면 되기 때문에, entity와 따로 분리해서 사용

    private Integer index;
    private String title; // 음식명, 장소명
    private String category; // 카테고리
    private String address; // 주소
    private String roadAddress; // 도로명
    private String homePageLink; // 홈페이지 주소
    private String imageLink; // 음식, 가게 이미지 주소
    private boolean isVisit; // 방문 여부
    private int visitCount; // 방문 횟수
    private LocalDateTime lastVisitDate; // 마지막 방문일자

}
