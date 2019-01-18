/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.esi.web;

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
import com.thinkgem.jeesite.modules.esi.entity.EsiElecDynamic;
import com.thinkgem.jeesite.modules.esi.service.EsiElecDynamicService;

/**
 * 电商动态Controller
 * @author zhenying
 * @version 2019-01-16
 */
@Controller
@RequestMapping(value = "${adminPath}/esi/esiElecDynamic")
public class EsiElecDynamicController extends BaseController {

	@Autowired
	private EsiElecDynamicService esiElecDynamicService;
	
	@ModelAttribute
	public EsiElecDynamic get(@RequestParam(required=false) String id) {
		EsiElecDynamic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = esiElecDynamicService.get(id);
		}
		if (entity == null){
			entity = new EsiElecDynamic();
		}
		return entity;
	}
	
	@RequiresPermissions("esi:esiElecDynamic:view")
	@RequestMapping(value = {"list", ""})
	public String list(EsiElecDynamic esiElecDynamic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EsiElecDynamic> page = esiElecDynamicService.findPage(new Page<EsiElecDynamic>(request, response), esiElecDynamic); 
		model.addAttribute("page", page);
		return "modules/esi/esiElecDynamicList";
	}

	@RequiresPermissions("esi:esiElecDynamic:view")
	@RequestMapping(value = "form")
	public String form(EsiElecDynamic esiElecDynamic, Model model) {
		model.addAttribute("esiElecDynamic", esiElecDynamic);
		return "modules/esi/esiElecDynamicForm";
	}

	@RequiresPermissions("esi:esiElecDynamic:edit")
	@RequestMapping(value = "save")
	public String save(EsiElecDynamic esiElecDynamic, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, esiElecDynamic)){
			return form(esiElecDynamic, model);
		}
		esiElecDynamicService.save(esiElecDynamic);
		addMessage(redirectAttributes, "保存电商动态成功");
		return "redirect:"+Global.getAdminPath()+"/esi/esiElecDynamic/?repage";
	}
	
	@RequiresPermissions("esi:esiElecDynamic:edit")
	@RequestMapping(value = "delete")
	public String delete(EsiElecDynamic esiElecDynamic, RedirectAttributes redirectAttributes) {
		esiElecDynamicService.delete(esiElecDynamic);
		addMessage(redirectAttributes, "删除电商动态成功");
		return "redirect:"+Global.getAdminPath()+"/esi/esiElecDynamic/?repage";
	}

}