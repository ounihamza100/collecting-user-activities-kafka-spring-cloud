package com.dashboard.model;

import lombok.*;

/**
 * Created by haithem.ben-chaaben on 29/03/2019.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDetails {

    private long executionTime;
    private String userName;
    private String device;
    private String country;
    private String plateform;
    private String city;
    private String uri;
    private String controllerName;
    private String methodName;
    private String microService;
    private String port;
    private StringBuffer url;
    private Long startRequest;
}

