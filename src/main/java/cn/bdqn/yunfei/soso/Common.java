package cn.bdqn.yunfei.soso;

/**
 * 常用辅助工具类
 */

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

@SuppressWarnings("all")
public class Common {
    /**
     * @params:判断是否为注册用户 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static boolean isUser(CardUtil cardUtil, String number,
                                 String password) {
        Map<String, MobileCard> cardMap = cardUtil.getCard();
        boolean isExist = false;
        boolean isRight = false;
        Set keys = cardMap.keySet();
        for (Iterator iter = keys.iterator(); iter.hasNext(); ) {
            MobileCard mobileCard = (MobileCard) (cardMap.get(iter.next()));
            if (mobileCard.getCardNumber().equals(number)) {
                isExist = true;
                if (mobileCard.getPassword().equals(password)) {
                    isRight = true;
                }
            }
        }
        if (!isExist) {
            System.out.println("该用户不存在！");
        } else if (!isRight) {
            System.out.println("密码错误！");
        }

        return isRight;

    }

    /**
     * @params:初始化数据存储，使用序列化方式：该方法必须判定初始化时文档中是否有内容，如果没有，必须返回一个新的引用；
     * @param:如果有内容，返回文档中的实例类
     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static CardUtil initializing(CardUtil cardUtil) {
        File fileForMobileCard = new File(
                "F:\\IdeaWorkspaces\\src\\main\\java\\com\\mobile\\fileForMobileCard.txt");
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileForMobileCard);
        } catch (FileNotFoundException e) {
            System.out.println("未找到初始化文件！");
//            e.printStackTrace();
        }
        try {
            if (fis.read() == -1)
                return cardUtil;
            else {
                ois = new ObjectInputStream(new FileInputStream(fileForMobileCard));
                cardUtil = (CardUtil) (ois.readObject());
                return cardUtil;
            }
        } catch (ClassNotFoundException | IOException e) {
//            e.printStackTrace();
            System.out.println("初始化错误！");
        } finally {
            return cardUtil;
        }

    }

    // 存储信息，使用序列化方式
    public static void save(CardUtil cardUtil) {
        File fileForMobileCard = new File(
                "F:\\IdeaWorkspaces\\src\\main\\java\\com\\mobile\\fileForMobileCard.txt");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new FileOutputStream(fileForMobileCard));// 此处文件永远只存储一个CardUtil类实例
        } catch (FileNotFoundException e) {
            System.out.println("未找到存储文件！");
//            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("存储错误！");
//            e.printStackTrace();
        }
        try {
            oos.writeObject(cardUtil);
        } catch (IOException e) {
            System.out.println("存储错误！");
//            e.printStackTrace();
        }
    }

     /**
       * @params:保留一位小数
       * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
       */
    public static String convert(double before) {
        if (before <= 0.0) {
            return "0.0";
        } else {
            DecimalFormat formatData = new DecimalFormat("#.0");
            return formatData.format(before);
        }
    }

     /**
      * @params:GB与MB间转换
      * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
      */
    public static int adjustUnit(int Giga) {
        return Giga * 1024;
    }

     /**
       * @params:添加消费记录
       * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
       */
    public static void addConsumInfo(CardUtil cardUtil, String number, Scene scene) {
        ConsumInfo consumInfo = new ConsumInfo();
        consumInfo.setCardNumber(number);
        consumInfo.setType(scene.getType());
        consumInfo.setConsumData(scene.getData());
        Set keySet = cardUtil.getConsumInfos().keySet();
        List<ConsumInfo> consumInfoList = null;
        Map<String, List<ConsumInfo>> consumInfoMap = cardUtil.getConsumInfos();
        boolean isExist = false;
        for (Iterator iter = keySet.iterator(); iter.hasNext(); ) {
            String phoneNumber = (String) (iter.next());
            if (phoneNumber.equals(number)) {
                isExist = true;
                consumInfoList = cardUtil.getConsumInfos().get(number);
                consumInfoList.add(consumInfo);
                System.out.println("已添加一条消费信息");
            }
        }
        if (!isExist) {
            consumInfoList = new ArrayList<ConsumInfo>();
            consumInfoList.add(consumInfo);
            consumInfoMap.put(number, consumInfoList);
            System.out.println("不存在此卡的消费记录，已添加一条消息记录");
        }

         /**
           * @params:序列化存储
           * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
           */
        Common.save(cardUtil);
    }
}
