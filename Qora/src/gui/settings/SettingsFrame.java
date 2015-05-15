package gui.settings;

import gui.Gui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JPanel;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import controller.Controller;
import settings.Settings;
import utils.IPAddressFormatValidator;
import utils.RestartApplication;

@SuppressWarnings("serial")
public class SettingsFrame extends JFrame{
	public JSONObject settingsJSONbuf;
	private SettingsTabPane settingsTabPane;
	
	public SettingsFrame() 
	{
		
		//CREATE FRAME
		super("Qora - Settings");
		
		//ICON
		List<Image> icons = new ArrayList<Image>();
		icons.add(Toolkit.getDefaultToolkit().getImage("images/icons/icon16.png"));
		icons.add(Toolkit.getDefaultToolkit().getImage("images/icons/icon32.png"));
		icons.add(Toolkit.getDefaultToolkit().getImage("images/icons/icon64.png"));
		icons.add(Toolkit.getDefaultToolkit().getImage("images/icons/icon128.png"));
		this.setIconImages(icons);
		
		setResizable(false);

		settingsJSONbuf = new JSONObject();
		settingsJSONbuf = Settings.getInstance().Dump(); 
		
		GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{339, 0};
        gridBagLayout.rowHeights = new int[] {300, 30};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0};
        getContentPane().setLayout(gridBagLayout);
        
        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 1;
        getContentPane().add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] {80, 80, 80, 80, 80, 80};
        gbl_panel.rowHeights = new int[]{23, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        JButton btnNewButton = new JButton("Apply");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.gridwidth = 2;
        gbc_btnNewButton.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
        gbc_btnNewButton.insets = new Insets(3, 0, 0, 3);
        gbc_btnNewButton.gridx = 1;
        gbc_btnNewButton.gridy = 0;
        
        btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				int n = JOptionPane.showConfirmDialog(
						new JFrame(), "Whatever settings have been applied, you must restart the wallet. Save and restart the wallet? \n" //
								+ "Yes - that would save and restart the wallet. \n"
								+ "No - that would save and continue work wallet. \n"
								+ "Cancel - to return to edit the settings.",
		                "Ñonfirmation",
		                JOptionPane.YES_NO_CANCEL_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					saveSettings();
					settingsTabPane.close();
	                
	                //DISPOSE
	                //setVisible(false);
	                //dispose();
					RestartApplication restartApplication = new RestartApplication();
					
						try {
							//Controller.getInstance().stopAll(true);
							Settings.getInstance(true);
							Controller.getInstance().start();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//restartApplication.RestartApplication(null);
						
					//Controller.getInstance().stopAll();
					//gui game = new gui();
	                //gui.gui.dispose();
	                
				}
				if (n == JOptionPane.NO_OPTION) {
					saveSettings();
					settingsTabPane.close();
	                
	                //DISPOSE
	                setVisible(false);
	                dispose();	
				}				
				if (n == JOptionPane.CANCEL_OPTION) {
					
				}
			}
		});	  
        
        panel.add(btnNewButton, gbc_btnNewButton);
        
        JButton btnCancel = new JButton("Cancel");
        GridBagConstraints gbc_btnCancel = new GridBagConstraints();
        gbc_btnCancel.gridwidth = 2;
        gbc_btnCancel.fill = GridBagConstraints.BOTH;
        gbc_btnCancel.insets = new Insets(3, 0, 0, 0);
        gbc_btnCancel.gridx = 3;
        gbc_btnCancel.gridy = 0;
        btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				settingsTabPane.close();
                
                //DISPOSE
                setVisible(false);
                dispose();
			}
		});	  
        panel.add(btnCancel, gbc_btnCancel);
        //////////
		//SETTINGS TABPANE
        this.settingsTabPane = new SettingsTabPane();
		
		//ON CLOSE
		this.addWindowListener(new WindowAdapter()
		{
            public void windowClosing(WindowEvent e)
            {
            	//CLOSE DEBUG
            	settingsTabPane.close();
                
                //DISPOSE
                setVisible(false);
                dispose();
            }
        });
		       
		 //ADD GENERAL TABPANE TO FRAME
        this.add(this.settingsTabPane); 
  
        /////////
        //SHOW FRAME
        this.pack();
        this.setLocationRelativeTo(null);
        
        
        this.setVisible(true);
	}	
	public void onClose()
	{
		
	}
	
	public void saveSettings()
	{
		
	}
}
