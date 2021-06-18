package com.wanghao.dataconversion.dto;

/**
 * @author wanghao
 */
public class AreaDto {

    /**
     * 区域
     */
    private String area;


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

    public AreaDto() {
    }

    public AreaDto(String area, String year, String rd_tech, String SCI_eco, String tech_ni, String SCI_rd, String SCI_ecr, String eri) {
        this.area = area;
        this.year = year;
        this.rd_tech = rd_tech;
        this.SCI_eco = SCI_eco;
        this.tech_ni = tech_ni;
        this.SCI_rd = SCI_rd;
        this.SCI_ecr = SCI_ecr;
        this.eri = eri;
    }


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
        return "AreaDto{" +
                "area='" + area + '\'' +
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

    