package hust.ioic.oa.domain;

/**
 * 未完成命令队列java bean类，结构保持更TempCommandQueue一致
 * @author lecky
 *
 */
public class UncompeletCommandQueue extends TempCommandQueue {
	private String jsonofobj;
	private String enprNo;
	public String getJsonofobj() {
		return jsonofobj;
	}
	public void setJsonofobj(String jsonofobj) {
		this.jsonofobj = jsonofobj;
	}

	
	public UncompeletCommandQueue() {
	}
	
	public UncompeletCommandQueue(TempCommandQueue tempCommandQueue) {
		super(tempCommandQueue);
	}
	public String getEnprNo() {
		return enprNo;
	}
	public void setEnprNo(String enprNo) {
		this.enprNo = enprNo;
	}

	
	

}
