package com.example.restaurant.wishlist.service;

import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.entity.WishListEntity;
import com.example.restaurant.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class  WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

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

    public WishListDto add(WishListDto wishListDto) {
        //데이터 베이스에 저장을 시켜주는 역할(저장시키기 위해선 MemoryDbEntity를 만듦 -> 이 MemoryDbEntity를 가지고 추가를 시켜주면 됨.)

        var entity = dtoToEntity(wishListDto); //그렇기 위해선 dto를 Entity로 바꿔서 저장
        var saveEntity = wishListRepository.save(entity); //entity로 바꾼 데이터를 repository의 save 메서드를 통해 저장

        return entityToDto(saveEntity); // 저장된 saveEntity를 다시 dto로 바꿔서 WishListDto형태로 return
    }

    public WishListEntity dtoToEntity(WishListDto wishListDto) { //dto -> entity

        var entity = new WishListEntity();
        entity.setIndex(wishListDto.getIndex());
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getCategory());
        entity.setAddress(wishListDto.getAddress());
        entity.setRoadAddress(wishListDto.getRoadAddress());
        entity.setHomePageLink(wishListDto.getHomePageLink());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setVisit(wishListDto.isVisit());
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setLastVisitDate(wishListDto.getLastVisitDate());

        return entity;
    }

    public WishListDto entityToDto(WishListEntity wishListEntity) {

        var dto = new WishListDto();
        dto.setIndex(wishListEntity.getIndex());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());

        return dto;
    }

    public List<WishListDto> findAll() {
        return wishListRepository.findAll() // WishListEntity로 넘어옴.
                .stream() // listAll에 stream을 걸어줌.
                .map(it -> entityToDto(it)) // map을 통해서 entity를 dto로 모두 바꿔줌
                .collect(Collectors.toList()); // collect를 통해서 List로 바꿔줌.
    }

    public void delete(int index) {
        wishListRepository.deleteById(index); // 특정 index 삭제
    }

    public void addVisit(int index) {
        var wishItem = wishListRepository.findById(index); //값이 있거나 없을수도 있어서 확인
        if(wishItem.isPresent()) { //값이있을떄 visitCount update(방문할때마다 1씩증가)
            var item = wishItem.get();
            item.setVisit(true);
            item.setVisitCount(item.getVisitCount() + 1);

        }
    }
}
