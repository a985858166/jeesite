/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cxj.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cxj.entity.CxjOrder;
import com.thinkgem.jeesite.modules.cxj.service.CxjOrderService;

/**
 * 生产通知单管理Controller
 * @author zhenying
 * @version 2019-01-18
 */
@Controller
@RequestMapping(value = "${adminPath}/cxj/cxjOrder")
public class CxjOrderController extends BaseController {

	@Autowired
	private CxjOrderService cxjOrderService;
	
	@ModelAttribute
	public CxjOrder get(@RequestParam(required=false) String id) {
		CxjOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cxjOrderService.get(id);
		}
		if (entity == null){
			entity = new CxjOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("cxj:cxjOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(CxjOrder cxjOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CxjOrder> page = cxjOrderService.findPage(new Page<CxjOrder>(request, response), cxjOrder); 
		model.addAttribute("page", page);
		return "modules/cxj/cxjOrderList";
	}

	@RequiresPermissions("cxj:cxjOrder:view")
	@RequestMapping(value = "form")
	public String form(CxjOrder cxjOrder, Model model) {
		model.addAttribute("cxjOrder", cxjOrder);
		return "modules/cxj/cxjOrderForm";
	}

	@RequiresPermissions("cxj:cxjOrder:edit")
	@RequestMapping(value = "save")
	public String save(CxjOrder cxjOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cxjOrder)){
			return form(cxjOrder, model);
		}
		cxjOrderService.save(cxjOrder);
		addMessage(redirectAttributes, "保存生产通知单信息成功");
		return "redirect:"+Global.getAdminPath()+"/cxj/cxjOrder/?repage";
	}
	
	@RequiresPermissions("cxj:cxjOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(CxjOrder cxjOrder, RedirectAttributes redirectAttributes) {
		cxjOrderService.delete(cxjOrder);
		addMessage(redirectAttributes, "删除生产通知单信息成功");
		return "redirect:"+Global.getAdminPath()+"/cxj/cxjOrder/?repage";
	}

}