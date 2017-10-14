package JFAME;

/**
 * Created by uuuup on 2017/9/5.
 */
//GUI编程实例 实现管理系统

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;


import java.awt.*;
//import java.awt.event.*; //添加退出事件时候使用


class LSJFream extends JFrame {
    private static final long serialVersionUID = 1L;
    // 菜单栏的信息
    private JMenuBar bar = new JMenuBar();
    private JMenuItem menu1 = new JMenu("管理员信息");
    private JMenuItem menu2 = new JMenu("雇员表管理");
    private JMenuItem menu3 = new JMenu("雇员信息查询");
    private JMenuItem menu4 = new JMenu("部门表信息管理");
    private JMenuItem menu5 = new JMenu("部门信息查询");
    private JMenuItem menu6 = new JMenu("关于");
    private JMenuItem menu7 = new JMenu("退出");
    // 卡是布局的panel
    CardLayout cardLayout1 = new CardLayout();
    JPanel mainPanel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    JPanel panel6 = new JPanel();
    JPanel panel7 = new JPanel();
    JPanel panel8 = new JPanel();
    JPanel panel9 = new JPanel();
    JPanel panel10 = new JPanel();
    JPanel panel11 = new JPanel();
    JPanel panel12 = new JPanel();
    JPanel panel13 = new JPanel();
    JPanel panel14 = new JPanel();
    JPanel panel15 = new JPanel();


    // 对默认panel用JSplitPane分割
    private JSplitPane jsp = new JSplitPane();


    // 左侧的树形结构信息
    private JTree jtree = null;
    private DefaultMutableTreeNode root;


    public LSJFream() {
    };


    public LSJFream(String title) {
        this.setTitle(title);
        this.setBounds(500, 200, 600, 450);


// 左侧的树形结构
        jtree = new JTree();
        root = new DefaultMutableTreeNode("资产关系系统");
        this.creatTree(root); // 创建树
        jtree = new JTree(root);
// ----------------------------- Tree事件 （点击切换画布）------------------------
        jtree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent arg0) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtree
                        .getLastSelectedPathComponent();
                if (node == null) {
                    return;
                } else if ("退出本系统".equals(node.toString())) {
                    int n = JOptionPane.showConfirmDialog(null, "确定退出？", "提示",
                            JOptionPane.YES_NO_OPTION);// i=0/1
                    if (n == 0) {
                        System.exit(0);
                    }
                    if (n == 1) {


                    }
                } else if ("登陆".equals(node.toString())) {
                    cardLayout1.show(mainPanel, "panel1");
                } else if ("管理员密码修改".equals(node.toString())) {
                    cardLayout1.show(mainPanel, "panel2");
                } else if ("增加雇员".equals(node.toString())) {
                    cardLayout1.show(mainPanel, "panel3");
                } else if ("修改雇员信息".equals(node.toString())) {
                    cardLayout1.show(mainPanel, "panel4");
                } else if ("删除雇员".equals(node.toString())) {
                    cardLayout1.show(mainPanel, "panel5");
                } else if ("按雇员编号查询".equals(node.toString())) {
                    cardLayout1.show(mainPanel, "panel6");
                } else if ("按雇员字段模糊查询".equals(node.toString())) {
                    cardLayout1.show(mainPanel, "panel7");
                } else if ("显示所有雇员".equals(node.toString())) {
                    cardLayout1.show(mainPanel, "panel8");
                } else if ("增加部门".equals(node.toString())) {
                    cardLayout1.show(mainPanel, "panel9");
                } else if ("修改部门信息".equals(node.toString())) {
                    cardLayout1.show(mainPanel, "panel10");
                } else if ("删除部门".equals(node.toString())) {
                    cardLayout1.show(mainPanel, "panel11");
                } else if ("按部门编号查询".equals(node.toString())) {
                    cardLayout1.show(mainPanel, "panel12");
                } else if ("按部门字段模糊查询".equals(node.toString())) {
                    cardLayout1.show(mainPanel, "panel13");
                } else if ("显示所有部门".equals(node.toString())) {
                    cardLayout1.show(mainPanel, "panel14");
                } else if ("关于本系统".equals(node.toString())) {
                    cardLayout1.show(mainPanel, "panel15");
                } else {
                    return;
                }
            }
        });


        JScrollPane jscp = new JScrollPane(jtree);// 添加滚动
        jtree.setBackground(Color.CYAN);


// 对中间panel的分割
        jsp.setDividerSize(1);
        jsp.setOneTouchExpandable(false); // 设置分隔条的折叠按钮
        jsp.setDividerLocation(160);
        jsp.setContinuousLayout(true);
        jsp.setLeftComponent(jscp);
        this.getContentPane().add(jsp);


// 右侧的主界面
        this.addCardPanel();
        mainPanel.setBackground(Color.CYAN);
        jsp.setRightComponent(mainPanel);


// 添加菜单栏
        this.setJMenuBar(bar);
        this.addMenu();


        this.setVisible(true);



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设定窗体关闭后自动退出进程
// 添加退出事件
// this.addWindowListener(new WindowAdapter() {
// @Override
// public void windowClosing(WindowEvent e) {
// super.windowClosing(e);
// System.exit(0);
// }
//
// });
    }


    // 右侧添加卡片
    private void addCardPanel() {
        mainPanel.setLayout(cardLayout1);
        mainPanel.setBackground(Color.CYAN);
// 实例化卡片内容
        panel1(panel1);
        panel2(panel2);
        panel3(panel3);
        panel4(panel4);
        panel5(panel5);
        panel6(panel6);
        panel7(panel7);
        panel8(panel8);
        panel9(panel9);
        panel10(panel10);
        panel11(panel11);
        panel12(panel12);
        panel13(panel13);
        panel14(panel14);
        panel15(panel15);
// 卡式布局
        mainPanel.add(panel1, "panel1");
//添加卡片   设置标签
        mainPanel.add(panel2, "panel2");
        mainPanel.add(panel3, "panel3");
        mainPanel.add(panel4, "panel4");
        mainPanel.add(panel5, "panel5");
        mainPanel.add(panel6, "panel6");
        mainPanel.add(panel7, "panel7");
        mainPanel.add(panel8, "panel8");
        mainPanel.add(panel9, "panel9");
        mainPanel.add(panel10, "panel10");
        mainPanel.add(panel11, "panel11");
        mainPanel.add(panel12, "panel12");
        mainPanel.add(panel13, "panel13");
        mainPanel.add(panel14, "panel14");
        mainPanel.add(panel15, "panel15");
    }


    // 添加菜单事件
    private void addMenu() {
        JMenuItem jm1_1 = new JMenuItem("登录");
        JMenuItem jm1_2 = new JMenuItem("管理员密码修改");
        JMenuItem jm2_1 = new JMenuItem("增加雇员");
        JMenuItem jm2_2 = new JMenuItem("修改雇员信息");
        JMenuItem jm2_3 = new JMenuItem("删除雇员");
        JMenuItem jm3_1 = new JMenuItem("按雇员编号查询");
        JMenuItem jm3_2 = new JMenuItem("按雇员字段模糊查询");
        JMenuItem jm3_3 = new JMenuItem("显示所有雇员");
        JMenuItem jm4_1 = new JMenuItem("增加部门");
        JMenuItem jm4_2 = new JMenuItem("修改部门信息");
        JMenuItem jm4_3 = new JMenuItem("删除部门");
        JMenuItem jm5_1 = new JMenuItem("按部门编号查询");
        JMenuItem jm5_2 = new JMenuItem("按部门字段模糊查询");
        JMenuItem jm5_3 = new JMenuItem("显示所有部门");
        JMenuItem jm6 = new JMenuItem("关于本系统");
        JMenuItem jm7 = new JMenuItem("退出本系统");
// 上面的菜单按钮
        menu1.add(jm1_1);
        menu1.add(jm1_2);
        menu2.add(jm2_1);
        menu2.add(jm2_2);
        menu2.add(jm2_3);
        menu3.add(jm3_1);
        menu3.add(jm3_2);
        menu3.add(jm3_3);
        menu4.add(jm4_1);
        menu4.add(jm4_2);
        menu4.add(jm4_3);
        menu5.add(jm5_1);
        menu5.add(jm5_2);
        menu5.add(jm5_3);
        menu6.add(jm6);
        menu7.add(jm7);
        bar.add(menu1);
        bar.add(menu2);
        bar.add(menu3);
        bar.add(menu4);
        bar.add(menu5);
        bar.add(menu6);
        bar.add(menu7);
    }


    // 创建左侧的树形结构
    private void creatTree(DefaultMutableTreeNode root) {
        DefaultMutableTreeNode bigNode = null;
        DefaultMutableTreeNode node2 = null;
        bigNode = new DefaultMutableTreeNode("管理员信息");// 主tree
        root.add(bigNode);
        node2 = new DefaultMutableTreeNode("登陆"); // 子tree
        bigNode.add(node2);
        node2 = new DefaultMutableTreeNode("管理员密码修改"); // 子tree
        bigNode.add(node2);
        bigNode = new DefaultMutableTreeNode("雇员表信息管理");// 主tree
        root.add(bigNode);
        node2 = new DefaultMutableTreeNode("增加雇员"); // 子tree
        bigNode.add(node2);
        node2 = new DefaultMutableTreeNode("修改雇员信息"); // 子tree
        bigNode.add(node2);
        node2 = new DefaultMutableTreeNode("删除雇员"); // 子tree
        bigNode.add(node2);
        bigNode = new DefaultMutableTreeNode("雇员信息查询");// 主tree
        root.add(bigNode);
        node2 = new DefaultMutableTreeNode("按雇员编号查询"); // 子tree
        bigNode.add(node2);
        node2 = new DefaultMutableTreeNode("按雇员字段模糊查询"); // 子tree
        bigNode.add(node2);
        node2 = new DefaultMutableTreeNode("显示所有雇员"); // 子tree
        bigNode.add(node2);
        bigNode = new DefaultMutableTreeNode("部门表信息管理");// 主tree
        root.add(bigNode);
        node2 = new DefaultMutableTreeNode("增加部门"); // 子tree
        bigNode.add(node2);
        node2 = new DefaultMutableTreeNode("修改部门信息"); // 子tree
        bigNode.add(node2);
        node2 = new DefaultMutableTreeNode("删除部门"); // 子tree
        bigNode.add(node2);
        bigNode = new DefaultMutableTreeNode("部门信息查询");// 主tree
        root.add(bigNode);
        node2 = new DefaultMutableTreeNode("按部门编号查询"); // 子tree
        bigNode.add(node2);
        node2 = new DefaultMutableTreeNode("按部门字段模糊查询"); // 子tree
        bigNode.add(node2);
        node2 = new DefaultMutableTreeNode("显示所有部门"); // 子tree
        bigNode.add(node2);
        bigNode = new DefaultMutableTreeNode("关于");// 主tree
        root.add(bigNode);
        node2 = new DefaultMutableTreeNode("关于本系统"); // 子tree
        bigNode.add(node2);
        bigNode = new DefaultMutableTreeNode("退出");// 主tree
        root.add(bigNode);
        node2 = new DefaultMutableTreeNode("退出本系统"); // 子tree
        bigNode.add(node2); // 子tree


    }


    // panel1的具体实现(登陆界面)
    private void panel1(JPanel panel1) {
// 设计各个panel的具体组件
        panel1.setBackground(Color.CYAN);
        panel1.setLayout(null);
        JLabel titleLab = new JLabel("雇员信息管理系统", JLabel.CENTER);
        titleLab.setForeground(Color.WHITE);
        titleLab.setFont(new Font("华文行楷", Font.BOLD, 40));
        titleLab.setBounds(20, 30, 400, 40);
        panel1.add(titleLab);
        JLabel userNameLab = new JLabel("用户名：");
        userNameLab.setFont(new Font("华文行楷", Font.PLAIN, 20));
        userNameLab.setBounds(20, 150, 400, 20);
        panel1.add(userNameLab);
        JTextField userNameFile = new JTextField(25);
        userNameFile.setBounds(100, 150, 300, 20);
        userNameFile.setFont(new Font("华文行楷", Font.PLAIN, 20));
        panel1.add(userNameFile);
        JLabel userPassLab = new JLabel("密   码：");
        userPassLab.setFont(new Font("华文行楷", Font.PLAIN, 20));
        userPassLab.setBounds(20, 200, 400, 20);
        panel1.add(userPassLab);
        JTextField userPassFile = new JPasswordField(25);
        userPassFile.setBounds(100, 200, 300, 20);
        userPassFile.setFont(new Font("华文行楷", Font.PLAIN, 20));
        panel1.add(userPassFile);
        JButton dengluBut = new JButton("登陆");
        dengluBut.setFont(new Font("华文行楷", Font.PLAIN, 20));
        dengluBut.setBounds(100, 280, 100, 30);
        panel1.add(dengluBut);
        JButton quxiaoBut = new JButton("取消");
        quxiaoBut.setFont(new Font("华文行楷", Font.PLAIN, 20));
        quxiaoBut.setBounds(260, 280, 100, 30);
        panel1.add(quxiaoBut);
    }


    // panel2的具体实现（管理员修改密码）
    private void panel2(JPanel panel2) {
        panel2.setBackground(Color.CYAN);
        JLabel titleLab = new JLabel("管理员密码修改", JLabel.CENTER);
        titleLab.setForeground(Color.WHITE);
        titleLab.setFont(new Font("华文行楷", Font.BOLD, 40));
        titleLab.setBounds(20, 30, 400, 40);
        panel2.add(titleLab);
// JTextField userNameFile = new JTextField();
// userNameFile.setBounds(100, 100, 300, 20);
// userNameFile.setFont(new Font("华文行楷", Font.PLAIN, 20));
// panel2.add(userNameFile);
// JLabel userPassLab = new JLabel("密   码：");
// userPassLab.setFont(new Font("华文行楷", Font.PLAIN, 20));
// userPassLab.setBounds(20, 150, 400, 20);
    }


    // ---------其他卡片的实现----------------------------------------------
    private void panel3(JPanel panel3) {
        panel3.setBackground(Color.CYAN);
        JLabel titleLab = new JLabel("增加雇员", JLabel.CENTER);
        titleLab.setForeground(Color.WHITE);
        titleLab.setFont(new Font("华文行楷", Font.BOLD, 40));
        titleLab.setBounds(20, 30, 400, 40);
        panel3.add(titleLab);
    }


    private void panel4(JPanel panel4) {
        panel4.setBackground(Color.CYAN);
        JLabel titleLab = new JLabel("修改雇员信息", JLabel.CENTER);
        titleLab.setForeground(Color.WHITE);
        titleLab.setFont(new Font("华文行楷", Font.BOLD, 40));
        titleLab.setBounds(20, 30, 400, 40);
        panel4.add(titleLab);
    }


    private void panel5(JPanel panel5) {
        panel5.setBackground(Color.CYAN);
        JLabel titleLab = new JLabel("删除雇员", JLabel.CENTER);
        titleLab.setForeground(Color.WHITE);
        titleLab.setFont(new Font("华文行楷", Font.BOLD, 40));
        titleLab.setBounds(20, 30, 400, 40);
        panel5.add(titleLab);
    }


    private void panel6(JPanel panel6) {
        panel6.setBackground(Color.CYAN);
        JLabel titleLab = new JLabel("按雇员编号排序", JLabel.CENTER);
        titleLab.setForeground(Color.WHITE);
        titleLab.setFont(new Font("华文行楷", Font.BOLD, 40));
        titleLab.setBounds(20, 30, 400, 40);
        panel6.add(titleLab);
    }


    private void panel7(JPanel panel7) {
        panel7.setBackground(Color.CYAN);
        JLabel titleLab = new JLabel("按雇员字段模糊查询", JLabel.CENTER);
        titleLab.setForeground(Color.WHITE);
        titleLab.setFont(new Font("华文行楷", Font.BOLD, 40));
        titleLab.setBounds(20, 30, 400, 40);
        panel7.add(titleLab);
    }


    private void panel8(JPanel panel8) {
        panel8.setBackground(Color.CYAN);
        JLabel titleLab = new JLabel("显示所有雇员", JLabel.CENTER);
        titleLab.setForeground(Color.WHITE);
        titleLab.setFont(new Font("华文行楷", Font.BOLD, 40));
        titleLab.setBounds(20, 30, 400, 40);
        panel8.add(titleLab);
    }


    private void panel9(JPanel panel9) {
        panel9.setBackground(Color.CYAN);
        JLabel titleLab = new JLabel("增加部门", JLabel.CENTER);
        titleLab.setForeground(Color.WHITE);
        titleLab.setFont(new Font("华文行楷", Font.BOLD, 40));
        titleLab.setBounds(20, 30, 400, 40);
        panel9.add(titleLab);
    }


    private void panel10(JPanel panel10) {
        panel10.setBackground(Color.CYAN);
        JLabel titleLab = new JLabel("修改部门信息", JLabel.CENTER);
        titleLab.setForeground(Color.WHITE);
        titleLab.setFont(new Font("华文行楷", Font.BOLD, 40));
        titleLab.setBounds(20, 30, 400, 40);
        panel10.add(titleLab);
    }


    private void panel11(JPanel panel11) {
        panel11.setBackground(Color.CYAN);
        JLabel titleLab = new JLabel("删除部门", JLabel.CENTER);
        titleLab.setForeground(Color.WHITE);
        titleLab.setFont(new Font("华文行楷", Font.BOLD, 40));
        titleLab.setBounds(20, 30, 400, 40);
        panel11.add(titleLab);
    }


    private void panel12(JPanel panel12) {
        panel12.setBackground(Color.CYAN);
        JLabel titleLab = new JLabel("按部门编号查询", JLabel.CENTER);
        titleLab.setForeground(Color.WHITE);
        titleLab.setFont(new Font("华文行楷", Font.BOLD, 40));
        titleLab.setBounds(20, 30, 400, 40);
        panel12.add(titleLab);
    }


    private void panel13(JPanel panel13) {
        panel13.setBackground(Color.CYAN);
        JLabel titleLab = new JLabel("按部门字段模糊查询", JLabel.CENTER);
        titleLab.setForeground(Color.WHITE);
        titleLab.setFont(new Font("华文行楷", Font.BOLD, 40));
        titleLab.setBounds(20, 30, 400, 40);
        panel13.add(titleLab);
    }


    private void panel14(JPanel panel14) {
        panel14.setBackground(Color.CYAN);
        JLabel titleLab = new JLabel("显示所有部门", JLabel.CENTER);
        titleLab.setForeground(Color.WHITE);
        titleLab.setFont(new Font("华文行楷", Font.BOLD, 40));
        titleLab.setBounds(20, 30, 400, 40);
        panel14.add(titleLab);
    }


    private void panel15(JPanel panel15) {
        panel15.setBackground(Color.CYAN);
        JLabel titleLab = new JLabel("关于本系统", JLabel.CENTER);
        titleLab.setForeground(Color.WHITE);
        titleLab.setFont(new Font("华文行楷", Font.BOLD, 40));
        titleLab.setBounds(20, 30, 400, 40);
        panel15.add(titleLab);
    }
}


