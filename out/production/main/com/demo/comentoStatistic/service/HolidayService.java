package com.demo.comentoStatistic.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HolidayService {
    private final Set<LocalDate> holidaySet = new HashSet<>();

    public void setHolidays(List<String> holidayDateStrings) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        holidaySet.clear();
        for (String dateStr : holidayDateStrings) {
            holidaySet.add(LocalDate.parse(dateStr, formatter));
        }
    }

    public boolean isHoliday(LocalDate date) {
        return holidaySet.contains(date);
    }
}
