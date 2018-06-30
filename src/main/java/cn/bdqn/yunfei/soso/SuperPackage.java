package cn.bdqn.yunfei.soso;
/**
 * 超人套餐类
 */
import java.io.Serializable;
@SuppressWarnings("all")
public class SuperPackage extends ServicePackage implements Serializable,Connectable,Vocal,SMS{
	private final static int VOCALTIME=200;
	private final static int SMSCOUNT=50;
	private final static int FLOW=1;
	public SuperPackage(){
		super.setPrice(78);
	}
	public static int getVocaltime() {
		return VOCALTIME;
	}
	public static int getSmscount() {
		return SMSCOUNT;
	}
	public static int getFlow() {
		return FLOW*1024;
	}
	public void showInfo(){
		System.out.println("超人套餐： 通话时长为200分钟/月，短信条数为50条/月，上网流量为1GB/月。");
	}
	 /**
	   * @params:返回实际使用的短信条数
	   * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	   */
	public int SMS(int smsAmount, MobileCard card){
		int beforeUsed=card.getRealSMSCount();
		int remainSMS=0;
		boolean flag=false;//这个布尔值用来判断再使用嗖嗖时是否余额恰好为0
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
//						e.printStackTrace();
					}
					break;
				}
			}
		}else if(remainSMS>0){
			for(int i=0;i<smsAmount;i++){
				if(i>=remainSMS){//注意这里判断条件是大于等于，单纯大于是不行的
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
	 /**
	   * @params:返回实际消耗的数据流量MB为单位
	   * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	   */
	public int network(int flow,MobileCard card){
		int beforeUsed=card.getRealFlow();
		int remainFlow=0;
		boolean flag=false;
		//计算当前剩余流量
		remainFlow=Common.adjustUnit(FLOW)-card.getRealFlow();
		//如果当前剩余流量小于等于0
		if(remainFlow<=0){
			for(int i=0;i<flow;i++){
				if(card.getRemainingAmount()>=0.1){
					card.setRemainingAmount(card.getRemainingAmount()-0.1);
					card.setConsumedAmount(card.getConsumedAmount()+0.1);
					card.setRealFlow(card.getRealFlow()+1);
					flag=true;
				}else{
					if(!flag)
					System.out.println("本次已使用流量"+(card.getRealFlow()-beforeUsed)+"MB，您的余额不足，请充值后再使用！");
					else{
						card.setRemainingAmount(card.getRemainingAmount()-0.1);
						card.setConsumedAmount(card.getConsumedAmount()+0.1);
						card.setRealFlow(card.getRealFlow()+1);
						System.out.println("本次已使用流量"+(card.getRealFlow()-beforeUsed)+"MB，您的余额不足，请充值后再使用！");
						
					}
					try {
						throw new MyException();
					} catch (MyException e) {
//						e.printStackTrace();
					}
					break;//当余额不足时，跳出循环!
				}
			}//如果当前剩余流量大于0
		}else if(remainFlow>0){
			for(int i=0;i<flow;i++){
				if(i>=remainFlow){
					if(card.getRemainingAmount()>=0.1){
						card.setRemainingAmount(card.getRemainingAmount()-0.1);
						card.setConsumedAmount(card.getConsumedAmount()+0.1);
						card.setRealFlow(card.getRealFlow()+1);
						flag=true;
					}else{
						if(!flag)
						System.out.println("本次已使用流量"+(card.getRealFlow()-beforeUsed)+"MB,您的余额不足，请充值后再使用！");
						else{
							card.setRemainingAmount(card.getRemainingAmount()-0.1);
							card.setConsumedAmount(card.getConsumedAmount()+0.1);
							card.setRealFlow(card.getRealFlow()+1);
							System.out.println("本次已使用流量"+(card.getRealFlow()-beforeUsed)+"MB,您的余额不足，请充值后再使用！");
						}
						try {
							throw new MyException();
						} catch (MyException e) {
//							e.printStackTrace();
						}
						break;
					}
					
				}else{
					card.setRealFlow(card.getRealFlow()+1);
				}
			}
		}
		
		return card.getRealFlow()-beforeUsed;
	}
	//返回实际的语音时间
	public int call(int vocalMinute, MobileCard card){
		int beforeUsed=card.getRealVocalTime();
		int remainCall=0;
		boolean flag=false;
		//计算当前剩余流量
		remainCall=VOCALTIME-card.getRealVocalTime();
		//如果当前剩余流量小于等于0
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
//							e.printStackTrace();
						}
						break;
					}
					
				}else{
					card.setRealVocalTime((card.getRealVocalTime())+1);
				}
			}
		}
		return card.getRealVocalTime()-beforeUsed;
	}
}

