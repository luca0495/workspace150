package Check;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PopUp
{

	public static void infoBox(Component c,String infoMessage)
	{		
	    JOptionPane.showMessageDialog(c, infoMessage, "Info", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void warningBox(Component c, String warningMessage)
	{		
	    JOptionPane.showMessageDialog(c, warningMessage, "Attenzione", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void errorBox(Component c,String errorMessage)
	{		
	    JOptionPane.showMessageDialog(c, errorMessage, "Errore", JOptionPane.ERROR_MESSAGE);
	}
		    
	public static boolean confirmBox(Component c)
	{
	    if (JOptionPane.showConfirmDialog(c, "Sei sicuro ?", "Richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
	    {
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	}
}
