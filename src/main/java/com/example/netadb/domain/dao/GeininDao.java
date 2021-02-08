package com.example.netadb.domain.dao;

import com.example.netadb.domain.dao.entity.Geinin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class GeininDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Transactional
    public void insert(Geinin geinin) {
        final String SQL =
                "INSERT INTO geinin (combi_name, debut, description, office, created_at, updated_at) " +
                        "values (:combi_name,:debut,:description,:office,NOW(3),NOW(3)); ";
        //BeanPropertySqlParameterSourceで変数読み込み
        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(geinin));
    }

    public Geinin selectById(String g_id) {
        final String SQL =
                "SELECT g_id, combi_name, debut, description, office " +
                        "FROM geinin where g_id = :g_id; ";
        SqlParameterSource params = new MapSqlParameterSource().addValue("g_id", g_id);
        try {
            return jdbcTemplate.queryForObject(SQL, params, new BeanPropertyRowMapper<>(Geinin.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Transactional
    public void update(final Geinin geinin) {
        final String SQL =
                "UPDATE geinin set "+
                    "combi_name = :combi_name, " +
                    "debut = :debut, " +
                    "description = :description, " +
                    "office = :office, " +
                    "updated_at = NOW(3) " +
                    "where g_id = :g_id; ";

        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(geinin));
    }

    @Transactional
    public void delete(String g_id) {
        final String SQL =
                "DELETE FROM geinin where g_id = :g_id; ";

        //MapSqlParameterSource().addValueでidを読み込み
        SqlParameterSource params = new MapSqlParameterSource().addValue("g_id", g_id);
        jdbcTemplate.update(SQL, params);
    }

    public List<Geinin> findAllGeinin() {
        final String SQL =
                "SELECT g_id, combi_name, debut, description, office " +
                        "FROM geinin; ";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Geinin.class));
    }

}
