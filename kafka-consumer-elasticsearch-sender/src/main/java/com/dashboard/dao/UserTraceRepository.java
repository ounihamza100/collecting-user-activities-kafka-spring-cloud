package com.dashboard.dao;


import com.dashboard.model.UserDetailsToElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by haithem.ben-chaaben on 29/03/2019.
 */
public interface UserTraceRepository extends ElasticsearchRepository<UserDetailsToElastic, Long> {
}
