package cn.bdqn.yunfei.soso;

import java.util.List;
import java.util.Scanner;

/**
 * 入口
 * 框架类
 */
@SuppressWarnings("all")
public class Interaction {
    /**
     * @params:一级菜单方法 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static void start() {
        int numSelected = -1;// 用户选择的号码
        Scanner input = new Scanner(System.in);
        String cardNum = null;
        String password = null;
        boolean flag = true;
        CardUtil cardUtil2 = new CardUtil();
        CardUtil cardUtil = Common.initializing(cardUtil2);//反序列化存储的cardUtil对象！
        do {
            System.out.println("*************欢迎使用嗖嗖移动业务大厅***************");
            System.out
                    .println("1.用户登录\t2.用户注册\t3.使用嗖嗖\t4.话费充值\t5.资费说明\t6.退出系统");
            System.out.print("请选择：");
            numSelected = input.nextInt();
            switch (numSelected) {
                case 1:
                    System.out.print("请输入手机卡号： ");
                    cardNum = input.next();
                    System.out.print("请输入密码： ");
                    password = input.next();
                    if (Common.isUser(cardUtil, cardNum, password)) {
                        /**
                         * @params:进入二级菜单
                         * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
                         */
                        cardMenu(cardUtil, cardNum);
                        break;
                    } else {
                        break;
                    }

                case 2:
                    /**
                     * @params:注册一张新卡
                     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
                     */
                    MobileCard newMobileCard = new MobileCard();
                    addCard(cardUtil, newMobileCard);
                    break;
                case 3:
                    /**
                     * @params:使用嗖嗖功能
                     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
                     */
                    System.out.print("请输入卡号： ");
                    cardNum = input.next();
                    System.out.print("请输入密码：");
                    password = input.next();
                    if (Common.isUser(cardUtil, cardNum, password)) {
                        CardUtil.usingSoso(cardUtil, cardNum);
                        break;
                    } else {
                        break;
                    }

                case 4:
                    /**
                     * @params:使用资费充值功能
                     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
                     */
                    System.out.print("请输入卡号： ");
                    cardNum = input.next();
                    System.out.print("请输入密码：");
                    password = input.next();
                    if (Common.isUser(cardUtil, cardNum, password)) {
                        CardUtil.deposit(cardUtil);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    System.out.print("请输入卡号： ");
                    cardNum = input.next();
                    System.out.print("请输入密码：");
                    password = input.next();
                    if (Common.isUser(cardUtil, cardNum, password)) {
                         /**
                           * @params:打印资费说明
                           * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
                           */
                        CardUtil.showDescription();
                        break;
                    } else {
                        break;
                    }
                case 6:
                    System.out.println("谢谢使用！");
                    flag = false;
                    break;
                default:
                    break;
            }
        } while (flag);

    }

    /**
     * @params:二级菜单方法
     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static void cardMenu(CardUtil cardUtil, String cardNum) {
        Scanner input = new Scanner(System.in);
        int numSelected = -1;
        boolean nonExit = true;
        do {
            System.out.println("*****嗖嗖移动用户菜单*****");
            System.out.print("1.本月账单查询\n2.套餐余量查询\n3.打印消费详单\n4.套餐变更\n5.办理退网\n请选择（输入1~5选择功能，其他键返回上一级）:");
            numSelected = input.nextInt();
            switch (numSelected) {
                /**
                 * @params:本月账单查询
                 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
                 */
                case 1:
                    CardUtil.showAccountDetail(cardUtil, cardNum);
                    break;
                /**
                 * @params:套餐余量查询
                 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
                 */
                case 2:
                    CardUtil.showRemainDetail(cardUtil, cardNum);
                    break;
                /**
                 * @params:打印消费详单
                 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
                 */
                case 3:
                    CardUtil.printConsumedDetail(cardUtil, cardNum);
                    break;
                /**
                 * @params:套餐变更
                 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
                 */
                case 4:
                    CardUtil.changingServicePackage(cardUtil, cardNum);
                    break;
                /**
                 * @params:办理退网
                 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
                 */
                case 5:
                    CardUtil.deleteCard(cardUtil, cardNum);
                    /**
                     * @params:删除卡片后回到一级菜单
                     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
                     */
                    nonExit = false;
                    break;
                default:
                    /**
                     * @params:返回方法，退回到一级菜单，一级菜单内随即break,
                     * 再次执行循环，这样调用方法start内初始化的字断信息依然存在，满足程序要求。
                     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
                     */
                    return;

            }
        } while (nonExit);
        return;

    }

    /**
     * @params:注册新用户
     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static void addCard(CardUtil cardUtil, MobileCard card) {
        int numSelected = -1;
        Scanner input = new Scanner(System.in);
        System.out.println("*****可选择的卡号*****");
        List<String> cardNumList = CardUtil.createCardNums();
        System.out.print("请选择卡号（输入1~9的序号）： ");
        numSelected = input.nextInt();
        card.setCardNumber(cardNumList.get(numSelected - 1));
        do {
            System.out.print("1.话唠套餐\t2.网虫套餐\t3.超人套餐，请选择套餐（输入序号）： ");
            int numSelected2 = input.nextInt();
            if (numSelected2 == 1) {
                card.setSerPackage(new VocalPackage());
                break;
            } else if (numSelected2 == 2) {
                card.setSerPackage(new NetPackage());
                break;
            } else if (numSelected2 == 3) {
                card.setSerPackage(new SuperPackage());
                break;
            } else {
                System.out.println("请输入数字1~3！");
            }
        } while (true);
        System.out.print("请输入姓名： ");
        card.setUserName(input.next());
        System.out.print("请输入密码： ");
        card.setPassword(input.next());
        System.out.print("请输入预存话费金额： ");
        card.setRemainingAmount(input.nextDouble() - card.getSerPackage().getPrice());
        while (card.getRemainingAmount() < 0) {
            System.out.print("您预存的话费金额不足以支付本月固定套餐资费，请重新充值： ");
            card.setRemainingAmount(input.nextDouble() - card.getSerPackage().getPrice());
        }
        card.setConsumedAmount(card.getSerPackage().getPrice());
        System.out.println("注册成功！");
        card.showMessage();
        cardUtil.getCard().put(card.getCardNumber(), card);
        /**
         * @params:序列化存储
         * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
         */
        Common.save(cardUtil);

    }


    /**
     * @params:main入口
     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓华丽的注解线↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     */
    public static void main(String[] args) {
        start();
    }
}
