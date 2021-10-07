//package DocumentsViews;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;
//import javax.swing.SwingConstants;
//import java.sql.PreparedStatement;
//import DocumentsManager.Livre;
//
//public class PupUpDocument {
//
//	JTextField Titrein,Typein,NomAut1in,NomAut2in,NomAut3in,NomAut4in,NomAut5in,NombreExemplairein,Edeteurin,AneeDaditionin;
//	
//	private Livre l= new Livre();
//	private String[] auteurTab=new String[5];
//	
//	
//	
//	public void creationPup_UpLivre(String typePUP,JFrame framepere) {//,PreparedStatement p
//		JFrame d = new JFrame();
//		d.setForeground(Color.RED);
//		d.setFont(new Font("Dialog", Font.BOLD, 18));
//		d.setBackground(Color.DARK_GRAY);
//		d.setBounds(100, 100, 417, 525);
//		d.setSize(417, 525);
//		d.addWindowListener(new java.awt.event.WindowAdapter() {
//		    @Override
//		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//		        if (
//		        	JOptionPane.showConfirmDialog(d, 
//	            "Voulez-vous vraiment fermer cette fenêtre?", "Fermer la boîte de dialogue?", 
//	            JOptionPane.YES_NO_OPTION,
//		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
//		        framepere.setEnabled(true);
//		        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		        }
//	    }
//		});
//		
//		d.getContentPane().setLayout(null);
//		d.setTitle("Le formulaire :"+typePUP);
//		d.setLocationRelativeTo(null);
//		
//		JLabel labaleTitre = new JLabel("Titre de document :");
//		labaleTitre.setToolTipText("le titre de notre nouvele document");
//		labaleTitre.setHorizontalAlignment(SwingConstants.LEFT);
//		labaleTitre.setForeground(Color.WHITE);
//		labaleTitre.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 13));
//		labaleTitre.setBounds(26, 73, 135, 25);
//		d.getContentPane().add(labaleTitre);
//		
//		JLabel labaleTypedoc = new JLabel("Type de document :");
//		labaleTypedoc.setToolTipText("le titre de notre nouvele document");
//		labaleTypedoc.setHorizontalAlignment(SwingConstants.LEFT);
//		labaleTypedoc.setForeground(Color.WHITE);
//		labaleTypedoc.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 13));
//		labaleTypedoc.setBounds(30, 119, 135, 25);
//		d.getContentPane().add(labaleTypedoc);
//		
//		JLabel labaleNomAuteur1 = new JLabel("Nom d'auteur 1 :");
//		labaleNomAuteur1.setToolTipText("le titre de notre nouvele document");
//		labaleNomAuteur1.setHorizontalAlignment(SwingConstants.LEFT);
//		labaleNomAuteur1.setForeground(Color.WHITE);
//		labaleNomAuteur1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 13));
//		labaleNomAuteur1.setBounds(32, 165, 135, 25);
//		d.getContentPane().add(labaleNomAuteur1);
//		
//		JLabel labalnomAut2 = new JLabel("Nom d'auteur 2 :");
//		labalnomAut2.setToolTipText("le titre de notre nouvele document");
//		labalnomAut2.setHorizontalAlignment(SwingConstants.LEFT);
//		labalnomAut2.setForeground(Color.WHITE);
//		labalnomAut2.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 13));
//		labalnomAut2.setBounds(30, 210, 135, 25);
//		d.getContentPane().add(labalnomAut2);
//		
//		JLabel labalNbreExmp = new JLabel("Nombre exemplaire :");
//		labalNbreExmp.setToolTipText("le titre de notre nouvele document");
//		labalNbreExmp.setHorizontalAlignment(SwingConstants.LEFT);
//		labalNbreExmp.setForeground(Color.WHITE);
//		labalNbreExmp.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 13));
//		labalNbreExmp.setBounds(28, 259, 135, 25);
//		d.getContentPane().add(labalNbreExmp);
//		
//		JLabel labalEditeure = new JLabel("L'edeteur :");
//		labalEditeure.setToolTipText("le titre de notre nouvele document");
//		labalEditeure.setHorizontalAlignment(SwingConstants.LEFT);
//		labalEditeure.setForeground(Color.WHITE);
//		labalEditeure.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 13));
//		labalEditeure.setBounds(31, 302, 135, 25);
//		d.getContentPane().add(labalEditeure);
//		
//		JLabel labalAneeDadition = new JLabel("Ann\u00E9e d\u2019\u00E9dition :");
//		labalAneeDadition.setToolTipText("le titre de notre nouvele document");
//		labalAneeDadition.setHorizontalAlignment(SwingConstants.LEFT);
//		labalAneeDadition.setForeground(Color.WHITE);
//		labalAneeDadition.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 13));
//		labalAneeDadition.setBounds(27, 355, 135, 25);
//		d.getContentPane().add(labalAneeDadition);
//		
//		Titrein = new JTextField();
//		Titrein.setHorizontalAlignment(SwingConstants.LEFT);
//		Titrein.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		Titrein.setColumns(10);
//		Titrein.setBounds(170, 66, 188, 28);
//		d.getContentPane().add(Titrein);
//		
//		Typein = new JTextField();
//		Typein.setHorizontalAlignment(SwingConstants.LEFT);
//		Typein.setColumns(10);
//		Typein.setBounds(170, 112, 188, 28);
//		d.getContentPane().add(Typein);
//		
//		NomAut1in = new JTextField();
//		NomAut1in.setHorizontalAlignment(SwingConstants.LEFT);
//		NomAut1in.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		NomAut1in.setColumns(10);
//		NomAut1in.setBounds(170, 158, 188, 28);
//		d.getContentPane().add(NomAut1in);
//		
//		NomAut2in = new JTextField();
//		NomAut2in.setHorizontalAlignment(SwingConstants.LEFT);
//		NomAut2in.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		NomAut2in.setColumns(10);
//		NomAut2in.setBounds(170, 204, 188, 28);
//		d.getContentPane().add(NomAut2in);
//		
//		NombreExemplairein = new JTextField();
//		NombreExemplairein.setHorizontalAlignment(SwingConstants.LEFT);
//		NombreExemplairein.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		NombreExemplairein.setColumns(10);
//		NombreExemplairein.setBounds(170, 250, 188, 28);
//		d.getContentPane().add(NombreExemplairein);
//		
//		Edeteurin = new JTextField();
//		Edeteurin.setHorizontalAlignment(SwingConstants.LEFT);
//		Edeteurin.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		Edeteurin.setColumns(10);
//		Edeteurin.setBounds(170, 299, 188, 28);
//		d.getContentPane().add(Edeteurin);
//		
//		AneeDaditionin = new JTextField();
//		AneeDaditionin.setHorizontalAlignment(SwingConstants.LEFT);
//		AneeDaditionin.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		AneeDaditionin.setColumns(10);
//		AneeDaditionin.setBounds(170, 348, 188, 28);
//		d.getContentPane().add(AneeDaditionin);
//		
//		JButton btnSave = new JButton("Engestrer");
//		btnSave.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				
//				
//				l.setTitre(Titrein.getText());
//				l.setTypeLivres(Typein.getText());
//				
//				auteurTab[0]= NomAut1in.getText();
//				auteurTab[1]=NomAut2in.getText();
//				
//				l.setAuteur(auteurTab);
//				l.setNbExemplaire(Integer.parseInt(NombreExemplairein.getText()));
//				l.setEditeur(Edeteurin.getText());
//				l.setAnneeAdition(AneeDaditionin.getText());
//				
//				
//				//-->hado mazal ma tzadox 
//				l.setISBN("123-123-1234");
//				l.setNbPages(160);
//				l.setTome("Tome123");
//				
//				
//				
//				
//				
//				
//				if(typePUP.equals("ajoute")) {	
//					SaveAdd();
//					table_load();
//					viderInputTexteFilied();
//					frame.setEnabled(true);
//					d.dispose();
//					JOptionPane.showMessageDialog(null, "Ce document est bien ajouter ");
//				}
//				else {
//					try {
//						//====> khasseni n3ayat hna n3ayat l dak l3iba f paramettres
//						
//						ddddddddddddddddddddddddddd
//						Var_titreLivre.setText(Titre);
//						Var_typeLivre.setText(Type);
//						Var_NombreLivre.setText(Nombreexemplaire);
//						Var_EditeurLivre.setText(Edeteur);
//						Var_AnneeADDLivre.setText(AnneeAdt);
//						Var_AtteurLivre.setText(PrenomAtr+" "+NonAteur);
//						table_load();
//						viderInputTexteFilied();
//						frame.setEnabled(true);
//						d.dispose();
//						JOptionPane.showMessageDialog(null, "Ce document est bien modifier");
//					} catch (SQLException e1) {
//						JOptionPane.showMessageDialog(null, "erreur : la modification n'est pas appliquer !");
//						e1.printStackTrace();
//					}
//					
//					
//				}
//					
//				
//				
//			}
//
//		});
//		
//		btnSave.setForeground(new Color(0, 100, 0));
//		btnSave.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
//		btnSave.setBounds(156, 437, 115, 26);
//		d.getContentPane().add(btnSave);
//		
//		JButton btnCanclle = new JButton("Annuller");
//		btnCanclle.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				viderInputTexteFilied();
//				frame.setEnabled(true);
//				d.dispose();
//			}
//		});
//		btnCanclle.setForeground(new Color(255, 69, 0));
//		btnCanclle.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
//		btnCanclle.setBounds(278, 437, 115, 26);
//		d.getContentPane().add(btnCanclle);
//		
//		JLabel lblNewLab_ = new JLabel("");
//		lblNewLab_.setIcon(new ImageIcon("E:\\photo_PC\\728202.jpg"));
//		lblNewLab_.setBounds(0, 0, 903, 590);
//		d.getContentPane().add(lblNewLab_);
//		
//		frame.setEnabled(false);
//		d.setVisible(true);
//}
//
//}
