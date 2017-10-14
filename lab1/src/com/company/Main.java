package com.company;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;
import java.lang.*;


public class Main {
    private Graph G;
   // private JFrame jframe = new JFrame();



    public Main(){
        //GUI设计
        JFrame frame = new JFrame("软件工程实验一");
        frame.setBounds(500, 200, 300, 500);
        frame.setLayout(null);
        JButton button1 = new JButton("生成有向图");
        JButton button2 = new JButton("展示有向图");
        JButton button3 = new JButton("查询桥接词");
        JButton button4 = new JButton("生成新文本");
        JButton button5 = new JButton("最短路径");
        JButton button6 = new JButton("随机游走");
        button1.setBounds(100, 10, 100, 50);
        button2.setBounds(100, 80, 100, 50);
        button3.setBounds(100, 150, 100, 50);
        button4.setBounds(100, 220, 100, 50);
        button5.setBounds(100, 290, 100, 50);
        button6.setBounds(100, 360, 100, 50);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));//设置当前打开的默认路径


        button1.addActionListener(new ActionListener() {
            @Override//当点击按钮1时生成一个新窗口，并让用户输入路径
            public void actionPerformed(ActionEvent e) {
                String path ;
                try{
                    int result = chooser.showOpenDialog(null);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        path = chooser.getSelectedFile().getPath();

                        if (readFileByChars(path).equals(new String("文件不存在")))
                            JOptionPane.showMessageDialog(null, "文件不存在",
                                    "Error！", JOptionPane.ERROR_MESSAGE);//如果结果不是空，则显示有向图已经建立
                        else if (G == null) JOptionPane.showMessageDialog(null, "有向图尚未生成",
                                "Error!", JOptionPane.ERROR_MESSAGE);//如果结果不是空，则显示有向图已经建立
                        else JOptionPane.showMessageDialog(null, "有向图已经建立",
                                    "提示信息", JOptionPane.INFORMATION_MESSAGE);//如果结果不是空，则显示有向图已经建立

                    }
                }catch (Exception e2){
                    e2.printStackTrace();
                }
                //D:\JAVACODE\JAVA_LAB1\text1.txt
            }
        });

        button2.addActionListener(new ActionListener() {//展示生成有向图
            @Override
            public void actionPerformed(ActionEvent e) {
                if(G != null ) {
                    try {
                        showDirectedGraph(G);
                    }catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                else JOptionPane.showMessageDialog(null,"有向图尚未生成",
                        "Error!", JOptionPane.ERROR_MESSAGE);//如果结果不是空，则显示有向图已经建立
            }
        });

        button3.addActionListener(new ActionListener() {//查询连接词
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( G == null) JOptionPane.showMessageDialog(null,"有向图尚未生成",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                else{
                    String word1 = JOptionPane.showInputDialog(null,"word1");
                    while(word1.length() == 0 )
                        word1 = JOptionPane.showInputDialog(null,"请再次输入word1");

                    String word2 = JOptionPane.showInputDialog(null,"word2");
                    while(word2.length() == 0 )
                        word2 =JOptionPane.showInputDialog(null,"请再次输入word2");

                    JOptionPane.showMessageDialog(null,queryBridgeWords(word1,word2),
                            "BridgeWords",JOptionPane.INFORMATION_MESSAGE);
                    //System.out.println(queryBridgeWords(word1,word2));

                }
            }
        });

        button4.addActionListener(new ActionListener() {//生成新文本
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( G == null)JOptionPane.showMessageDialog(null,"有向图尚未生成",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                else {
                    String inputtext = JOptionPane.showInputDialog(null, "请输入文本");
                    JOptionPane.showMessageDialog(null,generateNewText(inputtext),
                    "NewText",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (G == null)JOptionPane.showMessageDialog(null,"有向图尚未生成",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                else {
                    String word1 = JOptionPane.showInputDialog(null, "请输入路径起点");
                    while (word1.length() == 0)
                        word1 = JOptionPane.showInputDialog(null, "请再次输入路径起点");

                    String word2 = JOptionPane.showInputDialog(null, "请输入路径终点");

                    try{
                        if (calcShortestPath(word1, word2).length() != 0)
                            JOptionPane.showMessageDialog(null, calcShortestPath(word1, word2));
                    }catch (Exception e2){
                            e2.printStackTrace();
                    }



                }
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (G == null) JOptionPane.showMessageDialog(null, "有向图尚未生成",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                else {
                    String[] arr = randomWalk().split("\\s+");
                    List<String> list = new ArrayList(Arrays.asList(arr));
                    List <String> answerToFile = new ArrayList<>();//存储向文件写入的结果

                    JFrame jFrame  = new JFrame();//创建一个文本框
                    JTextPane messagePane = new JTextPane() ;
                    JButton insertBtn = new JButton("继续");
                    JButton stopBtn = new JButton("停止");

                    JPanel p = new JPanel();
                    jFrame.getContentPane().add(messagePane, BorderLayout.CENTER);//进行布局
                    p.add(insertBtn);
                    p.add(stopBtn);
                    jFrame.getContentPane().add(p,BorderLayout.SOUTH);

                    SimpleAttributeSet attrset = new SimpleAttributeSet();//设置字体大小
                    StyleConstants.setFontSize(attrset,16);

                    JScrollPane jsp=new JScrollPane(messagePane);//滚动条
                    jFrame.add(jsp);

                    insertBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String str;

                            if (list.size() != 0 ) {
                                str = list.get(0);
                                answerToFile.add(str+" ");
                            }
                            else str = new String("遍历已经结束");

                            Document docs = messagePane.getDocument();//获得文本对象
                            try {
                                docs.insertString(docs.getLength(),str+"\n",attrset);//对文本进行追加
                            } catch (BadLocationException e1) {
                                e1.printStackTrace();
                            }

                            if (list.size() != 0)list.remove(0);
                        }
                    });
                    //停止按钮则负责将数据输入到文件中
                    stopBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try{
                                File file = new File("D:\\JAVACODE\\JAVA_LAB1\\randomWalk.txt");//向文件中写入数据
                                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                                BufferedWriter bw = new BufferedWriter(fw);
                                for (String str :answerToFile) {
                                    bw.write(str);
                                }
                                bw.close();

                            }catch(IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });

                    jFrame.setSize(200, 300);
                    jFrame.setVisible(true);

                }
            }

        });
    }

    public  String readFileByChars(String filePath) {
        String result = new String();
        File file = new File(filePath);
        if (!file.exists()) result = "文件不存在";
        else {
            Reader reader ;
            try {
                //一次读一个字符 空格的ascii码 32 大写字母65到90 小写字母97到122
                reader = new InputStreamReader(new FileInputStream(file));
                int tempchar;

                while ((tempchar = reader.read()) != -1) {

                        if (tempchar == 10)
                            result = result + " ";

                        else if (tempchar == 32 || (tempchar >= 97 && tempchar <= 122)) {
                            result = result + (char) tempchar;
                        } else if ((tempchar >= 65 && tempchar <= 90)) {
                            result = result + (char) (tempchar + 32);
                        } else result = result + " ";

                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.G = new Graph(result);//构建Graph
            //this.G.showGraph();
        }
        //System.out.println(result);
        return result;
    }

    public void showDirectedGraph(Graph G) throws IOException {
        GraphViz gv = new GraphViz();

        gv.addln(gv.start_graph());//调用GraphViz类来画图
        for (Side side : this.G.getSides() ){
            gv.addln(side.getHead()+" -> "+side.getTail()+"[ label = "+side.getKey()+" ]");
        }
        gv.addln(gv.end_graph());
        //System.out.println(gv.getDotSource());
//        JFrame jframe = new JFrame();
//        jframe.setSize(200,200);
//        jframe.setVisible(true);

        String type = "jpg";//将画的图存储起来
        File out = new File("D:\\pics\\lab1." + type);    // Windows
        gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );

        try {
            openFile("D:\\pics\\lab1.jpg");
        }catch (IOException e) {
            e.printStackTrace();
        }
//        JLabel background = new JLabel(new ImageIcon("D:\\pics\\lab1.jpg"));//url代表你图片的路径
//        jframe.add(background);//将你的jlabel背景加到jframe上
//        JScrollPane jsp=new JScrollPane(background);//滚动条
//        jframe.add(jsp);

        return ;
    }

    public List<String> BridgeWords(String word1, String word2){
        List<String> answers = new ArrayList<>();
        int positionOfWord1 = this.G.getPosition(word1);
        int positionOfWord2 = this.G.getPosition(word2);


        if (!(positionOfWord1 == -1 || positionOfWord2 == -1)) {
            Graph.Vertex temp = this.G.vertexLists[positionOfWord1];
            while (temp.next != null) {//访问第一个词的邻接词
                if (this.G.inNext(temp.next.ch, word2))//如果第二个词出现在邻接词的下一个词
                    answers.add(temp.next.ch);//就把它添加进结果数组
                temp = temp.next;
            }
        }

        else ;//不存在则返回空集合

        return answers;
    }

    public  String queryBridgeWords(String word1, String word2) {
        String answer ;

        int positionOfWord1 = this.G.getPosition(word1);
        int positionOfWord2 = this.G.getPosition(word2);

        if (positionOfWord1 == -1 || positionOfWord2 == -1){//先查询这两个词是否在文本中出现
            answer = "No " + word1 + " or " + word2 + " in the graph!";
            return answer;
        }

        else
        {
            List<String> answers = BridgeWords(word1, word2);
            int size = answers.size();//根据数组的大小来输出结果
            if (size == 0)
                answer = "No bridge from " + word1 + " to " + word2;

            else if (size == 1){
                answer ="The bridge words from “" + word1 + "” to “" + word2 + "” is: ";
                for (String str :answers)
                    answer = answer + str;
            }

            else {
                answer ="The bridge words from “" + word1 + "” to “" + word2 + "” are: ";
                for (String str :answers)
                    answer = answer + str + " ";
            }
            return  answer;
        }
    }

    private String generateNewText(String inputText){
        String [] arr = inputText.split("\\s+");
        String  answer = new String();
        if (arr.length == 0 || arr.length == 1) return inputText;

        for (int i = 0; i < arr.length - 1; i++){
            answer = answer + arr[i];
            List<String> listOfBridgeWords = BridgeWords(arr[i],arr[i+1]);

            if (listOfBridgeWords.size() == 0)
                answer = answer + " ";

            else if (listOfBridgeWords.size() == 1)
                answer = answer + " " + listOfBridgeWords.get(0) + " ";

            else {
                int index = (int)(Math.random()*listOfBridgeWords.size());
                answer = answer + " " + listOfBridgeWords.get(index) + " ";
            }

        }

        answer  = answer + arr[arr.length - 1];
        return  answer;
    }

    private String calcShortestPath(String word1, String word2){
        //List<String> path = new ArrayList<>();//
        int positionOfWord1 = this.G.getPosition(word1);
        int positionOfWord2 = this.G.getPosition(word2);

        //先查询这两个词是否在文本中出现
        if (positionOfWord1 == -1 && word2.length() == 0) return "No " + word1 + " in the graph!";
        else if(positionOfWord1 == -1 || (positionOfWord2 == -1 && word2.length() != 0) )//这是真正不存在的情况，
            return "No " + word1 + " or " + word2 + " in the graph!";


        int BigNum = 10000000;
        int[] path = new int[this.G.getSize()];//存储最短路径的最后节点
        int[][] graph = new int[this.G.getSize()][this.G.getSize()];//邻接矩阵
        int[] dist = new int[this.G.getSize()];//源点到目标点的最短距离
        boolean[] vis= new boolean[this.G.getSize()];//是否访问访问过节点
        Arrays.fill(vis, false);//将vis数组初始化为false

        for(int i = 0; i < this.G.getSize(); i++) {//初始化邻接矩阵
            for (int j = 0; j < this.G.getSize(); j++) {
                if (this.G.martixOfGraph[i][j] == 0)//如果图中邻接矩阵值是0, 则将其置为极大值 表示不可达
                    graph[i][j] = BigNum;
                else
                    graph[i][j] = this.G.martixOfGraph[i][j];
            }
        }
        int index = this.G.getPosition(word1);//初始化距离
        for (int i = 0; i < dist.length; i++)  dist[i] = graph[index][i];
        Arrays.fill(path,index);

        vis[index] = true;//开始循环遍历
        int min , v = 0;
        for (int i = 0; i < this.G.getSize(); i++){
            min = BigNum;
            for(int j = 0; j < this.G.getSize(); j++)    {//找到当前最小的点
                if(vis[j] != true && dist[j] < min)    {
                    min = dist[j];
                    v = j;
                }
            }
            if(min == BigNum) break;//此时证明源点到剩下点均是不可达的
            else vis[v] = true;//将该点标记为访问过

            for(int j = 0; j < this.G.getSize(); j++) {
                if( vis[j] != true && dist[j] > dist[v] + graph[v][j]) {
                    dist[j] = dist[v] + graph[v][j];
                    path[j] = v;
                }
            }
        }//对图的处理结束，接下来是输出结果

        String answer = new String();
        if(word2.length() == 0){//当第二个字符串没有输入时

            JFrame jFrame  = new JFrame();//创建一个文本框  文本框布局
            JButton jButton = new JButton("next");
            jFrame.getContentPane().add(jButton,BorderLayout.SOUTH);
            JTextPane messagePane = new JTextPane() ;
            SimpleAttributeSet attrset = new SimpleAttributeSet();//设置字体大小
            StyleConstants.setFontSize(attrset,16);

            JScrollPane jsp=new JScrollPane(messagePane);//滚动条
            jFrame.add(jsp);

            jButton.addActionListener(new ActionListener() {
                int i = 0;
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Side> list = new ArrayList<>();
                    String answerToJframe = new String();//存储一条路径
                    //answerToJframe = "";
                    String str1, str2;
                    if ( i < G.getSize()  ){
                        Stack<String> stack =  new Stack<>();//利用栈存储路径

                        int j = i;
                        if (j != index){//如果不是源点
                            j = path[j];
                            while (j != index){
                                stack.push(G.vertexLists[j].ch);
                                j = path[j];
                            }
                        }//路径添加完毕

                        if (dist[i] != BigNum && i != index){//对可达路径的处理
                            answerToJframe = answerToJframe + word1 + "->";
                            str1 = word1;
                            while(!stack.empty()){
                                str2 = stack.pop();
                                answerToJframe = answerToJframe + str2 + "->" ;
                                list.add(new Side(str1, str2));
                                str1 = str2;
                            }
                            list.add(new Side(str1, G.vertexLists[i].ch));
                            answerToJframe = answerToJframe + G.vertexLists[i].ch +";     " + word1 + "到" + G.vertexLists[i].ch
                                    + "的最短路径长度为：" + dist[i] +"\n";
                        }

                        else  answerToJframe = word1 + " and " + G.vertexLists[i].ch +"不可达" + "\n";
                        answerToJframe = answerToJframe + "\n";
                        Document docs = messagePane.getDocument();//获得文本对象
                        try {
                            docs.insertString(docs.getLength(),answerToJframe ,attrset);//对文本进行追加
                        } catch (BadLocationException e1) {
                            e1.printStackTrace();
                        }

                        GraphViz gv = new GraphViz();
                        if (!list.isEmpty()) {//当有边时候开始画
                            gv.addln(gv.start_graph());//调用GraphViz类来画图
                            for (Side side : G.getSides()) {
                                if (list.contains(side))//如果边在路径中，则显示为红色
                                    gv.addln(side.getHead() + " -> " + side.getTail() + "[ label = " + side.getKey() + " " + "," + "color = \"red\"]");
                                else
                                    gv.addln(side.getHead() + " -> " + side.getTail() + "[ label = " + side.getKey() + " ]");

                            }
                            gv.addln(gv.end_graph());

                            String type = "jpg";//将画的图存储起来
                            File out = new File("D:\\pics\\lab1." + type);    // Windows
                            gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
                            try {
                               // taskkill /f /im
                                openFile("D:\\pics\\lab1.jpg");
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }

                    }
                    else {
                        Document docs = messagePane.getDocument();//获得文本对象
                        try {
                            docs.insertString(docs.getLength(),"展示结束\n" ,attrset);//对文本进行追加
                        } catch (BadLocationException e1) {
                            e1.printStackTrace();
                        }
                    }
                    list.clear();
                    i++;
                }
            });
            jFrame.setSize(500, 500);
            jFrame.setVisible(true);

            answer = "";
        }

        else if (dist[this.G.getPosition(word2)] != BigNum){//当二者之间有最短路径
            Stack<String> stack =  new Stack<>();
            List<Side> side_path = new ArrayList<>();//把经过的边存储起来好画图
            String str1, str2;

            int j = this.G.getPosition(word2);
            j = path[j];
            while (j != index){
                stack.push(this.G.vertexLists[j].ch);
                j = path[j];
            }

            answer = word1 + "->";
            str1 = word1;
            while(!stack.empty()){
                str2 = stack.pop();
                answer = answer + str2 + "->";

                side_path.add(new Side(str1, str2));//将边加入列表
                str1 = str2;
            }
            answer = answer + word2 +";   "+word1+"到"+word2+"的最短路径长度为："+dist[this.G.getPosition(word2)] + "\n";
            side_path.add(new Side(str1, word2));

            //开始画图
            GraphViz gv = new GraphViz();
            gv.addln(gv.start_graph());//调用GraphViz类来画图
            for (Side side : this.G.getSides() ){
                if (side_path.contains(side))//如果边在路径中，则显示为红色
                    gv.addln(side.getHead()+" -> "+side.getTail()+"[ label = "+side.getKey()+" "+","+"color = \"red\"]");
                else
                    gv.addln(side.getHead()+" -> "+side.getTail()+"[ label = "+side.getKey()+" ]");

            }
            gv.addln(gv.end_graph());
            //展示图

            String type = "jpg";//将画的图存储起来
            File out = new File("D:\\pics\\lab1." + type);    // Windows
            gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );

            try {
                openFile("D:\\pics\\lab1.jpg");
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            answer = word1 + " and " + word2 +"不可达" + "\n";
        }

        return  answer;
    }

    private String randomWalk(){
        List<Side> randomPath = new ArrayList<>();//存储随机路径，便于单步执行
        java.util.Random r=new java.util.Random();
        int start = (r.nextInt() % this.G.getSize() + this.G.getSize() ) % this.G.getSize(); //随机生成一个数并以此节点开始遍历;
        int numberOfSide = this.G.vertexLists[start].getsize();//该节点所拥有的边的数量

        while( numberOfSide != 0 ) {//不等于0就是有出边
            int randomSide = ( r.nextInt() % numberOfSide + numberOfSide ) % numberOfSide;//得到该节点的出边
            Graph.Vertex outSide = this.G.vertexLists[start].next;
            for (int i = 0; i < randomSide; i++){
                outSide = outSide.next;
            }


            if( randomPath.contains(new Side(this.G.vertexLists[start].ch, outSide.ch))) {
                randomPath.add(new Side(this.G.vertexLists[start].ch, outSide.ch));
                break;
            }

            else{
                randomPath.add(new Side(this.G.vertexLists[start].ch, outSide.ch));//加入重复边之后结束
            }

            start = this.G.getPosition(outSide.ch); //以新加入的节点为起始节点
            numberOfSide = this.G.vertexLists[start].getsize();
        }

        String  answer = new String();
        for(int i = 0; i < randomPath.size(); i++){
            answer += randomPath.get(i).getHead() + " ";//无论是有重复边结束, 还是没有出边结束，都需要把最后一天边完整输出；
        }
        answer += randomPath.get(randomPath.size()-1).getTail();//加入最后一天边的尾节点;

        return  answer;
    }

    public void openFile (String path) throws IOException{
        Runtime.getRuntime().exec("cmd /C Start " + path);//cmd 相当于双击打开文件
    }


    public static void main(String[] args) {
        Main main = new Main();
    }
}

