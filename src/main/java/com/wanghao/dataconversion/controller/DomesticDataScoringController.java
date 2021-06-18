package com.wanghao.dataconversion.controller;

import com.wanghao.dataconversion.entity.DomesticDataScoring;
import com.wanghao.dataconversion.service.DomesticDataScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghao
 */
@RestController
public class DomesticDataScoringController {

    private DomesticDataScoringService domesticDataScoringService;

    @Autowired
    public void setDomesticDataScoringService(DomesticDataScoringService domesticDataScoringService) {
        this.domesticDataScoringService = domesticDataScoringService;
    }

    @RequestMapping("/Dconversion")
    @Transactional
    public String conversion() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("数据写入 START");
        long startTime = System.currentTimeMillis();

        List<String> tableList = getTables();
        // 获取省份列表
        List<String> countryList;
        countryList = domesticDataScoringService.getProvinces();

        for (String tableName : tableList) {
            
            for (String country : countryList) {
                //获取该省份的所有年份的值
                List<DomesticDataScoring> list = domesticDataScoringService.findAllByprovince(country);
                // 将各个年份的数据插入到对应的表中
                StringBuilder sb = new StringBuilder();
                sb.append("insert into " + tableName + " values ( '" + country + "' ");
                for (DomesticDataScoring score : list) {
                    Method method = DomesticDataScoring.class.getDeclaredMethod("get" + tableName);
                    String value = (String) method.invoke(score);
                    sb.append("," + value);
                }
                sb.append(")");
                System.out.println(sb.toString());
                domesticDataScoringService.insert(sb.toString());
            }

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
        tables.add("Erii");
        tables.add("Sci");
        return tables;
    }
}
