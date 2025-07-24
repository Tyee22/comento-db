package com.demo.comentoStatistic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HolidayMapper {

    @Select("SELECT DATE_FORMAT(holiday_date, '%Y-%m-%d') FROM holidays")
    List<String> findAllHolidayDates();
}
