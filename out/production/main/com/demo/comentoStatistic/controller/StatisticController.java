package com.demo.comentoStatistic.controller;

import com.demo.comentoStatistic.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    // 기존 로그인 수 조회 API
    @GetMapping(value = "/logins/{yearMonth}", produces = "application/json")
    public ResponseEntity<Map<String, Object>> getLoginCount(@PathVariable("yearMonth") String yearMonth) {
        int count = statisticService.getLoginCount(yearMonth);
        Map<String, Object> response = new HashMap<>();
        response.put("yearMonth", yearMonth);
        response.put("loginCount", count);
        response.put("is_success", true);
        return ResponseEntity.ok(response);
    }

    // 공휴일 + 주말 제외 로그인 수 조회 API
    @GetMapping(value = "/logins/workingday/{yearMonth}", produces = "application/json")
    public ResponseEntity<Map<String, Object>> getWorkingDayLoginCount(@PathVariable("yearMonth") String yearMonth) {
        int count = statisticService.getWorkingDayLoginCount(yearMonth);
        Map<String, Object> response = new HashMap<>();
        response.put("yearMonth", yearMonth);
        response.put("workingDayLoginCount", count);
        response.put("is_success", true);
        return ResponseEntity.ok(response);
    }
}
