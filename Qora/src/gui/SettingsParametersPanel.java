package gui;

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


import java.util.regex.Matcher;

@SuppressWarnings("serial")
public class SettingsParametersPanel extends JPanel 
{
	private KnownPeersTableModel knownPeersTableModel;
	private JTextField txtRpc;
	private JTextField txtWeb;
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
        
        JCheckBox chckbxGuiAllowed = new JCheckBox("GUI enabled");
        chckbxGuiAllowed.setHorizontalAlignment(SwingConstants.LEFT);
        chckbxGuiAllowed.setSelected(Settings.getInstance().isGuiEnabled());
        GridBagConstraints gbc_chckbxGuiAllowed = new GridBagConstraints();
        gbc_chckbxGuiAllowed.fill = GridBagConstraints.BOTH;
        gbc_chckbxGuiAllowed.insets = new Insets(0, 0, 5, 5);
        gbc_chckbxGuiAllowed.gridwidth = 4;
        gbc_chckbxGuiAllowed.gridx = 1;
        gbc_chckbxGuiAllowed.gridy = 1;
        add(chckbxGuiAllowed, gbc_chckbxGuiAllowed);
        
        JLabel lblAnExplanatoryText = new JLabel("An explanatory text: An explanatory text: An explanatory text.");
        lblAnExplanatoryText.setVerticalAlignment(SwingConstants.TOP);
        lblAnExplanatoryText.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblAnExplanatoryText = new GridBagConstraints();
        gbc_lblAnExplanatoryText.fill = GridBagConstraints.BOTH;
        gbc_lblAnExplanatoryText.insets = new Insets(0, 0, 5, 5);
        gbc_lblAnExplanatoryText.gridwidth = 4;
        gbc_lblAnExplanatoryText.gridx = 1;
        gbc_lblAnExplanatoryText.gridy = 2;
        add(lblAnExplanatoryText, gbc_lblAnExplanatoryText);
        
        JCheckBox chckbxNewCheckBox = new JCheckBox("RPC enabled");
        chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
        chckbxNewCheckBox.setSelected(Settings.getInstance().isRpcEnabled());
        GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
        gbc_chckbxNewCheckBox.gridwidth = 2;
        gbc_chckbxNewCheckBox.fill = GridBagConstraints.BOTH;
        gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
        gbc_chckbxNewCheckBox.gridx = 1;
        gbc_chckbxNewCheckBox.gridy = 3;
        add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
        
        JLabel lblNewLabel = new JLabel("RPC port:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 3;
        gbc_lblNewLabel.gridy = 3;
        add(lblNewLabel, gbc_lblNewLabel);
        
        txtRpc = new JTextField();
        txtRpc.setText(Integer.toString(Settings.getInstance().getRpcPort()));
        txtRpc.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_txtRpc = new GridBagConstraints();
        gbc_txtRpc.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtRpc.anchor = GridBagConstraints.WEST;
        gbc_txtRpc.insets = new Insets(0, 0, 5, 5);
        gbc_txtRpc.gridx = 4;
        gbc_txtRpc.gridy = 3;
        add(txtRpc, gbc_txtRpc);
        txtRpc.setColumns(10);
        
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
        
        txtWeb = new JTextField();
        txtWeb.setText(Integer.toString(Settings.getInstance().getWebPort()));
        txtWeb.setHorizontalAlignment(SwingConstants.LEFT);
        txtWeb.setColumns(10);
        GridBagConstraints gbc_txtWeb = new GridBagConstraints();
        gbc_txtWeb.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtWeb.anchor = GridBagConstraints.WEST;
        gbc_txtWeb.insets = new Insets(0, 0, 5, 5);
        gbc_txtWeb.gridx = 4;
        gbc_txtWeb.gridy = 5;
        add(txtWeb, gbc_txtWeb);
        
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
        
        JCheckBox chckbxWebAllowed = new JCheckBox("Generator Key Caching");
        chckbxWebAllowed.setHorizontalAlignment(SwingConstants.LEFT);
        chckbxWebAllowed.setSelected(Settings.getInstance().isGeneratorKeyCachingEnabled());
        GridBagConstraints gbc_chckbxWebAllowed = new GridBagConstraints();
        gbc_chckbxWebAllowed.fill = GridBagConstraints.BOTH;
        gbc_chckbxWebAllowed.insets = new Insets(0, 0, 5, 5);
        gbc_chckbxWebAllowed.gridwidth = 4;
        gbc_chckbxWebAllowed.gridx = 1;
        gbc_chckbxWebAllowed.gridy = 7;
        add(chckbxWebAllowed, gbc_chckbxWebAllowed);
        
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
        GridBagConstraints gbc_textField_2 = new GridBagConstraints();
        gbc_textField_2.gridwidth = 2;
        gbc_textField_2.insets = new Insets(0, 0, 5, 5);
        gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_2.gridx = 2;
        gbc_textField_2.gridy = 9;
        add(textDataFolder, gbc_textField_2);
        
        JButton btnBrowse1 = new JButton("Browse...");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.anchor = GridBagConstraints.WEST;
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 4;
        gbc_btnNewButton.gridy = 9;
        btnBrowse1.addActionListener(new ActionListener() {
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
        add(btnBrowse1, gbc_btnNewButton);
        
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
        GridBagConstraints gbc_textField_3 = new GridBagConstraints();
        gbc_textField_3.gridwidth = 2;
        gbc_textField_3.insets = new Insets(0, 0, 5, 5);
        gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_3.gridx = 2;
        gbc_textField_3.gridy = 10;
        add(textWallet, gbc_textField_3);
        
        JButton btnBrowse2 = new JButton("Browse...");
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.anchor = GridBagConstraints.WEST;
        gbc_button.insets = new Insets(0, 0, 5, 5);
        gbc_button.gridx = 4;
        gbc_button.gridy = 10;
        
        btnBrowse2.addActionListener(new ActionListener() {
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
        
        add(btnBrowse2, gbc_button);
        
        JLabel lblNewLabel_1 = new JLabel("An explanatory text: An explanatory text: An explanatory text.");
        lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
        gbc_lblNewLabel_1.gridwidth = 4;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 11;
        add(lblNewLabel_1, gbc_lblNewLabel_1);
         
	}
	public void close() 
	{
		//REMOVE OBSERVERS/HANLDERS
		this.knownPeersTableModel.removeObservers();
	}
	
}
