package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.util.List;


/**
 * Created by uuuup on 2017/9/5.
 */
public class Graph {
    private List<String> Nodes = new ArrayList<>();//list只是一个接口 具体实现需要Arraylist 和 LinkedList
    private List<Side> Sides = new ArrayList<>();
    Vertex[] vertexLists;//邻接表
    int martixOfGraph[][];//邻接矩阵
    private int size;

    class Vertex{//顶点类
        String ch;
        Vertex next;
        int value;

        Vertex(String ch, int value){
            this.ch = ch;
            this.value =value;
        }

        void add(String ch, int value){
            Vertex node = this;
            while( node.next != null){
                node = node.next;
            }
            node.next = new Vertex(ch,value);
        }

        int getsize(){//得到节点所所有的边的数量
            Vertex node = this;
            int count = 0;
            while (node.next != null){
                node = node.next;
                count++;
            }
            return count;

        }
    }

    public Graph(String str){//构建邻接和邻接矩阵
        preGraph(str);
        this.vertexLists = new Vertex[Nodes.size()];
        this.size = Nodes.size();

        int i = 0;
        for (String s: Nodes){//构建邻接表的顶点
            this.vertexLists[i++] = new Vertex(s,0);
        }

        this.martixOfGraph = new int[Nodes.size()][Nodes.size()];//将邻接矩阵赋给对象

        for (Side s: Sides){
            this.vertexLists[getPosition(s.getHead())].add(s.getTail(), s.getKey());//构建邻接表的邻接部分
            this.martixOfGraph[getPosition(s.getHead())][getPosition(s.getTail())] = s.getKey();//这里有所不同的是 各边的权值是各边出现的次数
        }

    }


    public void preGraph(String str){//对字符串进行先行处理 得到顶点表和边表
        String[] arr1 = str.split("[\\s]+");
        String[] arr ;//新建字符串数组要有大小
        List<String> listhaskongge = new ArrayList<>();

        for (int i = 0; i < arr1.length; i++) {
            if ( arr1[i].length() == 0) ;//处理空串
            else listhaskongge.add(arr1[i]);
        }

        arr = new String[listhaskongge.size()];
        listhaskongge.toArray(arr);

        List<String> list = new ArrayList<>(Arrays.asList(arr));
        //for (String st: list) System.out.println(st);


        for ( int i = 0 ; i < list.size() - 1 ; i ++ ) {//构造顶点表
            for ( int j = list.size() - 1 ; j > i; j -- ) {
                if (list.get(j).equals(list.get(i))) {//去除重复的点
                    list.remove(j);
                }
            }
        }

        for(String s: list) {
            Nodes.add(s);
            //System.out.println(s);
        }

        int lenOfString = arr.length;//构建边表
        boolean flag = false;
        for (int i = 0; i < lenOfString - 1 ;i++ ){
            Side S = new Side(arr[i], arr[i+1]);

            for (Side s : this.Sides){
                if (s.equals(S)) {
                    flag = true;
                    s.increase();
                }
            }
            if (flag == false) Sides.add(S);
            flag = false;
        }
    }

    public List<Side> getSides(){
        return Sides;
    }//得到所有边

    public int getSize() {
        return this.size;
    }

    public boolean inNext(String str1, String str2){//判断str1 是不是跟str2邻接
        Vertex vertex = this.vertexLists[getPosition(str1)];

        while (vertex.next != null){
            if( vertex.next.ch.equals(str2))
                return true;

            vertex = vertex.next;
        }

        return false;
    }

    public int getPosition(String ch) {//得到单词在邻接表中的位置
        for(int i=0; i<this.vertexLists.length; i++)
            if(vertexLists[i].ch.equals(ch))
                return i;
        return -1;
    }

    public  void showGraph(){//在控制台上显示图
        ///System.out.println("*****" + vertexLists[0].ch +"******");
        for (int i = 0; i < this.size; i++){
            Vertex temp = vertexLists[i].next;

           // System.out.print("******");
            while (temp != null){
                System.out.print(vertexLists[i].ch+"  "+temp.ch + " "+ temp.value +";");
                temp = temp.next;
            }
            //System.out.println("$$$$$$$$$$");
        }
    }

    public void drawGraph(){
        GraphViz gv = new GraphViz();

        gv.addln(gv.start_graph());//调用GraphViz类来画图
        for (Side side : this.Sides ){
            gv.addln(side.getHead()+" -> "+side.getTail()+"[ label = "+side.getKey()+" ]");
        }
        gv.addln(gv.end_graph());
        //System.out.println(gv.getDotSource());

        JFrame frame = new JFrame();
        frame.setSize(200,200);
        frame.setVisible(true);

        String type = "jpg";//将画的图存储起来
        File out = new File("D:\\pics\\lab1." + type);    // Windows
        gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );

        JLabel background = new JLabel(new ImageIcon("D:\\pics\\lab1.jpg"));//url代表你图片的路径
        frame.add(background);//将你的jlabel背景加到jframe上
    }


    public static void main(String[] args){
        String S = new String("To  explore strange new worlds To seek out new life and new civilizations To  explore");
        Graph G = new Graph(S);
        G.showGraph();
        for(int i = 0; i < G.getSize(); i++){
            for (int j = 0; j < G.getSize(); j++){
                System.out.print(G.martixOfGraph[i][j]);
            }
            System.out.println();
        }
        System.out.print(G.inNext("new","civilizations"));
        System.out.print(G.inNext("new","life"));
        System.out.print(G.inNext("new","new"));
        G.drawGraph();
    }
}
