package jpaint;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.TransferHandler;
import javax.swing.border.Border;


public class JpaintInterfce extends JFrame{
	private JMenuBar mMenuBar = new JMenuBar();
	
	JMenu mFileMenu = new JMenu("Fichier");
	JMenu mEditMenu = new JMenu("Editer");
	JMenu mHelpMenu = new JMenu("Aide");
	
	
	JMenuItem mNewPaint = new JMenuItem("Nouveau", new ImageIcon("images/document.png"));
	JMenuItem mSavePaint = new JMenuItem("Enregistrer", new ImageIcon("images/save.png"));
	JMenuItem mQuit = new JMenuItem("Quitter", new ImageIcon("images/arrow.png"));
	
	JMenuItem mColorPinceauMenu = new JMenuItem("Couleur");
	JMenuItem mTaillePinceauMenu = new JMenuItem("Taille mPinceauTool");
	JMenuItem mBgColorMenu = new JMenuItem("Couleur de fond");
	
	JMenuItem mAboutMenu = new JMenuItem("A propos",new ImageIcon("images/trending1"));
	
	JToolBar mToolBar = new JToolBar();
	
	JLabel message = new JLabel("Dessins en cours");
	JPanel statusBar = new JPanel();
	
	JButton mPinceauTool = new JButton(new ImageIcon("images/pinceau.png"));
	JButton mPinceauColor = new JButton(new ImageIcon("images/col.png"));
	JButton mGommeTool = new JButton(new ImageIcon("images/gomme2.png"));
	JButton mBgColorTool = new JButton(new ImageIcon("images/bgcolor.png"));
	JButton mNewPaintTool = new JButton(new ImageIcon("images/document_32.png"));
	JButton mOpenFileTool = new JButton(new ImageIcon("images/folder_32.png"));
	JButton mSaveTool = new JButton(new ImageIcon("images/save_32.png"));
	JButton mCrayonTool = new JButton(new ImageIcon("images/pencil_32.png"));
	JButton mTaillePinceauTool = new JButton(new ImageIcon("images/pencil_plus_32.png"));
	
	JButton mGommeDock = new JButton(new ImageIcon("images/gomme2.png"));
	JButton mBgColorDock = new JButton(new ImageIcon("images/bgcolor.png"));
	JButton mPinceauDock = new JButton(new ImageIcon("images/pinceau.png"));
	JButton mCrayonDock = new JButton(new ImageIcon("images/pencil_32.png"));
	JButton mFormeCarreDock = new JButton(new ImageIcon("images/carre_32.png"));
	JButton mFormeRondDock = new JButton(new ImageIcon("images/rond_32.png"));
	JButton mTextDock = new JButton(new ImageIcon("images/text_32.png"));
	JButton mZoomDock = new JButton(new ImageIcon("images/search_32.png"));
	JButton mColorRed = new JButton(new ImageIcon("images/col3.png"));
	JButton mColorBlue = new JButton(new ImageIcon("images/col4.png"));
	JButton mColorBlack = new JButton(new ImageIcon("images/col5.png"));
	JButton mColorViolet = new JButton(new ImageIcon("images/col6.png"));
	JButton mColorWhite = new JButton(new ImageIcon("images/col7.png"));
	JButton mColorGrey = new JButton(new ImageIcon("images/col8.png"));
	JButton mColorYellow = new JButton(new ImageIcon("images/col1.png"));
	JButton mColorGreen = new JButton(new ImageIcon("images/col2.png"));
	
	private ZoneDessin mZoneDessin = new ZoneDessin();	
	private boolean saved = false;
	
	private Border mZoneDessinBorder = BorderFactory.createLineBorder(Color.blue, 3);
	
	JScrollPane scrollPane = new JScrollPane(mZoneDessin);
	JPanel mCentralZone = new JPanel();
	
	JPanel mDock = new JPanel();
	
	Border[] dockBorder = {BorderFactory.createTitledBorder("Outils"), 
			BorderFactory.createTitledBorder("Palette"),
			BorderFactory.createLineBorder(Color.black), 
			BorderFactory.createRaisedBevelBorder()};
	
	JLabel texte;
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	

	public JpaintInterfce() {
		this.setSize(800, 550);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setupMenu();
		this.setupToolBar();
		this.setupStatusBar();
		this.setupDock();
		this.setupZoneDessin();
		this.mZoneDessin.setBorder(mZoneDessinBorder);
		this.mCentralZone.add(scrollPane, BorderLayout.CENTER);
		this.getContentPane().add(mCentralZone, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public void setupZoneDessin()
	{
		/*
		texte = new JLabel("Bonjouuuuuuuurrr!!!");
		this.mZoneDessin.add(texte, BorderLayout.NORTH);
		*/
		texte = new JLabel();
		texte.setTransferHandler(new TransferHandler("text"));
		texte.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				JComponent comp = (JComponent) e.getSource();
				TransferHandler handle = comp.getTransferHandler();
				handle.exportAsDrag(comp, e, TransferHandler.MOVE);
			}	
		});
		
	}

	private void setupDock()
	{
		JPanel toolsDock = new JPanel();
		JPanel colorsDock = new JPanel();
		//JLabel textToolsDock = new JLabel("Outils");
		//JLabel textColorsDock = new JLabel("Palette de couleurs"); 
		
		toolsDock.setLayout(new GridLayout(4,2));
		colorsDock.setLayout(new GridLayout(4,2));
		
		mTextDock.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JpaintInterfce.this.mZoneDessin.add(texte, BorderLayout.CENTER);
				String textInput = JOptionPane.showInputDialog(null, 
						"Entrez votre texte");
				JpaintInterfce.this.texte.setText(textInput);	
			}
		});
		
		mFormeCarreDock.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				FormeDialog formeDialog = new FormeDialog(null, "Ajouter une forme", true);
				formeDialog.showFormeDialog();
			}
		});
		
		mFormeRondDock.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				FormeDialog formeDialog = new FormeDialog(null, "Ajouter une forme", true);
				formeDialog.showFormeDialog();
			}
		});
				
		toolsDock.add(mCrayonDock);
		toolsDock.add(mPinceauDock);
		toolsDock.add(mGommeDock);
		toolsDock.add(mBgColorDock);
		toolsDock.add(mFormeCarreDock);
		toolsDock.add(mFormeRondDock);
		toolsDock.add(mTextDock);
		toolsDock.add(mZoomDock);
		toolsDock.setBorder(dockBorder[0]);	
		
		colorsDock.add(mColorGreen);
		colorsDock.add(mColorBlack);
		colorsDock.add(mColorBlue);
		colorsDock.add(mColorRed);
		colorsDock.add(mColorViolet);
		colorsDock.add(mColorYellow);
		colorsDock.add(mColorWhite);
		colorsDock.add(mColorGrey);
		colorsDock.setBorder(dockBorder[1]);
		
		mDock.setPreferredSize(new Dimension(100, 70));
		mDock.setLayout(new GridLayout(2,1));
		mDock.add(toolsDock, BorderLayout.NORTH);
		mDock.add(colorsDock, BorderLayout.SOUTH);
		mDock.setBorder(dockBorder[2]);
		
		this.getContentPane().add(mDock, BorderLayout.WEST);
	}
	
	
	private void setupStatusBar()
	{
		statusBar.add(this.message, BorderLayout.WEST);
		statusBar.setBorder(dockBorder[3]);
		
		this.getContentPane().add(statusBar, BorderLayout.SOUTH);
	}
	
	
	private void setupMenu()
	{
		mNewPaint.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(saved)
					mZoneDessin.clear();
				else
				{
					int option = JOptionPane.showConfirmDialog(null, "Voulez vous enregistrez les modifications",
							"Enregistrement", JOptionPane.YES_NO_CANCEL_OPTION);
					
					if(option == JOptionPane.OK_OPTION)
					{
						mZoneDessin.clear();
					}
				}	
			}
		});
		
		mSavePaint.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				saved = true;
			}
		});
		
		
		mQuit.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int option = JOptionPane.showConfirmDialog(null, "Voulez vous fermez JPaint?",
						"Quitter", JOptionPane.YES_NO_CANCEL_OPTION);
				
				if(option == JOptionPane.OK_OPTION)
				{
					System.exit(0);
				}				
			}
		});
		
		mNewPaint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		mQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
		mSavePaint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		
		mFileMenu.add(mNewPaint);
		mFileMenu.addSeparator();
		mFileMenu.add(mSavePaint);
		mFileMenu.addSeparator();
		mFileMenu.add(mQuit);
		mFileMenu.setMnemonic('F');
		
		mAboutMenu.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				AboutDialog aboutDialog = new AboutDialog(null, "A propos de JPaint", true);
				aboutDialog.showAboutDialog();
			}
		});
		
		mColorPinceauMenu.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Color newColor = JColorChooser.showDialog(
	                     JpaintInterfce.this,
	                     "Choose Background Color",
	                     Color.red);
				mZoneDessin.setmCouleurPointer(newColor);
			}
		});
		
		
		mEditMenu.add(mColorPinceauMenu);
		mEditMenu.addSeparator();
		mEditMenu.add(mTaillePinceauMenu);
		mEditMenu.addSeparator();
		mEditMenu.add(mBgColorMenu);
		mEditMenu.setMnemonic('E');
		
		mHelpMenu.add(mAboutMenu);
		mHelpMenu.setMnemonic('A');
		
		mMenuBar.add(mFileMenu);
		mMenuBar.add(mEditMenu);
		mMenuBar.add(mHelpMenu);
		
		this.setJMenuBar(mMenuBar);
	}

	private void setupToolBar()
	{
		mPinceauColor.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{	 
				Color newColor = JColorChooser.showDialog(
	                     JpaintInterfce.this,
	                     "Choisissez la couleur du mPinceauTool",
	                     Color.red);
				mZoneDessin.setmCouleurPointer(newColor);
				mZoneDessin.setmEraseMode(false);
			}
		});
		
		mCrayonTool.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Image image = toolkit.getImage("images/pencil_32.png");
				
				 Cursor c = toolkit.createCustomCursor(image , new Point(mZoneDessin.getX(),
					     mZoneDessin.getY()), "img");
				 
				 mZoneDessin.setCursor(c);
				 mZoneDessin.setmPointerTaille(4);
				 //mZoneDessin.setmEraseMode(false);
			}
		});
		
		mPinceauTool.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Image image = toolkit.getImage("images/pinceau.png");
				
				 Cursor c = toolkit.createCustomCursor(image , new Point(mZoneDessin.getX(),
					     mZoneDessin.getY()), "png");
				 
				 mZoneDessin.setCursor(c);
				 mZoneDessin.setmPointerTaille(12);	
				 //mZoneDessin.setmEraseMode(false);
			}
		});
		 
		mTaillePinceauTool.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String[] taillemPinceauToolx = {"2","4","6","8","10","12","14","15"};
				
				String chaineTaille = (String) JOptionPane.showInputDialog (JpaintInterfce.this, "choisissez la taille du Pinceau", "Pinceau",
						JOptionPane.QUESTION_MESSAGE,
						null,taillemPinceauToolx,taillemPinceauToolx[1]);
				int taille = Integer.parseInt(chaineTaille);
				mZoneDessin.setmPointerTaille(taille);
				
				if(taille < 6)
				{
					Image image = toolkit.getImage("images/pencil_32.png");
					
					 Cursor c = toolkit.createCustomCursor(image , new Point(mZoneDessin.getX(),
						     mZoneDessin.getY()), "png");
				}
				else
				{
					Image image = toolkit.getImage("images/pinceau.png");
					
					 Cursor c = toolkit.createCustomCursor(image , new Point(mZoneDessin.getX(),
						     mZoneDessin.getY()), "png");
				}
				mZoneDessin.setmEraseMode(false);
			}
		});
		
		mGommeTool.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Image image = toolkit.getImage("images/gomme2.png");
				
				 Cursor c = toolkit.createCustomCursor(image , new Point(mZoneDessin.getX(),
					     mZoneDessin.getY()), "png");
				 
				mZoneDessin.setCursor(c);
				
				if(mZoneDessin.getmPointerTaille() < 14)
					mZoneDessin.setmPointerTaille(14);
				
				mZoneDessin.setEraseExist(true);
				mZoneDessin.setmEraseMode(true);
			}
		});
		
		mBgColorTool.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Color newColor = JColorChooser.showDialog(
	                     JpaintInterfce.this,
	                     "Choisissez la couleur de fond",
	                     Color.red);
				mZoneDessin.setmCouleurBackground(newColor);
				mZoneDessin.setBgChanging(true);
				mZoneDessin.repaint();
			}
		});
		
		mTaillePinceauTool.setToolTipText("Taille du pinceau");
		mPinceauTool.setToolTipText("Taille du pinceau");
		mPinceauColor.setToolTipText("Coleur du pinceau");
		mGommeTool.setToolTipText("Gomme");
		mBgColorTool.setToolTipText("Couleur de fond");
		
		mToolBar.add(mNewPaintTool);
		mToolBar.add(mOpenFileTool);
		mToolBar.add(mSaveTool);
		mToolBar.addSeparator();
		mToolBar.add(mTaillePinceauTool);
		mToolBar.add(mCrayonTool);
		mToolBar.add(mPinceauTool);
		mToolBar.add(mPinceauColor);
		mToolBar.addSeparator();
		mToolBar.add(mGommeTool);
		mToolBar.add(mBgColorTool);
		
		
		this.getContentPane().add(mToolBar, BorderLayout.NORTH);
	}
	
	
	class AboutDialog extends JDialog
	{
		JLabel logo;
		JButton okButton;
		
		public AboutDialog(JFrame parent, String title, boolean modal)
		{
			super(parent, title, modal);
			this.setSize(450, 300);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			this.setup();
			
		}

		public  void setup() 
		{
			logo = new JLabel(new ImageIcon("images/trending1.jpg"));
			okButton = new JButton("OK");
			JPanel aboutPanel = new JPanel();
			aboutPanel.setBackground(Color.white);
			aboutPanel.setLayout(new BorderLayout());
			aboutPanel.add(logo, BorderLayout.CENTER);
			aboutPanel.add(okButton, BorderLayout.SOUTH);
						
			okButton.addActionListener(new ActionListener() 
			{	
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					dismissAboutDiaolg();
				}
			});
			
			this.getContentPane().add(aboutPanel);
		}
		
		public void showAboutDialog()
		{
			this.setVisible(true);
		}
		
		public void dismissAboutDiaolg()
		{
			this.setVisible(false);
		}
	
	}
	
	
	class FormeDialog extends JDialog
	{
		JLabel widthText;
		JLabel heightText;
		JTextField formeWidth;
		JTextField formeHeight;
		boolean filled = true;
		JButton cancelButton;
		JButton okButton;
		
		public FormeDialog(JFrame parent, String title, boolean modal)
		{
			super(parent, title, modal);
			this.setSize(220,120);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			this.setup();
			
		}

		public  void setup() 
		{
			okButton = new JButton("OK");
			cancelButton = new JButton("Annuler");
			
			formeWidth = new JTextField();
			formeWidth.setPreferredSize(new Dimension(50, 30));
			
			formeHeight = new JTextField();
			formeHeight.setPreferredSize(new Dimension(50, 30));
			
			widthText = new JLabel("Longueur :");
			heightText = new JLabel("Largeur :");
			
			JPanel widthPanel = new JPanel();
			widthPanel.setPreferredSize(new Dimension(300, 30));
			JPanel heightPanel = new JPanel();
			heightPanel.setPreferredSize(new Dimension(300, 30));
			JPanel buttonPanel = new JPanel();
			buttonPanel.setPreferredSize(new Dimension(300, 30));
			
			widthPanel.setLayout(new BorderLayout());
			widthPanel.add(widthText, BorderLayout.WEST);
			widthPanel.add(formeWidth, BorderLayout.CENTER);
			
			heightPanel.setLayout(new BorderLayout());
			heightPanel.add(heightText, BorderLayout.WEST);
			heightPanel.add(formeHeight, BorderLayout.CENTER);
			
			buttonPanel.add(okButton, BorderLayout.WEST);
			buttonPanel.add(cancelButton, BorderLayout.EAST);
			
						
			okButton.addActionListener(new ActionListener() 
			{	
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					mZoneDessin.setFormeAbs(Integer.parseInt(formeWidth.getText()));
					mZoneDessin.setFormeOrd(Integer.parseInt(formeHeight.getText()));
					mZoneDessin.drawFormeCarre(1);
					dismissFormeDiaolg();
				}
			});
			
			cancelButton.addActionListener(new ActionListener() 
			{	
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					dismissFormeDiaolg();
				}
			});
			
			JPanel pan = new JPanel();
			pan.setPreferredSize(new Dimension(200,110));
			pan.setLayout(new GridLayout(3,1));
			pan.add(widthPanel);
			pan.add(heightPanel);
			pan.add(buttonPanel);
			
			this.getContentPane().add(pan);
		}
		
		public void showFormeDialog()
		{
			this.setVisible(true);
		}
		
		public void dismissFormeDiaolg()
		{
			this.setVisible(false);
		}
	
	}


}

