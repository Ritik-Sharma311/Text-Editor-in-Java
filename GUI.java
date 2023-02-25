import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import javax.swing.text.DefaultEditorKit;
public class GUI extends JFrame implements ActionListener{
  JTextArea textArea,jta3;
  JTextField jtf;
  JFrame jf,jf1;
  JMenuBar menuBar;
  JComboBox<String> fontType;
  JComboBox<Integer> fontSize;
  JMenu menuFile, menuEdit, menuRun, createJavaP,menuAbout;
  JMenuItem newFile, openFile, saveFile, close, cut, copy, runFile, createJavaPFile, compileFile, aboutFile, paste, clearFile;
  JToolBar mainToolbar;
    JButton jb, newButton, openButton, saveButton, clearButton, runButton, compileButton, javaButton, closeButton, boldButton, italicButton;
	//String s1="";
	String s2="";
	String s4="";
	String s5="";
    String s11="";	
   // String fn="";
   // String fname="";
    //String result="";
    //String result1="";
	Runtime r=Runtime.getRuntime();

   ImageIcon boldIcon = new ImageIcon("bold.png");
    ImageIcon italicIcon = new ImageIcon("italic.png");
     ImageIcon newIcon = new ImageIcon("new.png");
     ImageIcon openIcon = new ImageIcon("open.png");
   ImageIcon saveIcon = new ImageIcon("save.png");
     ImageIcon closeIcon = new ImageIcon("close.png");
   ImageIcon clearIcon = new ImageIcon("clear.png");
   ImageIcon runIcon = new ImageIcon("run.png");
   ImageIcon compileIcon = new ImageIcon("compile.png");
   ImageIcon javaIcon = new ImageIcon("java.png");
  ImageIcon cutIcon = new ImageIcon("cut.png");
    ImageIcon copyIcon = new ImageIcon("copy.png");
   ImageIcon pasteIcon = new ImageIcon("paste.png");
   //ImageIcon aboutIcon = new ImageIcon(".png");
    private boolean edit = false;
    public GUI()
	{
		try {
		     UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    }
	    catch(Exception e2) {	e2.printStackTrace();  }
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setTitle("Notepad+JavaEditor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        textArea = new JTextArea("", 0, 0);
        textArea.setFont(new Font("Georgia", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.addKeyListener(new KeyAdapter() 
		{
            public void keyReleased(KeyEvent e)
			{
                setTitle("Editor" + "     [ Length: " + textArea.getText().length()
                        + "    Lines: " + (textArea.getText() + "|").split("\n").length
                        + "    Words: " + textArea.getText().trim().split("\\s+").length + " ]" + " - Notepad+JavaEditor");
						 edit = true;
            }
        });
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Container content = getContentPane();
        content.setLayout(new BorderLayout()); 
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane);
        content.add(panel);
        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
		menuRun = new JMenu("Run");
		createJavaP =new JMenu("CNJP");
		menuAbout = new JMenu("About");
        newFile = new JMenuItem("New", newIcon);
        openFile = new JMenuItem("Open", openIcon);
        saveFile = new JMenuItem("Save", saveIcon);
        close = new JMenuItem("Quit", closeIcon);
		runFile = new JMenuItem("Run", runIcon);
		createJavaPFile = new JMenuItem("Create New Java Program",javaIcon);
        clearFile = new JMenuItem("Clear", clearIcon);
		compileFile = new JMenuItem("Compile", compileIcon);
        menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
       menuBar.add(menuRun);
	   menuBar.add(createJavaP);
	   menuBar.add(menuAbout);
        this.setJMenuBar(menuBar);
		
        newFile.addActionListener(this);  
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK)); 
        menuFile.add(newFile); 

        openFile.addActionListener(this);
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        menuFile.add(openFile);

        saveFile.addActionListener(this);
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        menuFile.add(saveFile);

        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        close.addActionListener(this);
        menuFile.add(close);

        clearFile.addActionListener(this);
        clearFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));
        menuEdit.add(clearFile);
		
		runFile.addActionListener(this);
        runFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        menuRun.add(runFile);
		
		compileFile.addActionListener(this);
        compileFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
        menuRun.add(compileFile);
		
		createJavaPFile.addActionListener(this);
		createJavaPFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_MASK));
        createJavaP.add(createJavaPFile);
		

        cut = new JMenuItem(new DefaultEditorKit.CutAction());
        cut.setText("Cut");
        cut.setIcon(cutIcon);
        cut.setToolTipText("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        menuEdit.add(cut);

        copy = new JMenuItem(new DefaultEditorKit.CopyAction());
        copy.setText("Copy");
        copy.setIcon(copyIcon);
        copy.setToolTipText("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        menuEdit.add(copy);

        paste = new JMenuItem(new DefaultEditorKit.PasteAction());
        paste.setText("Paste");
        paste.setIcon(pasteIcon);
        paste.setToolTipText("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        menuEdit.add(paste);

        mainToolbar = new JToolBar();
        this.add(mainToolbar, BorderLayout.NORTH);
        newButton = new JButton(newIcon);
        newButton.setToolTipText("New");
        newButton.addActionListener(this);
        mainToolbar.add(newButton);
        mainToolbar.addSeparator();

        openButton = new JButton(openIcon);
        openButton.setToolTipText("Open");
        openButton.addActionListener(this);
        mainToolbar.add(openButton);
        mainToolbar.addSeparator();

        saveButton = new JButton(saveIcon);
        saveButton.setToolTipText("Save");
        saveButton.addActionListener(this);
        mainToolbar.add(saveButton);
        mainToolbar.addSeparator();

        clearButton = new JButton(clearIcon);
        clearButton.setToolTipText("Clear All");
        clearButton.addActionListener(this);
        mainToolbar.add(clearButton);
        mainToolbar.addSeparator();
		
		javaButton = new JButton(javaIcon);
        javaButton.setToolTipText("New Java Program");
        javaButton.addActionListener(this);
        mainToolbar.add(javaButton);
        mainToolbar.addSeparator();
		
		compileButton = new JButton(compileIcon);
        compileButton.setToolTipText("Compile");
        compileButton.addActionListener(this);
        mainToolbar.add(compileButton);
        mainToolbar.addSeparator();

        runButton = new JButton(runIcon);
        runButton.setToolTipText("Run");
        runButton.addActionListener(this);
        mainToolbar.add(runButton);
        mainToolbar.addSeparator();
		

        closeButton = new JButton(closeIcon);
        closeButton.setToolTipText("Quit");
        closeButton.addActionListener(this);
        mainToolbar.add(closeButton);
        mainToolbar.addSeparator();

        boldButton = new JButton(boldIcon);
        boldButton.setToolTipText("Bold");
        boldButton.addActionListener(this);
        mainToolbar.add(boldButton);
        mainToolbar.addSeparator();

        italicButton = new JButton(italicIcon);
        italicButton.setToolTipText("Italic");
        italicButton.addActionListener(this);
        mainToolbar.add(italicButton);
        mainToolbar.addSeparator();
        fontType = new JComboBox<String>();
		
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for (String font : fonts)
			{
            fontType.addItem(font);
        }
        fontType.setMaximumSize(new Dimension(170, 30));
        fontType.setToolTipText("Font Type");
        mainToolbar.add(fontType);
        mainToolbar.addSeparator();

        fontType.addActionListener(new ActionListener()
		{
            public void actionPerformed(ActionEvent ev)
			{
                String p = fontType.getSelectedItem().toString();
                int s = textArea.getFont().getSize();
                textArea.setFont(new Font(p, Font.PLAIN, s));
            }
        });

        fontSize = new JComboBox<Integer>();

        for (int i = 5; i <= 100; i++)
			{
            fontSize.addItem(i);
        }
        fontSize.setMaximumSize(new Dimension(70, 30));
        fontSize.setToolTipText("Font Size");
        mainToolbar.add(fontSize);

        fontSize.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent ev)
			{
                String sizeValue = fontSize.getSelectedItem().toString();
                int sizeOfFont = Integer.parseInt(sizeValue);
                String fontFamily = textArea.getFont().getFamily();

                Font font1 = new Font(fontFamily, Font.PLAIN, sizeOfFont);
                textArea.setFont(font1);
            }
        });
		    jf=new JFrame("Enter Java Class Name");
            jtf=new JTextField();
			jtf.setText("");
			jtf.setFont(new Font("Georgia",Font.PLAIN,20));
			jf.add(jtf,BorderLayout.CENTER);
			jb=new JButton("OK");
			jf.add(jb,BorderLayout.SOUTH);
			jf.setSize(350,100);
			jf.setLocationRelativeTo(null);
		    jf1=new JFrame();
		    jta3=new JTextArea();
            JScrollPane scrollPane1=new JScrollPane(jta3);
            jta3.setLineWrap(true);
		    jta3.setFont(new Font("Georgia", Font.PLAIN, 16));
            scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
            scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	
			jf1.add(scrollPane1, BorderLayout.CENTER);
			jf1.setSize(500,500);
			jf1.setLocationRelativeTo(null); 
    }
    public void processWindowEvent(WindowEvent e)
	{
        if (e.getID() == WindowEvent.WINDOW_CLOSING)
			{
						
            if (edit)
				{
                Object[] options = {"Save and exit", "No Save and exit"};
                int n = JOptionPane.showOptionDialog(this, "Do you want to save the file ?", "Question",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (n == 0)
					{ 
                    saveFile();
                    this.dispose();
                }
				else if (n == 1) {
                    this.dispose();
                }
            } 
			else
				{
                System.exit(99);
            }
		
        }
    }
    public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == javaButton || e.getSource() == createJavaPFile)
		{
			try {
			 jtf.setText("");
			jf.setVisible(true);
			jb.addActionListener(new ActionListener()
			{
				 public void actionPerformed(ActionEvent ev1)
				 {
					 String s1=jtf.getText().trim();
					  if(!s1.equals(" "))
					  {
						   textArea.setText("public class "+s1+"\n"
                   +"{"+"\n"
                   +"public static void main(String... s)"+"\n"
                   +"{"+"\n"
                   +"     "+"\n"
                   +"}"+"\n"
                    +"}");
					  }
					  jf.dispose();
					  edit=true;
				 }
		});
			
		}
         catch(Exception e7){	System.out.println(e7);  }
		}					
        if (e.getSource() == close || e.getSource() == closeButton) 
		{
            if (edit) 
			{				
               Object[] options = {"Save and exit", "No Save and exit"};
                int n1 = JOptionPane.showOptionDialog(this, "Do you want to save the file ?", "Question",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (n1 == 0) 
				{   
					if(!s4.equals(""))
			{
				try {
				 BufferedWriter out = new BufferedWriter(new FileWriter(s2));
                out.write(textArea.getText());
                out.close();
                edit = false;
				setTitle(s4+" - Notepad+JavaEditor");
				}
				catch(Exception e9) {	System.out.println(e9);	}				
			}
			else
            saveFile();
                    this.dispose();
                } 
				else if (n1 == 1) {
                    this.dispose();
                }
            } 
			else 
			{
                this.dispose();
            }
        }
        else if (e.getSource() == newFile || e.getSource() == newButton) 
		{
            if (edit) 
			{																												
                Object[] options = {"Save", "No Save",};
                int n = JOptionPane.showOptionDialog(this, "Do you want to save the file at first ?", "Question",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (n == 0) 
				{	
					if(!s4.equals(""))
			    {
				try
				{
				 BufferedWriter out = new BufferedWriter(new FileWriter(s2));
                out.write(textArea.getText());
                out.close();
                edit = false;
				setTitle(s4+" - Notepad+JavaEditor");
				}
				catch(Exception e9){   System.out.println(e9);  }				
			}
			else{
            saveFile();	
			}			
                   // edit = false;
                } 
				else if (n == 1) 
				{
                    edit = false;
                    clearText(textArea);
                }
            } 
			else 
			{
                clearText(textArea);
            }
        } 
        else if (e.getSource() == openFile || e.getSource() == openButton) 
		{
            
           if (edit) 
			{																												
                Object[] options = {"Save", "No Save",};
                int n = JOptionPane.showOptionDialog(this, "Do you want to save the file at first ?", "Question",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (n == 0) 
				{	
					if(!s4.equals(""))
			    {
				try
				{
				//	System.out.println(s2+"jjjjjjjjjjjjjjjjjjjjj");
				 BufferedWriter out = new BufferedWriter(new FileWriter(s2));
                out.write(textArea.getText());
                out.close();
                edit = false;
				setTitle(s4+" - Notepad+JavaEditor");
				}
				catch(Exception e9)
				{
					System.out.println(e9);
				}				
			}
			else
            saveFile();					
                  
                }
                else if (n == 1) 
				{
                    edit = false;
                    clearText(textArea);
                }				
			}
			
			JFileChooser open = new JFileChooser(s2); 
            int option = open.showOpenDialog(this); 
            if (option == JFileChooser.APPROVE_OPTION) 
			{
                clearText(textArea);
                try {
                    File openFile = open.getSelectedFile();
					
					s4=openFile.getName();
					//	System.out.println(s4+"aaaaaaaaaaaaaaaaaaaaaa");
                    setTitle(s4+" - Notepad+JavaEditor");
					s2=openFile.getPath();
					//	System.out.println(s2+"lllllllllllllllllllllll");
					Path p=Paths.get(s2).getParent();
					s11=p.toString();
					//System.out.println(s11+"hhhhhhhhhhhhhhhhhhhhhhh");
                    Scanner scan = new Scanner(new FileReader(openFile.getPath()));
                    while (scan.hasNext()) 
					{
                        textArea.append(scan.nextLine() + "\n");
                    }

                }
				catch (Exception ex) { 
                    System.err.println(ex.getMessage());
                }
            }
        } 
        else if (e.getSource() == saveFile || e.getSource() == saveButton) 
		{
			if(!s4.equals(""))
			{
				try
				{
				 BufferedWriter out = new BufferedWriter(new FileWriter(s2));
                out.write(textArea.getText());
                out.close();
                edit = false;
				setTitle(s4+" - Notepad+JavaEditor");
				}
				catch(Exception e9)
				{
					System.out.println(e9);
				}				
			}
			else
            saveFile();
        }
		else if(e.getSource() == compileFile || e.getSource() == compileButton)
		{ 
			String result1="";
			if(edit)
			{
				if(!s4.equals(""))
				{
					try
				{
				 BufferedWriter out = new BufferedWriter(new FileWriter(s2));
                out.write(textArea.getText());
                out.close();
                edit = false;
				//	System.out.println(s4+"nnnnnnnnnnnnn");
				setTitle(s4+" - Notepad+JavaEditor");
				
				}
				catch(Exception e9)
				{
					System.out.println(e9);
				}
				}
				else 
				{
					saveFile();
				}
			}
			try
			{				
				jf1.setTitle("Compile("+s4+")");
				
				//jf1.setVisible(true);
				
			Process error=r.exec("C:\\Program Files\\Java\\jdk-18.0.2.1\\bin\\javac.exe "+s4,null,new File(s11));
            BufferedReader err=new BufferedReader(new InputStreamReader(error.getErrorStream()));
            while(true)
           {

              String temp=err.readLine();
              if(temp!=null)
              {
                result1+=temp;
                result1+="\n";
               }
              else break;
              }
			  if(result1.equals(""))
              {
			  //jta3.setText(null);
              jta3.setText("Compilation Successfull:  "+s4);
                err.close();
				jf1.setVisible(true);
                 }
                 else
				 {
                   jta3.setText(result1);
			   jf1.setVisible(true);
				 }
                      }
		
               catch(Exception e1)
             {
				 System.out.println(e1);
              }			
		}
		
		else if (e.getSource() == runFile || e.getSource() == runButton) 
		 {
             String result="";			 
			 if(edit)
			{
				if(!s4.equals(""))
				{
					try
				{
				 BufferedWriter out = new BufferedWriter(new FileWriter(s2));
                out.write(textArea.getText());
                out.close();
                edit = false;
				setTitle(s4+" - Notepad+JavaEditor");				
				}
				catch(Exception e9)
				{
					System.out.println(e9);
				}
				}
				else 
				{
					saveFile();
				}
			}
			
			try
			{
				jf1.setTitle("Execute("+s4+")");
			
				String s8[]=s4.split("[.]",0);
				String s9=s8[0];
				//	System.out.println(s9+"oooooooooooooooooo");
				//	System.out.println(s11+"pppppppppppppppppppp");
				Process p=r.exec("C:\\Program Files\\Java\\jdk-18.0.2.1\\bin\\java.exe "+s9,null,new File(s11));
                BufferedReader output=new BufferedReader(new InputStreamReader(p.getInputStream()));
                BufferedReader error=new BufferedReader(new InputStreamReader(p.getErrorStream()));
                while(true)
                {
                    String temp=output.readLine();
                    if(temp!=null)
                    {
                        result+=temp;
                        result+="\n";
                    }
                    else
                    {
                    break;
                    }
                }
				while(true)
                {
                    String temp=error.readLine();
                    if(temp!=null)
                    {
                         result+=temp;
                         result+="\n";
                    }
                    else
                    {
                        break;
                    }
                }
               output.close();
               error.close();
		
               jta3.setText(result);
			   jf1.setVisible(true);
               }
			   catch(Exception e6)
			   {
				   System.out.println(e6);
			   }				
			}		
        else if (e.getSource() == boldButton) {
            if (textArea.getFont().getStyle() == Font.BOLD) 
			{
				 if (textArea.getFont().getStyle() == Font.ITALIC) 
			     {				
				 textArea.setFont(textArea.getFont().deriveFont(Font.ITALIC));
                 }
                 else
				 {
                  textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN));
				 }					 			 
            }
			else 
			{				
				if (textArea.getFont().getStyle() == Font.ITALIC) 
			     {				
				 textArea.setFont(textArea.getFont().deriveFont(Font.BOLD | Font.ITALIC));
                 }
                  else
				 {
                  textArea.setFont(textArea.getFont().deriveFont(Font.BOLD));
				 }					 
            }
        }
        else if (e.getSource() == italicButton) 
		{
            if (textArea.getFont().getStyle() == Font.ITALIC) 
			{
				
				 if (textArea.getFont().getStyle() == Font.BOLD) 
			     {
				
                 //textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN));
				 textArea.setFont(textArea.getFont().deriveFont(Font.BOLD));
                 }
                 else
				 {
                  textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN));
				 }					 	
            }
			else 
			{
               if (textArea.getFont().getStyle() == Font.BOLD) 
			     {				
               // textArea.setFont(textArea.getFont().deriveFont(Font.BOLD));
				 textArea.setFont(textArea.getFont().deriveFont(Font.BOLD | Font.ITALIC));
                 }
                  else
				 {
                  textArea.setFont(textArea.getFont().deriveFont(Font.ITALIC));
				 }		
            }
        }
        if (e.getSource() == clearFile || e.getSource() == clearButton) 
		{

            Object[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(this, "Are you sure to clear the text Area ?", "Question",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (n == 0) 
			{			
                clearText(textArea);
            }
        }
    }
	private static void clearText(JTextArea textArea1)
	{
        textArea1.setText("");
    }
    private void saveFile() 
	{
        JFileChooser fileChoose = new JFileChooser(s2);
        int option = fileChoose.showSaveDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) 
		{
            try 
			{
                File openFile = fileChoose.getSelectedFile();
				s4=openFile.getName();
                setTitle(s4+" - Notepad+JavaEditor");
                s2=openFile.getPath();
				Path p=Paths.get(s2).getParent();
				s11=p.toString();
                BufferedWriter out = new BufferedWriter(new FileWriter(openFile.getPath()));
                out.write(textArea.getText());
                out.close();
                edit = false;
            } 
			catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }	
 }