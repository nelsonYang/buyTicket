package com.nelson.buyticket.view;

import com.nelson.buyticket.handler.Handler;
import com.nelson.buyticket.threaLocal.ParameterThreadLocal;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Administrator
 */
public class ReserveView extends AbstractView {

    private Handler checkOrderInfoHanlder;
    private Handler getQueueCountHandler;
    private Handler bookTicketHandler;

    public ReserveView(AbstractView nextView, Handler validateCodeHandler, final ParameterThreadLocal theadLocal, final Handler checkOrderInfoHanlder, final Handler getQueueCountHandler, final Handler bookTicketHandler) {
        super(nextView, validateCodeHandler, theadLocal);
        this.bookTicketHandler = bookTicketHandler;
        this.checkOrderInfoHanlder = checkOrderInfoHanlder;
        this.getQueueCountHandler = getQueueCountHandler;
        this.textField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    randCode = textField.getText();
                    System.out.println(randCode);
                    frame.setVisible(false);
                    theadLocal.set("secondRandCode", randCode);
                    checkOrderInfoHanlder.handler();
                    getQueueCountHandler.handler();
                    bookTicketHandler.handler();

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
