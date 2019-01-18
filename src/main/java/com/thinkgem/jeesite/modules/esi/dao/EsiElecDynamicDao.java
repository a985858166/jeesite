/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.esi.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.esi.entity.EsiElecDynamic;

/**
 * 电商动态DAO接口
 * @author zhenying
 * @version 2019-01-16
 */
@MyBatisDao
public interface EsiElecDynamicDao extends CrudDao<EsiElecDynamic> {
	
}