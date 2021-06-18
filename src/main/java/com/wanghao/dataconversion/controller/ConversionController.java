package com.wanghao.dataconversion.controller;

import com.wanghao.dataconversion.dto.AreaDto;
import com.wanghao.dataconversion.entity.InternationalDataScore;
import com.wanghao.dataconversion.service.InternationalDataScoreService;
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
public class ConversionController {

    private InternationalDataScoreService internationalDataScoreService;

    @Autowired
    public void setInternationalDataScoreService(InternationalDataScoreService internationalDataScoreService) {
        this.internationalDataScoreService = internationalDataScoreService;
    }

    @RequestMapping("/conversion")
    @Transactional
    public String conversion() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("数据写入 START");
        long startTime = System.currentTimeMillis();

        List<String> tableList = getTables();
        // 获取国家列表
        List<String> countryList;
        countryList = internationalDataScoreService.getCountrys();
        //获取区域列表
        List<String> areaList;
        areaList = internationalDataScoreService.getAreas();

        for (String tableName : tableList) {

            String methodName = getMehod(tableName);
            for (String country : countryList) {
                //获取该国家的所有年份的值
                List<InternationalDataScore> list = internationalDataScoreService.findAllByCountry(country);
                // 将各个年份的数据插入到对应的表中
                StringBuilder sb = new StringBuilder();
                sb.append("insert into " + tableName + " values ( '" + country + "' ");
                for (InternationalDataScore score : list) {
                    Method method = InternationalDataScore.class.getDeclaredMethod("get" + methodName);
                    String value = (String) method.invoke(score);
                    sb.append("," + value);
                }
                sb.append(")");
                System.out.println(sb.toString());
                internationalDataScoreService.insert(sb.toString());
            }

            for (String area : areaList) {
                //获取该区域的汇总数据
                List<AreaDto> list = internationalDataScoreService.findAllByArea(area);

                // 将各个区域的汇总数据插入到对应的表中
                StringBuilder sb = new StringBuilder();
                sb.append("insert into " + tableName + " values ( '" + area + "' ");
                for (AreaDto score : list) {
                    Method method = AreaDto.class.getDeclaredMethod("get" + methodName);
                    String value = (String) method.invoke(score);
                    sb.append("," + value);
                }
                sb.append(")");
                System.out.println(sb.toString());
                internationalDataScoreService.insert(sb.toString());
            }

            //获取该区域的汇总数据
            List<AreaDto> list = internationalDataScoreService.findAllSum();

            //插入全球汇总数据
            StringBuilder sb = new StringBuilder();
            sb.append("insert into " + tableName + " values ( '全球'");
            for (AreaDto score : list) {
                Method method = AreaDto.class.getDeclaredMethod("get" + methodName);
                String value = (String) method.invoke(score);
                sb.append("," + value);
            }
            sb.append(")");
            System.out.println(sb.toString());
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
        tables.add("Eri");
        tables.add("Rdtech");
        tables.add("Scieco");
        tables.add("Sciecr");
        tables.add("Scird");
        tables.add("Techni");
        return tables;
    }

    private String getMehod(String tableName) {
        switch (tableName) {
            case "Eri":
                return tableName;
            case "Rdtech":
                return "Rd_tech";
            case "Scieco":
                return "SCI_eco";
            case "Sciecr":
                return "SCI_ecr";
            case "Scird":
                return "SCI_rd";
            case "Techni":
                return "Tech_ni";
            default:
                return null;
        }
    }


}
