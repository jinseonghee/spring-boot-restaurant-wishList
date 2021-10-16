package com.example.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T> {
    // MemoryDbEntity를 상속받은 제네릭 타입 T가 필요

    private final List<T> db = new ArrayList<>(); //데이터를 저장할 arrayList(List에 데이터를 쭉 넣는 형태로 만듦)
    private int index = 0; //db에서 primary key로, 자동으로 auto generate 해주는 index id

    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it -> it.getIndex() == index).findFirst(); //getIndex의 값이 index의 값과 동일한 것에, findFirst()의 값을 return
        //filter는 db stream의 타입에 대한 부분, getIndex는 MemoryDbEntity에 정의된 변수 index를 가리킴
        //Generic Type에 MemoryDbEntity를 상속 받았기 때문에 제네릭 타입에 해당되는 변수에서 getIndex 메소드 접근 가능.
        //return Optional.empty();
    }

    @Override
    public T save(T entity) {

        var optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();
        //it.getIndex()가 찾고자 하는 entity의 getIndex와 동일하면 이미 데이터가 있는 case

        if(optionalEntity.isEmpty()) {
            //db에 데이터가 없는 경우
            index ++ ; // 데이터가 없을 경우 index 값을 1씩 증가
            entity.setIndex(index); //entity에 index setting
            db.add(entity); //db에 위에서 넘겨온 entity 저장
            return entity;

        } else {
            //db에 이미 데이터가 있는 경우
            //데이터가 있는 경우는 데이터를 update
            var preIndex = optionalEntity.get().getIndex(); //사전에 이미 있던 index를 가져옴
            entity.setIndex(preIndex); //새로운 데이터에 setIndex를 통해 해당값 세팅

            deleteById(preIndex); //기존의 데이터를 지움
            db.add(entity); //새로운 entity 추가
            return entity;

        }
    }

    @Override
    public void deleteById(int index) {

        var optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst(); //optional한 개체를 찾아옴

        if(optionalEntity.isPresent()) { //optionalEntity에 데이터가 이미 있는 경우
            db.remove(optionalEntity.get()); // 해당 object와 동일한 object를 remove
        }
    }

    @Override
    public List<T> listAll() {
        return db; //데이터베이스의 모든 내용을 return
    }
}
