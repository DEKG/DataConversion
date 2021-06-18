package com.wanghao.dataconversion.controller;

import com.wanghao.dataconversion.entity.InternationalDataScore;
import com.wanghao.dataconversion.service.InternationalDataScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghao
 */
@RestController
public class ConversionController {

    private InternationalDataScoreService internationalDataScoreService;

    @Autowired
    public void setInternationalDataScoreService(InternationalDataScoreService internationalDataScoreService) {
        this.internationalDataScoreService = internationalDataScoreService;
    }

    @RequestMapping("/conversion")
    @Transactional
    public String conversion() {
        System.out.println("数据写入 START");
        long startTime = System.currentTimeMillis();

        String tableName = "eri";
        // 获取国家列表
        List<String> countryList;
        countryList = internationalDataScoreService.getCountrys();
//        foreach(countryList);

        for (String country : countryList) {
            //获取该国家的所有年份的值
            List<InternationalDataScore> list = internationalDataScoreService.findAllByCountry(country);
//            foreach(list);
            List<String> tables = getTables();

            StringBuilder sb = new StringBuilder();
//            sb.append("insert into " + tableName + " values ( :counry");
            sb.append("insert into " + tableName + " values ( '" + country + "' ");
            for (InternationalDataScore score : list) {
                sb.append("," + score.getEri());
            }
            sb.append(")");
            System.out.println(sb.toString());
//            internationalDataScoreDao.insert(sb.toString(), country);
            internationalDataScoreService.insert(sb.toString());
        }

        long endTime = System.currentTimeMillis();
        System.out.println("数据写入 END");
        System.out.println("执行时间： " + (endTime - startTime) + "ms");
        return "ok";
    }

    /**
     * 打印输出list的内容
     *
     * @param list
     */
    private <T> void foreach(List<T> list) {
        for (Object object : list) {
            System.out.println(object.toString());
        }
    }

    private List<String> getTables() {
        List<String> tables = new ArrayList<>();
        tables.add("eri");
        tables.add("rdtech");
        tables.add("scieco");
        tables.add("sciecr");
        tables.add("scird");
        tables.add("techni");
        return tables;
    }
}
