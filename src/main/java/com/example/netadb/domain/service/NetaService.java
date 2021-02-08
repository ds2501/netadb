package com.example.netadb.domain.service;

import com.example.netadb.domain.dao.NetaDao;
import com.example.netadb.domain.dao.entity.Neta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetaService {
    private final NetaDao netaDao;

    public NetaService(final NetaDao netaDao) {
        this.netaDao = netaDao;
    }

    public List<Neta> findAllNeta() {
        return netaDao.findAllNeta();
    }

    public Neta selectById(final String n_id) {
        return netaDao.selectById(n_id);
    }

    public void delete(final String n_id) {
        netaDao.delete(n_id);
    }

    public void update(final Neta neta) {
        netaDao.update(neta);
    }

    public void insert(final Neta neta) {
        netaDao.insert(neta);
    }
}
