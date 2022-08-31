
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
interface common{
    String run(String str);
}
interface major{
    String many(String major, String action, String course);
}
/**
 *
 * @author 10174
 */
public class JunkString {

    public static void main(String[] args) {
        Scanner keyword = new Scanner(System.in, "GBK");
        System.out.println("请选择：（type integer) \n 1. 小编废话生成器；\n 2. ICS大三学生实录；");
        int choose = keyword.nextInt();
        switch(choose){
            case 1:
                xiaobianSCQ();
            case 2:
                String temp = "ICS学生要活得开朗一些，天天自学没啥本事，"
                        + "往家一蹲让人唠一辈子现在的ICS学生人人CPT202还是GroupWork，"
                        + "学得勇一点，配合自家Product Owner和ScrumMaster很容易把苏菲压过去，"
                        + "我的激进ICS学法配合家里蹲抑郁时刻，一行whileTrue计网能拿二十分";
                System.out.println(temp);
        }
    }
    
    public static void majorRubbish(){
        major me = (major, action, course) -> major + "学生要活得开朗一些，天天";
    }
    
    public static void printSth(String str, common lamda){
        String res = lamda.run(str);
        System.out.println(res);
    }
    
    public static void xiaobianSCQ(){
        common FeiHua = (s) -> "大家可能很好奇关于" + s + "问题的答案，那么今天小编为大家整理了有关" + s + "的相关问题，那么"
                + s + "究竟是什么呢？好，以上就是小编为大家整理的有关" + s + "的相关内容，喜欢的话请关注小编哦！";
        System.out.println("废话生成器加载成功！\n请输入你的问题，按下回车结束。");
        Scanner keyword = new Scanner(System.in, "GBK");
        String question = keyword.nextLine();
//        System.out.println(FeiHua.run(question));
        printSth(question, FeiHua);
    }
    
}
