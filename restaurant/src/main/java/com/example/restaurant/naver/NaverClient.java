package com.example.restaurant.naver;

import com.example.restaurant.naver.dto.SearchLocalReq;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NaverClient {

    @Value("${naver.client.id}") //yaml 파일에 있는 naver.client.id를 가져옴. 이 값이 없으면 application 자체가 구동되지 않음.
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;

    public void localSearch(SearchLocalReq searchLocalReq) {


    }

    public void imageSearch() {

    }



}
