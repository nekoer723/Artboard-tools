
import java.awt.*;
import java.awt.Shape;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.*;
import java.io.*;
import java.util.List;

/**
 * 界面的初始化
 */
@SuppressWarnings("serial")
public class pic2 extends JFrame{

    JMenuItem editFont0;
    JMenuItem editFont1;
    JMenuItem editFont2;
    JMenu edit0;
    JMenu edit1;
    JSlider jSlider;//粗细滑块
    public static pic2 paint=new pic2();
    private picListener dl;
    private JsliderListener jl = new JsliderListener();
    private Graphics2D g;
    public JTextField textin = new JTextField(null,10);
    //保存图形对象的集合
    //public List<NetJavaShape> shapesArray = new ArrayList<NetJavaShape>();
    public ArrayList<Shape1> list = new ArrayList<>();
    public JPanel jp2 = new JPanel(){
        public void paint(Graphics g1) {
            g=(Graphics2D)g1;
            //画船体
            System.out.println("repaint");
            super.paint(g);
            list = dl.list;
            for (Shape1 l : list) {
                l.draw(g);
            }
        }
    };
    public JButton openPic;

    public picListener getDl() {
        return dl;
    }

    public void setDl(picListener dl) {
        this.dl = dl;
    }

    public JTextField getTextin() {
        return textin;
    }

    public void setTextin(JTextField textin) {
        this.textin = textin;
    }

    public void showUi() throws Exception {

        setTitle("画图"); //窗体名称
        setSize(1200,700);//大小
        setDefaultCloseOperation(3); //关闭的方式
        setLocationRelativeTo(null); //居中
        //布局
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        setLayout(layout);
        this.setResizable(false);//窗体大小不变
        addMenu();


        //使用数组保存按钮名
        String buttonName[] = { "画直线", "椭圆", "圆形","实心圆形","画曲线",
                "橡皮擦","矩形","清空","撤销","文本框"};
        //按钮面板，用于保存图形按钮，使用网格布局
        JPanel jp1=new JPanel(new GridLayout(15, 1,10,10));
        jp1.setPreferredSize(new Dimension(100, 600));
        //实例化DrawListener对象
        dl=new picListener();
        //添加按钮
        for (int i=0 ; i<buttonName.length;i++) {
            JButton jButton = new JButton(buttonName[i]);
            jButton.addActionListener(dl);//为按钮添加监听
            jp1.add(jButton);
        }
//        openPic = new JButton("打开");
//        openPic.addActionListener(dl);
//        jp1.add(openPic);
        jp1.add(textin);
        textin.setVisible(true);



//        jp2 = new JPanel(){
//            public void paint(Graphics g1) {
//                g=(Graphics2D)g1;
//                //画船体
//                System.out.println("repaint");
//                super.paint(g);
//                list = dl.list;
//                for (Shape1 l : list) {
//                    l.draw(g);
//                }
//            }
//        };
        jp2.setPreferredSize(new Dimension(1070,600));
        jp2.setBackground(Color.WHITE);

        //定义颜色数组color 储存按钮上要显示的颜色信息
        Color[] colorArray = { Color.BLUE, Color.GREEN, Color.RED,
                Color.BLACK,Color.ORANGE,Color.PINK,Color.CYAN,
                Color.MAGENTA,Color.DARK_GRAY,Color.GRAY,
                Color.LIGHT_GRAY,Color.YELLOW};
        JPanel jp3 = new JPanel(new GridLayout(1,colorArray.length,3,3));

        for (int i = 0; i < colorArray.length; i++) {
            JButton button = new JButton();
            button.setOpaque(true);                //设置是否透明
            button.setBorderPainted(false);
            button.setBackground(colorArray[i]);
            button.setPreferredSize(new Dimension(30, 30));
            button.addActionListener(dl);//为按钮添加监听
            jp3.add(button);
        }
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);


        JButton nowColor = new JButton();
        nowColor.setPreferredSize(new Dimension(40,40));
        nowColor.setOpaque(true);                //设置是否透明
        nowColor.setBorderPainted(false);

        nowColor.setBackground(Color.BLACK);

        this.getContentPane().add(nowColor);
       // setVisible(true);


//        JLabel jLabel= new JLabel("线条粗细");
//        this.getContentPane().add(jLabel);
//        setVisible(true);

        jSlider = new JSlider(1,20,1);
        jSlider.addChangeListener(jl);
        this.getContentPane().add(jSlider);
        setVisible(true);




        //获取画笔对象
        g= (Graphics2D) jp2.getGraphics();
        dl.setG(g);
        dl.setNowColor(nowColor);
        //为面板添加鼠标监听，用于绘制图形
        jp2.addMouseListener(dl);
        jp2.addMouseMotionListener(dl);
    }


//    @Override
//    //重写paint方法
//    public void paint(Graphics g1) {
//        //调用父类的paint方法，绘制界面上的组件
//        g=(Graphics2D)g1;
//        super.paint(g);
//        //foreach遍历集合
//        list = dl.list;
//        for (Shape1 l : list) {
//            l.draw(g);
//        }
//
//    }

//    public static void main(String[] args) {
//
//        pic2 p = new pic2();
//        p.showUi();
//    }
    public void addMenu()throws Exception{
        //菜单条对象创建
        JMenuBar bar = new JMenuBar();
        //菜单创建
        JMenu menu=new JMenu("File");
        JMenu menu2=new JMenu("Font");
        JMenu menu3=new JMenu("Help");
        //菜单项监听器创建
        ItemListener il=new ItemListener(this);
        //创建三个菜单项
        JMenuItem item0= new JMenuItem("新建");
        JMenuItem item= new JMenuItem("打开");
        JMenuItem item1= new JMenuItem("保存");



        edit0 = new JMenu("字体");
        edit1 = new JMenu("大小");


        JMenuItem helptxt = new JMenuItem("打开帮助文档");


        editFont0 = new JMenuItem("华文行楷");
        editFont1 = new JMenuItem("宋体");
        editFont2 = new JMenuItem("Lucida Grande");
        JMenuItem editFont3 = new JMenuItem("Luminari");
        JMenuItem editFont4 = new JMenuItem("黑体");
        JMenuItem editFont5 = new JMenuItem("斜体");

        JMenuItem editSize0 = new JMenuItem("16");
        JMenuItem editSize1 = new JMenuItem("26");
        //给每个菜单项添加监听器
        item0.addActionListener(il);
        item.addActionListener(il);
        item1.addActionListener(il);

        editSize0.addActionListener(il);
        editSize1.addActionListener(il);

        editFont0.addActionListener(il);
        editFont1.addActionListener(il);
        editFont2.addActionListener(il);
        editFont3.addActionListener(il);
        editFont4.addActionListener(il);
        editFont5.addActionListener(il);

        helptxt.addActionListener(il);

        edit0.addActionListener(il);
        edit1.addActionListener(il);
        //将菜单条添加到窗体上
        this.setJMenuBar(bar);
        //将菜单添加到菜单条上
        bar.add(menu);
        bar.add(menu2);
        bar.add(menu3);
        //将菜单项添加到菜单上
        menu.add(item0);
        menu.add(item);
        menu.add(item1);

        menu2.add(edit0);
        menu2.add(edit1);

        menu3.add(helptxt);

        edit0.add(editFont0);
        edit0.add(editFont1);
        edit0.add(editFont2);
        edit0.add(editFont3);
        edit0.add(editFont4);
        edit0.add(editFont5);

        edit1.add(editSize0);
        edit1.add(editSize1);

    }

    public static void main(String[] args) throws Exception {
        paint.showUi();
    }
}

