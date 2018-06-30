package cn.bdqn.yunfei.soso;

public class MyException extends Exception {
	@SuppressWarnings("unused")
	public void handle(){
		System.out.println("您的余额已经不足，请充值以后再使用！");
		super.printStackTrace();
	}
}
