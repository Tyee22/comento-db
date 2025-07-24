package com.demo.comentoStatistic.controller;

import com.demo.comentoStatistic.dto.YearCountDto;
import com.demo.comentoStatistic.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PingController {

    @Autowired
    StatisticService statisticService;

    @RequestMapping(value="/api/v1/logins/{year}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YearCountDto> getYearLoginCount(@PathVariable("year") String year){
        YearCountDto dto = statisticService.getYearLogins(year); // DTO 반환으로 수정
        return ResponseEntity.ok(dto);
    }

    @RequestMapping(value="/api/v1/logins/{year}/{month}", produces = "application/json")
    @ResponseBody
    public Object getYearMonthLoginCount(@PathVariable("year") String year, @PathVariable("month") String month){
        return ResponseEntity.ok(statisticService.getYearMonthLogins(year, month));
    }
}
