package cn.bdqn.yunfei.soso;

/**
 * 卡片工具类
 */


import java.io.*;
import java.util.*;

@SuppressWarnings("all")
public class CardUtil implements Serializable {
    private Map<String, MobileCard> card = new HashMap();
    private Map<String, List<ConsumInfo>> consumInfos = new HashMap();

    /**
     * @params:注册新卡 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     * @params: 此方法用于生成9个新的电话号码并返回这9个号码的集合
     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static List<String> createCardNums() {
        List<String> cardNumList = new ArrayList(9);
        int genByRandom = 0;
        for (int i = 0; i < 9; i++) {                  // 嵌套循环生成9个号码放在list中
            StringBuffer initial = new StringBuffer("13");
            for (int j = 0; j < 9; j++) {
                genByRandom = (int) (Math.random() * 10);
                initial.append(genByRandom);
            }
            String newNumber = initial.toString();
            cardNumList.add(i, newNumber);
        }
        for (int i = 0; i < 9; i++) {                   // 打印生成的9个随机号码
            if (i == 2 || i == 5 || i == 8) {
                System.out.println((i + 1) + ". " + cardNumList.get(i));
            } else {
                System.out.print((i + 1) + ". " + cardNumList.get(i) + "\t");
            }

        }
        return cardNumList;
    }

    /**
     * @params:话费充值 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static void deposit(CardUtil cardUtil) {
        double money = 0.0;
        String number = null;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("请输入充值卡号：");
            number = input.next();
            System.out.print("请输入充值金额： ");
            money = input.nextDouble();
            if (money >= 50) {
                MobileCard card = cardUtil.getCard().get(number);
                card.setRemainingAmount(card.getRemainingAmount() + money);
                Common.save(cardUtil);                      //序列化保存信息
                break;
            } else {
                System.out.println("最少充值50元，请重新输入！");
            }
        } while (true);


    }

    /**
     * @params:使用嗖嗖 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static void usingSoso(CardUtil cardUtil, String number) {
        boolean flag = false;
        List<Scene> sceneList = new ArrayList<Scene>(6);
        CardUtil.initScenes(sceneList);
        MobileCard card = cardUtil.getCard().get(number);
        List<ConsumInfo> consumInfoList = cardUtil.getConsumInfos().get(number);
        Scene currentScene = null;
        int actualUsed = 0;
        do {
            int selectScene = (int) (1 + Math.random() * 6);
            switch (selectScene) {
                case 1:
                    if (card.getSerPackage() instanceof VocalPackage) {
                        System.out.println(sceneList.get(0).getDescription());
                        currentScene = sceneList.get(0);
                        actualUsed = ((VocalPackage) (card.getSerPackage())).call(
                                sceneList.get(0).getData(), card);
                        currentScene.setData(actualUsed);
                        if (actualUsed != 0)
                            Common.addConsumInfo(cardUtil, number, currentScene);
                        flag = true;
                        break;
                    } else if (card.getSerPackage() instanceof SuperPackage) {
                        System.out.println(sceneList.get(0).getDescription());
                        currentScene = sceneList.get(0);
                        actualUsed = ((SuperPackage) (card.getSerPackage())).call(
                                sceneList.get(0).getData(), card);
                        currentScene.setData(actualUsed);
                        if (actualUsed != 0)
                            Common.addConsumInfo(cardUtil, number, currentScene);
                        flag = true;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (card.getSerPackage() instanceof VocalPackage) {
                        System.out.println(sceneList.get(1).getDescription());
                        currentScene = sceneList.get(1);
                        actualUsed = ((VocalPackage) (card.getSerPackage())).call(
                                sceneList.get(1).getData(), card);
                        currentScene.setData(actualUsed);
                        if (actualUsed != 0)
                            Common.addConsumInfo(cardUtil, number, currentScene);
                        flag = true;
                        break;
                    } else if (card.getSerPackage() instanceof SuperPackage) {
                        System.out.println(sceneList.get(1).getDescription());
                        currentScene = sceneList.get(1);
                        actualUsed = ((SuperPackage) (card.getSerPackage())).call(
                                sceneList.get(1).getData(), card);
                        currentScene.setData(actualUsed);
                        if (actualUsed != 0)
                            Common.addConsumInfo(cardUtil, number, currentScene);
                        flag = true;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (card.getSerPackage() instanceof VocalPackage) {
                        System.out.println(sceneList.get(2).getDescription());
                        currentScene = sceneList.get(2);
                        actualUsed = ((VocalPackage) (card.getSerPackage())).SMS(
                                sceneList.get(2).getData(), card);
                        currentScene.setData(actualUsed);
                        if (actualUsed != 0)
                            Common.addConsumInfo(cardUtil, number, currentScene);
                        flag = true;
                        break;
                    } else if (card.getSerPackage() instanceof SuperPackage) {
                        System.out.println(sceneList.get(2).getDescription());
                        currentScene = sceneList.get(2);
                        actualUsed = ((SuperPackage) (card.getSerPackage())).SMS(
                                sceneList.get(2).getData(), card);
                        currentScene.setData(actualUsed);
                        if (actualUsed != 0)
                            Common.addConsumInfo(cardUtil, number, currentScene);
                        flag = true;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (card.getSerPackage() instanceof VocalPackage) {
                        System.out.println(sceneList.get(3).getDescription());
                        currentScene = sceneList.get(3);
                        actualUsed = ((VocalPackage) (card.getSerPackage())).SMS(
                                sceneList.get(3).getData(), card);
                        currentScene.setData(actualUsed);
                        if (actualUsed != 0)
                            Common.addConsumInfo(cardUtil, number, currentScene);
                        flag = true;
                        break;
                    } else if (card.getSerPackage() instanceof SuperPackage) {
                        System.out.println(sceneList.get(3).getDescription());
                        currentScene = sceneList.get(3);
                        actualUsed = ((SuperPackage) (card.getSerPackage())).SMS(
                                sceneList.get(3).getData(), card);
                        currentScene.setData(actualUsed);
                        if (actualUsed != 0)
                            Common.addConsumInfo(cardUtil, number, currentScene);
                        flag = true;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (card.getSerPackage() instanceof NetPackage) {
                        System.out.println(sceneList.get(4).getDescription());
                        currentScene = sceneList.get(4);
                        actualUsed = ((NetPackage) (card.getSerPackage())).network(sceneList.get(4).getData(), card);
                        currentScene.setData(actualUsed);
                        if (actualUsed != 0)
                            Common.addConsumInfo(cardUtil, number, currentScene);
                        flag = true;
                        break;
                    } else if (card.getSerPackage() instanceof SuperPackage) {
                        System.out.println(sceneList.get(4).getDescription());
                        currentScene = sceneList.get(4);
                        actualUsed = ((SuperPackage) (card.getSerPackage())).network(
                                sceneList.get(4).getData(), card);
                        currentScene.setData(actualUsed);
                        if (actualUsed != 0)
                            Common.addConsumInfo(cardUtil, number, currentScene);
                        flag = true;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (card.getSerPackage() instanceof NetPackage) {
                        System.out.println(sceneList.get(5).getDescription());
                        currentScene = sceneList.get(5);
                        actualUsed = ((NetPackage) (card.getSerPackage())).network(sceneList.get(5).getData(), card);
                        currentScene.setData(actualUsed);
                        if (actualUsed != 0)
                            Common.addConsumInfo(cardUtil, number, currentScene);
                        flag = true;
                        break;
                    } else if (card.getSerPackage() instanceof SuperPackage) {
                        System.out.println(sceneList.get(5).getDescription());
                        currentScene = sceneList.get(5);
                        actualUsed = ((SuperPackage) (card.getSerPackage())).network(
                                sceneList.get(5).getData(), card);
                        currentScene.setData(actualUsed);//此处返回的流量单位为MB
                        if (actualUsed != 0)
                            Common.addConsumInfo(cardUtil, number, currentScene);
                        flag = true;
                        break;
                    } else {
                        break;
                    }
                default:
                    System.out.println("请输入正确的数字");
                    break;

            }
            /**
             * @params:保存信息
             * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
             */
            Common.save(cardUtil);
        } while (!flag);

    }

    public Map<String, MobileCard> getCard() {
        return card;
    }

    public void setCard(Map<String, MobileCard> card) {
        this.card = card;
    }

    public Map<String, List<ConsumInfo>> getConsumInfos() {
        return consumInfos;
    }

    public void setConsumInfos(Map<String, List<ConsumInfo>> consumInfos) {
        this.consumInfos = consumInfos;
    }

    /**
     * @params:资费说明 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static void showDescription() {
        File file = new File("F:\\IdeaWorkspaces\\src\\main\\java\\com\\mobile\\ZZF.txt");
        BufferedReader bfr = null;
        try {
            bfr = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("未找到文件！");
//            e.printStackTrace();
        }
        String info = null;
        try {
            while ((info = bfr.readLine()) != null) {
                System.out.println(info);
            }
        } catch (IOException e) {
            System.out.println("读取错误！");
//            e.printStackTrace();
        }
        try {
            bfr.close();
        } catch (IOException e) {
            System.out.println("文件未能正确关闭！");
//            e.printStackTrace();
        }
    }

    /**
     * @params:本月账单查询 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static void showAccountDetail(CardUtil cardUtil, String number) {
        System.out.println("*****本月账单查询******");
        MobileCard card = (MobileCard) (cardUtil.getCard().get(number));
        System.out.println("您的卡号是： " + number);
        System.out.println("当月账单： ");
        System.out.println("套餐资费： " + card.getSerPackage().getPrice());
        System.out.println("合计： " + Common.convert(card.getConsumedAmount()));
        System.out.println("账户余额： " + Common.convert(card.getRemainingAmount()));
    }

    /**
     * @params:套餐余量查询 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static void showRemainDetail(CardUtil cardUtil, String number) {
        System.out.println("*****套餐余量查询******");
        System.out.println("您的卡号是： " + number + ", 套餐内剩余： ");
        MobileCard card = ((MobileCard) (cardUtil.getCard().get(number)));
        if (card.getSerPackage() instanceof VocalPackage) {
            int remainVocal = (((VocalPackage) (card.getSerPackage()))
                    .getVocaltime() - card.getRealVocalTime()) >= 0 ? ((VocalPackage) (card
                    .getSerPackage())).getVocaltime() - card.getRealVocalTime()
                    : 0;
            System.out.println("通话时长： " + remainVocal + "分钟");
            int remainSMS = (((VocalPackage) (card.getSerPackage()))
                    .getSmscount() - card.getRealSMSCount()) >= 0 ? ((VocalPackage) (card
                    .getSerPackage())).getSmscount() - card.getRealSMSCount()
                    : 0;
            System.out.println("短信条数： " + remainSMS + "条");
            int remainNet = (((VocalPackage) (card.getSerPackage())).getFlow() - card
                    .getRealFlow()) >= 0 ? ((VocalPackage) (card
                    .getSerPackage())).getFlow() - card.getRealFlow() : 0;
            System.out.println("上网流量： " + remainNet + "MB");
        } else if (card.getSerPackage() instanceof NetPackage) {
            int remainVocal = (((NetPackage) (card.getSerPackage()))
                    .getVocaltime() - card.getRealVocalTime()) >= 0 ? ((NetPackage) (card
                    .getSerPackage())).getVocaltime() - card.getRealVocalTime()
                    : 0;
            System.out.println("通话时长： " + remainVocal + "分钟");
            int remainSMS = (((NetPackage) (card.getSerPackage()))
                    .getSmscount() - card.getRealSMSCount()) >= 0 ? ((NetPackage) (card
                    .getSerPackage())).getSmscount() - card.getRealSMSCount()
                    : 0;
            System.out.println("短信条数： " + remainSMS + "条");
            int remainNet = (((NetPackage) (card.getSerPackage())).getFlow() - card
                    .getRealFlow()) >= 0 ? ((NetPackage) (card.getSerPackage()))
                    .getFlow() - card.getRealFlow()
                    : 0;
            System.out.println("上网流量： " + remainNet + "MB");
        } else if (card.getSerPackage() instanceof SuperPackage) {
            int remainVocal = (((SuperPackage) (card.getSerPackage()))
                    .getVocaltime() - card.getRealVocalTime()) >= 0 ? ((SuperPackage) (card
                    .getSerPackage())).getVocaltime() - card.getRealVocalTime()
                    : 0;
            System.out.println("通话时长： " + remainVocal + "分钟");
            int remainSMS = (((SuperPackage) (card.getSerPackage()))
                    .getSmscount() - card.getRealSMSCount()) >= 0 ? ((SuperPackage) (card
                    .getSerPackage())).getSmscount() - card.getRealSMSCount()
                    : 0;
            System.out.println("短信条数： " + remainSMS + "条");
            int remainNet = (((SuperPackage) (card.getSerPackage())).getFlow() - card
                    .getRealFlow()) >= 0 ? ((SuperPackage) (card
                    .getSerPackage())).getFlow() - card.getRealFlow() : 0;
            System.out.println("上网流量： " + remainNet + "MB");
        }
    }

    /**
     * @params:打印消费详单 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static void printConsumedDetail(CardUtil cardUtil, String number) {
        System.out.println("*****消费详单查询******");
        File file = new File("F:\\IdeaWorkspaces\\src\\main\\java\\com\\mobile\\ConsumInfo.txt");
        boolean isExist = false;
        BufferedWriter bfw = null;
        BufferedReader bfr = null;
        List<ConsumInfo> consumInfoList = null;
        try {
            bfw = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            System.out.println("保存异常！");
//            e.printStackTrace();
        }
        Set numberList = cardUtil.getConsumInfos().keySet();
        for (Iterator iter = numberList.iterator();
             iter.hasNext(); ) {
            String phoneNumber = (String) iter.next();
            if (phoneNumber.equals(number)) {
                isExist = true;
                consumInfoList = (ArrayList<ConsumInfo>) (cardUtil
                        .getConsumInfos().get(number));
            }
        }
        if (!isExist) {
            System.out.println("对不起，不存在此号码的消费记录，不能打印！");
        } else {
            try {
                bfw.write("******" + number
                        + "消费记录******\n序号\t类型\t数据（通话（条）/上网（MB）/短信（条））\n");
                bfw.flush();
                try {
                    for (int i = 0; i < consumInfoList.size(); i++) {
                        bfw.write((i + 1) + ". \t"
                                + consumInfoList.get(i).getType() + "\t"
                                + consumInfoList.get(i).getConsumData() + "\n");
                        bfw.flush();
                    }
                } catch (IOException e) {
                    System.out.println("对不起，不存在此号码的消费记录，不能打印！");
//                    e.printStackTrace();
                }
                bfw.close();

            } catch (IOException e) {
                System.out.println("写入错误！");
//                e.printStackTrace();
            }
            try {
                bfr = new BufferedReader(new FileReader(file));
                String info = null;
                try {
                    while ((info = bfr.readLine()) != null) {
                        System.out.println(info);
                    }
                    bfr.close();
                } catch (IOException e) {
                    System.out.println("读取错误！");
//                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                System.out.println("未找到文件！");
//                e.printStackTrace();
            }

        }
    }

    /**
     * @params:套餐变更 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static void changingServicePackage(CardUtil cardUtil, String number) {
        System.out.print("*****套餐变更******\n1.话唠套餐 \t2.网虫套餐\t3.超人套餐\t请选择（序号）： ");
        Scanner input = new Scanner(System.in);
        MobileCard card = cardUtil.getCard().get(number);
        while (true) {
            int selectedNum = input.nextInt();
            if (selectedNum == 1) {
                if (card.getSerPackage().getPrice() == 58) {
                    System.out.println("对不起，您已经是该套餐用户，无需换套餐！");
                    break;
                } else if (card.getRemainingAmount() - 58 < 0) {
                    System.out.println("对不起，您的余额不足以支付新套餐本月资费，请充值后再办理更换套餐业务！");
                    break;
                } else {
                    VocalPackage vocal = new VocalPackage();
                    card.setSerPackage(vocal);
                    card.setRealVocalTime(0);
                    card.setRealFlow(0);
                    card.setRealSMSCount(0);
                    card.setRemainingAmount(card.getRemainingAmount() - 58);
                    card.setConsumedAmount(vocal.getPrice());
                    System.out.print("更换套餐成功！");
                    vocal.showInfo();
                    Common.save(cardUtil);
                    break;
                }
            } else if (selectedNum == 2) {
                if (card.getSerPackage().getPrice() == 68) {
                    System.out.println("对不起，您已经是该套餐用户，无需换套餐！");
                    break;
                } else if (card.getRemainingAmount() - 68 < 0) {
                    System.out.println("对不起，您的余额不足以支付新套餐本月资费，请充值后再办理更换套餐业务！");
                    break;
                } else {
                    NetPackage net = new NetPackage();
                    card.setSerPackage(net);
                    card.setRealVocalTime(0);
                    card.setRealFlow(0);
                    card.setRealSMSCount(0);
                    card.setRemainingAmount(card.getRemainingAmount() - 68);
                    card.setConsumedAmount(net.getPrice());
                    System.out.print("更换套餐成功！");
                    net.showInfo();
                    Common.save(cardUtil);
                    break;
                }
            } else if (selectedNum == 3) {
                if (card.getSerPackage().getPrice() == 78) {
                    System.out.println("对不起，您已经是该套餐用户，无需换套餐！");
                    break;
                } else if (card.getRemainingAmount() - 78 < 0) {
                    System.out.println("对不起，您的余额不足以支付新套餐本月资费，请充值后再办理更换套餐业务！");
                    break;
                } else {
                    SuperPackage superPackage = new SuperPackage();
                    card.setSerPackage(superPackage);
                    card.setRealVocalTime(0);
                    card.setRealFlow(0);
                    card.setRealSMSCount(0);
                    card.setRemainingAmount(card.getRemainingAmount() - 78);
                    card.setConsumedAmount(superPackage.getPrice());
                    System.out.print("更换套餐成功！");
                    superPackage.showInfo();
                    Common.save(cardUtil);
                    break;
                }
            } else {
                System.out.print("您输入的号码不对，请重新输入:");
            }
        }


    }

    /**
     * @params:办理退网 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static void deleteCard(CardUtil cardUtil, String number) {
        Map<String, MobileCard> cardMap = cardUtil.getCard();
        Map<String, List<ConsumInfo>> consumInfoMap = cardUtil.getConsumInfos();
        Set keySetForCardMap = cardMap.keySet();
        Set keySetForConsumInfoMap = consumInfoMap.keySet();
        MobileCard card = null;
        cardMap.remove(number);//删除电话卡列表映射
        consumInfoMap.remove(number);//删除消费信息列表映射
        Common.save(cardUtil);//删除信息后序列化存储
        System.out.println("*****办理退网******\n卡号" + number + "办理退网成功！\n谢谢使用！");

    }

    /**
     * @params:初始化场景 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static void initScenes(List<Scene> sceneList) {
        Scene scene0 = new Scene();
        scene0.setType("通话");
        scene0.setData(90);
        scene0.setDescription(
                "问候用户，谁知其如此难缠，通话" + scene0.getData() + "分钟");
        sceneList.add(scene0);
        Scene scene1 = new Scene();
        scene1.setType("通话");
        scene1.setData(30);
        scene1.setDescription(
                "询问妈妈身体状况，本地通话" + scene1.getData() + "分钟");
        sceneList.add(scene1);
        Scene scene2 = new Scene();
        scene2.setType("短信");
        scene2.setData(5);
        scene2.setDescription(
                "参与环境保护实施方案问卷调查，发送短信" + scene2.getData() + "条");
        sceneList.add(scene2);
        Scene scene3 = new Scene();
        scene3.setType("短信");
        scene3.setData(50);
        scene3.setDescription(
                "通知朋友手机换号，发送短信" + scene3.getData() + "条");
        sceneList.add(scene3);
        Scene scene4 = new Scene();
        scene4.setType("上网");
        scene4.setData(1 * 1024);
        scene4.setDescription(
                "和女友用微信视频聊天， 使用流量1GB");
        sceneList.add(scene4);
        Scene scene5 = new Scene();
        scene5.setType("上网");
        scene5.setData(2 * 1024);
        scene5.setDescription(
                "晚上手机在线看韩剧，不留神睡着啦！使用流量2GB");
        sceneList.add(scene5);
    }

}
