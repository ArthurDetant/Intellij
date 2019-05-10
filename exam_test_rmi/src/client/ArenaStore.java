package client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import common.ClientInterface;
import common.ServerInterface;


public class ArenaStore implements ClientInterface{

	private JFrame frm ;
	private JLabel available;
	private JTextField customer;
	private JTextField nbPlaces;

	private JButton bookButton;
	private JButton listButton;
	
	public ClientInterface stub;
	public ServerInterface serv;

	public ArenaStore() 
	{
		try {
			buildUI();
			stub = (ClientInterface) UnicastRemoteObject.exportObject(this, 0) ;
			serv = (ServerInterface) Naming.lookup("rmi://localhost/Server");
			serv.register(stub);
			
			addListeners();
			
			frm.setSize(600, 250);
			frm.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addListeners(){

		bookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					serv.reserve(customer.getText(), Integer.parseInt(nbPlaces.getText()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				customer.setText("");
				nbPlaces.setText("");
				customer.grabFocus();
			}
		});
		
		listButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String list = serv.getNames();
					JOptionPane.showMessageDialog(frm, list, "Liste des inscrits", JOptionPane.INFORMATION_MESSAGE);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		frm.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					serv.remove(stub);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				System.exit(0) ;
			}
		}) ;
		
	}
	
	private void buildUI(){
		frm = new JFrame("Reservations") ;
		frm.getContentPane().setLayout(new BorderLayout()) ;
				
		JLabel title = new JLabel("Nombre de places restantes : ") ;
		title.setHorizontalAlignment(JLabel.HORIZONTAL) ;
		frm.add(title, BorderLayout.NORTH) ;
		available = new JLabel("unknown") ;
		available.setFont(new Font("Times", Font.PLAIN, 50));
		available.setHorizontalAlignment(JLabel.HORIZONTAL);
		frm .add(available, BorderLayout.CENTER) ;
		
		JPanel booking = new JPanel() ;
		booking.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		booking.add(new JLabel("Client : ")) ;
		customer = new JTextField() ;
		customer.setColumns(10);
		booking.add(customer) ;
		booking.add(new JLabel("Nb places : ")) ;
		nbPlaces = new JTextField() ;
		nbPlaces.setColumns(2);
		booking.add(nbPlaces) ;
		
		bookButton = new JButton("Reserver") ;
		booking.add(bookButton);
		
		listButton = new JButton("Lister");
		booking.add(listButton);
		
		frm.add(booking, BorderLayout.SOUTH) ;
		
		
	}
	
	public static void main(String[] args) {
		new ArenaStore() ;
	}

	@Override
	public void setRemainingPlaces(int n) throws RemoteException 
	{
		available.setText(""+n);
	}
}