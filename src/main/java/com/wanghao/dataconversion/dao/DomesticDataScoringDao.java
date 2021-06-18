package com.wanghao.dataconversion.dao;

import com.wanghao.dataconversion.dto.AreaDto;
import com.wanghao.dataconversion.entity.DomesticDataScoring;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author wanghao
 */
@Repository
public class DomesticDataScoringDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<DomesticDataScoring> findAll() {
        Class c = DomesticDataScoring.class;
        String sql = "select * from domestic_data_scoring";
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(sql);
        query.setResultTransformer(new AliasToBeanResultTransformer(c));
        setScalar(query, c);
        return query.list();
    }

    public List<String> getProvinces() {
        String sql = "SELECT DISTINCT province FROM domestic_data_scoring";
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(sql);
        return query.list();
    }

    public List<String> getAreas() {
        String sql = "SELECT DISTINCT area FROM domestic_data_scoring";
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(sql);
        return query.list();
    }

    public List<DomesticDataScoring> findAllByprovince(String province) {
        Class c = DomesticDataScoring.class;
        String sql = "select * from domestic_data_scoring where  province = :province  order by year asc";
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(sql);
        query.setParameter("province", province);
        query.setResultTransformer(new AliasToBeanResultTransformer(c));
        setScalar(query, c);
        return query.list();
    }

    public List<AreaDto> findAllByArea(String area) {
        Class c = AreaDto.class;
        String sql = "SELECT area,`year`,SUM( CONVERT (rd_tech, DECIMAL(10,6) ) ) AS rd_tech,SUM( CONVERT (SCI_eco, DECIMAL(10,6) ) ) AS SCI_eco,SUM( CONVERT (tech_ni, DECIMAL(10,6) ) ) AS tech_ni,SUM( CONVERT (SCI_rd, DECIMAL(10,6) ) ) AS SCI_rd,SUM( CONVERT (SCI_ecr, DECIMAL(10,6) ) ) AS SCI_ecr,SUM( CONVERT (ERI, DECIMAL(10,6) ) ) AS ERI  FROM domestic_data_scoring where    area = :area GROUP BY `year` ORDER BY `year` ASC;";
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(sql);
        query.setParameter("area", area);
        query.setResultTransformer(new AliasToBeanResultTransformer(c));
        setScalar(query, c);
        return query.list();
    }

    public List<AreaDto> findAllSum() {
        Class c = AreaDto.class;
        String sql = "SELECT '总和' AS area,`year`,SUM( CONVERT (rd_tech, DECIMAL(10,6) ) ) AS rd_tech,SUM( CONVERT (SCI_eco, DECIMAL(10,6) ) ) AS SCI_eco,SUM( CONVERT (tech_ni, DECIMAL(10,6) ) ) AS tech_ni,SUM( CONVERT (SCI_rd, DECIMAL(10,6) ) ) AS SCI_rd,SUM( CONVERT (SCI_ecr, DECIMAL(10,6) ) ) AS SCI_ecr,SUM( CONVERT (ERI, DECIMAL(10,6) ) ) AS ERI  FROM domestic_data_scoring GROUP BY `year` ORDER BY `year` ASC;";
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(sql);
        query.setResultTransformer(new AliasToBeanResultTransformer(c));
        setScalar(query, c);
        return query.list();
    }


    public void insert(String sql) {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(sql);
        query.executeUpdate();
    }

    /**
     * カラムの型を明示指定する。
     * <p>
     * 指定しないとクエリ依存で動的に決まってしまう。<br>
     * 1つでも指定すると未指定カラムは null になってしまう模様
     *
     * @since 2019-12-03 #【支払管理】売上仕訳の月跨ぎ対応(Sano)
     */
    @SuppressWarnings("deprecation")
    private void setScalar(NativeQuery<?> query, Class<?> resultClass) {
        Field[] fields = resultClass.getDeclaredFields();
        for (Field field : fields) {
            String propertyName = field.getName();
            String type = field.getType().getName();
            if (type.equalsIgnoreCase("java.lang.Integer") || type.equalsIgnoreCase("int")) {
                query.addScalar(propertyName, IntegerType.INSTANCE);
            } else if (type.equalsIgnoreCase("java.lang.Long") || type.equalsIgnoreCase("long")) {
                query.addScalar(propertyName, LongType.INSTANCE);
            } else if (type.equalsIgnoreCase("java.lang.Double") || type.equalsIgnoreCase("double")) {
                query.addScalar(propertyName, DoubleType.INSTANCE);
            } else if (type.equalsIgnoreCase("java.math.BigDecimal")) {
                query.addScalar(propertyName, BigDecimalType.INSTANCE);
            } else if (type.equalsIgnoreCase("java.math.BigInteger")) {
                query.addScalar(propertyName, BigIntegerType.INSTANCE);
            } else if (type.equalsIgnoreCase("java.util.Date")) {
                query.addScalar(propertyName, DateType.INSTANCE);
            } else {
                query.addScalar(propertyName, StringType.INSTANCE);
            }
        }
    }


}

