package hust.ioic.oa.view.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import hust.ioic.oa.base.BaseAction;
import hust.ioic.oa.domain.Center;
import hust.ioic.oa.domain.ReadScheme;

@Controller
@Scope("prototype")
public class FixedReadSettingAction extends BaseAction<ReadScheme> {
	private Integer[] centerIds;
	private Integer readSchemeId;

	public String readScheme() {
		if (model.getId() == null) {

			List<ReadScheme> readSchemeList = readSchemeService.findByEnprNo();
			ActionContext.getContext().put("readSchemeList", readSchemeList);
		} else {
			List<ReadScheme> readSchemeList = new ArrayList<ReadScheme>();
			readSchemeList.add(readSchemeService.getById(model.getId()));
			ActionContext.getContext().put("readSchemeList", readSchemeList);
		}
		return "readScheme";
	}

	public String add() {
		model.setEnprNo(getEnprNo());
		readSchemeService.save(model);
		return "toreadScheme";
	}

	public String edit() {
		ReadScheme readScheme = readSchemeService.getById(model.getId());
		readScheme.setSchemeID(model.getSchemeID());
		readScheme.setSchemeName(model.getSchemeName());
		readScheme.setBeginDay(model.getBeginDay());
		readScheme.setBeginTime(model.getBeginTime());
		readScheme.setDayInterval(model.getDayInterval());
		readScheme.setDayReadNum(model.getDayReadNum());
		readScheme.setHourInterval(model.getHourInterval());
		readScheme.setIsReadOnScheme(model.getIsReadOnScheme());
		readScheme.setOtherValue(model.getOtherValue());
		readScheme.setReadDayNum(model.getReadDayNum());
		readScheme.setReadInMonthEnd(model.getReadInMonthEnd());
		readScheme.setRemark(model.getRemark());
		readScheme.setReTryInterval(model.getReTryInterval());
		readScheme.setRetryNum(model.getRetryNum());
		readScheme.setTimerType(model.getTimerType());
		readSchemeService.update(readScheme);
		return "toreadScheme";
	}

	public String editUi() {
		ReadScheme readScheme = readSchemeService.getById(model.getId());
		/**
		 * 回显数据
		 */
		ActionContext.getContext().getValueStack().push(readScheme);

		// 校验抄表方案的抄表id和抄表名称不重复的参数
		List<Integer> schemeIDList = new ArrayList<>();
		List<String> schemeNameList = new ArrayList<String>();
		List<ReadScheme> readSchemes = readSchemeService.findByEnprNo();
		readSchemes.remove(readScheme);
		for (ReadScheme r : readSchemes) {
			schemeIDList.add(r.getSchemeID());
			schemeNameList.add(r.getSchemeName());

		}
		ActionContext.getContext().put("schemeIDList", schemeIDList);
		ActionContext.getContext().put("schemeNameList", schemeNameList);

		return "edit";
	}

	public String addUi() {
		// 校验抄表方案的抄表id和抄表名称不重复的参数
		List<Integer> schemeIDList = new ArrayList<>();
		List<String> schemeNameList = new ArrayList<String>();
		List<ReadScheme> readSchemes = readSchemeService.findByEnprNo();
		for (ReadScheme r : readSchemes) {
			schemeIDList.add(r.getSchemeID());
			schemeNameList.add(r.getSchemeName());

		}
		ActionContext.getContext().put("schemeIDList", schemeIDList);
		ActionContext.getContext().put("schemeNameList", schemeNameList);

		return "add";
	}

	public String center() {
		List<Center> centerList = centerService.findByEnprNo();
		List<ReadScheme> readSchemeList = readSchemeService.findByEnprNo();
		ActionContext.getContext().put("centerList", centerList);
		ActionContext.getContext().put("readSchemeList", readSchemeList);

		return "center";
	}

	public String setting() {

		List<Center> centerList = centerService.getByIds(centerIds);
		for (Center c : centerList) {
			c.setReadSchemeId(readSchemeId);
			centerService.update(c);
		}

		return "tocenter";
	}
	
	
	public String delete()
	{
		readSchemeService.delete(model.getId());
		return "toreadScheme";
	}
	
	public String top()
	{
		
		return "top";
	}
	
	public String buttom()
	{
		return "buttom";
	}
	

	public String list() {

		return "list";
	}

	public String left() {

		return "left";
	}

	public String right() {

		return "right";
	}

	public Integer[] getCenterIds() {
		return centerIds;
	}

	public void setCenterIds(Integer[] centerIds) {
		this.centerIds = centerIds;
	}

	public Integer getReadSchemeId() {
		return readSchemeId;
	}

	public void setReadSchemeId(Integer readSchemeId) {
		this.readSchemeId = readSchemeId;
	}

}
