package cn.bdqn.yunfei.soso;
/**
 * 套餐抽象父类
 */
import java.io.Serializable;

public abstract class ServicePackage implements Serializable{
	private double price;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public abstract void showInfo();
	
}
