package com.dashboard.model;

import lombok.*;

/**
 * Hamza.Ouni
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

