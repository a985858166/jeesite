/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cxj.service;

import java.util.List;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.cxj.email.SendEmailThread;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cxj.entity.CxjOrder;
import com.thinkgem.jeesite.modules.cxj.dao.CxjOrderDao;
import com.thinkgem.jeesite.modules.cxj.entity.CxjOrderDetailed;
import com.thinkgem.jeesite.modules.cxj.dao.CxjOrderDetailedDao;

/**
 * 生产通知单管理Service
 * @author zhenying
 * @version 2019-01-18
 */
@Service
@Transactional(readOnly = true)
public class CxjOrderService extends CrudService<CxjOrderDao, CxjOrder> {

	@Autowired
	private CxjOrderDetailedDao cxjOrderDetailedDao;
	@Autowired
	private SendEmailThread sendEmailThread;
	public CxjOrder get(String id) {
		CxjOrder cxjOrder = super.get(id);
		cxjOrder.setCxjOrderDetailedList(cxjOrderDetailedDao.findList(new CxjOrderDetailed(cxjOrder)));
		return cxjOrder;
	}
	
	public List<CxjOrder> findList(CxjOrder cxjOrder) {
		return super.findList(cxjOrder);
	}
	
	public Page<CxjOrder> findPage(Page<CxjOrder> page, CxjOrder cxjOrder) {
		return super.findPage(page, cxjOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(CxjOrder cxjOrder) {
	    //判断单号是否存在，如何不存在则证明这是个新订单
	    if (StringUtils.isBlank(cxjOrder.getOrderOn())){
	        //为该订单设置单号
	        cxjOrder.setOrderOn(DateUtils.getDate("yyyyMMdd").concat(String.valueOf((int)((Math.random()*9+1)*1000))));
	        //先判断通知人是否为空
	        if (StringUtils.isNotBlank(cxjOrder.getNotice())){
                String[] noteices = cxjOrder.getNotice().split(",");
                String[] emails = new String[noteices.length];
                //把id集合改为发件人的邮箱集合
                for (int i = 0; i < noteices.length; i++) {
                    emails[i] = UserUtils.get(noteices[i]).getEmail();
                }
                sendEmailThread.setEmail("你有新订单了","订单号为...".concat(cxjOrder.getOrderOn()),emails);
                sendEmailThread.runTask();
            }

        }
		super.save(cxjOrder);
		for (CxjOrderDetailed cxjOrderDetailed : cxjOrder.getCxjOrderDetailedList()){
			if (cxjOrderDetailed.getId() == null){
				continue;
			}
			if (CxjOrderDetailed.DEL_FLAG_NORMAL.equals(cxjOrderDetailed.getDelFlag())){
				if (StringUtils.isBlank(cxjOrderDetailed.getId())){
					cxjOrderDetailed.setCjxOrder(cxjOrder);
					cxjOrderDetailed.preInsert();
					cxjOrderDetailedDao.insert(cxjOrderDetailed);
				}else{
					cxjOrderDetailed.preUpdate();
					cxjOrderDetailedDao.update(cxjOrderDetailed);
				}
			}else{
				cxjOrderDetailedDao.delete(cxjOrderDetailed);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(CxjOrder cxjOrder) {
		super.delete(cxjOrder);
		cxjOrderDetailedDao.delete(new CxjOrderDetailed(cxjOrder));
	}
	
}