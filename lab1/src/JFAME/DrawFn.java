package JFAME;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
public class DrawFn extends JFrame{
    private JTextField txt_interval;
    private JTextField txt_c;
    private JTextField txt_b;
    private JTextField txt_a;
    private drawFnPanel panel = new drawFnPanel();
    /**
     * @param args
     */
    public static void main(String[] args) {
        DrawFn frame=new DrawFn();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public DrawFn() {
        super();
        getContentPane().setLayout(null);
        final JLabel aLabel = new JLabel();
        aLabel.setText("a=");
        aLabel.setBounds(10, 10, 21, 18);
        getContentPane().add(aLabel);
        txt_a = new JTextField();
        txt_a.setBounds(31, 8, 87, 22);
        getContentPane().add(txt_a);
        final JLabel bLabel = new JLabel();
        bLabel.setText("b=");
        bLabel.setBounds(136, 10, 21, 18);
        getContentPane().add(bLabel);
        txt_b = new JTextField();
        txt_b.setBounds(159, 8, 87, 22);
        getContentPane().add(txt_b);
        final JLabel cLabel = new JLabel();
        cLabel.setText("c=");
        cLabel.setBounds(267, 10, 32, 18);
        getContentPane().add(cLabel);
        txt_c = new JTextField();
        txt_c.setBounds(290, 8, 87, 22);
        getContentPane().add(txt_c);
        final JLabel label = new JLabel();
        label.setText("步长=");
        label.setBounds(398, 10, 47, 18);
        getContentPane().add(label);
        txt_interval = new JTextField();
        txt_interval.setBounds(440, 8, 87, 22);
        getContentPane().add(txt_interval);
        final JButton button = new JButton();
        button.addActionListener(new draw_actionAdapter(this));
        button.setText("绘制");
        button.setBounds(533, 5, 61, 28);
        getContentPane().add(button);
        panel.setBorder(new LineBorder(Color.black, 1, false));
        panel.setBounds(10, 36, 599, 389);
        getContentPane().add(panel);
    }
    public void paintFn(ActionEvent e){
        panel.paintFn(Integer.parseInt(txt_a.getText()), Integer.parseInt(txt_b.getText()), Integer.parseInt(txt_c.getText()), Integer.parseInt(txt_interval.getText()));
    }
}
class drawFnPanel extends JPanel{
    private int _a=0;
    private int _b=0;
    private int _c=0;
    private float _interval=0.01f;
    public void paintFn(int a,int b,int c,int interval){
        _a=a;
        _b=b;
        _c=c;
        _interval=interval;
        repaint();
    }
    private double getY(float x){
        return _a*x*x+_b*x+_c;
    }
    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        if(_a==0 && _b==0 && _c==0){
            return;
        }
        for(float i=-100f;i<100f;i=i+_interval){
            g2d.drawRect((int)i, (int)getY(i), 1, 1);
        }
    }
}
class draw_actionAdapter implements ActionListener {
    private DrawFn adapter;

    public draw_actionAdapter(DrawFn adapter) {
        this.adapter = adapter;
    }

    public void actionPerformed(ActionEvent e) {
        adapter.paintFn(e);
    }
}