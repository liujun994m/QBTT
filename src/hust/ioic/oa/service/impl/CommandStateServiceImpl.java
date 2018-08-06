package hust.ioic.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hust.ioic.oa.base.BaseServiceImpl;
import hust.ioic.oa.base.BaseDao;
import hust.ioic.oa.dao.CommandStateDao;
import hust.ioic.oa.domain.CommandState;
import hust.ioic.oa.service.CommandStateService;
@Service
public class CommandStateServiceImpl extends BaseServiceImpl<CommandState>
		implements CommandStateService {
	@Resource
	private CommandStateDao commandStateDao;
	@Override
	public BaseDao<CommandState> getDao() {

		return commandStateDao;
	}


}
