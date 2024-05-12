package com.academy.fintech.pe.exporter.db;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@AllArgsConstructor
public class ExporterRepository {
    @Autowired
    @Qualifier("exporterJdbcTemplate")
    private final JdbcTemplate jdbcTemplate;

    public void insert(Integer agreement_id, String status, Date disbursement_date, Date next_payment_date) {
        String sql = """
                insert into export_tasks (agreement_id, status, disbursement_date, next_payment_date)
                values (:agreement_id, :status, :disbursement_date, :next_payment_date)
                """;

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("agreement_id", agreement_id);
        parameters.addValue("status", status);
        parameters.addValue("disbursement_date", disbursement_date);
        parameters.addValue("next_payment_date", next_payment_date);

        jdbcTemplate.update(sql, parameters);
    }
}
