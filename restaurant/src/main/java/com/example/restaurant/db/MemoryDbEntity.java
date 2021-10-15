package com.example.restaurant.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoryDbEntity { // 모든 데이터베이스를 가진 애들은 이 class를 상속 받음

    protected int index;
}
