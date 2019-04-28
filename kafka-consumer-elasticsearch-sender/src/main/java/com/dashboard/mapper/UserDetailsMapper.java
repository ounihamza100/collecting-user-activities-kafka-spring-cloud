package com.dashboard.mapper;

import com.dashboard.model.UserDetails;
import com.dashboard.model.UserDetailsToElastic;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * Created by haithem.ben-chaaben on 29/03/2019.
 */
public class UserDetailsMapper {
    public static UserDetailsToElastic modelToElastic(UserDetails model) {
        Long ts = Timestamp.from(Instant.now()).getTime();
        UserDetailsToElastic elastic = UserDetailsToElastic.builder()
                .executionTime(model.getExecutionTime())
                .userName(model.getUserName())
                .device(model.getDevice())
                .country(model.getCountry())
                .plateform(model.getPlateform())
                .city(model.getCity())
                .uri(model.getUri())
                .controllerName(model.getControllerName())
                .methodName(model.getMethodName())
                .microService(model.getMicroService())
                .port(model.getPort())
                .url(model.getUrl())
                .startRequest(model.getStartRequest())
                .dateCreation(ts)
                .build();
        return elastic;
    }
}
