/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.front.web;

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
import com.thinkgem.jeesite.modules.front.entity.Users;
import com.thinkgem.jeesite.modules.front.service.UsersService;

/**
 * 前端用户管理Controller
 * @author shims
 * @version 2017-09-05
 */
@Controller
@RequestMapping(value = "${adminPath}/front/users")
public class UsersController extends BaseController {

	@Autowired
	private UsersService usersService;
	
	@ModelAttribute
	public Users get(@RequestParam(required=false) String id) {
		Users entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = usersService.get(id);
		}
		if (entity == null){
			entity = new Users();
		}
		return entity;
	}
	
	@RequiresPermissions("front:users:view")
	@RequestMapping(value = {"list", ""})
	public String list(Users users, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Users> page = usersService.findPage(new Page<Users>(request, response), users); 
		model.addAttribute("page", page);
		return "modules/front/usersList";
	}

	@RequiresPermissions("front:users:view")
	@RequestMapping(value = "form")
	public String form(Users users, Model model) {
		model.addAttribute("users", users);
		return "modules/front/usersForm";
	}

	@RequiresPermissions("front:users:edit")
	@RequestMapping(value = "save")
	public String save(Users users, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, users)){
			return form(users, model);
		}
		usersService.save(users);
		addMessage(redirectAttributes, "保存前端用户管理成功");
		return "redirect:"+Global.getAdminPath()+"/front/users/?repage";
	}
	
	@RequiresPermissions("front:users:edit")
	@RequestMapping(value = "delete")
	public String delete(Users users, RedirectAttributes redirectAttributes) {
		usersService.delete(users);
		addMessage(redirectAttributes, "删除前端用户管理成功");
		return "redirect:"+Global.getAdminPath()+"/front/users/?repage";
	}

}