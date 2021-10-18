package com.example.restaurant.controller;

import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor //autowired 대신 lombok을 사용
public class ApiController {

    private final WishListService wishListService; //ㅣombok을 사용해서 autowired 해줄 필요 없이 final 붙임

    @GetMapping("/search")
    public WishListDto search(@RequestParam String query) { //WishListDto로 return
        return wishListService.search(query);
    }

    @PostMapping("")
    public WishListDto add(@RequestBody WishListDto wishListDto) { //WishListDto를 받아서 데이터를 저장

        log.info("{}" , wishListDto); // 원하는 데이터 잘 들어오는지 확인
        return wishListService.add(wishListDto); //위에 데이터가 잘 들어오는지 확인이 되면 wishListDto를 담아서 wishListService.add로 넘긴다.
    }

    @GetMapping("/all")//Get으로 요청하면 전체 List를 return
    public List<WishListDto> findAll() { //dto 를 작성해서 실제 add가 되는지 확인하는 전체 List
        return wishListService.findAll();
    }

    @DeleteMapping("/{index}")
    public void delete(@PathVariable int index) {
        wishListService.delete(index);
    }

    @PostMapping("/{index}")
    public void addVisit(@PathVariable int index) {
        wishListService.addVisit(index); //클릭하면 리스트를 가져와서 화면을 보여주기 떄문에 index만 추가
    }
}
