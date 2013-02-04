package com.nelson.buyticket.view;

import com.nelson.buyticket.handler.Handler;
import com.nelson.buyticket.threaLocal.ParameterThreadLocal;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public abstract class AbstractView {

    protected AbstractView nextView;
    protected String randCode;
    protected JTextField textField;
    protected JFrame frame;
    protected Container container;
    private Handler validateCodeHandler;
    private ParameterThreadLocal threadLocal;

    public AbstractView(AbstractView nextView, Handler validateCodeHandler, ParameterThreadLocal threadLocal) {
        this.nextView = nextView;
        this.threadLocal = threadLocal;
        frame = new JFrame();
        frame.setSize(150, 120);
        container = frame.getContentPane();
        container.setLayout(new FlowLayout());
        textField = new JTextField(8);
        textField.setName("randCode");
        textField.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        this.validateCodeHandler = validateCodeHandler;
    }

    public void show() {
        String imagePath = validateCodeHandler.handler();
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel label = new JLabel(imageIcon);
        container.add(label);
        container.add(textField);
        frame.setVisible(true);
    }
}
