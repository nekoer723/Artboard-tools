import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


public class JsliderListener implements ChangeListener{
    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println(pic2.paint.jSlider.getValue());
        pic2.paint.getDl().setWidth(pic2.paint.jSlider.getValue());
        System.out.println("----------"+pic2.paint.getDl().getWidth());
    }
}
