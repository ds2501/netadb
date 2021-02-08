package com.example.netadb.domain.dao;

import com.example.netadb.domain.dao.entity.Neta;
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
public class NetaDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Transactional
    public void insert(Neta neta) {
        final String SQL =
                "INSERT INTO neta (g_id, neta_name, description, link, created_at, updated_at)" +
                        "values (:g_id,:neta_name,:description,:link,NOW(3),NOW(3));";
        SqlParameterSource params = new BeanPropertySqlParameterSource(neta);
        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(neta));
    }

    public Neta selectById(String n_id) {
        final String SQL =
                "SELECT n_id, neta_name, description, link " +
                        "FROM neta where n_id = :n_id; ";
        SqlParameterSource params = new MapSqlParameterSource().addValue("n_id", n_id);
        try {
            return jdbcTemplate.queryForObject(SQL, params, new BeanPropertyRowMapper<>(Neta.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Transactional
    public void update(final Neta neta) {
        final String SQL =
                "UPDATE neta set "+
                        "neta_name = :neta_name, " +
                        "description = :description, " +
                        "link = :link, " +
                        "updated_at = NOW(3) " +
                        "where n_id = :n_id; ";

        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(neta));
    }

    @Transactional
    public void delete(String n_id) {
        final String SQL =
                "DELETE FROM neta where n_id = :n_id; ";

        //MapSqlParameterSource().addValueでidを読み込み
        SqlParameterSource params = new MapSqlParameterSource().addValue("n_id", n_id);
        jdbcTemplate.update(SQL, params);
    }

    public List<Neta> findAllNeta() {
        final String SQL =
                "SELECT n.n_id, n.g_id, g.combi_name, n.neta_name, n.description, n.link " +
                        "FROM neta as n " +
                        "INNER JOIN geinin as g " +
                        "ON n.g_id = g.g_id; ";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Neta.class));
    }
}
