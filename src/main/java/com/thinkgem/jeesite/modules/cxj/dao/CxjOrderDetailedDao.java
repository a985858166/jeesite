/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cxj.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cxj.entity.CxjOrderDetailed;

/**
 * 生产通知单管理DAO接口
 * @author zhenying
 * @version 2019-01-18
 */
@MyBatisDao
public interface CxjOrderDetailedDao extends CrudDao<CxjOrderDetailed> {
	
}