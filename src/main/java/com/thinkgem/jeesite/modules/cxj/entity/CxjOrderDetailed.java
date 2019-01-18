/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cxj.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 生产通知单管理Entity
 * @author zhenying
 * @version 2019-01-18
 */
public class CxjOrderDetailed extends DataEntity<CxjOrderDetailed> {
	
	private static final long serialVersionUID = 1L;
	private CxjOrder cjxOrder;		// 订单编号 父类
	private String standard;		// 规格
	private String company;		// 单位
	private Integer number;		// 数量
	private Integer goodNumber;		// 发货数量
	
	public CxjOrderDetailed() {
		super();
	}

	public CxjOrderDetailed(String id){
		super(id);
	}

	public CxjOrderDetailed(CxjOrder cjxOrder){
		this.cjxOrder = cjxOrder;
	}

	@Length(min=0, max=50, message="订单编号长度必须介于 0 和 50 之间")
	public CxjOrder getCjxOrder() {
		return cjxOrder;
	}

	public void setCjxOrder(CxjOrder cjxOrder) {
		this.cjxOrder = cjxOrder;
	}
	
	@Length(min=0, max=50, message="规格长度必须介于 0 和 50 之间")
	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}
	
	@Length(min=0, max=50, message="单位长度必须介于 0 和 50 之间")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public Integer getGoodNumber() {
		return goodNumber;
	}

	public void setGoodNumber(Integer goodNumber) {
		this.goodNumber = goodNumber;
	}
	
}