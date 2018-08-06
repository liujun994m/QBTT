package hust.ioic.oa.service.impl;


import java.util.List;

import javax.annotation.Resource;

import hust.ioic.oa.service.RoleService;

import org.springframework.stereotype.Service;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.RoleDao;
import hust.ioic.oa.domain.Role;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role>  implements RoleService{
	@Resource
	private RoleDao roleDao;
	@Override
	public BaseDao<Role> getDao() {
	return roleDao;
}
/*	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}*/

}
