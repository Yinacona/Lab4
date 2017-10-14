package JFAME;

/**
 * Created by uuuup on 2017/9/11.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


        import java.awt.BorderLayout;
        import java.awt.Color;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.logging.Level;
        import java.util.logging.Logger;
        import javax.swing.Icon;
        import javax.swing.ImageIcon;
        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JTextPane;
        import javax.swing.WindowConstants;
        import javax.swing.text.BadLocationException;
        import javax.swing.text.SimpleAttributeSet;
        import javax.swing.text.Style;
        import javax.swing.text.StyleConstants;
        import javax.swing.text.StyledDocument;

/**
 *
 * @author Administrator
 */
public class JTextPaneDemo extends JFrame{
    private JTextPane messagePane = null ;
    private JButton insertBtn = null ;
    public JTextPaneDemo() {
        super("WuJinbo Demo") ;
        messagePane = new JTextPane() ;
        insertBtn = new JButton("Insert");
        addListener2Button();
        this.getContentPane().add(messagePane, BorderLayout.CENTER);
        this.getContentPane().add(insertBtn, BorderLayout.SOUTH);

        this.setSize(200, 300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new JTextPaneDemo().setVisible(true);
    }

    private void addListener2Button() {
        insertBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Icon homeIcon = new ImageIcon(ClassLoader.getSystemResource("com/jinbo/images/home.gif"));
                Icon refreshIcon = new ImageIcon(ClassLoader.getSystemResource("com/jinbo/images/refresh.gif"));
                Icon baiduIcon = new ImageIcon(ClassLoader.getSystemResource("com/jinbo/images/baidu.gif"));

                insertMessage(false, Color.RED,homeIcon);
                insertMessage(true, Color.BLUE,refreshIcon);
                insertMessage(false, Color.BLACK,baiduIcon);
            }
        });
    }

    private void insertMessage(boolean isBold, Color color,Icon icon) {
        StyledDocument doc = messagePane.getStyledDocument();

        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setBold(attr, isBold);
        StyleConstants.setForeground(attr, color);

        Style style = doc.addStyle("testname", null);
        StyleConstants.setIcon(style, icon);

        try {
            doc.insertString(doc.getLength(), "Hello World"+"\n", style);
            doc.insertString(doc.getLength(), "Hello World"+"\n", attr);
        } catch (BadLocationException ex) {
            Logger.getLogger(JTextPaneDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}