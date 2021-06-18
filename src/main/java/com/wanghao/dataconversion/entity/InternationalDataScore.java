package com.wanghao.dataconversion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wanghao
 * @date 2021-06-17
 */

@Entity
@Table(name = "international_data_score")
public class InternationalDataScore {

    /**
     * 序号
     */
    private String serial_number;

    /**
     * 区域
     */
    private String area;

    /**
     * 国家
     */
    private String country;

    /**
     * 国家代码
     */
    private String country_code;

    /**
     * year
     */
    private String year;

    /**
     * 经济赶超下技术能力TCI
     */
    private String rd_tech;

    /**
     * 经济赶超下社会能力SCI
     */
    private String SCI_eco;

    /**
     * 创新发展创新能力IDI
     */
    private String tech_ni;

    /**
     * 创新发展下社会能力SCI
     */
    private String SCI_rd;

    /**
     * 增长韧性下社会能力SCI
     */
    private String SCI_ecr;

    /**
     * 增长韧性下增长韧性ERI
     */
    private String eri;

    public InternationalDataScore() {
    }

    public InternationalDataScore(String serial_number, String area, String country, String country_code, String year, String rd_tech, String SCI_eco, String tech_ni, String SCI_rd, String SCI_ecr, String eri) {
        this.serial_number = serial_number;
        this.area = area;
        this.country = country;
        this.country_code = country_code;
        this.year = year;
        this.rd_tech = rd_tech;
        this.SCI_eco = SCI_eco;
        this.tech_ni = tech_ni;
        this.SCI_rd = SCI_rd;
        this.SCI_ecr = SCI_ecr;
        this.eri = eri;
    }

    @Id
    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRd_tech() {
        return rd_tech;
    }

    public void setRd_tech(String rd_tech) {
        this.rd_tech = rd_tech;
    }

    public String getSCI_eco() {
        return SCI_eco;
    }

    public void setSCI_eco(String SCI_eco) {
        this.SCI_eco = SCI_eco;
    }

    public String getTech_ni() {
        return tech_ni;
    }

    public void setTech_ni(String tech_ni) {
        this.tech_ni = tech_ni;
    }

    public String getSCI_rd() {
        return SCI_rd;
    }

    public void setSCI_rd(String SCI_rd) {
        this.SCI_rd = SCI_rd;
    }

    public String getSCI_ecr() {
        return SCI_ecr;
    }

    public void setSCI_ecr(String SCI_ecr) {
        this.SCI_ecr = SCI_ecr;
    }

    public String getEri() {
        return eri;
    }

    public void setEri(String eri) {
        this.eri = eri;
    }

    @Override
    public String toString() {
        return "InternationalDataScore{" +
                "serial_number='" + serial_number + '\'' +
                ", area='" + area + '\'' +
                ", country='" + country + '\'' +
                ", country_code='" + country_code + '\'' +
                ", year='" + year + '\'' +
                ", rd_tech='" + rd_tech + '\'' +
                ", SCI_eco='" + SCI_eco + '\'' +
                ", tech_ni='" + tech_ni + '\'' +
                ", SCI_rd='" + SCI_rd + '\'' +
                ", SCI_ecr='" + SCI_ecr + '\'' +
                ", eri='" + eri + '\'' +
                '}';
    }
}

    