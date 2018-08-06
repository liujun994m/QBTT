package hust.ioic.oa.domain;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

/**
 * 集中器，采集器，终端水表的拓扑结构表
 * 后期做页面返回时间的时候需要补充该类
 * @author lecky
 *
 */
public class DeviceRelation implements Serializable {
@Expose private Integer id;
@Expose private Integer centerDownInterface;
@Expose private Integer collectionDownInterface;
@Expose private Integer centerId;
@Expose private Integer collectionId;
@Expose private String enprNo;
	private Device device;
	public Integer getCenterId() {
		return centerId;
	}
	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCenterDownInterface() {
		return centerDownInterface;
	}
	public void setCenterDownInterface(Integer centerDownInterface) {
		this.centerDownInterface = centerDownInterface;
	}
	public Integer getCollectionDownInterface() {
		return collectionDownInterface;
	}
	public void setCollectionDownInterface(Integer collectionDownInterface) {
		this.collectionDownInterface = collectionDownInterface;
	}
	public String getEnprNo() {
		return enprNo;
	}
	public void setEnprNo(String enprNo) {
		this.enprNo = enprNo;
	}
	
}
