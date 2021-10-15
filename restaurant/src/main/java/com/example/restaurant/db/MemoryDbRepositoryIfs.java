package com.example.restaurant.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> { //제네릭을 통해 타입 지정

    Optional<T> findById(int index); //해당 타입에 관해서 한가지를 optional 하게 return (해당 타입 <T>에 대한 entity를 찾아서 return)
    T save(T entity);
    void deleteById(int index);
    List<T> listAll();
}
