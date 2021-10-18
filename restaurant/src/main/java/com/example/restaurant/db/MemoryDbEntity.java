package com.example.restaurant.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoryDbEntity { // 모든 데이터베이스를 가진 애들은 이 class를 상속 받음

    protected Integer index; // int형의 default값이 0이므로 자동으로 Index에 0이 들어갈 수도 있어서 Intger로 써준다.
}
