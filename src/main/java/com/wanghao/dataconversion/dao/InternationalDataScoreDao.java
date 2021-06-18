package com.wanghao.dataconversion.dao;

import com.wanghao.dataconversion.entity.InternationalDataScore;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author wanghao
 */
@Repository
public class InternationalDataScoreDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<InternationalDataScore> findAll() {
        Class c = InternationalDataScore.class;
        String sql = "select * from international_data_score";
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(sql);
        query.setResultTransformer(new AliasToBeanResultTransformer(c));
        setScalar(query, c);
        return query.list();
    }

    public List<String> getCountrys() {
        String sql = "SELECT DISTINCT country FROM international_data_score";
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(sql);
        return query.list();
    }

    public List<InternationalDataScore> findAllByCountry(String country) {
        Class c = InternationalDataScore.class;
        String sql = "select * from international_data_score where  country = :country  order by year asc";
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(sql);
        query.setParameter("country", country);
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
