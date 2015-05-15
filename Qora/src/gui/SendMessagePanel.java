package gui;

import gui.models.AccountsComboBoxModel;
import gui.models.AssetsComboBoxModel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.nio.charset.Charset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import qora.account.Account;
import qora.assets.Asset;
import qora.transaction.Transaction;
import utils.Converter;
import utils.Pair;
import controller.Controller;

@SuppressWarnings("serial")
public class SendMessagePanel extends JPanel 
{
	private JComboBox<Account> cbxFrom;
	private JTextField txtTo;
	private JTextField txtAmount;
	private JTextField txtFee;
	private JTextArea txtMessage;
	private JCheckBox encrypted;
	private JCheckBox isText;
	private JButton sendButton;
	private AccountsComboBoxModel accountsModel;
	private JComboBox<Asset> cbxFavorites;
	
	public SendMessagePanel()
	{
		this.setLayout(new GridBagLayout());
		
		//PADDING
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		//LABEL GBC
		GridBagConstraints labelGBC = new GridBagConstraints();
		labelGBC.insets = new Insets(5,5,5,5);
		labelGBC.fill = GridBagConstraints.HORIZONTAL;   
		labelGBC.anchor = GridBagConstraints.NORTHWEST;
		labelGBC.weightx = 0;	
		labelGBC.gridx = 0;
		
		//COMBOBOX GBC
		GridBagConstraints cbxGBC = new GridBagConstraints();
		cbxGBC.insets = new Insets(5,5,5,5);
		cbxGBC.fill = GridBagConstraints.NONE;  
		cbxGBC.anchor = GridBagConstraints.NORTHWEST;
		cbxGBC.weightx = 0;	
		cbxGBC.gridx = 1;	
		
		//TEXTFIELD GBC
		GridBagConstraints txtGBC = new GridBagConstraints();
		txtGBC.insets = new Insets(5,5,5,5);
		txtGBC.fill = GridBagConstraints.HORIZONTAL;  
		txtGBC.anchor = GridBagConstraints.NORTHWEST;
		txtGBC.weightx = 1;	
		txtGBC.gridwidth = 4;
		txtGBC.gridx = 1;	
		
		//FAVORITES GBC
		GridBagConstraints favoritesGBC = new GridBagConstraints();
		favoritesGBC.insets = new Insets(5, 5, 5, 5);
		favoritesGBC.fill = GridBagConstraints.BOTH;  
		favoritesGBC.anchor = GridBagConstraints.NORTHWEST;
		favoritesGBC.weightx = 1;
		favoritesGBC.gridwidth = 4;
		favoritesGBC.gridx = 0;	
		favoritesGBC.gridy = 0;	
		
		//ASSET FAVORITES
		cbxFavorites = new JComboBox<Asset>(new AssetsComboBoxModel());
		this.add(cbxFavorites, favoritesGBC);
		
		//BUTTON GBC
		GridBagConstraints buttonGBC = new GridBagConstraints();
		buttonGBC.insets = new Insets(5,5,5,5);
		buttonGBC.fill = GridBagConstraints.NONE;  
		buttonGBC.anchor = GridBagConstraints.NORTHWEST;
		buttonGBC.gridwidth = 2;
		buttonGBC.gridx = 0;		
		
		//LABEL FROM
		labelGBC.gridy = 1;
		JLabel fromLabel = new JLabel("From:");
		this.add(fromLabel, labelGBC);
		
		//COMBOBOX FROM
		txtGBC.gridy = 1;
		this.accountsModel = new AccountsComboBoxModel();
		this.cbxFrom = new JComboBox<Account>(accountsModel);
        this.add(this.cbxFrom, txtGBC);
        
		//ON FAVORITES CHANGE
		cbxFavorites.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	Asset asset = ((Asset) cbxFavorites.getSelectedItem());
		    	if(asset != null)
		    	{
		    		//REMOVE ITEMS
			    	cbxFrom.removeAllItems();
			    	
			    	//SET RENDERER
			    	cbxFrom.setRenderer(new AccountRenderer(asset.getKey()));
			    	
			    	//UPDATE MODEL
			    	accountsModel.removeObservers();
			    	accountsModel = new AccountsComboBoxModel();
			    	cbxFrom.setModel(accountsModel);
		    	}
		    }
		});
        
        //LABEL TO
      	labelGBC.gridy = 2;
      	JLabel toLabel = new JLabel("To:");
      	this.add(toLabel, labelGBC);
      		
      	//TXT TO
      	txtGBC.gridy = 2;
      	txtTo = new JTextField();
        this.add(txtTo, txtGBC);
        
        //LABEL MESSAGE
        labelGBC.gridy = 3;
        JLabel messageLabel = new JLabel("Message:");
        this.add(messageLabel, labelGBC);
        
        //TXT MESSAGE
        txtGBC.gridy = 3;
        txtMessage = new JTextArea();
        this.txtMessage.setRows(4);
      	this.txtMessage.setBorder(this.txtTo.getBorder());
      	
        this.add(txtMessage, txtGBC);
        
      	//LABEL ISTEXT 
      	labelGBC.gridy = 4;
      	JLabel isTextLabel = new JLabel("Text Message:");
      	this.add(isTextLabel, labelGBC);
      	
        //TEXT ISTEXT
        txtGBC.gridy = 4;
        isText = new JCheckBox();
        isText.setSelected(true);
        this.add(isText, txtGBC);
        
        //LABEL AMOUNT
      	labelGBC.gridy = 6;
      	JLabel amountLabel = new JLabel("Amount:");
      	this.add(amountLabel, labelGBC);
      		
      	//TXT AMOUNT
      	txtGBC.gridy = 6;
      	txtAmount = new JTextField("0.00000000");
        this.add(txtAmount, txtGBC);
        
        //LABEL FEE
      	labelGBC.gridy = 7;
      	JLabel feeLabel = new JLabel("Fee:");
      	this.add(feeLabel, labelGBC);
      		
      	//TXT FEE
      	txtGBC.gridy = 7;
      	txtFee = new JTextField();
      	txtFee.setText("1.00000000");
        this.add(txtFee, txtGBC);

        //LABEL ENCRYPTED
      	labelGBC.gridy = 8;
      	labelGBC.gridwidth = 2;
      	JLabel encLabel = new JLabel("Encrypt Message (Not yet available):");
      	this.add(encLabel, labelGBC);
      	
        //ENCRYPTED CHECKBOX
        txtGBC.gridy = 8;
        txtGBC.gridx = 3;
        encrypted = new JCheckBox();
        encrypted.setEnabled(false);
        encrypted.setSelected(false);
        this.add(encrypted, txtGBC);
        
        
        //BUTTON SEND
        buttonGBC.gridy = 9;
        sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(80, 25));
    	sendButton.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        onSendClick();
		    }
		});	
		this.add(sendButton, buttonGBC);
        
        //ADD BOTTOM SO IT PUSHES TO TOP
        labelGBC.gridy = 8;
        labelGBC.weighty = 1;
      	this.add(new JPanel(), labelGBC);
	}
	
	public void onSendClick()
	{
		//DISABLE
		this.sendButton.setEnabled(false);
		
		//TODO TEST
		//CHECK IF NETWORK OKE
		/*if(Controller.getInstance().getStatus() != Controller.STATUS_OKE)
		{
			//NETWORK NOT OKE
			JOptionPane.showMessageDialog(null, "You are unable to send a transaction while synchronizing or while having no connections!", "Error", JOptionPane.ERROR_MESSAGE);
			
			//ENABLE
			this.sendButton.setEnabled(true);
			
			return;
		}*/
		
		//CHECK IF WALLET UNLOCKED
		if(!Controller.getInstance().isWalletUnlocked())
		{
			//ASK FOR PASSWORD
			String password = PasswordPane.showUnlockWalletDialog(); 
			if(!Controller.getInstance().unlockWallet(password))
			{
				//WRONG PASSWORD
				JOptionPane.showMessageDialog(null, "Invalid password", "Unlock Wallet", JOptionPane.ERROR_MESSAGE);
				
				//ENABLE
				this.sendButton.setEnabled(true);
				
				return;
			}
		}
		
		//READ SENDER
		Account sender = (Account) cbxFrom.getSelectedItem();
		
		//READ RECIPIENT
		String recipientAddress = txtTo.getText();
		Account recipient = new Account(recipientAddress);
		
		int parsing = 0;
		try
		{
			//READ AMOUNT
			parsing = 1;
			BigDecimal amount = new BigDecimal(txtAmount.getText()).setScale(8);
			
			//READ FEE
			parsing = 2;
			BigDecimal fee = new BigDecimal(txtFee.getText()).setScale(8);
			
			//CHECK MIMIMUM FEE
			if(fee.compareTo(Transaction.MINIMUM_FEE) == -1)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Fee must be at least 1!", "Error", JOptionPane.ERROR_MESSAGE);
				
				//ENABLE
				this.sendButton.setEnabled(true);
				
				return;
			}
			
			String message = txtMessage.getText();
			
			boolean isTextB = isText.isSelected();
			
			byte[] messageBytes;
			
			if ( isTextB )
			{
				messageBytes = message.getBytes( Charset.forName("UTF-8") );
			}
			else
			{
				try
				{
					messageBytes = Converter.parseHexString( message );
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Message format is not hex!", "Error", JOptionPane.ERROR_MESSAGE);
					
					//ENABLE
					this.sendButton.setEnabled(true);
					
					return;
				}
			}
			if ( messageBytes.length < 1 || messageBytes.length > 4000 )
			{
				JOptionPane.showMessageDialog(new JFrame(), "Message size exceeded!", "Error", JOptionPane.ERROR_MESSAGE);
				
				//ENABLE
				this.sendButton.setEnabled(true);
				
				return;
			}
			
			boolean encryptMessage = encrypted.isSelected();
		
			byte[] encrypted = (encryptMessage)?new byte[]{1}:new byte[]{0};
			byte[] isTextByte = (isTextB)? new byte[] {1}:new byte[]{0};
			//CHECK IF PAYMENT OR ASSET TRANSFER
			Asset asset = (Asset) this.cbxFavorites.getSelectedItem();
			Pair<Transaction, Integer> result;
			if(asset.getKey() == 0l)
			{
				//CREATE PAYMENT
				result = Controller.getInstance().sendMessage(Controller.getInstance().getPrivateKeyAccountByAddress(sender.getAddress()), recipient, amount, fee, messageBytes, isTextByte, encrypted);
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(), "Feature not available", "Error", JOptionPane.ERROR_MESSAGE);
				//ENABLE
				this.sendButton.setEnabled(true);
				
				return;
			}
			
			//CHECK VALIDATE MESSAGE
			switch(result.getB())
			{
			case Transaction.VALIDATE_OKE:
				
				//RESET FIELDS
				this.txtAmount.setText("");
				this.txtTo.setText("");
				this.txtMessage.setText("");
				
				JOptionPane.showMessageDialog(new JFrame(), "Payment has been sent!", "Success", JOptionPane.INFORMATION_MESSAGE);
				break;	
			
			case Transaction.INVALID_ADDRESS:
				
				JOptionPane.showMessageDialog(new JFrame(), "Invalid address!", "Error", JOptionPane.ERROR_MESSAGE);
				break;
				
			case Transaction.NEGATIVE_AMOUNT:
				
				JOptionPane.showMessageDialog(new JFrame(), "Amount must be positive!", "Error", JOptionPane.ERROR_MESSAGE);
				break;
				
			case Transaction.NEGATIVE_FEE:
				
				JOptionPane.showMessageDialog(new JFrame(), "Fee must be at least 1!", "Error", JOptionPane.ERROR_MESSAGE);
				break;	
				
			case Transaction.NO_BALANCE:
			
				JOptionPane.showMessageDialog(new JFrame(), "Not enough balance!", "Error", JOptionPane.ERROR_MESSAGE);
				break;	
				
			default:
				
				JOptionPane.showMessageDialog(new JFrame(), "Unknown error!", "Error", JOptionPane.ERROR_MESSAGE);
				break;		
				
			}
		}
		catch(Exception e)
		{
			//CHECK WHERE PARSING ERROR HAPPENED
			switch(parsing)
			{
			case 1:
				
				JOptionPane.showMessageDialog(new JFrame(), "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
				break;
				
			case 2:
				
				JOptionPane.showMessageDialog(new JFrame(), "Invalid fee!", "Error", JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
		
		//ENABLE
		this.sendButton.setEnabled(true);
	}
}
