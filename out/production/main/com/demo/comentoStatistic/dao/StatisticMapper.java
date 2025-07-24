package com.demo.comentoStatistic.dao;

import com.demo.comentoStatistic.dto.YearCountDto;
import com.demo.comentoStatistic.dto.YearMonthCountDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
public interface StatisticMapper {

    YearCountDto selectYearLogin(String year);
    YearMonthCountDto selectYearMonthLogin(String yearMonth);

    int selectWorkingDayLoginCount(String yearMonth);
}