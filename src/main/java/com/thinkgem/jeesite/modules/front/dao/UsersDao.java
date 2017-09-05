/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.front.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.front.entity.Users;

/**
 * 前端用户管理DAO接口
 * @author shims
 * @version 2017-09-05
 */
@MyBatisDao
public interface UsersDao extends CrudDao<Users> {
	
}