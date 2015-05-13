package gui.models;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import settings.Settings;
import utils.ObserverMessage;
import controller.Controller;
import network.Peer;

@SuppressWarnings("serial")
public class KnownPeersTableModel extends AbstractTableModel implements Observer{

	private static final int COLUMN_ADDRESS = 0;
	public static final int COLUMN_CONNECTED = 1;
	
	private List<Peer> peers;
	private ArrayList<Boolean> peersStatus = new ArrayList<Boolean>();
	
	private String[] columnNames = {"IP", "�onnected now"};
	
	public void addAddress(String address) 
	{
		InetAddress address1;
		try {
			address1 = InetAddress.getByName(address);
			Peer peer = new Peer(address1);
			peers.add(peer);
			peersStatus.add(false);
			this.fireTableDataChanged();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public KnownPeersTableModel()
	{
		peers = Settings.getInstance().getKnownPeers();
		
				
		for(Peer peer: peers)
		{
			peersStatus.add(false);
		}
		Controller.getInstance().addActivePeersObserver(this);
	}
	
	@Override
	public int getColumnCount() 
	{
		return columnNames.length;
	}

	@Override
	public String getColumnName(int index) 
	{
		return columnNames[index];
	}

	@Override
	public int getRowCount() 
	{
		if(peers == null)
		{
			return 0;
		}
		
		return peers.size();
	}

	@Override
	public Object getValueAt(int row, int column)
	{
		if(peers == null)
		{
			return null;
		}
		
		switch(column)
		{
			case COLUMN_ADDRESS:
				return peers.get(row).getAddress().getHostAddress().toString();
			
			case COLUMN_CONNECTED:
				return peersStatus.get(row);
		}
		
		return null;
	}


	@Override
	public void update(Observable o, Object arg) 
	{	
		try
		{
			this.syncUpdate(o, arg);
		}
		catch(Exception e)
		{
			//GUI ERROR
		}
	}
	
	@SuppressWarnings("unchecked")	
	public synchronized void syncUpdate(Observable o, Object arg)
	{
		ObserverMessage message = (ObserverMessage) arg;
		
		if(message.getType() == ObserverMessage.LIST_PEER_TYPE)
		{
			List<Peer> peersBuff = (List<Peer>) message.getValue();
			int n = 0;
			
			for(Peer peer1: this.peers)
			{
				boolean connected = false;
				
				for(Peer peer2: peersBuff)
				{
					if(peer1.getAddress().toString().equals(peer2.getAddress().toString()))
					{
						this.peersStatus.set(n, true);
						connected = true;
					}
				}
				
				if(!connected)
				{
					this.peersStatus.set(n, false);
				}
				
				n++;
			}	
			
			this.fireTableDataChanged();
		}
	}

	public void removeObservers() 
	{
		Controller.getInstance().removeActivePeersObserver(this);
		
	}
}
