package hust.ioic.oa.utils;

/**
 * 集中器，采集器中转对象
 * 在做增加水表功能时，用来中转集中器和采集器选择下拉数据源
 * @author lecky
 *
 */
public class DeviceObject {
	private Integer id;
	private String name;
	private String isCenter;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsCenter() {
		return isCenter;
	}
	public void setIsCenter(String isCenter) {
		this.isCenter = isCenter;
	}
	public DeviceObject(Integer id, String name, String isCenter) {
		super();
		this.id = id;
		this.name = name;
		this.isCenter = isCenter;
	}
}
