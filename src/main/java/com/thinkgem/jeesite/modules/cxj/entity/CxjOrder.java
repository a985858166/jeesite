/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cxj.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 生产通知单管理Entity
 * @author zhenying
 * @version 2019-01-18
 */
public class CxjOrder extends DataEntity<CxjOrder> {
	
	private static final long serialVersionUID = 1L;
	private String orderOn;		// 订单编号
	private String customer;		// 客户
	private Date logisticsCreateTime;		// 发货时间
	private Integer logisticsStatus;		// 发货状态
	private String requirement;		// 产品要求
	private String otherFunctions;		// 其他功能
	private String notice;		// 通知者集合id
    private String noticeName;  //通知者集合名称
	private List<CxjOrderDetailed> cxjOrderDetailedList = Lists.newArrayList();		// 子表列表
	
	public CxjOrder() {
		super();
	}

	public CxjOrder(String id){
		super(id);
	}

	@Length(min=0, max=50, message="订单编号长度必须介于 0 和 50 之间")
	public String getOrderOn() {
		return orderOn;
	}

	public void setOrderOn(String orderOn) {
		this.orderOn = orderOn;
	}
	
	@Length(min=0, max=50, message="客户长度必须介于 0 和 50 之间")
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLogisticsCreateTime() {
		return logisticsCreateTime;
	}

	public void setLogisticsCreateTime(Date logisticsCreateTime) {
		this.logisticsCreateTime = logisticsCreateTime;
	}
	
	public Integer getLogisticsStatus() {
		return logisticsStatus;
	}

	public void setLogisticsStatus(Integer logisticsStatus) {
		this.logisticsStatus = logisticsStatus;
	}
	
	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	
	public String getOtherFunctions() {
		return otherFunctions;
	}

	public void setOtherFunctions(String otherFunctions) {
		this.otherFunctions = otherFunctions;
	}
	
	@Length(min=0, max=1024, message="通知者集合长度必须介于 0 和 1024 之间")
	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	public List<CxjOrderDetailed> getCxjOrderDetailedList() {
		return cxjOrderDetailedList;
	}

	public void setCxjOrderDetailedList(List<CxjOrderDetailed> cxjOrderDetailedList) {
		this.cxjOrderDetailedList = cxjOrderDetailedList;
	}

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }
}