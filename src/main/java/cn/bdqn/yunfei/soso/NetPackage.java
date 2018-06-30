package cn.bdqn.yunfei.soso;
/**
 * 网虫套餐类
 */

import java.io.Serializable;

@SuppressWarnings("all")
public class NetPackage extends ServicePackage implements Serializable, Connectable {
    private final static int FLOW = 3;//套餐内流量数单位GB
    private final static int VOCALTIME = 0;
    private final static int SMSCOUNT = 0;

    public NetPackage() {
        super.setPrice(68);
    }

    //打印套餐信息
    public void showInfo() {
        System.out.println("网虫套餐： 上网流量为3GB/月。");
    }

    public static int getVocaltime() {
        return VOCALTIME;
    }

    public static int getSmscount() {
        return SMSCOUNT;
    }

    public static int getFlow() {
        return FLOW * 1024;//返回MB
    }


     /**
       * @params:此处的flow为此次操作消耗的流量数
       * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
       */
    public int network(int flow, MobileCard card) {
        int beforeUsed = card.getRealFlow();
        int remainFlow = 0;
        boolean flag = false;
        //计算当前剩余流量
        remainFlow = Common.adjustUnit(FLOW) - card.getRealFlow();
        //如果当前剩余流量小于等于0
        if (remainFlow <= 0) {
            for (int i = 0; i < flow; i++) {
                if (card.getRemainingAmount() >= 0.1) {
                    card.setRemainingAmount(card.getRemainingAmount() - 0.1);
                    card.setConsumedAmount(card.getConsumedAmount() + 0.1);
                    card.setRealFlow(card.getRealFlow() + 1);
                    flag = true;
                } else {
                    if (!flag)
                        System.out.println("本次已使用流量" + (card.getRealFlow() - beforeUsed) + "MB，您的余额不足，请充值后再使用！");
                    else {
                        card.setRemainingAmount(card.getRemainingAmount() - 0.1);
                        card.setConsumedAmount(card.getConsumedAmount() + 0.1);
                        card.setRealFlow(card.getRealFlow() + 1);
                        System.out.println("本次已使用流量" + (card.getRealFlow() - beforeUsed) + "MB，您的余额不足，请充值后再使用！");

                    }
                    try {
                        throw new MyException();
                    } catch (MyException e) {
//                        e.printStackTrace();
                    }
                    break;
                }
            }
        } else if (remainFlow > 0) {
            for (int i = 0; i < flow; i++) {
                if (i >= remainFlow) {
                    if (card.getRemainingAmount() >= 0.1) {
                        card.setRemainingAmount(card.getRemainingAmount() - 0.1);
                        card.setConsumedAmount(card.getConsumedAmount() + 0.1);
                        card.setRealFlow(card.getRealFlow() + 1);
                        flag = true;
                    } else {
                        if (!flag)
                            System.out.println("本次已使用流量" + (card.getRealFlow() - beforeUsed) + "MB,您的余额不足，请充值后再使用！");
                        else {
                            card.setRemainingAmount(card.getRemainingAmount() - 0.1);
                            card.setConsumedAmount(card.getConsumedAmount() + 0.1);
                            card.setRealFlow(card.getRealFlow() + 1);
                            System.out.println("本次已使用流量" + (card.getRealFlow() - beforeUsed) + "MB,您的余额不足，请充值后再使用！");
                        }

                        try {
                            throw new MyException();
                        } catch (MyException e) {
//                            e.printStackTrace();
                        }
                        break;
                    }

                } else {
                    card.setRealFlow(card.getRealFlow() + 1);
                }
            }
        }
        return card.getRealFlow() - beforeUsed;
    }
}