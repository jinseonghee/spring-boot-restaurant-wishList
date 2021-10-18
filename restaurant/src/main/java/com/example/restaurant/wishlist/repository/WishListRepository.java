package com.example.restaurant.wishlist.repository;

import com.example.restaurant.db.MemoryDbRepositoryAbstract;
import com.example.restaurant.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;


@Repository // 데이터 베이스를 저장하는 곳
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> { //추상클래스에서 <T> 라는 부분이 모두 WishListEntity로 대체되서 동작
     //지금은 memory DB로 만들었지만, jpa를 사용하려면 JPA만 설정해서 이 클래스만 바꿔주면 된다. (extends를 JpaRepository)로 변경
}
