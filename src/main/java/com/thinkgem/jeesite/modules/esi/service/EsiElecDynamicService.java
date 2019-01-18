/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.esi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.esi.entity.EsiElecDynamic;
import com.thinkgem.jeesite.modules.esi.dao.EsiElecDynamicDao;

/**
 * 电商动态Service
 * @author zhenying
 * @version 2019-01-16
 */
@Service
@Transactional(readOnly = true)
public class EsiElecDynamicService extends CrudService<EsiElecDynamicDao, EsiElecDynamic> {

	public EsiElecDynamic get(String id) {
		return super.get(id);
	}
	
	public List<EsiElecDynamic> findList(EsiElecDynamic esiElecDynamic) {
		return super.findList(esiElecDynamic);
	}
	
	public Page<EsiElecDynamic> findPage(Page<EsiElecDynamic> page, EsiElecDynamic esiElecDynamic) {
		return super.findPage(page, esiElecDynamic);
	}
	
	@Transactional(readOnly = false)
	public void save(EsiElecDynamic esiElecDynamic) {
		super.save(esiElecDynamic);
	}
	
	@Transactional(readOnly = false)
	public void delete(EsiElecDynamic esiElecDynamic) {
		super.delete(esiElecDynamic);
	}
	
}