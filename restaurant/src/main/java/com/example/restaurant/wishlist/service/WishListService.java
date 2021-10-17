package com.example.restaurant.wishlist.service;

import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.wishlist.dto.WishListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;

    //controller를 통해서 query가 들어오면 결과를 return
    public WishListDto search(String query) {

        // 지역 검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query); //매개변수로 들어온 query를 저장
        var searchLocalRes = naverClient.searchLocal(searchLocalReq);

        if(searchLocalRes.getTotal() > 0) { //검색 된 데이터가 있는 경우

            var localItem = searchLocalRes.getItems().stream().findFirst().get(); //첫번째 값을 꺼내옴
            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>", ""); //정규식을 사용하여 이상한 문자가 오는것을 지움
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            // 이미지 검색 (지역검색을 했을경우, 지역검색의 내용이 있을때만 이미지 검색 가능)
            var searchImageRes = naverClient.searchImage(searchImageReq);

            if(searchImageRes.getTotal() > 0) {

                var imageItem = searchImageRes.getItems().stream().findFirst().get();

                // 결과를 리턴
                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());

                return result;
            }
        }

        return new WishListDto();  // 데이터가 아무것도 없을 땐 return null을 시켜도 되지만 빈 값 던짐
    }
}
