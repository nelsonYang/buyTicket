package com.nelson.buyticket.view;

import com.nelson.buyticket.handler.Handler;
import com.nelson.buyticket.threaLocal.ParameterThreadLocal;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Administrator
 */
public class LoginView extends AbstractView {

    private Handler loginSuggestionHandler;
    private Handler loginHandler;
    private Handler leftTicketHandler;
    private Handler tokenHandler;

    public LoginView(final AbstractView nextView, Handler validateCodeHandler, final ParameterThreadLocal theadLocal, final Handler loginSuggestionHandler, final Handler loginHandler, final Handler leftTicketHandler, final Handler tokenHandler) {
        super(nextView, validateCodeHandler, theadLocal);
        this.loginSuggestionHandler = loginSuggestionHandler;
        this.loginHandler = loginHandler;
        this.leftTicketHandler = leftTicketHandler;
        this.tokenHandler = tokenHandler;
        this.textField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    randCode = textField.getText();
                    frame.setVisible(false);
                    String loginRand = loginSuggestionHandler.handler();
                    System.out.println(randCode);
                    System.out.println(loginRand);
                    theadLocal.set("randCode", randCode);
                    theadLocal.set("loginRand", loginRand);
                    loginHandler.handler();
                    String token = tokenHandler.handler();
                    theadLocal.set("token", token);
                    String content = leftTicketHandler.handler();
                    if (!"-10".equals(content)) {
                        theadLocal.set("url", "https://dynamic.12306.cn/otsweb/passCodeAction.do?rand=randp");
                        theadLocal.set("jsonHeaderName", "/validateCode.json");
                        theadLocal.set("imageUrl", "c:/test1.jpg");
                        if (nextView != null) {
                            nextView.show();
                        }
                    } else {
                        System.out.println("unlogin please login first");
                    }

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
