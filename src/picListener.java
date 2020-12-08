import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
abstract class Shape1 implements Serializable{
    int x1, y1,x2, y2;
    Font font;
    Color c;
    float width;
    public abstract void draw(Graphics2D g);
}

class Oval extends Shape1 implements Serializable{

    public Oval(){

    }

    public Oval(int x1,int y1,int x2,int y2,Color c,float width){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.c=c;
        this.width=width;
    }


    public void draw(Graphics2D g) {
        g.setColor(this.c);
        g.setStroke(new BasicStroke(width));
        g.drawOval(x1, y1, x2, y2);
    }


}

class FillOval extends Shape1 implements Serializable{

    public FillOval(){

    }

    public FillOval(int x1,int y1,int x2,int y2,Color c,float width){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.c=c;
        this.width=width;
    }


    public void draw(Graphics2D g) {
        g.setColor(this.c);
        g.setStroke(new BasicStroke(width));
        g.fillOval(x1, y1, x2, y2);
    }


}

class Rect extends Shape1 implements Serializable{

    public Rect(){

    }
    //矩形的构造方法
    public Rect(int x1,int y1,int x2,int y2,Color color,float width){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.c=color;
        this.width=width;
    }

    //重写父类的Draw方法，实现矩形的绘制
    public void draw(Graphics2D g) {
        g.setColor(c);
        g.setStroke(new BasicStroke(width));
        g.drawRect(x1, y1, x2, y2);
    }

} //zxc(w,-1,-1,-1,-1,-1)
class ImpLine extends Shape1 implements Serializable{

    public ImpLine(int x1,int y1,int x2,int y2,Color color,float width){
        this.c=color;
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.width=width;
    }
    public void draw(Graphics2D g) {
        g.setColor(c);
        g.setStroke(new BasicStroke(width));
        g.drawLine(x1, y1, x2, y2);
    }

}
class Text extends Shape1 implements Serializable {

    public Text(int x1,int y1,Color c){
        this.c=c;
        this.x1=x1;
        this.y1=y1;
        font = pic2.paint.getDl().getG().getFont();
//        this.x2=x2;
//        this.y2=y2;
    }

    @Override
    public void draw(Graphics2D g) {
        String str=pic2.paint.getTextin().getText();
        String txt =str;
        //Font font=new Font("华文行楷",Font.BOLD+Font.ITALIC,26);
        //g.setFont(font);
        //Shape s=new Shape(x,y,txt,"Text");
        g.setColor(c);
        g.setFont(font);
        g.drawString(txt, x1, y1);

    }
}
public class picListener implements ActionListener, MouseListener, MouseMotionListener{

    private Color color = Color.BLACK;
    private Graphics2D g; //画笔属性
    private String str;//保存按钮上的字符串。区分不同的按钮
    private int x1,y1,x2,y2;//坐标
    private JButton nowColor;//当前颜色
    private float width;//画笔粗细
    private int flag1 = 0; //预显示

    private Shape1 flag2 = new ImpLine(-1,-1,-1,-1,Color.WHITE,0);
    public static ArrayList<Shape1> list =new ArrayList<Shape1>();


    //private JButton nowColor;//当前颜色按钮
    //保存图形对象的集合

    //private List<Shape1> shapesArray = new ArrayList<Shape1>();
    //图形
    private Shape1 shape;
    //在draw类中获取集合
    public ArrayList<Shape1> getShapesArray() {
        return list;
    }


    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public Shape1 getShape() {
        return shape;
    }

    public void setShape(Shape1 shape) {
        this.shape = shape;
    }

    public Graphics2D getG() {
        return g;
    }

    public void setG(Graphics2D g) {
        this.g = g;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public JButton getNowColor() {
        return nowColor;
    }

    public void setNowColor(JButton nowColor) {
        this.nowColor = nowColor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //判断是颜色按钮还是图形按钮
        if ("".equals(e.getActionCommand())) {
            JButton jb = (JButton) e.getSource();
            color = jb.getBackground();
            nowColor.setBackground(color);//处理当前颜色
        } else {
            str = e.getActionCommand();
            if("文本框".equals(str)) {
                pic2.paint.textin.setVisible(true);
                System.out.println(pic2.paint.getDl().getG().getFont().getName());
            } else if ("清空".equals(e.getActionCommand())) {
                list.removeAll(list);
                pic2.paint.jp2.repaint();
            } else if ("撤销".equals(e.getActionCommand())) {
                //list.remove(list.size() - 1);
                int t =list.size();
                int fff = 0;
                for(int i = 1; i<=t ;i++) {
                    if(list.get(list.size() - i) == flag2) {
                        fff=1;
                    }
                }
                if(fff == 1){
                    for(int i = 0; i<t ;i++) {
                        if(list.get(list.size() - 1) == flag2){
                            list.remove(list.size() - 1);
                            System.out.println("撤销");
                            pic2.paint.jp2.repaint();
                            break;
                        }
                        else {
                            list.remove(list.size() - 1);
                        }
                    }
                }

                //list.remove(list.size() - 1);
                //pic2.paint.jp2.repaint();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        g.setColor(color);//改变画笔的颜色
        x1=e.getX();//获取按下时鼠标的x坐标
        y1=e.getY();//获取按下时鼠标的y坐标

        if("画曲线".equals(str) ||"画直线".equals(str) ||"矩形".equals(str) ||"圆形".equals(str) ||"椭圆".equals(str)||"实心圆形".equals(str)
        ||"橡皮擦".equals(str)){
            System.out.println("press");
            list.add(flag2);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //list.add(flag2);
        if(flag1 > 0) {
            System.out.println("removeflage");
            list.remove(list.size()-1);
            pic2.paint.jp2.repaint();
            flag1 = 0;
        }
        x2=e.getX();//获取释放时鼠标的x坐标
        y2=e.getY();//获取释放时鼠标的y坐标
        //画直线的方法
        if ("画直线".equals(str)) {
            //实例化对象，
            shape=new ImpLine(x1,y1,x2,y2,color,width);
            //调用画图方法
            shape.draw(g);
            //将图形存入集合中
            list.add(shape);
        }
        if ("文本框".equals(str)) {


            shape = new Text(x1, y1, color);
            shape.draw(g);
            list.add(shape);
        }
        if ("椭圆".equals(str)) {
            shape = new Oval(Math.min(x2, x1),Math.min(y2, y1), Math.abs(x2-x1),Math.abs(y1-y2),color,width);
            shape.draw(g);
            list.add(shape);
        }
        if ("矩形".equals(str)) {
            shape = new Rect(Math.min(x2, x1),Math.min(y2, y1), Math.abs(x2-x1),Math.abs(y1-y2),color,width);
            shape.draw(g);
            list.add(shape);
        }
        if ("圆形".equals(str)) {
            int xt1 = Math.min(x2, x1);
            int yt1 = Math.min(y2, y1);
            int wt1 = Math.abs(x2-x1);
            //int ht1 = Math.abs(y1-y2);
            if(Math.abs(x2-x1)>Math.abs(y2-y1)){
                xt1 = (x2+x1)/2-(Math.abs(y1-y2)/2);
                wt1 = Math.abs(y2-y1);
            } else {
                yt1 = (y2+y1)/2-(Math.abs(x1-x2)/2);
                wt1 = Math.abs(x2-x1);
            }
            shape = new Oval(xt1,yt1,wt1,wt1,color,width);
            shape.draw(g);
            list.add(shape);
        }
        if ("实心圆形".equals(str)) {
            int xt1 = Math.min(x2, x1);
            int yt1 = Math.min(y2, y1);
            int wt1 = Math.abs(x2-x1);
            //int ht1 = Math.abs(y1-y2);
            if(Math.abs(x2-x1)>Math.abs(y2-y1)){
                xt1 = (x2+x1)/2-(Math.abs(y1-y2)/2);
                wt1 = Math.abs(y2-y1);
            } else {
                yt1 = (y2+y1)/2-(Math.abs(x1-x2)/2);
                wt1 = Math.abs(x2-x1);
            }
            shape = new FillOval(xt1,yt1,wt1,wt1,color,width);
            shape.draw(g);
            list.add(shape);
        }

    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    //鼠标拖动的方法 实现画线
    public void mouseDragged(MouseEvent e) {
        System.out.println("dragged");

        if ("画曲线".equals(str)){
            int x,y;
            x=e.getX();
            y=e.getY();
            //实例化对象，曲线也是直线画的所以不同新建一个曲线类了
            shape=new ImpLine(x,y,x1,y1,color,width);
            //调用画图方法
            shape.draw(g);
            //将图形存入集合中
            list.add(shape);
            x1 = x;
            y1 = y;

        } else if ("橡皮擦".equals(str)) {
            //Color tmp = color;
            //System.out.println(tmp);
            int x,y;
            x=e.getX();
            y=e.getY();
            //实例化对象，曲线也是直线画的所以不同新建一个曲线类了
            shape=new ImpLine(x,y,x1,y1,Color.WHITE,20);
            //System.out.println(tmp);
            //调用画图方法
            shape.draw(g);
            //将图形存入集合中
            list.add(shape);
            x1 = x;
            y1 = y;
            //color=tmp;
            //System.out.println(color.toString());

        } else if ("画直线".equals(str)){
            flag1 ++;
            int x,y;
            x=e.getX();
            y=e.getY();
            if(flag1 == 1) {
                shape = new ImpLine(x1,y1,x,y,color,width);
                shape.draw(g);
                list.add(shape);
                pic2.paint.jp2.repaint();
            }
            if(flag1 > 1) {
                list.remove(list.size()-1);
                shape = new ImpLine(x1,y1,x,y,color,width);
                shape.draw(g);
                list.add(shape);
                pic2.paint.jp2.repaint();
            }
            System.out.println(flag1);
            System.out.println("直线");
        } else if("矩形".equals(str) || "圆形".equals(str) || "椭圆".equals(str) || "实心圆形".equals(str)){
            flag1 ++;
            int x,y;
            x=e.getX();
            y=e.getY();
            if(flag1 == 1) {
                shape = new Rect(Math.min(x, x1), Math.min(y, y1), Math.abs(x - x1), Math.abs(y1 - y), color, width);
                shape.draw(g);
                list.add(shape);
                pic2.paint.jp2.repaint();
            }
            if(flag1 > 1) {
                list.remove(list.size()-1);
                shape = new Rect(Math.min(x, x1), Math.min(y, y1), Math.abs(x - x1), Math.abs(y1 - y), color, width);
                shape.draw(g);
                list.add(shape);
                pic2.paint.jp2.repaint();
            }
            System.out.println(flag1);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }



}
