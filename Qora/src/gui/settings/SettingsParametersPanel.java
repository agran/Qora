package gui.settings;

import gui.models.KnownPeersTableModel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.io.File;
import javax.swing.border.EmptyBorder;

import settings.Settings;


@SuppressWarnings("serial")
public class SettingsParametersPanel extends JPanel 
{
	private KnownPeersTableModel knownPeersTableModel;
	private JTextField txtRpcPort;
	private JTextField txtWebport;
	private JTextField textDataFolder;
	private JTextField textWallet;
	
	public SettingsParametersPanel()
	{
		
		//PADDING
		this.setBorder(new EmptyBorder(10, 5, 5, 10));
        
		this.knownPeersTableModel = new KnownPeersTableModel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] {40, 70, 112, 88, 92, 30, 0};
        gridBagLayout.rowHeights = new int[] {20, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);
        
        JCheckBox chckbxGuiEnabled = new JCheckBox("GUI enabled");
        chckbxGuiEnabled.setHorizontalAlignment(SwingConstants.LEFT);
        chckbxGuiEnabled.setSelected(Settings.getInstance().isGuiEnabled());
        GridBagConstraints gbc_chckbxGuiEnabled = new GridBagConstraints();
        gbc_chckbxGuiEnabled.fill = GridBagConstraints.BOTH;
        gbc_chckbxGuiEnabled.insets = new Insets(0, 0, 5, 5);
        gbc_chckbxGuiEnabled.gridwidth = 4;
        gbc_chckbxGuiEnabled.gridx = 1;
        gbc_chckbxGuiEnabled.gridy = 1;
        add(chckbxGuiEnabled, gbc_chckbxGuiEnabled);
        
        JLabel lblExplanatoryText = new JLabel("An explanatory text: An explanatory text: An explanatory text.");
        lblExplanatoryText.setVerticalAlignment(SwingConstants.TOP);
        lblExplanatoryText.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblExplanatoryText = new GridBagConstraints();
        gbc_lblExplanatoryText.fill = GridBagConstraints.BOTH;
        gbc_lblExplanatoryText.insets = new Insets(0, 0, 5, 5);
        gbc_lblExplanatoryText.gridwidth = 4;
        gbc_lblExplanatoryText.gridx = 1;
        gbc_lblExplanatoryText.gridy = 2;
        add(lblExplanatoryText, gbc_lblExplanatoryText);
        
        JCheckBox chckbxRpcEnabled = new JCheckBox("RPC enabled");
        chckbxRpcEnabled.setHorizontalAlignment(SwingConstants.LEFT);
        chckbxRpcEnabled.setSelected(Settings.getInstance().isRpcEnabled());
        GridBagConstraints gbc_chckbxRpcEnabled = new GridBagConstraints();
        gbc_chckbxRpcEnabled.gridwidth = 2;
        gbc_chckbxRpcEnabled.fill = GridBagConstraints.BOTH;
        gbc_chckbxRpcEnabled.insets = new Insets(0, 0, 5, 5);
        gbc_chckbxRpcEnabled.gridx = 1;
        gbc_chckbxRpcEnabled.gridy = 3;
        add(chckbxRpcEnabled, gbc_chckbxRpcEnabled);
        
        JLabel lblRpcPort = new JLabel("RPC port:");
        GridBagConstraints gbc_lblRpcPort = new GridBagConstraints();
        gbc_lblRpcPort.anchor = GridBagConstraints.EAST;
        gbc_lblRpcPort.insets = new Insets(0, 0, 5, 5);
        gbc_lblRpcPort.gridx = 3;
        gbc_lblRpcPort.gridy = 3;
        add(lblRpcPort, gbc_lblRpcPort);
        
        txtRpcPort = new JTextField();
        txtRpcPort.setText(Integer.toString(Settings.getInstance().getRpcPort()));
        txtRpcPort.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_txtRpcPort = new GridBagConstraints();
        gbc_txtRpcPort.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtRpcPort.anchor = GridBagConstraints.WEST;
        gbc_txtRpcPort.insets = new Insets(0, 0, 5, 5);
        gbc_txtRpcPort.gridx = 4;
        gbc_txtRpcPort.gridy = 3;
        add(txtRpcPort, gbc_txtRpcPort);
        txtRpcPort.setColumns(10);
        
        JLabel lblAnExplanatoryText_1 = new JLabel("An explanatory text: An explanatory text: An explanatory text.");
        lblAnExplanatoryText_1.setVerticalAlignment(SwingConstants.TOP);
        lblAnExplanatoryText_1.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblAnExplanatoryText_1 = new GridBagConstraints();
        gbc_lblAnExplanatoryText_1.fill = GridBagConstraints.BOTH;
        gbc_lblAnExplanatoryText_1.insets = new Insets(0, 0, 5, 0);
        gbc_lblAnExplanatoryText_1.gridwidth = 5;
        gbc_lblAnExplanatoryText_1.gridx = 1;
        gbc_lblAnExplanatoryText_1.gridy = 4;
        add(lblAnExplanatoryText_1, gbc_lblAnExplanatoryText_1);
        
        JCheckBox chckbxWebEnabled = new JCheckBox("WEB enabled");
        chckbxWebEnabled.setHorizontalAlignment(SwingConstants.LEFT);
        chckbxWebEnabled.setSelected(Settings.getInstance().isWebEnabled());
        GridBagConstraints gbc_chckbxWebEnabled = new GridBagConstraints();
        gbc_chckbxWebEnabled.gridwidth = 2;
        gbc_chckbxWebEnabled.fill = GridBagConstraints.BOTH;
        gbc_chckbxWebEnabled.insets = new Insets(0, 0, 5, 5);
        gbc_chckbxWebEnabled.gridx = 1;
        gbc_chckbxWebEnabled.gridy = 5;
        add(chckbxWebEnabled, gbc_chckbxWebEnabled);
        
        JLabel lblWebPort = new JLabel("WEB port:");
        GridBagConstraints gbc_lblWebPort = new GridBagConstraints();
        gbc_lblWebPort.anchor = GridBagConstraints.EAST;
        gbc_lblWebPort.insets = new Insets(0, 0, 5, 5);
        gbc_lblWebPort.gridx = 3;
        gbc_lblWebPort.gridy = 5;
        add(lblWebPort, gbc_lblWebPort);
        
        txtWebport = new JTextField();
        txtWebport.setText(Integer.toString(Settings.getInstance().getWebPort()));
        txtWebport.setHorizontalAlignment(SwingConstants.LEFT);
        txtWebport.setColumns(10);
        GridBagConstraints gbc_txtWebport = new GridBagConstraints();
        gbc_txtWebport.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtWebport.anchor = GridBagConstraints.WEST;
        gbc_txtWebport.insets = new Insets(0, 0, 5, 5);
        gbc_txtWebport.gridx = 4;
        gbc_txtWebport.gridy = 5;
        add(txtWebport, gbc_txtWebport);
        
        JLabel lblAnExplanatoryText_2 = new JLabel("An explanatory text: An explanatory text: An explanatory text.");
        lblAnExplanatoryText_2.setVerticalAlignment(SwingConstants.TOP);
        lblAnExplanatoryText_2.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblAnExplanatoryText_2 = new GridBagConstraints();
        gbc_lblAnExplanatoryText_2.fill = GridBagConstraints.BOTH;
        gbc_lblAnExplanatoryText_2.insets = new Insets(0, 0, 5, 0);
        gbc_lblAnExplanatoryText_2.gridwidth = 5;
        gbc_lblAnExplanatoryText_2.gridx = 1;
        gbc_lblAnExplanatoryText_2.gridy = 6;
        add(lblAnExplanatoryText_2, gbc_lblAnExplanatoryText_2);
        
        JCheckBox chckbxKeyCaching = new JCheckBox("Generator Key Caching");
        chckbxKeyCaching.setHorizontalAlignment(SwingConstants.LEFT);
        chckbxKeyCaching.setSelected(Settings.getInstance().isGeneratorKeyCachingEnabled());
        GridBagConstraints gbc_chckbxKeyCaching = new GridBagConstraints();
        gbc_chckbxKeyCaching.fill = GridBagConstraints.BOTH;
        gbc_chckbxKeyCaching.insets = new Insets(0, 0, 5, 5);
        gbc_chckbxKeyCaching.gridwidth = 4;
        gbc_chckbxKeyCaching.gridx = 1;
        gbc_chckbxKeyCaching.gridy = 7;
        add(chckbxKeyCaching, gbc_chckbxKeyCaching);
        
        JLabel lblAnExplanatoryText_3 = new JLabel("An explanatory text: An explanatory text: An explanatory text.");
        lblAnExplanatoryText_3.setVerticalAlignment(SwingConstants.TOP);
        lblAnExplanatoryText_3.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblAnExplanatoryText_3 = new GridBagConstraints();
        gbc_lblAnExplanatoryText_3.fill = GridBagConstraints.BOTH;
        gbc_lblAnExplanatoryText_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblAnExplanatoryText_3.gridwidth = 4;
        gbc_lblAnExplanatoryText_3.gridx = 1;
        gbc_lblAnExplanatoryText_3.gridy = 8;
        add(lblAnExplanatoryText_3, gbc_lblAnExplanatoryText_3);
        
        JLabel lblDataDir = new JLabel("Data dir:");
        GridBagConstraints gbc_lblDataDir = new GridBagConstraints();
        gbc_lblDataDir.anchor = GridBagConstraints.WEST;
        gbc_lblDataDir.insets = new Insets(0, 0, 5, 5);
        gbc_lblDataDir.gridx = 1;
        gbc_lblDataDir.gridy = 9;
        add(lblDataDir, gbc_lblDataDir);
        
        textDataFolder = new JTextField();
        textDataFolder.setText(Settings.getInstance().getDataDir());
        textDataFolder.setHorizontalAlignment(SwingConstants.LEFT);
        textDataFolder.setColumns(10);
        GridBagConstraints gbc_textDataFolder = new GridBagConstraints();
        gbc_textDataFolder.gridwidth = 2;
        gbc_textDataFolder.insets = new Insets(0, 0, 5, 5);
        gbc_textDataFolder.fill = GridBagConstraints.HORIZONTAL;
        gbc_textDataFolder.gridx = 2;
        gbc_textDataFolder.gridy = 9;
        add(textDataFolder, gbc_textDataFolder);
        
        JButton btnBrowseDataFolder = new JButton("Browse...");
        GridBagConstraints gbc_btnBrowseDataFolder = new GridBagConstraints();
        gbc_btnBrowseDataFolder.anchor = GridBagConstraints.WEST;
        gbc_btnBrowseDataFolder.insets = new Insets(0, 0, 5, 5);
        gbc_btnBrowseDataFolder.gridx = 4;
        gbc_btnBrowseDataFolder.gridy = 9;
        btnBrowseDataFolder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();  
                fileopen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileopen.setCurrentDirectory(new File(textDataFolder.getText()));
                int ret = fileopen.showDialog(null, "Set data dir");                
                if (ret == JFileChooser.APPROVE_OPTION) {
                	textDataFolder.setText(fileopen.getSelectedFile().toString());
                }
            }
        });
        add(btnBrowseDataFolder, gbc_btnBrowseDataFolder);
        
        JLabel lblWelletDir = new JLabel("Wellet dir:");
        GridBagConstraints gbc_lblWelletDir = new GridBagConstraints();
        gbc_lblWelletDir.anchor = GridBagConstraints.WEST;
        gbc_lblWelletDir.insets = new Insets(0, 0, 5, 5);
        gbc_lblWelletDir.gridx = 1;
        gbc_lblWelletDir.gridy = 10;
        add(lblWelletDir, gbc_lblWelletDir);
        
        textWallet = new JTextField();
        textWallet.setText(Settings.getInstance().getWalletDir());
        textWallet.setHorizontalAlignment(SwingConstants.LEFT);
        textWallet.setColumns(10);
        GridBagConstraints gbc_textWallet = new GridBagConstraints();
        gbc_textWallet.gridwidth = 2;
        gbc_textWallet.insets = new Insets(0, 0, 5, 5);
        gbc_textWallet.fill = GridBagConstraints.HORIZONTAL;
        gbc_textWallet.gridx = 2;
        gbc_textWallet.gridy = 10;
        add(textWallet, gbc_textWallet);
        
        JButton btnBrowseWallet = new JButton("Browse...");
        GridBagConstraints gbc_BrowseWalletbutton = new GridBagConstraints();
        gbc_BrowseWalletbutton.anchor = GridBagConstraints.WEST;
        gbc_BrowseWalletbutton.insets = new Insets(0, 0, 5, 5);
        gbc_BrowseWalletbutton.gridx = 4;
        gbc_BrowseWalletbutton.gridy = 10;
        
        btnBrowseWallet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();             
                fileopen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileopen.setCurrentDirectory(new File(textWallet.getText()));
                int ret = fileopen.showDialog(null, "Set wallet dir");                
                if (ret == JFileChooser.APPROVE_OPTION) {   
                    textWallet.setText(fileopen.getSelectedFile().toString());
                }
            }
        });
        
        add(btnBrowseWallet, gbc_BrowseWalletbutton);
        
        JLabel lblAnExplanatoryText_4 = new JLabel("An explanatory text: An explanatory text: An explanatory text.");
        lblAnExplanatoryText_4.setVerticalAlignment(SwingConstants.TOP);
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
        gbc_lblNewLabel_1.gridwidth = 4;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 11;
        add(lblAnExplanatoryText_4, gbc_lblNewLabel_1);
         
	}
	public void close() 
	{
		//REMOVE OBSERVERS/HANLDERS
		this.knownPeersTableModel.removeObservers();
	}
	
}
