package com.demo.comentoStatistic.mapper;

import com.demo.comentoStatistic.domain.RequestInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RequestMapper {

    @Select("SELECT * FROM request_info")
    List<RequestInfo> findAll();

    // ⬇️ 공휴일/주말 제외한 로그인 수 계산을 위한 메서드도 추가 필요!
    @Select("""
        SELECT *
        FROM request_info
        WHERE LEFT(create_date, 6) = #{yearMonth}
    """)
    List<RequestInfo> findByYearMonth(String yearMonth);
}
