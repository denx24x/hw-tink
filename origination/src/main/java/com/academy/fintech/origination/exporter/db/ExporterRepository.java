package com.academy.fintech.origination.exporter.db;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@AllArgsConstructor
public class ExporterRepository {
    @Autowired
    @Qualifier("exporterJdbcTemplate")
    private final JdbcTemplate jdbcTemplate;

    public void insert(Integer application_id, String status) {
        String sql = """
                insert into export_tasks (application_id, status)
                values (:application_id, :status)
                """;

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("application_id", application_id);
        parameters.addValue("status", status);

        jdbcTemplate.update(sql, parameters);
    }
}
