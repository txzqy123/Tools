package com.function.listener;

import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

public class KeyPressListener implements AWTEventListener {

	public void eventDispatched(AWTEvent event) {  
		System.out.println("按键监听");
//		if (((KeyEvent)event).getKeyCode() == KeyEvent.VK_F10) {// 如果密码是enter键？？
//			KeySprite.runFlg = 0;
//			KeySprite.run();
//		} else if(((KeyEvent)event).getKeyCode() == KeyEvent.VK_F12){
//			KeySprite.runFlg = 1;
//		}
    }  
}
