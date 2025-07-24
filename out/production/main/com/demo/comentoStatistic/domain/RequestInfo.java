package com.demo.comentoStatistic.domain;

import lombok.Data;

@Data
public class RequestInfo {
    private int requestId;
    private String requestCode;
    private String userId;
    private String createDate;
}
