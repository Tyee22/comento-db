package com.demo.comentoStatistic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class YearCountDto {
    private String year;
    private Integer totCnt;
}
