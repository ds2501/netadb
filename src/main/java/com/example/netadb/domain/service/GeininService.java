package com.example.netadb.domain.service;

import com.example.netadb.domain.dao.GeininDao;
import com.example.netadb.domain.dao.entity.Geinin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeininService {

    private final GeininDao geininDao;

    public GeininService(final GeininDao geininDao) {
        this.geininDao = geininDao;
    }

    public List<Geinin> findAllGeinin() {
        return geininDao.findAllGeinin();
    }

    public Geinin selectById(final String g_id) {
        return geininDao.selectById(g_id);
    }

    public void delete(final String g_id) {
        geininDao.delete(g_id);
    }

    public void update(final Geinin geinin) {
        geininDao.update(geinin);
    }

    public void insert(final Geinin geinin) {
        geininDao.insert(geinin);
    }

}
