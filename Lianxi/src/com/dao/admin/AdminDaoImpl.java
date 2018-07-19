package com.dao.admin;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.Admin;

public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao{

	//添加管理员
	public void save(Admin admin) {
		this.getHibernateTemplate().save(admin);
	}

//	按名称查找管理员
	public Admin getAdmin(String adminName) {
		String sql = "from Admin a where a.adminName='"+adminName+"'";
		List<Admin> list = (List<Admin>)this.getHibernateTemplate().find(sql);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void delete(int adminId) {
		this.getHibernateTemplate().delete(adminId);
	}

	@Override
	public void update(Admin admin) {
		this.getHibernateTemplate().update(admin);
	}

	@Override
	public Admin findById(int adminId) {
		Admin admin = this.getHibernateTemplate().get(Admin.class, adminId);
		return admin;
	}

	@Override
	public List<Admin> findAll() {
		String sql = "from Admin";
		List<Admin> list = this.getHibernateTemplate().find(sql);
		return list;
	}

	@Override
	public Admin login(String adminName, String adminPassword) {
		String hql = "from Admin a where a.adminName ='"+adminName+"' and a.adminPassword ='"+adminPassword+"'";
		List<Admin> list = (List<Admin>) this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
