package hust.ioic.oa.utils;

import hust.ioic.oa.domain.Operator;

import java.io.Serializable;
import java.net.Proxy.Type;

import org.hibernate.EmptyInterceptor;

import com.opensymphony.xwork2.ActionContext;

public class SqlIntercepter extends EmptyInterceptor {

	// 保存数据
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, org.hibernate.type.Type[] types) {
		String handleLog = "——————————————————————————save操作————————————————————————"
				+ "\r\n";
		handleLog += "操作人员编号："
				+ ((Operator) ActionContext.getContext().getSession()
						.get("operator")).getOperatorNo() + "\r\n";
		handleLog += "操作的ip地址："
				+ ActionContext.getContext().getSession().get("ip") + "\r\n";
		handleLog += "entity： " + entity.getClass().getName() + "\r\n";
		System.out.println(entity.getClass().getName() + " onsave");
		for (int i = 0; i < propertyNames.length; i++) {
			handleLog += "	" + propertyNames[i] + ": " + state[i] + "\r\n";

		}
		handleLog += "-------------------------------操作结束--------------------------------------"
				+ "\r\n" + "\r\n";
		StaticConstant.handleLog.info(handleLog);
		return super.onSave(entity, id, state, propertyNames, types);
	}

	// 删除数据
	@Override
	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, org.hibernate.type.Type[] types) {
		String handleLog = "——————————————————————————delete操作————————————————————————"
				+ "\r\n";
		handleLog += "操作人员编号："
				+ ((Operator) ActionContext.getContext().getSession()
						.get("operator")).getOperatorNo() + "\r\n";
		handleLog += "操作的ip地址："
				+ ActionContext.getContext().getSession().get("ip") + "\r\n";
		handleLog += "entity： " + entity.getClass().getName() + "\r\n";
		System.out.println(entity.getClass().getName() + " onsave");
		for (int i = 0; i < propertyNames.length; i++) {
			handleLog += "	" + propertyNames[i] + ": " + state[i] + "\r\n";

		}
		handleLog += "-------------------------------操作结束--------------------------------------"
				+ "\r\n" + "\r\n";
		StaticConstant.handleLog.info(handleLog);
		super.onDelete(entity, id, state, propertyNames, types);
	}

	// 修改数据
	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, org.hibernate.type.Type[] types) {
		String handleLog = "——————————————————————————修改操作————————————————————————"
				+ "\r\n";
		handleLog += "操作人员编号："
				+ ((Operator) ActionContext.getContext().getSession()
						.get("operator")).getOperatorNo() + "\r\n";
		handleLog += "操作的ip地址："
				+ ActionContext.getContext().getSession().get("ip") + "\r\n";
		handleLog += "entity： " + entity.getClass().getName() + "\r\n";
		System.out.println(entity.getClass().getName() + " onsave");
		for (int i = 0; i < propertyNames.length; i++) {
			handleLog += "	" + propertyNames[i] + ": 修改前：" + previousState[i]
					+ "-->修改后：" + currentState[i] + "\r\n";
		}
		handleLog += "	-------------------------------操作结束--------------------------------------"
				+ "\r\n" + "\r\n";
		StaticConstant.handleLog.info(handleLog);
		return super.onFlushDirty(entity, id, currentState, previousState,
				propertyNames, types);
	}

}
