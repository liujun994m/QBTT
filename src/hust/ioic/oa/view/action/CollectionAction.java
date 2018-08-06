package hust.ioic.oa.view.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import hust.ioic.oa.base.BaseAction;
import hust.ioic.oa.domain.Collection;
import hust.ioic.oa.domain.Center;
import hust.ioic.oa.domain.Device;
import hust.ioic.oa.domain.DeviceRelation;
import hust.ioic.oa.domain.Enterprise;

@Controller
@Scope("prototype")
public class CollectionAction extends BaseAction<Collection> {
	private Integer centerId;
	private Integer[] collectionIds;
	private Integer collectionId;

	public String addUi() {
		// 验证采集器地址和名称的参数
		List<Collection> collections = collectionService.findByEnprNo();
		List<String> collectionNameList = new ArrayList<String>();
		List<String> collectAddrList = new ArrayList<String>();
		for (Collection c : collections) {
			collectionNameList.add(c.getName());
			collectAddrList.add(c.getAddress());
		}
		ActionContext.getContext()
				.put("collectionNameList", collectionNameList);
		ActionContext.getContext().put("collectAddrList", collectAddrList);

		ActionContext.getContext().put("centerId", centerId);

		return "add";
	}

	public String look(){
		Collection collection =  collectionService.getById(collectionId);
		ActionContext.getContext().getValueStack().push(collection);
		return "collection_look";
	}
	
	public String add() {

		Center center = centerService.getById(centerId);
		model.setCenter(center);
		model.setEnprNo(getEnprNo());
		collectionService.save(model);
		ActionContext.getContext().put("centerId", centerId);

		return "tolist";
	}

	public String editUi() {
		Collection collection = collectionService.getById(collectionId);
		model.setId(collection.getId());
		model.setName(collection.getName());
		model.setRemark(collection.getRemark());
		model.setIsUse(collection.getIsUse());
		model.setAddress(collection.getAddress());
		model.setCenter(collection.getCenter());
		centerId = collection.getCenter().getId();
		ActionContext.getContext().put("centerId", centerId);

		// 验证采集器地址和名称的参数
		List<Collection> collections = collectionService.findByEnprNo();
		List<String> collectionNameList = new ArrayList<String>();
		List<String> collectAddrList = new ArrayList<String>();
		collections.remove(collection);
		for (Collection c : collections) {
			collectionNameList.add(c.getName());
			collectAddrList.add(c.getAddress());
		}
		ActionContext.getContext().put("collectionNameList", collectionNameList);
		ActionContext.getContext().put("collectAddrList", collectAddrList);

		return "edit";
	}

	public String edit() {
//		Center center = centerService.getById(centerId);
		Collection collection = collectionService.getById(collectionId);

		collection.setName(model.getName());
//		collection.setRemark(model.getRemark());
		collection.setIsUse(model.getIsUse());
		collection.setAddress(model.getAddress());
//		collection.setCenter(center);
//		List<DeviceRelation> deviceRelations = deviceRelationService
//				.getByCollectionId(model.getId());
//		for (DeviceRelation dr : deviceRelations) {
//			dr.setCenterId(centerId);
//			deviceRelationService.update(dr);
//		}
		collectionService.update(collection);
		return "tolist";
	}

	public String delete() {
		for (int k = 0; k < collectionIds.length; k++) {
			System.out.println(collectionIds[k]);
			collectionService.delete(collectionIds[k]);
			List<Integer> deviceids = deviceRelationService
					.deleteByCollectionId(collectionIds[k]);

			for (int i : deviceids) {
				Device d = deviceService.getById(i);
				d.setStatus(1);
				deviceService.update(d);
			}
		}

		ActionContext.getContext().put("centerId", centerId);
		return "tolist";
	}

	public Integer getCenterId() {
		return centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	public Integer[] getCollectionIds() {
		return collectionIds;
	}

	public void setCollectionIds(Integer[] collectionIds) {
		this.collectionIds = collectionIds;
	}

	public Integer getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	

}
