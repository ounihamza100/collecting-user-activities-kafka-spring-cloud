package com.dashboard.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by haithem.ben-chaaben on 29/03/2019.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Document(indexName = "dashboard", type = "users_details")
public class UserDetailsToElastic {

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
    @Id
    private Long dateCreation;
}
