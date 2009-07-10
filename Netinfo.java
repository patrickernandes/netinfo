import java.net.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 
  
public class Netinfo extends JFrame implements ActionListener
{   
    public JTextField texto1;
    public JTextArea info;
    public JLabel etiqueta;
    public JButton btlimpar,btsair,bturl,btwho,btping,btler;
    public JScrollPane rolagem;
    public URL urlo;   
    
    public static void main(String args[])
	{
		JFrame janela = new Netinfo();
		janela.setUndecorated(false);
		//janela.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		janela.setVisible(true);        
	}   
   
    public Netinfo() 
    {   
	    setTitle("NetInfo v01");	
	    setBounds(250,100,700,500);
        setResizable(false);
        getContentPane();
        setLayout(null);

	    JLabel nome = new JLabel("Patrick Ernandes - email: patrickernandes@gmail.com");
	
	    etiqueta = new JLabel("URL:");
        texto1 = new JTextField();
	    info = new JTextArea(500,300);
	    info.setLineWrap(true);
	    rolagem = new JScrollPane(info);
	    btlimpar = new JButton("Limpar");         btlimpar.addActionListener(this);
	    bturl = new JButton("URL Info");          bturl.addActionListener(this);
	    btwho = new JButton("URL Whois");         btwho.addActionListener(this);
	    btping = new JButton("URL Ping");         btping.addActionListener(this);
	    btler = new JButton("URL Ler");           btler.addActionListener(this);        
        btsair = new JButton("Sair");             btsair.addActionListener(this);
      		
	    etiqueta.setBounds(10,20,40,25);
	    texto1.setBounds(50,20,450,25);
	    btlimpar.setBounds(10,60,80,25);
	    bturl.setBounds(100,60,90,25);
	    btwho.setBounds(200,60,110,25);
	    btping.setBounds(320,60,100,25);
	    btler.setBounds(430,60,100,25);
	    rolagem.setBounds(10,90,670,350);
	    nome.setBounds(60,360,400,25);
	    btsair.setBounds(610,60,70,25);
	    
	    getContentPane().add(etiqueta);
        getContentPane().add(texto1);
        getContentPane().add(btlimpar);
        getContentPane().add(bturl);
        getContentPane().add(btwho);
        getContentPane().add(btping);
        getContentPane().add(btler);
        getContentPane().add(rolagem);
        //getContentPane().add(nome);
        getContentPane().add(btsair);
	
    }
    
public void actionPerformed(ActionEvent e)
{	
   	if (e.getSource()==bturl) //get URL
   	{   		   
   /*	if(texto1.getText().equals("quit"))
		{
		    System.exit(0);     
		} */
		try
		{		     
            urlo = new URL(texto1.getText());	         
		
		 	info.append("File: " + String.valueOf(urlo.getFile())+"\n");
		 	info.append("Port: " + String.valueOf(urlo.getDefaultPort())+"\n");
            info.append("Host: " + String.valueOf(urlo.getHost())+"\n");
		 	info.append("Reference: " + String.valueOf(urlo.getRef())+"\n");
		 	info.append("User Info: " + String.valueOf(urlo.getUserInfo())+"\n");
		 	info.append("Path: " + String.valueOf(urlo.getPath())+"\n");
	     	info.append("Query: " + String.valueOf(urlo.getQuery())+"\n");
        	info.append("Authority: "+String.valueOf(urlo.getAuthority()) +"\n");		
            info.append("Protocol: " + String.valueOf(urlo.getProtocol()) +"\n");
		}		
		catch (MalformedURLException exc)
		{
		 
		}		  
		  
	    try
		{
			URLConnection conector = urlo.openConnection();
			info.append("Date: " + String.valueOf(conector.getDate()) + "\n");
 		    info.append("Header Fields: " + String.valueOf(conector.getHeaderFields()) + "\n\n");
			info.append("Content: " + String.valueOf(conector.getContent())+ "\n");
     	    info.append("Cache Use: " + String.valueOf(conector.getUseCaches()) + "\n");
     	    info.append("File Name map: " + String.valueOf(conector.getFileNameMap()) + "\n");		   
     	    info.append("Content Type: " + String.valueOf(conector.getContentType()) + "\n");
	   	} 	 
	    catch (IOException io)
		{		
		  
		}
    }
    
    if (e.getSource()==btwho) //whois
   	{   
   		// Default server is whois.geektools.com
		String server = "whois.nic.br"; //default servidor
		int port = 43; //porta
		
		try
		{
			// Establish connection to whois server & port
			Socket connection = new Socket(server, port);
			PrintStream out = new PrintStream(connection.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = "";

			// Send the whois query
			out.println(texto1.getText());
			// Display the whois server's address & port
			//System.out.println("[" + server + ":" + port + "]");

			// Read/display the query's result
			while ((line = in.readLine()) != null)
			{
				//System.out.println(line);
				info.append(line + "\n");
			}
		}
		//catch (UnknownHostException e)
		catch (Exception exc)
		{
			
		}		 	                
    } 
        
/*    if (e.getSource()==btping) //ping
   	{     		
	    String ip = texto1.getText();
    	 try {
      Socket t = new Socket(ip, 7);
      DataInputStream dis = new DataInputStream(t.getInputStream());
      PrintStream ps = new PrintStream(t.getOutputStream());
      ps.println("Hello");
      String str = dis.readLine();
      if (str.equals("Hello"))
        System.out.println("Alive!") ;
      else
        System.out.println("Dead or echo port not responding");              
      t.close();
    }
    catch (IOException exc) 
    {
      //e.printStackTrace();
    }

    }
  */
        
    if (e.getSource()==btler) //ler url
   	{   
   		try
   		{	//texto1.setText("");	  
	    	//info.setText("");	
	    	urlo = new URL(texto1.getText());      
      		BufferedReader reader = new BufferedReader(new InputStreamReader(urlo.openStream()));
      		String linha = "";
      		while ((linha = reader.readLine()) != null) 
      		{
         		//System.out.println(linha);
         		info.append(linha + "\n");
      		}
      		reader.close();
      	}	
      	catch (Exception exc)
		{
			
		}    
    }   
    
    if (e.getSource()==btlimpar) //limpar
   	{   
   		//texto1.setText("");	  
	    info.setText("");	    
	 	    	    
	 	                
    }
   
    if (e.getSource()==btsair) //sair
   	{           
	    System.exit(0);   	 	      
    }  
}    
     
	    
}

