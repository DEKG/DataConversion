package com.wanghao.dataconversion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author  wanghao
 * @date 2021-06-18 
 */

@Entity
@Table ( name ="domestic_data_scoring" )
public class DomesticDataScoring  {

	private String serial_number;

	private String area;

	private String province;

	private String year;

	private String sci;

	private String erii;

	public DomesticDataScoring () {
	}

	public DomesticDataScoring (String serial_number, String area, String province, String year, String sci, String erii) {
		this.serial_number = serial_number;
		this.area = area;
		this.province = province;
		this.year = year;
		this.sci = sci;
		this.erii = erii;
	}

	@Id
   	@Column(name = "serial_number" )
	public String getSerial_number() {
		return this.serial_number;
	}

	public void setSerial_number(String seriialNumber) {
		this.serial_number = serial_number;
	}

   	@Column(name = "area" )
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

   	@Column(name = "province" )
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

   	@Column(name = "year" )
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

   	@Column(name = "sci" )
	public String getSci() {
		return this.sci;
	}

	public void setSci(String sci) {
		this.sci = sci;
	}

   	@Column(name = "erii" )
	public String getErii() {
		return this.erii;
	}

	public void setErii(String erii) {
		this.erii = erii;
	}

}
