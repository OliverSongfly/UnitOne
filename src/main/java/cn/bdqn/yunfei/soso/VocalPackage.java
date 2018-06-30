package cn.bdqn.yunfei.soso;
/**
 * 话唠套餐类
 */
import java.io.Serializable;
@SuppressWarnings("all")
public class VocalPackage extends ServicePackage implements Serializable,Vocal,SMS{
	private final static int VOCALTIME=500;
	private final static int SMSCOUNT=30;
	private final static int FLOW=0;
	
	public static int getFlow() {
		return FLOW;
	}
	public VocalPackage(){
		super.setPrice(58);
	}	
	public void showInfo(){
		System.out.println("话唠套餐： 通话时长为500分钟/月，短信条数为30条/月。");
	}
	public static int getVocaltime() {
		return VOCALTIME;
	}
	public static int getSmscount() {
		return SMSCOUNT;
	}
	public int call(int vocalMinute, MobileCard card){
		int beforeUsed=card.getRealVocalTime();
		int remainCall=0;
		boolean flag=false;
		 /**
		   * @params:计算当前剩余流量
		   * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		   */
		remainCall=VOCALTIME-card.getRealVocalTime();
		 /**
		   * @params:如果当前剩余流量小于等于0
		   * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		   */
		if(remainCall<=0){
			for(int i=0;i<vocalMinute;i++){
				if(card.getRemainingAmount()>=0.2){
					card.setRemainingAmount(card.getRemainingAmount()-0.2);
					card.setConsumedAmount(card.getConsumedAmount()+0.2);
					card.setRealVocalTime(card.getRealVocalTime()+1);
					flag=true;
				}else{
					if(!flag)
					System.out.println("本次已通话"+(card.getRealVocalTime()-beforeUsed)+"分钟，您的余额不足，请充值后再使用！");
					else{
						card.setRemainingAmount(card.getRemainingAmount()-0.2);
						card.setConsumedAmount(card.getConsumedAmount()+0.2);
						card.setRealVocalTime(card.getRealVocalTime()+1);
						System.out.println("本次已通话"+(card.getRealVocalTime()-beforeUsed)+"分钟，您的余额不足，请充值后再使用！");
						
					}
					try {
						throw new MyException();
					} catch (MyException e) {
						System.out.println("您的余额不足，请充值后再使用！");
//						e.printStackTrace();
					}
					break;
				}
			}
		}else if(remainCall>0){
			for(int i=0;i<vocalMinute;i++){
				if(i>=remainCall){
					if(card.getRemainingAmount()>=0.2){
						card.setRemainingAmount(card.getRemainingAmount()-0.2);
						card.setConsumedAmount(card.getConsumedAmount()+0.2);
						card.setRealVocalTime(card.getRealVocalTime()+1);
						flag=true;
					}else{
						if(!flag)
						System.out.println("本次已通话"+(card.getRealVocalTime()-beforeUsed)+"分钟,您的余额不足，请充值后再使用！");
						else{
							card.setRemainingAmount(card.getRemainingAmount()-0.2);
							card.setConsumedAmount(card.getConsumedAmount()+0.2);
							card.setRealVocalTime(card.getRealVocalTime()+1);
							System.out.println("本次已通话"+(card.getRealVocalTime()-beforeUsed)+"分钟,您的余额不足，请充值后再使用！");
						}
							
							
						try {
							throw new MyException();
						} catch (MyException e) {
							System.out.println("您的余额不足，请充值后再使用！");
//							e.printStackTrace();
						}
						break;
					}
					
				}else{
					card.setRealVocalTime(card.getRealVocalTime()+1);
				}
			}
		}
		return card.getRealVocalTime()-beforeUsed;
	}
	
	public int SMS(int smsAmount, MobileCard card){
		int beforeUsed=card.getRealSMSCount();
		int remainSMS=0;
		boolean flag=false;
		 /**
		   * @params:计算当前剩余流量
		   * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		   */
		remainSMS=SMSCOUNT-card.getRealSMSCount();
		 /**
		   * @params:如果当前剩余流量小于等于0
		   * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		   */
		if(remainSMS<=0){
			for(int i=0;i<smsAmount;i++){
				if(card.getRemainingAmount()>=0.1){
					card.setRemainingAmount(card.getRemainingAmount()-0.1);
					card.setConsumedAmount(card.getConsumedAmount()+0.1);
					card.setRealSMSCount(card.getRealSMSCount()+1);
					flag=true;
				}else{
					if(!flag)
					System.out.println("本次已发送短信"+(card.getRealSMSCount()-beforeUsed)+"条，您的余额不足，请充值后再使用！");
					else{
						card.setRemainingAmount(card.getRemainingAmount()-0.1);
						card.setConsumedAmount(card.getConsumedAmount()+0.1);
						card.setRealSMSCount(card.getRealSMSCount()+1);
						System.out.println("本次已发送短信"+(card.getRealSMSCount()-beforeUsed)+"条，您的余额不足，请充值后再使用！");
						
					}
					try {
						throw new MyException();
					} catch (MyException e) {
						System.out.println("您的余额不足，请充值后再使用！");
//						e.printStackTrace();
					}
					break;
				}
			}
		}else if(remainSMS>0){
			for(int i=0;i<smsAmount;i++){
				if(i>=remainSMS){
					if(card.getRemainingAmount()>=0.1){
						card.setRemainingAmount(card.getRemainingAmount()-0.1);
						card.setConsumedAmount(card.getConsumedAmount()+0.1);
						card.setRealSMSCount(card.getRealSMSCount()+1);
						flag=true;
					}else{
						if(!flag)
						System.out.println("本次已发送短信"+(card.getRealSMSCount()-beforeUsed)+"条,您的余额不足，请充值后再使用！");
						else{
							card.setRemainingAmount(card.getRemainingAmount()-0.1);
							card.setConsumedAmount(card.getConsumedAmount()+0.1);
							card.setRealSMSCount(card.getRealSMSCount()+1);
							System.out.println("本次已发送短信"+(card.getRealSMSCount()-beforeUsed)+"条,您的余额不足，请充值后再使用！");
						}
						try {
							throw new MyException();
						} catch (MyException e) {
							System.out.println("您的余额不足，请充值后再使用！");
//							e.printStackTrace();
						}
						break;
					}
					
				}else{
					card.setRealSMSCount(card.getRealSMSCount()+1);
				}
			}
		}
		return card.getRealSMSCount()-beforeUsed;
	}
}
