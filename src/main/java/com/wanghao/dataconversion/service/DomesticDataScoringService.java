package com.wanghao.dataconversion.service;

import com.wanghao.dataconversion.dao.DomesticDataScoringDao;
import com.wanghao.dataconversion.dto.AreaDto;
import com.wanghao.dataconversion.entity.DomesticDataScoring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghao
 */
@Service
public class DomesticDataScoringService {

    private DomesticDataScoringDao domesticDataScoringDao;

    @Autowired
    public void setDomesticDataScoringDao(DomesticDataScoringDao domesticDataScoringDao) {
        this.domesticDataScoringDao = domesticDataScoringDao;
    }

    public List<DomesticDataScoring> findAllByprovince(String province) {
        return domesticDataScoringDao.findAllByprovince(province);
    }

    public List<AreaDto> findAllByArea(String area) {
        return domesticDataScoringDao.findAllByArea(area);
    }

    public List<AreaDto> findAllSum() {
        return domesticDataScoringDao.findAllSum();
    }

    public void insert(String sql) {
        domesticDataScoringDao.insert(sql);
    }

    public List<String> getProvinces() {
        return domesticDataScoringDao.getProvinces();
    }

    public List<String> getAreas() {
        return domesticDataScoringDao.getAreas();
    }
}
