package hust.ioic.oa.utils;

import hust.ioic.oa.domain.Function;
import hust.ioic.oa.domain.Operator;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Installer {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();

		// ====================设置超级管理员=====================
		Operator user = new Operator();
		user.setUsername("admin");
		user.setPassword(DigestUtils.md5Hex("1234"));
		user.setOperatorNo("0000");
		user.setGender("男");
		user.setStatus(StaticConstant.OPERATOR_STATUS_USE);
		session.save(user);
		// ====================保存权限数据=====================
		Function menu, menu1, menu2, menu3, menu4, menu5, menu6,menu7;
		// --------------------
		menu = new Function("系统管理", null, null);
		menu1 = new Function("岗位管理", "/role_list", menu);
		menu2 = new Function("操作员管理", "/operator_list", menu);
		menu3 = new Function("区域管理", "/area_list", menu);
		menu4=new Function("资料导入", "/datumLoad_list", menu);
		/*menu5 = new Function("资料导出", "/exportDevice _list", menu);*/
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		/*session.save(menu5);*/
		//
		session.save(new Function("岗位列表", "/role_list", menu1));
		session.save(new Function("岗位删除", "/role_delete", menu1));
		session.save(new Function("岗位添加", "/role_add", menu1));
		session.save(new Function("岗位修改", "/role_edit", menu1));
		// // ---------------------
		session.save(new Function("操作员列表", "/operator_list", menu2));
		session.save(new Function("操作员删除", "/operator_delete", menu2));
		session.save(new Function("操作员添加", "/operator_add", menu2));
		session.save(new Function("操作员修改", "/operator_edit", menu2));
		session.save(new Function("初始化密码", "/operator_initPassword", menu2));
		// // --------------------
		session.save(new Function("区域列表", "/area_list", menu3));
		session.save(new Function("区域删除", "/area_delete", menu3));
		session.save(new Function("区域添加", "/area_add", menu3));
		session.save(new Function("区域修改", "/area_edit", menu3));
		//
		menu = new Function("抄表配置", null, null);
		//
		menu1 = new Function("设备类型管理", "/deviceType_list", menu);
		menu2 = new Function("设备管理", "/device_list", menu);
		
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		// //
		menu = new Function("抄表管理", null, null);
		//
		menu1 = new Function("抄表管理", "/tempCQ__list", menu);
		menu2 = new Function("监控信息", "/monitor_list", menu);	
		menu3 = new Function("定时抄表方案管理", "/fixedReadSetting_list", menu);
		menu4 = new Function("用水量导出", "/waterExport_list", menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
	
	

	}

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		System.out.println(installer);
		installer.install();
	}
}
