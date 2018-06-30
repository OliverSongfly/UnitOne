package cn.bdqn.yunfei.soso;

import java.io.Serializable;

/**
 * 手机卡类
 */
public class MobileCard implements Serializable {
    private String cardNumber = null;//该卡卡号
    private String userName = null;//该卡用户名称
    private String password = null;//该卡用户设置密码
    private ServicePackage serPackage = null;//该卡所用资费套餐
    private double consumedAmount = 0.0;//该卡本月使用资费
    private double remainingAmount = 0.0;//该卡本月剩余资费
    private int realVocalTime = 0;//该卡本月实际通话时间（分钟）
    private int realSMSCount = 0;//该卡本月所发信息条数
    private int realFlow = 0;//该卡本月所消耗流量（MB）
 /**
   * @params:显示卡片信息
   * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
   */
    public void showMessage() {
        System.out.println("卡号： " + cardNumber + "\t用户名: "
                + userName + "\t当前余额： " + remainingAmount);
        serPackage.showInfo();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @SuppressWarnings("unused")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ServicePackage getSerPackage() {
        return serPackage;
    }

    public void setSerPackage(ServicePackage serPackage) {
        this.serPackage = serPackage;
    }

    public double getConsumedAmount() {
        return consumedAmount;
    }

    public void setConsumedAmount(double consumedAmount) {
        this.consumedAmount = consumedAmount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public int getRealVocalTime() {
        return realVocalTime;
    }

    public void setRealVocalTime(int realVocalTime) {
        this.realVocalTime = realVocalTime;
    }

    public int getRealSMSCount() {
        return realSMSCount;
    }

    public void setRealSMSCount(int realSMSCount) {
        this.realSMSCount = realSMSCount;
    }

    public int getRealFlow() {
        return realFlow;
    }

    public void setRealFlow(int realFlow) {
        this.realFlow = realFlow;
    }

}
