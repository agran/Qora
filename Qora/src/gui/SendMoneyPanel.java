package gui;

import gui.models.AccountsComboBoxModel;
import gui.models.AssetsComboBoxModel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import qora.account.Account;
import qora.assets.Asset;
import qora.crypto.Crypto;
import qora.transaction.Transaction;
import utils.NameUtils;
import utils.NameUtils.NameResult;
import utils.Pair;
import controller.Controller;

@SuppressWarnings("serial")
public class SendMoneyPanel extends JPanel 
{
	private JComboBox<Account> cbxFrom;
	private JTextField txtTo;
	private JTextField txtAmount;
	private JTextField txtFee;
	private JTextField txtRecDetails;
	private JButton sendButton;
	private AccountsComboBoxModel accountsModel;
	private JComboBox<Asset> cbxFavorites;
	private JComboBox<String> cbxPaymentSolution;
	
	private final String ORDINARY_PAYMENT = "Ordinary payment";
	private final String NAME_PAYMENT = "Name payment";
	
	
	
	
	public SendMoneyPanel()
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
		txtGBC.gridwidth = 2;
		txtGBC.gridx = 1;	
		
		//FAVORITES GBC
		GridBagConstraints favoritesGBC = new GridBagConstraints();
		favoritesGBC.insets = new Insets(5, 5, 5, 5);
		favoritesGBC.fill = GridBagConstraints.BOTH;  
		favoritesGBC.anchor = GridBagConstraints.NORTHWEST;
		favoritesGBC.weightx = 1;
		favoritesGBC.gridwidth = 2;
		favoritesGBC.gridx = 0;	
		favoritesGBC.gridy = 0;	
		
		//PAYMENT SOLUTION GBC
		GridBagConstraints paymentGBC = new GridBagConstraints();
		paymentGBC.insets = new Insets(5, 5, 5, 5);
		paymentGBC.fill = GridBagConstraints.HORIZONTAL;  
		paymentGBC.anchor = GridBagConstraints.NORTHWEST;
		paymentGBC.weightx = 1;
		paymentGBC.gridwidth = 2;
		paymentGBC.gridx = 0;	
		paymentGBC.gridy = 1;	
		
		//ASSET FAVORITES
		cbxFavorites = new JComboBox<Asset>(new AssetsComboBoxModel());
		this.add(cbxFavorites, favoritesGBC);
		
		//PAYMENT SOLUTION
		cbxPaymentSolution = new JComboBox<String>();
		cbxPaymentSolution.addItem(ORDINARY_PAYMENT);
		cbxPaymentSolution.addItem(NAME_PAYMENT);
		this.add(cbxPaymentSolution, paymentGBC);
		
		cbxPaymentSolution.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshReceiverDetails();
				
			}
		});
		
		
		
		//BUTTON GBC
		GridBagConstraints buttonGBC = new GridBagConstraints();
		buttonGBC.insets = new Insets(5,5,5,5);
		buttonGBC.fill = GridBagConstraints.NONE;  
		buttonGBC.anchor = GridBagConstraints.NORTHWEST;
		buttonGBC.gridwidth = 2;
		buttonGBC.gridx = 0;		
		
		//LABEL FROM
		labelGBC.gridy = 2;
		JLabel fromLabel = new JLabel("From:");
		this.add(fromLabel, labelGBC);
		
		//COMBOBOX FROM
		txtGBC.gridy = 2;
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
      	labelGBC.gridy = 3;
      	JLabel toLabel = new JLabel("To:");
      	this.add(toLabel, labelGBC);
      		
      	//TXT TO
      	txtGBC.gridy = 3;
      	txtTo = new JTextField();
        this.add(txtTo, txtGBC);
        
        txtTo.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				refreshReceiverDetails();
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// does not matter
				
			}
			
		
		});
        
        //LABEL AMOUNT
      	labelGBC.gridy = 4;
      	JLabel amountLabel = new JLabel("Amount:");
      	this.add(amountLabel, labelGBC);
      		
      	//TXT AMOUNT
      	txtGBC.gridy = 4;
      	txtAmount = new JTextField();
        this.add(txtAmount, txtGBC);
        
        //LABEL FEE
      	labelGBC.gridy = 5;
      	JLabel feeLabel = new JLabel("Fee:");
      	this.add(feeLabel, labelGBC);
      		
      	//TXT AMOUNT
      	txtGBC.gridy = 5;
      	txtFee = new JTextField();
      	txtFee.setText("1");
        this.add(txtFee, txtGBC);
        
        //LABEL RECEIVER DETAILS 
      	labelGBC.gridy = 6;
      	JLabel recDetailsLabel = new JLabel("Receiver details:");
      	this.add(recDetailsLabel, labelGBC);
      		
      	//RECEIVER DETAILS 
      	txtGBC.gridy = 6;
      	txtRecDetails = new JTextField();
      	txtRecDetails.setEditable(false);
        this.add(txtRecDetails, txtGBC);
        
        //BUTTON SEND
        buttonGBC.gridy = 7;
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
        labelGBC.gridy = 6;
        labelGBC.weighty = 1;
      	this.add(new JPanel(), labelGBC);
	}
	
	
	private void refreshReceiverDetails()
	{
		String toValue = txtTo.getText();
		
		if(toValue.isEmpty())
		{
			txtRecDetails.setText("");
			return;
		}
		
		if(Controller.getInstance().getStatus() != Controller.STATUS_OKE)
		{
			txtRecDetails.setText("Status must be OK to show receiver details.");
			return;
		}
		
			if(cbxPaymentSolution.getSelectedItem() == NAME_PAYMENT)
			{
				Pair<Account, NameResult> nameToAdress = NameUtils.nameToAdress(toValue);
				
				if(nameToAdress.getB() == NameResult.OK)
				{
					Account acct = nameToAdress.getA();
					txtRecDetails.setText(acct.getBalance(1).toPlainString() + " - " + acct.getAddress());
					
				}else
				{
					
					txtRecDetails.setText(nameToAdress.getB().getStatusMessage());
				}
			}else
			{
					//CHECK IF RECIPIENT IS VALID ADDRESS
					if(!Crypto.getInstance().isValidAddress(toValue))
					{
						txtRecDetails.setText("Invalid address!");
					}else
					{
						Account account = new Account(toValue);
						txtRecDetails.setText(account.getBalance(1).toPlainString() + " - " + account.getAddress());
					}
			}
	}
	
	public void onSendClick()
	{
		//DISABLE
		this.sendButton.setEnabled(false);
		
		//CHECK IF NETWORK OKE
		if(Controller.getInstance().getStatus() != Controller.STATUS_OKE)
		{
			//NETWORK NOT OKE
			JOptionPane.showMessageDialog(null, "You are unable to send a transaction while synchronizing or while having no connections!", "Error", JOptionPane.ERROR_MESSAGE);
			
			//ENABLE
			this.sendButton.setEnabled(true);
			
			return;
		}
		
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
		
		//READ RECIPIENT (NAME OR QORA ADRESS)
		String recipientAddress = txtTo.getText();
		
		
		Account recipient;
		
		//ORDINARY PAYMENT
		if(cbxPaymentSolution.getSelectedItem().equals(ORDINARY_PAYMENT))
		{
			recipient = new Account(recipientAddress);
		//NAME PAYMENT
		}else if(cbxPaymentSolution.getSelectedItem().equals(NAME_PAYMENT))
		{
			
			Pair<Account, NameResult> result = NameUtils.nameToAdress(recipientAddress);
			
			if(result.getB() == NameResult.OK)
			{
				recipient = result.getA();
			}else
			{
				JOptionPane.showMessageDialog(null, result.getB().getStatusMessage() , "Error", JOptionPane.ERROR_MESSAGE);
				
				//ENABLE
				this.sendButton.setEnabled(true);
				
				return;
			}
			
		}else
		{
			
			//THIS ONLY HAPPENS IF WE MADE A MISTAKE ;)
			JOptionPane.showMessageDialog(null, "An Internal Error occured! " + cbxPaymentSolution.getSelectedItem() + " is not known!" , "Error", JOptionPane.ERROR_MESSAGE);
			
			//ENABLE
			this.sendButton.setEnabled(true);
			
			return;
		}
		
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
		
			//CHECK IF PAYMENT OR ASSET TRANSFER
			Asset asset = (Asset) this.cbxFavorites.getSelectedItem();
			Pair<Transaction, Integer> result;
			if(asset.getKey() == 0l)
			{
				//CREATE PAYMENT
				result = Controller.getInstance().sendPayment(Controller.getInstance().getPrivateKeyAccountByAddress(sender.getAddress()), recipient, amount, fee);
			}
			else
			{
				//CREATE ASSET TRANSFER
				result = Controller.getInstance().transferAsset(Controller.getInstance().getPrivateKeyAccountByAddress(sender.getAddress()), recipient, asset, amount, fee);
			}
			
			//CHECK VALIDATE MESSAGE
			switch(result.getB())
			{
			case Transaction.VALIDATE_OKE:
				
				//RESET FIELDS
				this.txtAmount.setText("");
				this.txtTo.setText("");
				
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
