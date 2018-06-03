package src.application;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Demo extends JFrame {
    private ArrayList<Integer> list=new ArrayList<>();
    public Demo(int...times){
        super();
     //   ran = new Random();
        for (int i = 0; i <times.length ; i++) {
            list.add(times[i]);
        }
        setTitle("绘制柱形图");
        setBounds(100, 100, 400, 271);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void paint(Graphics g){
        int Width = getWidth();
        int Height = getHeight();
        int leftMargin = 20;//柱形图左边界
        int topMargin = 50;//柱形图上边界
        Graphics2D g2 = (Graphics2D) g;
        int ruler = Height-topMargin-5;
        int rulerStep = ruler/10;//将当前的高度评分为10个单位
        g2.setColor(Color.WHITE);//绘制白色背景
        g2.fillRect(0, 0, Width, Height);//绘制矩形图
        g2.setColor(Color.LIGHT_GRAY);
        for(int i=0;i<=10;i++){//绘制灰色横线和百分比
            g2.drawString((100-10*i)+"%", 5, topMargin+rulerStep*i);//写下百分比
            g2.drawLine(5, topMargin+rulerStep*i, Width, topMargin+rulerStep*i);//绘制灰色横线
        }
        g2.setColor(Color.PINK);
//        for(int i=0;i<4;i++){//绘制柱形图
//            int value = ran.nextInt(Height-topMargin-10)+10;//随机产生柱形的百分比
//            int step = (i+1)*40;//设置每隔柱形图的水平间隔为40
//            //绘制矩形
////          g2.drawRoundRect(leftMargin+step*2, Height-value, 40, value, 40, 10);
//            g2.fillRoundRect(leftMargin+step*2, Height-value, 40, value, 40, 10);
//            //列出产品的编号
//            g2.drawString("产品"+(i+1), leftMargin+step*2, Height-value-5);
//        }
        for (int i = 0; i <list.size() ; i++) {
            int value=list.get(i)+10;
            int step = (i+1)*40;//设置每隔柱形图的水平间隔为40
            //绘制矩形
//          g2.drawRoundRect(leftMargin+step*2, Height-value, 40, value, 40, 10);
            g2.fillRoundRect(leftMargin+step*2, Height-value, 40, value, 40, 10);
            //列出产品的编号
            g2.drawString("方法"+(i+1), leftMargin+step*2, Height-value-5);
        }
    }
}
