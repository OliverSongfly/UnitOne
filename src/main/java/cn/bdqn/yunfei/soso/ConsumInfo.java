package cn.bdqn.yunfei.soso;
/**
 * 资费信息类
 */
import java.io.Serializable;
@SuppressWarnings("all")
public class ConsumInfo implements Serializable {
	private String cardNumber;
	private String type;
	private int consumData;
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getConsumData() {
		return consumData;
	}
	public void setConsumData(int consumData) {
		this.consumData = consumData;
	}
	
	
}
