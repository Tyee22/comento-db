package com.demo.comentoStatistic.service;

import com.demo.comentoStatistic.domain.RequestInfo;
import com.demo.comentoStatistic.dto.YearCountDto;
import com.demo.comentoStatistic.mapper.HolidayMapper;
import com.demo.comentoStatistic.mapper.RequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final RequestMapper requestMapper;
    private final HolidayMapper holidayMapper;
    private final HolidayService holidayService;

    // 월별 단순 로그인 수
    public int getLoginCount(String yearMonth) {
        return requestMapper.findByYearMonth(yearMonth).size();
    }

    // 주말+공휴일 제외 로그인 수
    public int getWorkingDayLoginCount(String yearMonth) {
        List<RequestInfo> all = requestMapper.findByYearMonth(yearMonth);
        List<String> holidays = holidayMapper.findAllHolidayDates();
        holidayService.setHolidays(holidays);

        return (int) all.stream()
                .map(r -> {
                    try {
                        return LocalDate.parse(r.getCreateDate().substring(0, 6), DateTimeFormatter.ofPattern("yyMMdd"));
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(date -> date != null)
                .filter(date -> {
                    DayOfWeek day = date.getDayOfWeek();
                    return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
                })
                .filter(date -> !holidayService.isHoliday(date))
                .count();
    }

    // 연도별 로그인 수 → DTO 반환
    public YearCountDto getYearLogins(String year) {
        String yearPrefix = (year.length() == 4) ? year.substring(2) : year;

        List<RequestInfo> all = requestMapper.findAll();
        int count = (int) all.stream()
                .filter(r -> r.getCreateDate() != null && r.getCreateDate().length() >= 2)
                .filter(r -> r.getCreateDate().startsWith(yearPrefix)) // ← 변경된 변수 사용
                .count();

        return new YearCountDto(year, count);
    }


    // 연도+월별 처리 필요 시
    public Object getYearMonthLogins(String year, String month) {
        String yearMonth = year.substring(2) + month; // 예: 24 + 08 = 2408
        return getLoginCount(yearMonth);
    }
}
