package JFAME;

/**
 * Created by uuuup on 2017/9/6.
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class TestDrawArc extends JFrame{
    MyCanvas1 cnv;

    public TestDrawArc(){
        super("半圆");
        cnv = new MyCanvas1();
        this.add(cnv);
        this.setSize(500, 500);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TestDrawArc();
    }
}

class MyCanvas1 extends Canvas{
    public MyCanvas1(){
        super();
    }

    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.red);
        g.drawArc(50, 50, 300, 300, 0, 180);
    }
}