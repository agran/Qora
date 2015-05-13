package gui;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class SettingsTabPane extends JTabbedPane{

	//private static final long serialVersionUID = 2717571093561259483L;

	private SettingsKnownPeersPanel settingsKnownPeersPanel;
	private SettingsParametersPanel settingsParametersPanel;
	
	public SettingsTabPane()
	{
		super();
		
		//ADD TABS
		
		settingsKnownPeersPanel = new SettingsKnownPeersPanel();
	    JScrollPane scrollPane1 = new JScrollPane(settingsKnownPeersPanel);
        this.addTab("Known Peers", scrollPane1);
        
        settingsParametersPanel = new SettingsParametersPanel();
        JScrollPane scrollPane2 = new JScrollPane(settingsParametersPanel);
   
        
        this.addTab("Second",scrollPane2);
        this.addTab("Third", new JScrollPane());

	}
	public void close() 
	{
		//REMOVE OBSERVERS/HANLDERS
		this.settingsKnownPeersPanel.close();
		this.settingsParametersPanel.close();
	}
}