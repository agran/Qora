import gui.Gui;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import api.ApiClient;
import controller.Controller;

import settings.Settings;

public class Start {

	public static void main(String args[])
	{	
		boolean cli = false;
		
		for(String arg: args)
		{
			if(arg.equals("-cli"))
			{
				cli = true;
			}
		}
		
		if(!cli)
		{			
			try
			{
				//ONE MUST BE ENABLED
				if(!Settings.getInstance().isGuiEnabled() && !Settings.getInstance().isRpcEnabled())
				{
					throw new Exception("Both gui and rpc cannot be disabled!");
				}
				
				//STARTING NETWORK/BLOCKCHAIN/RPC
				Controller.getInstance().start();
				
				if(Settings.getInstance().isGuiEnabled())
				{
					//START GUI
					new Gui();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
				//USE SYSTEM STYLE
		        try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				//ERROR STARTING
				System.out.println("STARTUP ERROR: " + e.getMessage());
				
				if(Settings.getInstance().isGuiEnabled())
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "Startup Error", JOptionPane.ERROR_MESSAGE);
				}
				
				//FORCE SHUTDOWN
				System.exit(0);
			}
		}
		else
		{
			Scanner scanner = new Scanner(System.in);
			ApiClient client = new ApiClient();
			
			while(true)
			{
				
				System.out.print("[COMMAND] ");
				String command = scanner.nextLine();
				
				if(command.equals("quit"))
				{
					scanner.close();
					System.exit(0);
				}
				
				String result = client.executeCommand(command);
				System.out.println("[RESULT] " + result);
			}
		}		
	}
}
