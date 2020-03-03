package com.dashboard.dao;


import com.dashboard.model.UserDetailsToElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Hamza.Ouni
 */
public interface UserTraceRepository extends ElasticsearchRepository<UserDetailsToElastic, Long> {
}
