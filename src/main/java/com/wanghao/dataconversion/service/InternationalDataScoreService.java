package com.wanghao.dataconversion.service;

import com.wanghao.dataconversion.dao.InternationalDataScoreDao;
import com.wanghao.dataconversion.entity.InternationalDataScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wanghao
 */
@Service
public class InternationalDataScoreService {

    private InternationalDataScoreDao internationalDataScoreDao;

    @Autowired
    public void setInternationalDataScoreDao(InternationalDataScoreDao internationalDataScoreDao) {
        this.internationalDataScoreDao = internationalDataScoreDao;
    }

    public List<InternationalDataScore> findAllByCountry(String country) {
        return internationalDataScoreDao.findAllByCountry(country);
    }

    public void insert(String sql) {
        internationalDataScoreDao.insert(sql);
    }

    public List<String> getCountrys() {
        return internationalDataScoreDao.getCountrys();
    }
}
