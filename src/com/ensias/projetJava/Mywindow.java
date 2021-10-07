package com.ensias.projetJava;

import java.awt.AlphaComposite;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import DocumentsManager.DocumentManager;
import DocumentsManager.Livre;
import net.proteanit.sql.DbUtils;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableColumn;
import java.awt.Toolkit;
public class Mywindow {
 //Les variable globale :
	private JFrame frame;
	private JScrollPane scrollPane_livres,scrollPane_adhrents,scrollPane_empr,scrollPane_DetailAhd;
	private JTable table;
	private JTextField inputCherche_livre,inputChercheEmp;
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private JPanel panel_principale,panel_info_livre,panel_gestion_livres,panel_page_home, panel_gestion_adherents,panel_detaileAdherent,panel_gestion_Emprunt;
	private String Titre,Type,NonAteur,PrenomAtr,Nombreexemplaire,Edeteur,AnneeAdt,
				    NomAdherent,PrenAdherent,AdresseMail,AdressPressonel,NumeroTelephone,NumeroNational,TypeAdherent;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textFieldNomAdh,textFieldPrenAdh,textFieldAdressPre,textFieldAdrMail,textFieldNumeroTele,textFieldNumeroNational;
	private JButton removeZoneRLivre,RemoveZoneChercheAdh,RemoveZoneChercheEmp;
	
	private JLabel Var_NumeroTele, Var_adressePer, Var_MailAdh,Var_NombreEmp,Var_PrenomAdhrents,Var_CNE,Var_NomADhrent , Var_CodeAdherent;
	  
	private JTable table_adherents;
	private JTextField inputChercheAdh;
	private JTable table_EmpADhDetaile,table_empr;
	private JTable detaile;

	private DocumentManager manager;
	private  int nombreLivres,nombreDictionnaire,nombreMagazine;
	JLabel lblNombreDictionnaire_2;
	JLabel lblNombreDictionnaire;
	JLabel lblNombreDictionnaire_1;
	JLabel lblNombrePeressones;
	JLabel lblNombreEtudiants;
	JLabel lblNombreProfisseurs;
	JLabel Var_titreDocument,Var_NombreDespoDocument,Var_EditeurDocument,Var_AnneeADDocument,Var_AtteurLivre
	   ,Var_AtteurLivre_4, Var_AtteurLivre_3, Var_AtteurLivre_2, Var_AtteurLivre_1, Var_NombreExemplaire, Var_ISBNDocument;
	JLabel lbl_CNE;
	
	private String adhSelectionner,typeAdhSelected,docSelectionner;
	JLabel lbl_V3DEtail, lbl_V1DEtail,lbl_V1DEtailData,lbl_V2DEtail,lbl_V2DEtaildData,lbl_V3DEtailData;
	JLabel lblNombreLivreEmprunter,lblNombreDictionnaireEmprunter,lblNombreMagazineEmprunter;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*-----------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mywindow window = new Mywindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
/*-----------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	
	
	/**
	 * Create the application.
	 */
	public Mywindow() {
		initialize();
		connection();
		TableauxEmprunts("Tous");
		TablauxDocuments("documents");
		TableauxAdherents("Tous");
		//table_loadAdhrentEmp();
		adhSelectionner=new String();
		typeAdhSelected=new String();
		docSelectionner=new String();
		//l'initialisation de nombre des deferents types des documents
		lblNombreDictionnaire_2.setText(""+new DocumentManager().getNombreLivres(con));
		lblNombreDictionnaire.setText(""+new DocumentManager().getNombreDictionnaires(con));
		lblNombreDictionnaire_1.setText(""+new DocumentManager().getNombreMagazine(con));
		//l'initialisation de nombre des deferents  des Adhrents
		lblNombrePeressones.setText(""+new DocumentManager().getNombrePerssonnes(con));
		lblNombreEtudiants.setText(""+new DocumentManager().getNombreEtudiant(con));
		lblNombreProfisseurs.setText(""+new DocumentManager().getNombreProfesseur(con));
		//linitialisation de nombre des documents emprunter 
		lblNombreLivreEmprunter.setText(""+new DocumentManager().getNombreLivreEmp(con));;
		lblNombreDictionnaireEmprunter.setText(""+new DocumentManager().getNombreDictionnaireEmp(con));;
		lblNombreMagazineEmprunter.setText(""+new DocumentManager().getNombreMagazineEmp(con));
		lblNombreLivreEmprunter.setText("2");
		
	}
	
	
	
	
	
	
/*------------------------------------------------- LA CONNECTION A LA BASE DE DONNEE -----------------------------------------------------*/
	
	
	
	//la connection a a base de donnee
		public void connection() {
			
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost/bibliothequeensiasproject", "root", "");
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
				}
				catch(ClassNotFoundException cl){
				  JOptionPane.showMessageDialog(null, " errer de connection ");	
				}
			
		}
		
	
/*-----------------------------  LES METHODES DE REMPLISSAGE DES TABLEAU A PARTIRE DE LA BASE DE DONNEE -----------------------------------*/

	
//==> la remplissage des tableaux :
		
		
		//1#-->tableau des documents 
					public void TablauxDocuments(String type) {

						//type=soit Documents ou Dictionaires ou  Magazines ou Livres
							DocumentManager  doc=new DocumentManager();
//							table.getColumnModel().getColumn(columnIndex).setMinWidth(0);	
							if(type.equals("documents"))
								table.setModel(DbUtils.resultSetToTableModel(doc.getDocuments(con)));
							else if(type.equals("livres"))
								table.setModel(DbUtils.resultSetToTableModel(doc.getLivres(con)));
							else if(type.equals("dictionnaires"))
								table.setModel(DbUtils.resultSetToTableModel(doc.getDictionaires(con)));
							else if(type.equals("magazines"))
								table.setModel(DbUtils.resultSetToTableModel(doc.getMagazines(con)));
								
							table.removeColumn(table.getColumnModel().getColumn(19));
							table.removeColumn(table.getColumnModel().getColumn(18));
							table.removeColumn(table.getColumnModel().getColumn(17));
							table.removeColumn(table.getColumnModel().getColumn(16));
							table.removeColumn(table.getColumnModel().getColumn(15));
							table.removeColumn(table.getColumnModel().getColumn(14));
							table.removeColumn(table.getColumnModel().getColumn(13));
							table.removeColumn(table.getColumnModel().getColumn(12));
							table.removeColumn(table.getColumnModel().getColumn(10));
							table.removeColumn(table.getColumnModel().getColumn(9));
							table.removeColumn(table.getColumnModel().getColumn(8));
							table.removeColumn(table.getColumnModel().getColumn(7));
							table.removeColumn(table.getColumnModel().getColumn(6));
							table.removeColumn(table.getColumnModel().getColumn(0));
							System.out.println("#########"+table.getModel().getRowCount());
							
							nombreLivres=doc.getNombreLivres(con);
							nombreDictionnaire=doc.getNombreDictionnaires(con);
							nombreMagazine=doc.getNombreMagazine(con);
							
							System.out.println("#########"+nombreMagazine);
							scrollPane_livres.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 255), new Color(25, 25, 112)), null));
							if(table.getModel().getRowCount() < 12) {
								scrollPane_livres.setBounds(10, 189, 705, 24+table.getModel().getRowCount()*25);
							}
							else {
								scrollPane_livres.setBounds(10, 189, 705, 314);
							}
					}
		//1#-->tableau des adherents:
					public void TableauxAdherents(String type){
							DocumentManager  doc=new DocumentManager();
							
						if(type.equals("Tous"))
							table_adherents.setModel(DbUtils.resultSetToTableModel(doc.getListe(con)));
						else if(type.equals("La liste des etudiants"))
							table_adherents.setModel(DbUtils.resultSetToTableModel(doc.getEtudiats(con)));
						else if(type.equals("La liste des professeurs"))
							table_adherents.setModel(DbUtils.resultSetToTableModel(doc.getProfesseur(con)));
						else if(type.equals("autre"))
							table_adherents.setModel(DbUtils.resultSetToTableModel(doc.getPersonnes(con)));
						
						table_adherents.removeColumn(table_adherents.getColumnModel().getColumn(8));
						table_adherents.removeColumn(table_adherents.getColumnModel().getColumn(7));
						table_adherents.removeColumn(table_adherents.getColumnModel().getColumn(0));
						//table_adherents.removeColumn(table_adherents.getColumnModel().getColumn(9));
						
						
						
							scrollPane_adhrents.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 255), new Color(25, 25, 112)), null));
							if(table_adherents.getModel().getRowCount() < 12) {
								scrollPane_adhrents.setBounds(10, 189, 705, 24+table_adherents.getModel().getRowCount()*25);
							}
							else {
								scrollPane_adhrents.setBounds(10, 189, 705, 314);
							}
					}		

		//1#-->tableau des emprunts:
					public void TableauxEmprunts(String type){
						//(25, 189, 690, 314)
						  DocumentManager  doc=new DocumentManager();
						
						if(type.equals("Tous"))
							table_empr.setModel(DbUtils.resultSetToTableModel(doc.getDocumentsEmpruntes(con)));
						else if(type.equals("La liste des livres"))
							table_empr.setModel(DbUtils.resultSetToTableModel(doc.getLivresEmpruntes(con)));
						else if(type.equals("La liste des dictionnaires"))
							table_empr.setModel(DbUtils.resultSetToTableModel(doc.getDictionairesEmpruntes(con)));
						else if(type.equals("La liste des magazines"))
							table_empr.setModel(DbUtils.resultSetToTableModel(doc.getMagazinesEmpruntes(con)));

						table_empr.removeColumn(table_empr.getColumnModel().getColumn(8));
						table_empr.removeColumn(table_empr.getColumnModel().getColumn(0));
						
							scrollPane_empr.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 255), new Color(25, 25, 112)), null));
							if(table_empr.getModel().getRowCount() < 12) {
								scrollPane_empr.setBounds(10, 189, 705, 24+table_empr.getModel().getRowCount()*25);
							}
							else {
								scrollPane_empr.setBounds(10, 189, 705, 314);
							}
							
						
					}
		//tableau des document formule ajoute emrunte	
					public void TablauxDocumentsEmpruntes(String type,JTable tb,JScrollPane jscrole) {

							DocumentManager  doc=new DocumentManager();
//							table.getColumnModel().getColumn(columnIndex).setMinWidth(0);	
							if(type.equals("documents"))
								tb.setModel(DbUtils.resultSetToTableModel(doc.getDocumentsFormuleEmp(con)));
							else if(type.equals("livres"))
								tb.setModel(DbUtils.resultSetToTableModel(doc.getLivresFormuleEmp(con)));
							else if(type.equals("dictionnaires"))
								tb.setModel(DbUtils.resultSetToTableModel(doc.getDictionairesEmpruntes(con)));
							else if(type.equals("magazines"))
								tb.setModel(DbUtils.resultSetToTableModel(doc.getMagazinesEmpruntes(con)));
							tb.removeColumn(tb.getColumnModel().getColumn(19));
							tb.removeColumn(tb.getColumnModel().getColumn(18));
							tb.removeColumn(tb.getColumnModel().getColumn(17));
							tb.removeColumn(tb.getColumnModel().getColumn(16));
							tb.removeColumn(tb.getColumnModel().getColumn(15));
							tb.removeColumn(tb.getColumnModel().getColumn(14));
							tb.removeColumn(tb.getColumnModel().getColumn(13));
							tb.removeColumn(tb.getColumnModel().getColumn(12));
							tb.removeColumn(tb.getColumnModel().getColumn(10));
							tb.removeColumn(tb.getColumnModel().getColumn(9));
							tb.removeColumn(tb.getColumnModel().getColumn(8));
							tb.removeColumn(tb.getColumnModel().getColumn(7));
							tb.removeColumn(tb.getColumnModel().getColumn(6));
							tb.removeColumn(tb.getColumnModel().getColumn(5));
							tb.removeColumn(tb.getColumnModel().getColumn(4));
							tb.removeColumn(tb.getColumnModel().getColumn(1));
							tb.removeColumn(tb.getColumnModel().getColumn(0));
							
							
							
							if(tb.getModel().getRowCount() < 12) {
								jscrole.setBounds(10, 126,406, 24+tb.getModel().getRowCount()*25);
							}
							else {
								jscrole.setBounds(10, 126,406, 332);
								
							}
							jscrole.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 255), new Color(25, 25, 112)), null));
					}		
	
					
					
		/*-------- LES TABLEAUX DES PAGES DE DETAILLE --------*/
	
	//2##---->tableau des adherent livre emprunter :
					public void TableauxDocumentAdh(String id) {
						
							DocumentManager  doc=new DocumentManager();
							table_EmpADhDetaile.setModel(DbUtils.resultSetToTableModel(doc.getListeDocumentsADh(con,id)));
							scrollPane_DetailAhd.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 255), new Color(25, 25, 112)), null));
							Var_NombreEmp.setText("  "+table_EmpADhDetaile.getModel().getRowCount());
							if(table_EmpADhDetaile.getModel().getRowCount() < 4) {
								scrollPane_DetailAhd.setBounds(28, 279, 681, 24+table_EmpADhDetaile.getModel().getRowCount()*25);
							}
							else {
								scrollPane_DetailAhd.setBounds(28, 279, 681, 180);
							}
					
				}
	
	
	/*-------------------------------------- AJOUTER DES ELEMENTS A LA BASE DE DONNEE -------------------------------------*/
					
  //#----> AJOUTER UN DOCUMENT A LA BASE DE DONNEE :
					
					public void SaveAdd() {
						try {
							pst=con.prepareStatement("INSERT INTO document( TitreD, typeD, nomauteur, prenomauteur, nbrexemplaire, edeteur, annesAdition) VALUES (?, ?, ?, ?, ?, ?, ?);");
							pst.setString(1,Titre);
							pst.setString(2,Type);
							pst.setString(3,NonAteur);
							pst.setString(4,PrenomAtr);
							pst.setString(5,Nombreexemplaire);
							pst.setString(6,Edeteur);
							pst.setString(7,AnneeAdt);
							pst.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
   //#----> AJOUTER UNE EMPRUNTE A LA BASE DE DONNEE :

   //#----> AJOUTER UN ADHERENT A LA BASE DE DONNEE :
				    public void SaveAddAdherent() {
					try {
						pst=con.prepareStatement("INSERT INTO adadherent( NomAdh, PrenomAdh, AdressePres, mail, tele) VALUES (?, ?, ?, ?, ?);");
						pst.setString(1,NomAdherent);
						pst.setString(2,PrenAdherent);
						pst.setString(3,AdressPressonel);
						pst.setString(4,AdresseMail);
						pst.setString(5,NumeroTelephone);
						//ta yla zadt les autre xhamps dans la base de donnee
						pst.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		

   /*----------------------------------------- VIDER LES TEXTE FIELDE DES PUP UP -------------------------------------------*/
	
	
	//vider LES CHAMPS DE PUP UP DE LIVRE :
				
				private void viderInputTexteFilied() {
					textField_7.setText("");
					textField_8.setText("");
					textField_9.setText("");
					textField_10.setText("");
					textField_11.setText("");
					textField_12.setText("");
					textField_13.setText("");
					textField_7.requestFocus();
				}
	//VIDER LES CHAMPS DE PUP UP DE L'ADHERENT :
				
				public void viderInputTexteFiliedAdherent(){
					textFieldNomAdh.setText("");
					textFieldPrenAdh.setText("");
					textFieldAdressPre.setText("");
					textFieldAdrMail.setText("");
					textFieldNumeroTele.setText("");
					textFieldNumeroNational.setText("");
					//khasse ta select twali l 0 ta wa7da ma khasse tkon mselectionya
				}

		
				
				
				
				
				
				
				
				
				
		//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ La création de la boîte de dialogue de la gestion des Adherents ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\	
				
				
//la formule pour l'ajout d'un personne
				public void FormuleAddPersonne(String type) {
					JFrame d = new JFrame();
					d.setForeground(Color.RED);
					d.setFont(new Font("Dialog", Font.BOLD, 18));
					d.getContentPane().setBackground(new Color(255, 255, 255));
					d.setBounds(100, 100, 417, 525);
					d.setSize(419, 433);
					d.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					        if (
					        	JOptionPane.showConfirmDialog(d, 
				            "Voulez-vous vraiment fermer cette fenêtre?", "Fermer la boîte de dialogue?", 
				            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					        	frame.setEnabled(true);
					        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					        }
				    }
					});
					
					d.getContentPane().setLayout(null);
					d.setTitle("Le formulaire :");
					d.setLocationRelativeTo(null);
					
					
					
					JLabel labalenom = new JLabel("Nom :");
					labalenom.setVerticalAlignment(SwingConstants.BOTTOM);
					labalenom.setToolTipText("le titre de notre nouvele document");
					labalenom.setHorizontalAlignment(SwingConstants.LEFT);
					labalenom.setForeground(Color.BLACK);
					labalenom.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalenom.setBounds(10, 89, 106, 25);
					d.getContentPane().add(labalenom);
					
					JLabel labalemail = new JLabel("Adresse mail");
					labalemail.setVerticalAlignment(SwingConstants.BOTTOM);
					labalemail.setToolTipText("le titre de notre nouvele document");
					labalemail.setHorizontalAlignment(SwingConstants.LEFT);
					labalemail.setForeground(Color.BLACK);
					labalemail.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalemail.setBounds(10, 194, 118, 25);
					d.getContentPane().add(labalemail);
					
					JLabel labalNumero = new JLabel("Numero telephone :");
					labalNumero.setVerticalAlignment(SwingConstants.BOTTOM);
					labalNumero.setToolTipText("le titre de notre nouvele document");
					labalNumero.setHorizontalAlignment(SwingConstants.LEFT);
					labalNumero.setForeground(Color.BLACK);
					labalNumero.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalNumero.setBounds(10, 229, 115, 25);
					d.getContentPane().add(labalNumero);
					
					JLabel labalcode = new JLabel("Code adherent :");
					labalcode.setVerticalAlignment(SwingConstants.BOTTOM);
					labalcode.setToolTipText("le titre de notre nouvele document");
					labalcode.setHorizontalAlignment(SwingConstants.LEFT);
					labalcode.setForeground(Color.BLACK);
					labalcode.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalcode.setBounds(10, 124, 106, 25);
					d.getContentPane().add(labalcode);
					
					JLabel labaladresse = new JLabel("Adresse personnelle :");
					labaladresse.setVerticalAlignment(SwingConstants.BOTTOM);
					labaladresse.setToolTipText("le titre de notre nouvele document");
					labaladresse.setHorizontalAlignment(SwingConstants.LEFT);
					labaladresse.setForeground(Color.BLACK);
					labaladresse.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labaladresse.setBounds(10, 159, 133, 25);
					d.getContentPane().add(labaladresse);
					
					JLabel lblNewLab_ = new JLabel("ajouter   Personne");
					lblNewLab_.setVerticalAlignment(SwingConstants.BOTTOM);
					lblNewLab_.setForeground( new Color(204, 0, 51));
					lblNewLab_.setFont(new Font("Urdu Typesetting", Font.BOLD, 25));
					lblNewLab_.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLab_.setBackground(Color.GRAY);
					lblNewLab_.setBounds(0, 0, 404, 36);
					d.getContentPane().add(lblNewLab_);
					
					JLabel lblIprenom = new JLabel("Prenom :");
					lblIprenom.setVerticalAlignment(SwingConstants.BOTTOM);
					lblIprenom.setToolTipText("le titre de notre nouvele document");
					lblIprenom.setHorizontalAlignment(SwingConstants.LEFT);
					lblIprenom.setForeground(Color.BLACK);
					lblIprenom.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblIprenom.setBounds(10, 54, 106, 25);
					d.getContentPane().add(lblIprenom);
					
					
					
					
					JTextField PrenomPersonne = new JTextField();
					PrenomPersonne.setBackground(Color.GRAY);
					PrenomPersonne.setHorizontalAlignment(SwingConstants.LEFT);
					PrenomPersonne.setFont(new Font("Tahoma", Font.PLAIN, 13));
					PrenomPersonne.setColumns(10);
					PrenomPersonne.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					PrenomPersonne.setBackground(new Color(255, 255,255));
					PrenomPersonne.setBounds(187, 54, 208, 28);
					d.getContentPane().add(PrenomPersonne);
					
					JTextField nomPersonne = new JTextField();
					nomPersonne.setHorizontalAlignment(SwingConstants.LEFT);
					nomPersonne.setFont(new Font("Tahoma", Font.PLAIN, 13));
					nomPersonne.setColumns(10);
					nomPersonne.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					nomPersonne.setBackground(Color.WHITE);
					nomPersonne.setBounds(187, 89, 208, 28);
					d.getContentPane().add(nomPersonne);
					
					JTextField codePersonne = new JTextField();
					codePersonne.setHorizontalAlignment(SwingConstants.LEFT);
					codePersonne.setFont(new Font("Tahoma", Font.PLAIN, 13));
					codePersonne.setColumns(10);
					codePersonne.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					codePersonne.setBackground(Color.WHITE);
					codePersonne.setBounds(187, 124, 208, 28);
					d.getContentPane().add(codePersonne);
					
					JTextField adressePersonne = new JTextField();
					adressePersonne.setHorizontalAlignment(SwingConstants.LEFT);
					adressePersonne.setFont(new Font("Tahoma", Font.PLAIN, 13));
					adressePersonne.setColumns(10);
					adressePersonne.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					adressePersonne.setBackground(Color.WHITE);
					adressePersonne.setBounds(187, 159, 208, 28);
					d.getContentPane().add(adressePersonne);
					
					JTextField mailPersonne = new JTextField();
					mailPersonne.setHorizontalAlignment(SwingConstants.LEFT);
					mailPersonne.setFont(new Font("Tahoma", Font.PLAIN, 13));
					mailPersonne.setColumns(10);
					mailPersonne.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					mailPersonne.setBackground(Color.WHITE);
					mailPersonne.setBounds(187, 194, 208, 28);
					d.getContentPane().add(mailPersonne);
					
					JTextField telePersonne = new JTextField();
					telePersonne.setHorizontalAlignment(SwingConstants.LEFT);
					telePersonne.setFont(new Font("Tahoma", Font.PLAIN, 13));
					telePersonne.setColumns(10);
					telePersonne.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					telePersonne.setBackground(Color.WHITE);
					telePersonne.setBounds(187, 233, 208, 28);
					d.getContentPane().add(telePersonne);
					
					if(!type.equals("ajouter")) {
						nomPersonne.setText(Var_NomADhrent.getText());
						PrenomPersonne.setText(Var_PrenomAdhrents.getText());
						codePersonne.setText(Var_CodeAdherent.getText());
						mailPersonne.setText(Var_MailAdh.getText());
						adressePersonne.setText(Var_adressePer.getText());
						telePersonne.setText(Var_NumeroTele.getText());
					}
					
					
					
					
					
					
					
					JButton btnSavePersonne = new JButton("Engestrer");
					btnSavePersonne.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							if(type.equals("ajouter")) {
								////
								try {
									pst=con.prepareStatement("INSERT INTO personne(Nom, Prenom, Code_Adherent, mail, adresse,telephone,Type,CNE,CNP) VALUES (?, ?, ?, ?, ?,?,?,?,? );");
									pst.setString(1,nomPersonne.getText());
									pst.setString(2,PrenomPersonne.getText());
									pst.setString(3,codePersonne.getText());
									pst.setString(4,mailPersonne.getText());
									pst.setString(5,adressePersonne.getText());
									pst.setString(6,telePersonne.getText());
									pst.setString(7,"personne");
									pst.setString(8," - ");
									pst.setString(9," - ");
									pst.executeUpdate();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								lblNombrePeressones.setText(""+new DocumentManager().getNombrePerssonnes(con));
								
								frame.setEnabled(true);
								d.dispose();
								TableauxAdherents("Tous");
								//viderInputTexteFiliedAdherent();
								JOptionPane.showMessageDialog(null, " bien ajouter ");
								
							}
							else {
								
								try {
																					
									pst=con.prepareStatement("update personne set Nom=?, Prenom=?, Code_Adherent=?, mail=?, adresse=?,telephone=?"
											+ "where id="+adhSelectionner);
									pst.setString(1,nomPersonne.getText());
									pst.setString(2,PrenomPersonne.getText());
									pst.setString(3,codePersonne.getText());
									pst.setString(4,mailPersonne.getText());
									pst.setString(5,adressePersonne.getText());
									pst.setString(6,telePersonne.getText());
									pst.executeUpdate();
									
									
									Var_NomADhrent.setText(nomPersonne.getText());
									Var_PrenomAdhrents.setText(PrenomPersonne.getText());
									Var_CodeAdherent.setText(codePersonne.getText());
									Var_MailAdh.setText(mailPersonne.getText());
									Var_adressePer.setText(adressePersonne.getText());
									Var_NumeroTele.setText(telePersonne.getText());
									TableauxAdherents("Tous");
									
									frame.setEnabled(true);
									d.dispose();
									JOptionPane.showMessageDialog(null, "Les informations de l'adherent est bien modifier");
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(null, "erreur : la modification n'est pas appliquer !");
									e1.printStackTrace();
								}
								
							}
							
							
						}
					});
					
					btnSavePersonne.setForeground(new Color(0, 100, 0));
					btnSavePersonne.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
					btnSavePersonne.setBounds(149, 331, 118, 28);
					d.getContentPane().add(btnSavePersonne);
					
					JButton btnCancllePresonne = new JButton("Annuller");
					btnCancllePresonne.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.setEnabled(true);
							d.dispose();
						}
					});
					btnCancllePresonne.setForeground(new Color(255, 69, 0));
					btnCancllePresonne.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
					btnCancllePresonne.setBounds(277, 331, 118, 28);
					d.getContentPane().add(btnCancllePresonne);
					
					
					
					frame.setEnabled(false);
					d.setVisible(true);
					
				}
//formule ajout d'un etudaiant
				public void FormulAddEtudiant(String type) {
					JFrame d = new JFrame();
					d.getContentPane().setBackground(new Color(255, 255, 255));
					d.setForeground(Color.RED);
					d.setFont(new Font("Dialog", Font.BOLD, 18));
					d.setBackground(Color.DARK_GRAY);
					d.setBounds(100, 100, 417, 525);
					d.setSize(419, 433);
					d.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					        if (
					        	JOptionPane.showConfirmDialog(d, 
				            "Voulez-vous vraiment fermer cette fenêtre?", "Fermer la boîte de dialogue?", 
				            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					        frame.setEnabled(true);
					        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					        }
				    }
					});
					
					d.getContentPane().setLayout(null);
					d.setTitle("Le formulaire :");
					d.setLocationRelativeTo(null);
					
					
					
					JLabel labalenom = new JLabel("Nom :");
					labalenom.setVerticalAlignment(SwingConstants.BOTTOM);
					labalenom.setToolTipText("le titre de notre nouvele document");
					labalenom.setHorizontalAlignment(SwingConstants.LEFT);
					labalenom.setForeground(Color.BLACK);
					labalenom.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalenom.setBounds(10, 89, 106, 25);
					d.getContentPane().add(labalenom);
					
					JLabel labaleadresse = new JLabel("Adresse personnelle :");
					labaleadresse.setVerticalAlignment(SwingConstants.BOTTOM);
					labaleadresse.setToolTipText("le titre de notre nouvele document");
					labaleadresse.setHorizontalAlignment(SwingConstants.LEFT);
					labaleadresse.setForeground(Color.BLACK);
					labaleadresse.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labaleadresse.setBounds(10, 194, 133, 25);
					d.getContentPane().add(labaleadresse);
					
					JLabel labalmail = new JLabel("Adresse mail :");
					labalmail.setVerticalAlignment(SwingConstants.BOTTOM);
					labalmail.setToolTipText("le titre de notre nouvele document");
					labalmail.setHorizontalAlignment(SwingConstants.LEFT);
					labalmail.setForeground(Color.BLACK);
					labalmail.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalmail.setBounds(10, 229, 115, 25);
					d.getContentPane().add(labalmail);
					
					JLabel labalCne = new JLabel("CNE");
					labalCne.setVerticalAlignment(SwingConstants.BOTTOM);
					labalCne.setToolTipText("le titre de notre nouvele document");
					labalCne.setHorizontalAlignment(SwingConstants.LEFT);
					labalCne.setForeground(Color.BLACK);
					labalCne.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalCne.setBounds(10, 124, 106, 25);
					d.getContentPane().add(labalCne);
					
					JLabel labalecode = new JLabel("Code adherent :");
					labalecode.setVerticalAlignment(SwingConstants.BOTTOM);
					labalecode.setToolTipText("le titre de notre nouvele document");
					labalecode.setHorizontalAlignment(SwingConstants.LEFT);
					labalecode.setForeground(Color.BLACK);
					labalecode.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalecode.setBounds(10, 159, 133, 25);
					d.getContentPane().add(labalecode);
					
					JLabel lbl_Etudiant = new JLabel("ajouter   Etudiant");
					lbl_Etudiant.setVerticalAlignment(SwingConstants.BOTTOM);
					lbl_Etudiant.setForeground( new Color(204, 0, 51));
					lbl_Etudiant.setFont(new Font("Urdu Typesetting", Font.BOLD, 25));
					lbl_Etudiant.setHorizontalAlignment(SwingConstants.CENTER);
					lbl_Etudiant.setBackground(Color.GRAY);
					lbl_Etudiant.setBounds(0, 0, 404, 36);
					d.getContentPane().add(lbl_Etudiant);
					
					JLabel lblIprenom = new JLabel("Prenom :");
					lblIprenom.setVerticalAlignment(SwingConstants.BOTTOM);
					lblIprenom.setToolTipText("le titre de notre nouvele document");
					lblIprenom.setHorizontalAlignment(SwingConstants.LEFT);
					lblIprenom.setForeground(Color.BLACK);
					lblIprenom.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblIprenom.setBounds(10, 54, 106, 25);
					d.getContentPane().add(lblIprenom);
					
					
					JTextField PrenomEtudiant = new JTextField();
					PrenomEtudiant.setBackground(Color.GRAY);
					PrenomEtudiant.setHorizontalAlignment(SwingConstants.LEFT);
					PrenomEtudiant.setFont(new Font("Tahoma", Font.PLAIN, 13));
					PrenomEtudiant.setColumns(10);
					PrenomEtudiant.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					PrenomEtudiant.setBackground(new Color(255, 255,255));
					PrenomEtudiant.setBounds(187, 54, 197, 28);
					d.getContentPane().add(PrenomEtudiant);
					
					JTextField nomEtudiant = new JTextField();
					nomEtudiant.setHorizontalAlignment(SwingConstants.LEFT);
					nomEtudiant.setFont(new Font("Tahoma", Font.PLAIN, 13));
					nomEtudiant.setColumns(10);
					nomEtudiant.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					nomEtudiant.setBackground(Color.WHITE);
					nomEtudiant.setBounds(187, 89, 197, 28);
					d.getContentPane().add(nomEtudiant);
					
					JTextField CNEEtudiant = new JTextField();
					CNEEtudiant.setHorizontalAlignment(SwingConstants.LEFT);
					CNEEtudiant.setFont(new Font("Tahoma", Font.PLAIN, 13));
					CNEEtudiant.setColumns(10);
					CNEEtudiant.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					CNEEtudiant.setBackground(Color.WHITE);
					CNEEtudiant.setBounds(187, 124, 197, 28);
					d.getContentPane().add(CNEEtudiant);
					
					JTextField codeEtudiant = new JTextField();
					codeEtudiant.setHorizontalAlignment(SwingConstants.LEFT);
					codeEtudiant.setFont(new Font("Tahoma", Font.PLAIN, 13));
					codeEtudiant.setColumns(10);
					codeEtudiant.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					codeEtudiant.setBackground(Color.WHITE);
					codeEtudiant.setBounds(187, 159, 197, 28);
					d.getContentPane().add(codeEtudiant);
					
					JTextField adresseEtudiant = new JTextField();
					adresseEtudiant.setHorizontalAlignment(SwingConstants.LEFT);
					adresseEtudiant.setFont(new Font("Tahoma", Font.PLAIN, 13));
					adresseEtudiant.setColumns(10);
					adresseEtudiant.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					adresseEtudiant.setBackground(Color.WHITE);
					adresseEtudiant.setBounds(187, 194, 197, 28);
					d.getContentPane().add(adresseEtudiant);
					
					JTextField mailEtudiant = new JTextField();
					mailEtudiant.setHorizontalAlignment(SwingConstants.LEFT);
					mailEtudiant.setFont(new Font("Tahoma", Font.PLAIN, 13));
					mailEtudiant.setColumns(10);
					mailEtudiant.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					mailEtudiant.setBackground(Color.WHITE);
					mailEtudiant.setBounds(187, 229, 197, 28);
					d.getContentPane().add(mailEtudiant);
					
					JLabel lblNumero = new JLabel("Numero telephone :");
					lblNumero.setVerticalAlignment(SwingConstants.BOTTOM);
					lblNumero.setToolTipText("le titre de notre nouvele document");
					lblNumero.setHorizontalAlignment(SwingConstants.LEFT);
					lblNumero.setForeground(Color.BLACK);
					lblNumero.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblNumero.setBounds(10, 264, 115, 25);
					d.getContentPane().add(lblNumero);
					
					JTextField teleEtudiant = new JTextField();
					teleEtudiant.setHorizontalAlignment(SwingConstants.LEFT);
					teleEtudiant.setFont(new Font("Tahoma", Font.PLAIN, 13));
					teleEtudiant.setColumns(10);
					teleEtudiant.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					teleEtudiant.setBackground(Color.WHITE);
					teleEtudiant.setBounds(187, 264, 197, 28);
					d.getContentPane().add(teleEtudiant);
					
					
					
					JButton btnSaveEtudiant = new JButton("Engestrer");
					btnSaveEtudiant.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(type.equals("ajouter")) {
								try {
									
									pst=con.prepareStatement("INSERT INTO personne( Code_Adherent,Nom,Prenom,adresse,telephone,mail,Type,CNE,CNP) VALUES ( ?, ?, ?, ?, ?,?,?,?,?);");
									pst.setString(1,codeEtudiant.getText());
									pst.setString(2,nomEtudiant.getText());
									pst.setString(3,PrenomEtudiant.getText());
									pst.setString(4,adresseEtudiant.getText());
									pst.setString(5,teleEtudiant.getText());
									pst.setString(6,mailEtudiant.getText());
									pst.setString(7,"etudiant");
									pst.setString(8,CNEEtudiant.getText());
									pst.setString(9," - ");
									pst.executeUpdate();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								
								lblNombreEtudiants.setText(""+new DocumentManager().getNombreEtudiant(con));
								
								
								frame.setEnabled(true);
								d.dispose();
								TableauxAdherents("La liste des etudiants");
								//viderInputTexteFiliedAdherent();
								JOptionPane.showMessageDialog(null, "Cette etudiant \" "+PrenomEtudiant.getText()+" "+nomEtudiant.getText()+" \" est bien ajouter ");
							}
							else {
								
							}
							
						}
					});
					
					btnSaveEtudiant.setForeground(new Color(0, 100, 0));
					btnSaveEtudiant.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
					btnSaveEtudiant.setBounds(149, 344, 118, 28);
					d.getContentPane().add(btnSaveEtudiant);
					
					JButton btnCanclleEtudiant = new JButton("Annuller");
					btnCanclleEtudiant.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.setEnabled(true);
							d.dispose();
						}
					});
					btnCanclleEtudiant.setForeground(new Color(255, 69, 0));
					btnCanclleEtudiant.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
					btnCanclleEtudiant.setBounds(277, 344, 118, 28);
					d.getContentPane().add(btnCanclleEtudiant);
					
					
					frame.setEnabled(false);
					d.setVisible(true);
					
				}
//la formule d'ajoute d'un proffesseur
				public void FormulaireAddProfesseur(String type) {
					JFrame d = new JFrame();
					d.getContentPane().setBackground(new Color(255, 255, 255));
					d.setForeground(Color.RED);
					d.setFont(new Font("Dialog", Font.BOLD, 18));
					d.setBackground(Color.DARK_GRAY);
					d.setBounds(100, 100, 417, 525);
					d.setSize(419, 433);
					d.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					        if (
					        	JOptionPane.showConfirmDialog(d, 
				            "Voulez-vous vraiment fermer cette fenêtre?", "Fermer la boîte de dialogue?", 
				            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					        frame.setEnabled(true);
					        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					        }
				    }
					});
					
					d.getContentPane().setLayout(null);
					d.setTitle("Le formulaire :");
					d.setLocationRelativeTo(null);
					
					
					JLabel labalenom = new JLabel("Nom :");
					labalenom.setVerticalAlignment(SwingConstants.BOTTOM);
					labalenom.setToolTipText("le titre de notre nouvele document");
					labalenom.setHorizontalAlignment(SwingConstants.LEFT);
					labalenom.setForeground(Color.BLACK);
					labalenom.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalenom.setBounds(10, 89, 106, 25);
					d.getContentPane().add(labalenom);
					
					JLabel labaleadresse = new JLabel("Adresse personnelle :");
					labaleadresse.setVerticalAlignment(SwingConstants.BOTTOM);
					labaleadresse.setToolTipText("le titre de notre nouvele document");
					labaleadresse.setHorizontalAlignment(SwingConstants.LEFT);
					labaleadresse.setForeground(Color.BLACK);
					labaleadresse.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labaleadresse.setBounds(10, 194, 133, 25);
					d.getContentPane().add(labaleadresse);
					
					JLabel labalmail = new JLabel("Adresse mail :");
					labalmail.setVerticalAlignment(SwingConstants.BOTTOM);
					labalmail.setToolTipText("le titre de notre nouvele document");
					labalmail.setHorizontalAlignment(SwingConstants.LEFT);
					labalmail.setForeground(Color.BLACK);
					labalmail.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalmail.setBounds(10, 229, 115, 25);
					d.getContentPane().add(labalmail);
					
					JLabel labalCne = new JLabel("CNP");
					labalCne.setVerticalAlignment(SwingConstants.BOTTOM);
					labalCne.setToolTipText("le titre de notre nouvele document");
					labalCne.setHorizontalAlignment(SwingConstants.LEFT);
					labalCne.setForeground(Color.BLACK);
					labalCne.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalCne.setBounds(10, 124, 106, 25);
					d.getContentPane().add(labalCne);
					
					JLabel labalecode = new JLabel("Code adherent :");
					labalecode.setVerticalAlignment(SwingConstants.BOTTOM);
					labalecode.setToolTipText("le titre de notre nouvele document");
					labalecode.setHorizontalAlignment(SwingConstants.LEFT);
					labalecode.setForeground(Color.BLACK);
					labalecode.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalecode.setBounds(10, 159, 133, 25);
					d.getContentPane().add(labalecode);
					
					JLabel lbl_Professeur = new JLabel("ajouter   Professeur");
					lbl_Professeur.setVerticalAlignment(SwingConstants.BOTTOM);
					lbl_Professeur.setForeground( new Color(204, 0, 51));
					lbl_Professeur.setFont(new Font("Urdu Typesetting", Font.BOLD, 25));
					lbl_Professeur.setHorizontalAlignment(SwingConstants.CENTER);
					lbl_Professeur.setBackground(Color.GRAY);
					lbl_Professeur.setBounds(0, 0, 404, 36);
					d.getContentPane().add(lbl_Professeur);
					
					JLabel lblIprenom = new JLabel("Prenom :");
					lblIprenom.setVerticalAlignment(SwingConstants.BOTTOM);
					lblIprenom.setToolTipText("le titre de notre nouvele document");
					lblIprenom.setHorizontalAlignment(SwingConstants.LEFT);
					lblIprenom.setForeground(Color.BLACK);
					lblIprenom.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblIprenom.setBounds(10, 54, 106, 25);
					d.getContentPane().add(lblIprenom);
					
					
					JTextField PrenomProfesseur = new JTextField();
					PrenomProfesseur.setBackground(Color.GRAY);
					PrenomProfesseur.setHorizontalAlignment(SwingConstants.LEFT);
					PrenomProfesseur.setFont(new Font("Tahoma", Font.PLAIN, 13));
					PrenomProfesseur.setColumns(10);
					PrenomProfesseur.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					PrenomProfesseur.setBackground(new Color(255, 255,255));
					PrenomProfesseur.setBounds(187, 54, 197, 28);
					d.getContentPane().add(PrenomProfesseur);
					
					JTextField nomProfesseur = new JTextField();
					nomProfesseur.setHorizontalAlignment(SwingConstants.LEFT);
					nomProfesseur.setFont(new Font("Tahoma", Font.PLAIN, 13));
					nomProfesseur.setColumns(10);
					nomProfesseur.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					nomProfesseur.setBackground(Color.WHITE);
					nomProfesseur.setBounds(187, 89, 197, 28);
					d.getContentPane().add(nomProfesseur);
					
					JTextField CNPProfesseur = new JTextField();
					CNPProfesseur.setHorizontalAlignment(SwingConstants.LEFT);
					CNPProfesseur.setFont(new Font("Tahoma", Font.PLAIN, 13));
					CNPProfesseur.setColumns(10);
					CNPProfesseur.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					CNPProfesseur.setBackground(Color.WHITE);
					CNPProfesseur.setBounds(187, 124, 197, 28);
					d.getContentPane().add(CNPProfesseur);
					
					JTextField codeProfesseur = new JTextField();
					codeProfesseur.setHorizontalAlignment(SwingConstants.LEFT);
					codeProfesseur.setFont(new Font("Tahoma", Font.PLAIN, 13));
					codeProfesseur.setColumns(10);
					codeProfesseur.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					codeProfesseur.setBackground(Color.WHITE);
					codeProfesseur.setBounds(187, 159, 197, 28);
					d.getContentPane().add(codeProfesseur);
					
					JTextField adresseProfesseur = new JTextField();
					adresseProfesseur.setHorizontalAlignment(SwingConstants.LEFT);
					adresseProfesseur.setFont(new Font("Tahoma", Font.PLAIN, 13));
					adresseProfesseur.setColumns(10);
					adresseProfesseur.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					adresseProfesseur.setBackground(Color.WHITE);
					adresseProfesseur.setBounds(187, 194, 197, 28);
					d.getContentPane().add(adresseProfesseur);
					
					JTextField mailProfesseur = new JTextField();
					mailProfesseur.setHorizontalAlignment(SwingConstants.LEFT);
					mailProfesseur.setFont(new Font("Tahoma", Font.PLAIN, 13));
					mailProfesseur.setColumns(10);
					mailProfesseur.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					mailProfesseur.setBackground(Color.WHITE);
					mailProfesseur.setBounds(187, 229, 197, 28);
					d.getContentPane().add(mailProfesseur);
					
					
					
					
					JLabel lblNumero = new JLabel("Numero telephone :");
					lblNumero.setVerticalAlignment(SwingConstants.BOTTOM);
					lblNumero.setToolTipText("le titre de notre nouvele document");
					lblNumero.setHorizontalAlignment(SwingConstants.LEFT);
					lblNumero.setForeground(Color.BLACK);
					lblNumero.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblNumero.setBounds(10, 264, 115, 25);
					d.getContentPane().add(lblNumero);
				
					JTextField teleProfesseur = new JTextField();
					teleProfesseur.setHorizontalAlignment(SwingConstants.LEFT);
					teleProfesseur.setFont(new Font("Tahoma", Font.PLAIN, 13));
					teleProfesseur.setColumns(10);
					teleProfesseur.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					teleProfesseur.setBackground(Color.WHITE);
					teleProfesseur.setBounds(187, 264, 197, 28);
					d.getContentPane().add(teleProfesseur);
					
					
					JButton btnSaveProfesseur = new JButton("Engestrer");
					btnSaveProfesseur.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(type.equals("ajouter")) {
								try {
									//teleProfesseur,adresseProfesseur,mailProfesseur,codeProfesseur,CNPProfesseur,nomProfesseur,PrenomProfesseur
																				   
									pst=con.prepareStatement("INSERT INTO personne (Code_Adherent,Nom,Prenom,adresse,telephone,mail,Type,CNE,CNP) VALUES ( ?, ?, ?, ?, ?,?,?,?,? );");
									pst.setString(1,codeProfesseur.getText());
									pst.setString(2,nomProfesseur.getText());
									pst.setString(3,PrenomProfesseur.getText());
									pst.setString(4,adresseProfesseur.getText());
									pst.setString(5,teleProfesseur.getText());
									pst.setString(6,mailProfesseur.getText());
									pst.setString(8,"professeur");
									pst.setString(9," - ");
									pst.setString(7,CNPProfesseur.getText());
									
									pst.executeUpdate();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								lblNombreProfisseurs.setText(""+new DocumentManager().getNombreProfesseur(con));
								
								frame.setEnabled(true);
								d.dispose();
								TableauxAdherents("La liste des professeurs");
								//viderInputTexteFiliedAdherent();
								JOptionPane.showMessageDialog(null, "Cette professeur \" "+PrenomProfesseur.getText()+" "+nomProfesseur.getText()+" \" est bien ajouter ");
								
							}
							}
						}
					);
					btnSaveProfesseur.setForeground(new Color(0, 100, 0));
					btnSaveProfesseur.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
					btnSaveProfesseur.setBounds(149, 344, 118, 28);
					d.getContentPane().add(btnSaveProfesseur);
					
					JButton btnCanclleProfesseur = new JButton("Annuller");
					btnCanclleProfesseur.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.setEnabled(true);
							d.dispose();
						}
					});
					btnCanclleProfesseur.setForeground(new Color(255, 69, 0));
					btnCanclleProfesseur.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
					btnCanclleProfesseur.setBounds(277, 344, 118, 28);
					d.getContentPane().add(btnCanclleProfesseur);
					
					frame.setEnabled(false);
					d.setVisible(true);	
					
				}			
				
			
				
			
				
			//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ La création de la boîte de dialogue de la gestion des empruntes ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\
				
			private void AjouterEmpruntFormule() {
					JTextField rechercheTexte ;
					JFrame d = new JFrame();
					d.getContentPane().setBackground(new Color(255, 255, 255));
					d.setForeground(Color.RED);
					d.setFont(new Font("Dialog", Font.BOLD, 18));
					d.setBackground(Color.DARK_GRAY);
					d.setBounds(100, 100, 417, 525);
					d.setSize(440, 545);
					d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					d.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					        if (
					        	JOptionPane.showConfirmDialog(d, 
					        			"Voulez-vous vraiment fermer cette fenêtre?", "Fermer la boîte de dialogue?", 
					        			JOptionPane.YES_NO_OPTION,
					        			JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					        			frame.setEnabled(true);
					        			d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					        }
					       
					        	
					       
					        
				    }
					});
					
					d.getContentPane().setLayout(null);
					d.setTitle("Le formulaire :");
					d.setLocationRelativeTo(null);
					
					JLabel lbl_Professeur = new JLabel("Selectionnez       un       document");
					lbl_Professeur.setVerticalAlignment(SwingConstants.BOTTOM);
					lbl_Professeur.setForeground( new Color(204, 0, 51));
					lbl_Professeur.setFont(new Font("Urdu Typesetting", Font.BOLD, 25));
					lbl_Professeur.setHorizontalAlignment(SwingConstants.CENTER);
					lbl_Professeur.setBackground(Color.GRAY);
					lbl_Professeur.setBounds(12, 10, 404, 36);
					d.getContentPane().add(lbl_Professeur);
					
					
					
					
					
					JScrollPane scrollPane_Livre_Pup = new JScrollPane();
					scrollPane_Livre_Pup.setBounds(10, 126,406, 332);
					d.getContentPane().add(scrollPane_Livre_Pup);
					
					JTable tableDocumentPup = new JTable();
					tableDocumentPup.setForeground(new Color(0, 0, 0));
					tableDocumentPup.setRowHeight(25);

					TablauxDocumentsEmpruntes("documents",tableDocumentPup,scrollPane_Livre_Pup);
					tableDocumentPup.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							
							DefaultTableModel model=(DefaultTableModel) tableDocumentPup.getModel();
							int selectRowIndex=tableDocumentPup.getSelectedRow();
							
							
							String date= new String();
							date=""+java.time.ZonedDateTime.now();

							System.out.println("====>"+Var_PrenomAdhrents.getText());
							System.out.println("=====>>"+model.getValueAt(selectRowIndex, 11).toString());
						
							try {
								
								pst=con.prepareStatement("INSERT INTO emprunt(idAdh,Prenom,Nom,idDoc,Titre,Type_Document,Date_d_emprunt,nb_Despo) VALUES ( ?, ?, ?, ?, ?,?,?,? );");
								pst.setString(1,adhSelectionner);
								pst.setString(2,Var_PrenomAdhrents.getText());
								pst.setString(3,Var_NomADhrent.getText());
								pst.setString(4,model.getValueAt(selectRowIndex, 0).toString());
								pst.setString(5,model.getValueAt(selectRowIndex, 2).toString());
								pst.setString(6,model.getValueAt(selectRowIndex, 11).toString());
								pst.setString(7,date.substring(0, 10));
								pst.setString(8,model.getValueAt(selectRowIndex, 20).toString());
								pst.executeUpdate();
								
								System.out.println("le nombre desbo"+model.getValueAt(selectRowIndex, 20).toString());
								
								int i=Integer.parseInt(model.getValueAt(selectRowIndex, 20).toString());
								i--;
								
								pst=con.prepareStatement("update document set Nombre_Desponeble=? where id="+model.getValueAt(selectRowIndex, 0).toString());
								pst.setString(1,i+"");
								pst.executeUpdate();
								System.out.println("le nombre desbo"+i);
								
								TablauxDocuments("documents");
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
							TableauxDocumentAdh(adhSelectionner);
							frame.setEnabled(true);
							 d.dispose();
							 JOptionPane.showMessageDialog(null, "Le document sous le titre \" "+model.getValueAt(selectRowIndex, 2).toString()+"\" est bien emprunter");

						}
					});
					scrollPane_Livre_Pup.setViewportView(tableDocumentPup);
					tableDocumentPup.setBackground(new Color(204, 0, 51));
					
					
					JButton btnCancllecheck = new JButton("Annuller");
					btnCancllecheck.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.setEnabled(true);
							d.dispose();
						}
					});
					btnCancllecheck.setForeground(new Color(255, 69, 0));
					btnCancllecheck.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
					btnCancllecheck.setBounds(286, 470, 118, 28);
					d.getContentPane().add(btnCancllecheck);
					
					JButton removeTexte = new JButton("");
					removeTexte.setBounds(359, 69, 25, 26);
					d.getContentPane().add(removeTexte);
					removeTexte.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\remove.png"));
					removeTexte.setVisible(false);
					
					rechercheTexte = new JTextField("  Reherche dans la table");
					rechercheTexte.setForeground(new Color(105, 105, 105));
					rechercheTexte.setFont(new Font("Tahoma", Font.PLAIN, 12));
					rechercheTexte.setColumns(100);
					rechercheTexte.setBorder(new MatteBorder(0, 1, 2, 0, (Color) new Color(220, 20, 60)));
					rechercheTexte.setBackground(Color.WHITE);
					rechercheTexte.setBounds(155, 71, 232, 32);
					rechercheTexte.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent e) {
							rechercheTexte.setText("");
							
						}
					});
					d.getContentPane().add(rechercheTexte);
					
					removeTexte.setOpaque(false);
					removeTexte.setFocusPainted(false);
					removeTexte.setBorderPainted(false);
					removeTexte.setContentAreaFilled(false);
					removeTexte.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							DefaultTableModel dm = (DefaultTableModel)tableDocumentPup.getModel();
							dm.getDataVector().removeAllElements();
							dm.fireTableDataChanged(); // notifies the JTable that the model has changed
							
							//TablauxDocuments("documents");
							removeTexte.setVisible(false);
							rechercheTexte.setText("");
						}
					});
					removeTexte.setFont(new Font("Tahoma", Font.BOLD, 10));
					removeTexte.setBackground(new Color(0,0,0, 0));
					
					
					
					JButton recherchebtn = new JButton("");
					recherchebtn.setBounds(380, 67, 38, 32);
					d.getContentPane().add(recherchebtn);
					recherchebtn.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\search (2).png"));
					recherchebtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(rechercheTexte.getText().length() > 0) {
							DefaultTableModel dm = (DefaultTableModel) tableDocumentPup.getModel();
							dm.getDataVector().removeAllElements();
							dm.fireTableDataChanged();
							String titreChereche=rechercheTexte.getText();
							    //ResultSet rs1=con.prepareStatement("SELECT * FROM document where titre LIKE '%"+titreChereche+"%';").executeQuery();
							//pst.setString(1, titreChereche);
							    //tableDocumentPup.setModel(DbUtils.resultSetToTableModel(rs1));
							removeTexte.setVisible(true);
							scrollPane_Livre_Pup.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 4, new Color(204, 0, 51)));
							if(tableDocumentPup.getModel().getRowCount() < 12) {
								
								scrollPane_Livre_Pup.setBounds(10, 189, 705, 24+tableDocumentPup.getModel().getRowCount()*25);
							}
							else {
								scrollPane_Livre_Pup.setBounds(10, 189, 705, 314);
							}
						}
							
					}
					});
					recherchebtn.setFocusPainted(false);
					recherchebtn.setContentAreaFilled(false);
					recherchebtn.setBorderPainted(false);
					recherchebtn.setFont(new Font("Tahoma", Font.BOLD, 17));
					
					JTextField textField = new JTextField("");
					textField.setForeground(SystemColor.controlDkShadow);
					textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
					textField.setColumns(100);
					textField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(220, 20, 60)));
					textField.setBackground(Color.WHITE);
					textField.setBounds(71, 44, 292, 3);
					d.getContentPane().add(textField);
					
					JComboBox combobox_livres=new JComboBox();
					combobox_livres.setBounds(13, 72, 132, 30);
					d.getContentPane().add(combobox_livres);
					combobox_livres.setFont(new Font("Tahoma", Font.BOLD, 15));
					combobox_livres.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(220, 20, 60)));
					combobox_livres.setForeground(new Color(220, 20, 60));
					combobox_livres.setFocusable(false);
					combobox_livres.setModel(new DefaultComboBoxModel(new String[] {"Tous", "Livres", "Dictionnaires", "Magazines"}));
					combobox_livres.setSelectedIndex(0);
					combobox_livres.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							int selectonier=combobox_livres.getSelectedIndex();//ou 
							String selectonierString=(String) combobox_livres.getSelectedItem();
							if(selectonier==0)
								TablauxDocumentsEmpruntes("documents",tableDocumentPup,scrollPane_Livre_Pup);
							else if(selectonier==1)
								TablauxDocumentsEmpruntes("livres",tableDocumentPup,scrollPane_Livre_Pup);
							else if(selectonier==2)
								TablauxDocumentsEmpruntes("dictionnaires",tableDocumentPup,scrollPane_Livre_Pup);
							else if(selectonier==3)
								TablauxDocumentsEmpruntes("magazines",tableDocumentPup,scrollPane_Livre_Pup);
							
						}
					});
					combobox_livres.setBackground(Color.LIGHT_GRAY);
					
				
				
					frame.setEnabled(false);
					d.setVisible(true);
				}

				
			//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ La création de la boîte de dialogue de la gestion des Documents ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\		
//creation de la formule qui nous appermet d'ajouter un livre
				public void FormulleAjoteLivre() {
					
					JFrame d = new JFrame();
					d.getContentPane().setBackground(new Color(255, 255, 255));
					d.setForeground(Color.RED);
					d.setFont(new Font("Dialog", Font.BOLD, 18));
					d.setBackground(Color.DARK_GRAY);
					d.setBounds(100, 100, 417, 525);
					d.setSize(619, 385);
					d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
					d.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					        if (
					        	JOptionPane.showConfirmDialog(d, 
				            "Voulez-vous vraiment fermer cette fenêtre?", "Fermer la boîte de dialogue?", 
				            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					        frame.setEnabled(true);
					        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					        }
				    }
					});
					
					d.getContentPane().setLayout(null);
					d.setTitle("Le formulaire :");
					d.setLocationRelativeTo(null);
					
					JLabel labaleTitre = new JLabel("Titre :");
					labaleTitre.setVerticalAlignment(SwingConstants.BOTTOM);
					labaleTitre.setToolTipText("le titre de notre nouvele document");
					labaleTitre.setHorizontalAlignment(SwingConstants.LEFT);
					labaleTitre.setForeground(Color.BLACK);
					labaleTitre.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labaleTitre.setBounds(10, 60, 106, 25);
					d.getContentPane().add(labaleTitre);
					
					JLabel labaleTypedoc = new JLabel("Type:");
					labaleTypedoc.setVerticalAlignment(SwingConstants.BOTTOM);
					labaleTypedoc.setToolTipText("le titre de notre nouvele document");
					labaleTypedoc.setHorizontalAlignment(SwingConstants.LEFT);
					labaleTypedoc.setForeground(Color.BLACK);
					labaleTypedoc.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labaleTypedoc.setBounds(10, 150, 118, 25);
					d.getContentPane().add(labaleTypedoc);
					
					JLabel labalPrenomAut = new JLabel("La liste des auteurs :");
					labalPrenomAut.setToolTipText("le titre de notre nouvele document");
					labalPrenomAut.setHorizontalAlignment(SwingConstants.CENTER);
					labalPrenomAut.setForeground(Color.BLACK);
					labalPrenomAut.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 17));
					labalPrenomAut.setBounds(326, 46, 269, 25);
					d.getContentPane().add(labalPrenomAut);
					
					JLabel labalNbreExmp = new JLabel("Tome :");
					labalNbreExmp.setVerticalAlignment(SwingConstants.BOTTOM);
					labalNbreExmp.setToolTipText("le titre de notre nouvele document");
					labalNbreExmp.setHorizontalAlignment(SwingConstants.LEFT);
					labalNbreExmp.setForeground(Color.BLACK);
					labalNbreExmp.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalNbreExmp.setBounds(10, 180, 115, 25);
					d.getContentPane().add(labalNbreExmp);
					
					JLabel labalEditeure = new JLabel("Edeteur :");
					labalEditeure.setVerticalAlignment(SwingConstants.BOTTOM);
					labalEditeure.setToolTipText("le titre de notre nouvele document");
					labalEditeure.setHorizontalAlignment(SwingConstants.LEFT);
					labalEditeure.setForeground(Color.BLACK);
					labalEditeure.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalEditeure.setBounds(10, 90, 106, 25);
					d.getContentPane().add(labalEditeure);
					
					JLabel labalAneeDadition = new JLabel("Ann\u00E9e :");
					labalAneeDadition.setVerticalAlignment(SwingConstants.BOTTOM);
					labalAneeDadition.setToolTipText("le titre de notre nouvele document");
					labalAneeDadition.setHorizontalAlignment(SwingConstants.LEFT);
					labalAneeDadition.setForeground(Color.BLACK);
					labalAneeDadition.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalAneeDadition.setBounds(10, 120, 118, 25);
					d.getContentPane().add(labalAneeDadition);
					
					JLabel lblNewLab_ = new JLabel("ajouter Livre");
					lblNewLab_.setVerticalAlignment(SwingConstants.BOTTOM);
					lblNewLab_.setForeground( new Color(204, 0, 51));
					lblNewLab_.setFont(new Font("Urdu Typesetting", Font.BOLD, 25));
					lblNewLab_.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLab_.setBackground(Color.GRAY);
					lblNewLab_.setBounds(10, 0, 595, 36);
					d.getContentPane().add(lblNewLab_);
					
					JLabel lblIsbn = new JLabel("ISBN :");
					lblIsbn.setVerticalAlignment(SwingConstants.BOTTOM);
					lblIsbn.setToolTipText("le titre de notre nouvele document");
					lblIsbn.setHorizontalAlignment(SwingConstants.LEFT);
					lblIsbn.setForeground(Color.BLACK);
					lblIsbn.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblIsbn.setBounds(10, 30, 106, 25);
					d.getContentPane().add(lblIsbn);
					
					JLabel lblAuteur = new JLabel("Auteur 2 :");
					lblAuteur.setVerticalAlignment(SwingConstants.BOTTOM);
					lblAuteur.setToolTipText("le titre de notre nouvele document");
					lblAuteur.setHorizontalAlignment(SwingConstants.LEFT);
					lblAuteur.setForeground(Color.BLACK);
					lblAuteur.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblAuteur.setBounds(338, 110, 84, 25);
					d.getContentPane().add(lblAuteur);
					
					JLabel lblAuteur_1 = new JLabel("Auteur 3 :");
					lblAuteur_1.setVerticalAlignment(SwingConstants.BOTTOM);
					lblAuteur_1.setToolTipText("le titre de notre nouvele document");
					lblAuteur_1.setHorizontalAlignment(SwingConstants.LEFT);
					lblAuteur_1.setForeground(Color.BLACK);
					lblAuteur_1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblAuteur_1.setBounds(338, 140, 84, 25);
					d.getContentPane().add(lblAuteur_1);
					
					JLabel lblAuteur_2 = new JLabel("Auteur 4 :");
					lblAuteur_2.setVerticalAlignment(SwingConstants.BOTTOM);
					lblAuteur_2.setToolTipText("le titre de notre nouvele document");
					lblAuteur_2.setHorizontalAlignment(SwingConstants.LEFT);
					lblAuteur_2.setForeground(Color.BLACK);
					lblAuteur_2.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblAuteur_2.setBounds(338, 170, 84, 25);
					d.getContentPane().add(lblAuteur_2);
					
					JLabel lblAuteur_3 = new JLabel("Auteur 5 :");
					lblAuteur_3.setVerticalAlignment(SwingConstants.BOTTOM);
					lblAuteur_3.setToolTipText("le titre de notre nouvele document");
					lblAuteur_3.setHorizontalAlignment(SwingConstants.LEFT);
					lblAuteur_3.setForeground(Color.BLACK);
					lblAuteur_3.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblAuteur_3.setBounds(338, 200, 84, 25);
					d.getContentPane().add(lblAuteur_3);
					
					JLabel lblNombrePages = new JLabel("Nombre des copies :");
					lblNombrePages.setVerticalAlignment(SwingConstants.BOTTOM);
					lblNombrePages.setToolTipText("le titre de notre nouvele document");
					lblNombrePages.setHorizontalAlignment(SwingConstants.LEFT);
					lblNombrePages.setForeground(Color.BLACK);
					lblNombrePages.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblNombrePages.setBounds(10, 210, 115, 25);
					d.getContentPane().add(lblNombrePages);
					
					JLabel lblAuteur_5 = new JLabel("Auteur 1:");
					lblAuteur_5.setVerticalAlignment(SwingConstants.BOTTOM);
					lblAuteur_5.setHorizontalAlignment(SwingConstants.LEFT);
					lblAuteur_5.setToolTipText("le titre de notre nouvele document");
					lblAuteur_5.setForeground(Color.BLACK);
					lblAuteur_5.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblAuteur_5.setBounds(338, 80, 84, 25);
					d.getContentPane().add(lblAuteur_5);
					
					JLabel lblNombrePages_1 = new JLabel("Nombre des pages :");
					lblNombrePages_1.setVerticalAlignment(SwingConstants.BOTTOM);
					lblNombrePages_1.setToolTipText("le titre de notre nouvele document");
					lblNombrePages_1.setHorizontalAlignment(SwingConstants.LEFT);
					lblNombrePages_1.setForeground(Color.BLACK);
					lblNombrePages_1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblNombrePages_1.setBounds(10, 240, 115, 25);
					d.getContentPane().add(lblNombrePages_1);
					
					
					
					
					JTextField IsbnLivreInp = new JTextField();
					IsbnLivreInp.setBackground(Color.GRAY);
					IsbnLivreInp.setHorizontalAlignment(SwingConstants.LEFT);
					IsbnLivreInp.setFont(new Font("Tahoma", Font.PLAIN, 13));
					IsbnLivreInp.setColumns(10);
					IsbnLivreInp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					IsbnLivreInp.setBackground(new Color(255, 255,255));
					IsbnLivreInp.setBounds(128, 30, 188, 28);
					d.getContentPane().add(IsbnLivreInp);
					
					JTextField titreLivreInp = new JTextField();
					titreLivreInp.setHorizontalAlignment(SwingConstants.LEFT);
					titreLivreInp.setFont(new Font("Tahoma", Font.PLAIN, 13));
					titreLivreInp.setColumns(10);
					titreLivreInp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					titreLivreInp.setBackground(Color.WHITE);
					titreLivreInp.setBounds(128, 60, 188, 28);
					d.getContentPane().add(titreLivreInp);
					
					JTextField editeurLivreInp = new JTextField();
					editeurLivreInp.setHorizontalAlignment(SwingConstants.LEFT);
					editeurLivreInp.setFont(new Font("Tahoma", Font.PLAIN, 13));
					editeurLivreInp.setColumns(10);
					editeurLivreInp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					editeurLivreInp.setBackground(Color.WHITE);
					editeurLivreInp.setBounds(126, 90, 188, 28);
					d.getContentPane().add(editeurLivreInp);
					
					JTextField anneeLivreInp = new JTextField();
					anneeLivreInp.setHorizontalAlignment(SwingConstants.LEFT);
					anneeLivreInp.setFont(new Font("Tahoma", Font.PLAIN, 13));
					anneeLivreInp.setColumns(10);
					anneeLivreInp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					anneeLivreInp.setBackground(Color.WHITE);
					anneeLivreInp.setBounds(126, 120, 188, 28);
					d.getContentPane().add(anneeLivreInp);
					
					JTextField typeLivreInp = new JTextField();
					typeLivreInp.setHorizontalAlignment(SwingConstants.LEFT);
					typeLivreInp.setFont(new Font("Tahoma", Font.PLAIN, 13));
					typeLivreInp.setColumns(10);
					typeLivreInp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					typeLivreInp.setBackground(Color.WHITE);
					typeLivreInp.setBounds(126, 150, 188, 28);
					d.getContentPane().add(typeLivreInp);
						
					JTextField tomeLivreInp = new JTextField();
					tomeLivreInp.setHorizontalAlignment(SwingConstants.LEFT);
					tomeLivreInp.setFont(new Font("Tahoma", Font.PLAIN, 13));
					tomeLivreInp.setColumns(10);
					tomeLivreInp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					tomeLivreInp.setBackground(Color.WHITE);
					tomeLivreInp.setBounds(126, 180, 188, 28);
					d.getContentPane().add(tomeLivreInp);
					
					JTextField auteur1LivreInp = new JTextField(" ");
					auteur1LivreInp.setHorizontalAlignment(SwingConstants.LEFT);
					auteur1LivreInp.setFont(new Font("Tahoma", Font.PLAIN, 13));
					auteur1LivreInp.setColumns(10);
					auteur1LivreInp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					auteur1LivreInp.setBackground(Color.WHITE);
					auteur1LivreInp.setBounds(405, 80, 188, 28);
					d.getContentPane().add(auteur1LivreInp);
					
					JTextField auteur2LivreInp = new JTextField(" ");
					auteur2LivreInp.setHorizontalAlignment(SwingConstants.LEFT);
					auteur2LivreInp.setFont(new Font("Tahoma", Font.PLAIN, 13));
					auteur2LivreInp.setColumns(10);
					auteur2LivreInp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					auteur2LivreInp.setBackground(Color.WHITE);
					auteur2LivreInp.setBounds(405, 110, 188, 28);
					d.getContentPane().add(auteur2LivreInp);
					
					JTextField nbExemplaireLivreInp = new JTextField();
					nbExemplaireLivreInp.setHorizontalAlignment(SwingConstants.LEFT);
					nbExemplaireLivreInp.setFont(new Font("Tahoma", Font.PLAIN, 13));
					nbExemplaireLivreInp.setColumns(10);
					nbExemplaireLivreInp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					nbExemplaireLivreInp.setBackground(Color.WHITE);
					nbExemplaireLivreInp.setBounds(126, 210, 188, 28);
					d.getContentPane().add(nbExemplaireLivreInp);
					
					
					
					JTextField auteur3LivreInp = new JTextField(" ");
					auteur3LivreInp.setHorizontalAlignment(SwingConstants.LEFT);
					auteur3LivreInp.setFont(new Font("Tahoma", Font.PLAIN, 13));
					auteur3LivreInp.setColumns(10);
					auteur3LivreInp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					auteur3LivreInp.setBackground(Color.WHITE);
					auteur3LivreInp.setBounds(405, 140, 188, 28);
					d.getContentPane().add(auteur3LivreInp);
					
					JTextField auteur4LivreInp = new JTextField(" ");
					auteur4LivreInp.setHorizontalAlignment(SwingConstants.LEFT);
					auteur4LivreInp.setFont(new Font("Tahoma", Font.PLAIN, 13));
					auteur4LivreInp.setColumns(10);
					auteur4LivreInp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					auteur4LivreInp.setBackground(Color.WHITE);
					auteur4LivreInp.setBounds(403, 170, 188, 28);
					d.getContentPane().add(auteur4LivreInp);
					
					JTextField auteur5LivreInp = new JTextField(" ");
					auteur5LivreInp.setHorizontalAlignment(SwingConstants.LEFT);
					auteur5LivreInp.setFont(new Font("Tahoma", Font.PLAIN, 13));
					auteur5LivreInp.setColumns(10);
					auteur5LivreInp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					auteur5LivreInp.setBackground(Color.WHITE);
					auteur5LivreInp.setBounds(403, 200, 188, 28);
					d.getContentPane().add(auteur5LivreInp);
					
					JTextField nbPagesLivreInp = new JTextField();
					nbPagesLivreInp.setHorizontalAlignment(SwingConstants.LEFT);
					nbPagesLivreInp.setFont(new Font("Tahoma", Font.PLAIN, 13));
					nbPagesLivreInp.setColumns(10);
					nbPagesLivreInp.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					nbPagesLivreInp.setBackground(Color.WHITE);
					nbPagesLivreInp.setBounds(126, 240, 188, 28);
					d.getContentPane().add(nbPagesLivreInp);
					
					JTextField SeparateurLivre = new JTextField();
					SeparateurLivre.setHorizontalAlignment(SwingConstants.LEFT);
					SeparateurLivre.setFont(new Font("Tahoma", Font.PLAIN, 13));
					SeparateurLivre.setColumns(10);
					SeparateurLivre.setBorder(new MatteBorder(0, 3, 2, 0, (Color) new Color(204, 0, 51)));
					SeparateurLivre.setBackground(Color.WHITE);
					SeparateurLivre.setBounds(326, 46, 2, 212);
					d.getContentPane().add(SeparateurLivre);
					
					
					
					
					
					
				//l'ajoute	
				JButton btnSaveLivre = new JButton("Engestrer");
					btnSaveLivre.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							try {
//								auteur5Dictionnaire,auteur4Dictionnaire,auteur3Dictionnaire,auteur2Dictionnaire,auteur1Dictionnaire,nbTomesDictionnaire,editeurDictionnaire	
//								LangueDictionnaire,nbExmpDictionnaire,anneeDictionnaire,titreDictionnaire,IsbnDictionnaire
								pst=con.prepareStatement("INSERT INTO document (ISBN,titre,editeur,anneeAdition,nombre_Exemplaire,Nombre_Desponeble,Type_Document,"
										+ "auteur1,auteur2,auteur3,auteur4,auteur5,"
										+"Langue,Nombre_Tomes,"
										+"Nombre_pages,Type,Tome,"
										+ "Periodiciter,mois,jour) "
										+ "VALUES ( ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
								
//								IsbnLivreInp,titreLivreInp,editeurLivreInp,anneeLivreInp,typeLivreInp,auteur4LivreInp,nbPagesLivreInp
//								tomeLivreInp,auteur1LivreInp,auteur2LivreInp,nbExemplaireLivreInp,auteur3LivreInp,auteur5LivreInp
								pst.setString(1,IsbnLivreInp.getText());
								pst.setString(2,titreLivreInp.getText());
								pst.setString(3,editeurLivreInp.getText());
								pst.setString(4,anneeLivreInp.getText());
								pst.setString(5,nbExemplaireLivreInp.getText());
								
								pst.setString(6,nbExemplaireLivreInp.getText());
								pst.setString(7,"livre");
								
								pst.setString(8,auteur1LivreInp.getText());
								pst.setString(9,auteur2LivreInp.getText());
								pst.setString(10,auteur3LivreInp.getText());
								pst.setString(11,auteur4LivreInp.getText());
								pst.setString(12,auteur5LivreInp.getText());
								pst.setString(13," -");
								pst.setString(14," -");
								pst.setString(15,nbPagesLivreInp.getText());
								pst.setString(16,typeLivreInp.getText());
								pst.setString(17,tomeLivreInp.getText());
								pst.setString(18," -");
								pst.setString(19," -");
								pst.setString(20," -");
								
								pst.executeUpdate();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							lblNombreDictionnaire_2.setText(""+new DocumentManager().getNombreLivres(con));
							
							frame.setEnabled(true);
							d.dispose();
							 TablauxDocuments("livres");
							//viderInputTexteFiliedAdherent();
							 
							JOptionPane.showMessageDialog(null, "Ce livre \" "+titreLivreInp.getText()+" \" est bien ajouter ");
						
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							//if(type.equals("ajoute")) {
								 
								
							//}
//							else {
//								try {
//									pst=con.prepareStatement("update document set TitreD=?, typeD=?, nomauteur=?, prenomauteur=?, nbrexemplaire=?, edeteur=?, annesAdition=?"
//											+ "where id=12");
//									//hna khasseni n3raf njib l'id dyal document
//									pst.setString(1,Titre);
//									pst.setString(2,Type);
//									pst.setString(3,NonAteur);
//									pst.setString(4,PrenomAtr);
//									pst.setString(5,Nombreexemplaire);
//									pst.setString(6,Edeteur);
//									pst.setString(7,AnneeAdt);
//									pst.executeUpdate();
//									Var_titreDocument.setText(Titre);
//									Var_typeDocument.setText(Type);
//									Var_NombreDespoDocument.setText(Nombreexemplaire);
//									Var_EditeurDocument.setText(Edeteur);
//									Var_AnneeADDocument.setText(AnneeAdt);
//									Var_AtteurLivre.setText(PrenomAtr+" "+NonAteur);
//									
//									TablauxDocuments("documents");
//									viderInputTexteFilied();
//									frame.setEnabled(true);
//									d.dispose();
//									JOptionPane.showMessageDialog(null, "Ce document est bien modifier");
//								} catch (SQLException e1) {
//									JOptionPane.showMessageDialog(null, "erreur : la modification n'est pas appliquer !");
//									e1.printStackTrace();
//								}}
							
							
						}

					});
					
					btnSaveLivre.setForeground(new Color(0, 100, 0));
					btnSaveLivre.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
					btnSaveLivre.setBounds(326, 290, 115, 26);
					d.getContentPane().add(btnSaveLivre);
					

					JButton btnCanclleLivre = new JButton("Annuller");
					btnCanclleLivre.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							viderInputTexteFilied();
							frame.setEnabled(true);
							d.dispose();
						}
					});
					btnCanclleLivre.setForeground( new Color(204, 0, 51));
					btnCanclleLivre.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
					btnCanclleLivre.setBounds(457, 290, 115, 26);
					d.getContentPane().add(btnCanclleLivre);
					
					
					
					
					frame.setEnabled(false);
					d.setVisible(true);
					
					
				}

//la creation d'une fomuler d'ajout d'un dictionnaire
				public void FormulleAjoteDictionnaire(String type) {
					
					JFrame d = new JFrame();
					d.getContentPane().setBackground(new Color(255, 255, 255));
					d.setForeground(Color.RED);
					d.setFont(new Font("Dialog", Font.BOLD, 18));
					d.setBackground(Color.DARK_GRAY);
					d.setBounds(100, 100, 417, 525);
					d.setSize(619, 366);
					d.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					        if (
					        	JOptionPane.showConfirmDialog(d, 
				            "Voulez-vous vraiment fermer cette fenêtre?", "Fermer la boîte de dialogue?", 
				            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					        frame.setEnabled(true);
					        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					        }
				    }
					});
					
					d.getContentPane().setLayout(null);
					d.setTitle("Le formulaire :");
					d.setLocationRelativeTo(null);
					
					JLabel labaleTitre = new JLabel("Titre :");
					labaleTitre.setVerticalAlignment(SwingConstants.BOTTOM);
					labaleTitre.setToolTipText("le titre de notre nouvele document");
					labaleTitre.setHorizontalAlignment(SwingConstants.LEFT);
					labaleTitre.setForeground(Color.BLACK);
					labaleTitre.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labaleTitre.setBounds(10, 60, 106, 25);
					d.getContentPane().add(labaleTitre);
					
					JLabel labaleTypedoc = new JLabel("Langue:");
					labaleTypedoc.setVerticalAlignment(SwingConstants.BOTTOM);
					labaleTypedoc.setToolTipText("le titre de notre nouvele document");
					labaleTypedoc.setHorizontalAlignment(SwingConstants.LEFT);
					labaleTypedoc.setForeground(Color.BLACK);
					labaleTypedoc.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labaleTypedoc.setBounds(10, 150, 118, 25);
					d.getContentPane().add(labaleTypedoc);
					
					JLabel LblAuteur = new JLabel("La liste des auteurs :");
					LblAuteur.setToolTipText("le titre de notre nouvele document");
					LblAuteur.setHorizontalAlignment(SwingConstants.CENTER);
					LblAuteur.setForeground(Color.BLACK);
					LblAuteur.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 17));
					LblAuteur.setBounds(326, 46, 269, 25);
					d.getContentPane().add(LblAuteur);
					
					JLabel labalNbreExmp = new JLabel("Nombre des tomes :");
					labalNbreExmp.setVerticalAlignment(SwingConstants.BOTTOM);
					labalNbreExmp.setToolTipText("le titre de notre nouvele document");
					labalNbreExmp.setHorizontalAlignment(SwingConstants.LEFT);
					labalNbreExmp.setForeground(Color.BLACK);
					labalNbreExmp.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalNbreExmp.setBounds(10, 180, 115, 25);
					d.getContentPane().add(labalNbreExmp);
					
					JLabel labalEditeure = new JLabel("Edeteur :");
					labalEditeure.setVerticalAlignment(SwingConstants.BOTTOM);
					labalEditeure.setToolTipText("le titre de notre nouvele document");
					labalEditeure.setHorizontalAlignment(SwingConstants.LEFT);
					labalEditeure.setForeground(Color.BLACK);
					labalEditeure.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalEditeure.setBounds(10, 90, 106, 25);
					d.getContentPane().add(labalEditeure);
					
					JLabel labalAneeDadition = new JLabel("Ann\u00E9e :");
					labalAneeDadition.setVerticalAlignment(SwingConstants.BOTTOM);
					labalAneeDadition.setToolTipText("le titre de notre nouvele document");
					labalAneeDadition.setHorizontalAlignment(SwingConstants.LEFT);
					labalAneeDadition.setForeground(Color.BLACK);
					labalAneeDadition.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalAneeDadition.setBounds(10, 120, 118, 25);
					d.getContentPane().add(labalAneeDadition);
					
					JLabel lblNewLab_ = new JLabel("ajouter   Dictionnaire");
					lblNewLab_.setVerticalAlignment(SwingConstants.BOTTOM);
					lblNewLab_.setForeground( new Color(204, 0, 51));
					lblNewLab_.setFont(new Font("Urdu Typesetting", Font.BOLD, 25));
					lblNewLab_.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLab_.setBackground(Color.GRAY);
					lblNewLab_.setBounds(10, 0, 595, 36);
					d.getContentPane().add(lblNewLab_);
					
					JLabel lblIsbn = new JLabel("ISBN :");
					lblIsbn.setVerticalAlignment(SwingConstants.BOTTOM);
					lblIsbn.setToolTipText("le titre de notre nouvele document");
					lblIsbn.setHorizontalAlignment(SwingConstants.LEFT);
					lblIsbn.setForeground(Color.BLACK);
					lblIsbn.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblIsbn.setBounds(10, 30, 106, 25);
					d.getContentPane().add(lblIsbn);
					
					JLabel lblAuteur = new JLabel("Auteur 2 :");
					lblAuteur.setVerticalAlignment(SwingConstants.BOTTOM);
					lblAuteur.setToolTipText("le titre de notre nouvele document");
					lblAuteur.setHorizontalAlignment(SwingConstants.LEFT);
					lblAuteur.setForeground(Color.BLACK);
					lblAuteur.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblAuteur.setBounds(338, 110, 84, 25);
					d.getContentPane().add(lblAuteur);
					
					JLabel lblAuteur_1 = new JLabel("Auteur 3 :");
					lblAuteur_1.setVerticalAlignment(SwingConstants.BOTTOM);
					lblAuteur_1.setToolTipText("le titre de notre nouvele document");
					lblAuteur_1.setHorizontalAlignment(SwingConstants.LEFT);
					lblAuteur_1.setForeground(Color.BLACK);
					lblAuteur_1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblAuteur_1.setBounds(338, 140, 84, 25);
					d.getContentPane().add(lblAuteur_1);
					
					JLabel lblAuteur_2 = new JLabel("Auteur 4 :");
					lblAuteur_2.setVerticalAlignment(SwingConstants.BOTTOM);
					lblAuteur_2.setToolTipText("le titre de notre nouvele document");
					lblAuteur_2.setHorizontalAlignment(SwingConstants.LEFT);
					lblAuteur_2.setForeground(Color.BLACK);
					lblAuteur_2.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblAuteur_2.setBounds(338, 170, 84, 25);
					d.getContentPane().add(lblAuteur_2);
					
					JLabel lblAuteur_3 = new JLabel("Auteur 5 :");
					lblAuteur_3.setVerticalAlignment(SwingConstants.BOTTOM);
					lblAuteur_3.setToolTipText("le titre de notre nouvele document");
					lblAuteur_3.setHorizontalAlignment(SwingConstants.LEFT);
					lblAuteur_3.setForeground(Color.BLACK);
					lblAuteur_3.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblAuteur_3.setBounds(338, 200, 84, 25);
					d.getContentPane().add(lblAuteur_3);
					
					JLabel lblNombrePages = new JLabel("Nombre des copies :");
					lblNombrePages.setVerticalAlignment(SwingConstants.BOTTOM);
					lblNombrePages.setToolTipText("le titre de notre nouvele document");
					lblNombrePages.setHorizontalAlignment(SwingConstants.LEFT);
					lblNombrePages.setForeground(Color.BLACK);
					lblNombrePages.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblNombrePages.setBounds(10, 210, 115, 25);
					d.getContentPane().add(lblNombrePages);
					
					JLabel lblAuteur_5 = new JLabel("Auteur 1:");
					lblAuteur_5.setVerticalAlignment(SwingConstants.BOTTOM);
					lblAuteur_5.setHorizontalAlignment(SwingConstants.LEFT);
					lblAuteur_5.setToolTipText("le titre de notre nouvele document");
					lblAuteur_5.setForeground(Color.BLACK);
					lblAuteur_5.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblAuteur_5.setBounds(338, 80, 84, 25);
					d.getContentPane().add(lblAuteur_5);  
					
					
					
					
					JTextField IsbnDictionnaire = new JTextField();
					IsbnDictionnaire.setBackground(Color.GRAY);
					IsbnDictionnaire.setHorizontalAlignment(SwingConstants.LEFT);
					IsbnDictionnaire.setFont(new Font("Tahoma", Font.PLAIN, 13));
					IsbnDictionnaire.setColumns(10);
					IsbnDictionnaire.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					IsbnDictionnaire.setBackground(new Color(255, 255,255));
					IsbnDictionnaire.setBounds(128, 30, 188, 28);
					d.getContentPane().add(IsbnDictionnaire);
					
					JTextField titreDictionnaire = new JTextField();
					titreDictionnaire.setHorizontalAlignment(SwingConstants.LEFT);
					titreDictionnaire.setFont(new Font("Tahoma", Font.PLAIN, 13));
					titreDictionnaire.setColumns(10);
					titreDictionnaire.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					titreDictionnaire.setBackground(Color.WHITE);
					titreDictionnaire.setBounds(128, 60, 188, 28);
					d.getContentPane().add(titreDictionnaire);
					
					
					JTextField anneeDictionnaire = new JTextField();
					anneeDictionnaire.setHorizontalAlignment(SwingConstants.LEFT);
					anneeDictionnaire.setFont(new Font("Tahoma", Font.PLAIN, 13));
					anneeDictionnaire.setColumns(10);
					anneeDictionnaire.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					anneeDictionnaire.setBackground(Color.WHITE);
					anneeDictionnaire.setBounds(126, 120, 188, 28);
					d.getContentPane().add(anneeDictionnaire);
					
					
					JTextField nbExmpDictionnaire = new JTextField();
					nbExmpDictionnaire.setHorizontalAlignment(SwingConstants.LEFT);
					nbExmpDictionnaire.setFont(new Font("Tahoma", Font.PLAIN, 13));
					nbExmpDictionnaire.setColumns(10);
					nbExmpDictionnaire.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					nbExmpDictionnaire.setBackground(Color.WHITE);
					nbExmpDictionnaire.setBounds(126, 210, 188, 28);
					d.getContentPane().add(nbExmpDictionnaire);
					
					JTextField LangueDictionnaire = new JTextField();
					LangueDictionnaire.setHorizontalAlignment(SwingConstants.LEFT);
					LangueDictionnaire.setFont(new Font("Tahoma", Font.PLAIN, 13));
					LangueDictionnaire.setColumns(10);
					LangueDictionnaire.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					LangueDictionnaire.setBackground(Color.WHITE);
					LangueDictionnaire.setBounds(126, 150, 188, 28);
					d.getContentPane().add(LangueDictionnaire);
					
					JTextField auteur4Dictionnaire = new JTextField(" ");
					auteur4Dictionnaire.setHorizontalAlignment(SwingConstants.LEFT);
					auteur4Dictionnaire.setFont(new Font("Tahoma", Font.PLAIN, 13));
					auteur4Dictionnaire.setColumns(10);
					auteur4Dictionnaire.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					auteur4Dictionnaire.setBackground(Color.WHITE);
					auteur4Dictionnaire.setBounds(403, 170, 188, 28);
					d.getContentPane().add(auteur4Dictionnaire);
					
					JTextField editeurDictionnaire = new JTextField();
					editeurDictionnaire.setHorizontalAlignment(SwingConstants.LEFT);
					editeurDictionnaire.setFont(new Font("Tahoma", Font.PLAIN, 13));
					editeurDictionnaire.setColumns(10);
					editeurDictionnaire.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					editeurDictionnaire.setBackground(Color.WHITE);
					editeurDictionnaire.setBounds(126, 90, 188, 28);
					d.getContentPane().add(editeurDictionnaire);
					
					JTextField nbTomesDictionnaire = new JTextField();
					nbTomesDictionnaire.setHorizontalAlignment(SwingConstants.LEFT);
					nbTomesDictionnaire.setFont(new Font("Tahoma", Font.PLAIN, 13));
					nbTomesDictionnaire.setColumns(10);
					nbTomesDictionnaire.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					nbTomesDictionnaire.setBackground(Color.WHITE);
					nbTomesDictionnaire.setBounds(126, 180, 188, 28);
					d.getContentPane().add(nbTomesDictionnaire);
					
					JTextField auteur1Dictionnaire = new JTextField(" ");
					auteur1Dictionnaire.setHorizontalAlignment(SwingConstants.LEFT);
					auteur1Dictionnaire.setFont(new Font("Tahoma", Font.PLAIN, 13));
					auteur1Dictionnaire.setColumns(10);
					auteur1Dictionnaire.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					auteur1Dictionnaire.setBackground(Color.WHITE);
					auteur1Dictionnaire.setBounds(405, 80, 188, 28);
					d.getContentPane().add(auteur1Dictionnaire);
					
					JTextField auteur2Dictionnaire = new JTextField(" ");
					auteur2Dictionnaire.setHorizontalAlignment(SwingConstants.LEFT);
					auteur2Dictionnaire.setFont(new Font("Tahoma", Font.PLAIN, 13));
					auteur2Dictionnaire.setColumns(10);
					auteur2Dictionnaire.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					auteur2Dictionnaire.setBackground(Color.WHITE);
					auteur2Dictionnaire.setBounds(405, 110, 188, 28);
					d.getContentPane().add(auteur2Dictionnaire);
					
					JTextField SeparateurDictionnaire = new JTextField();
					SeparateurDictionnaire.setHorizontalAlignment(SwingConstants.LEFT);
					SeparateurDictionnaire.setFont(new Font("Tahoma", Font.PLAIN, 13));
					SeparateurDictionnaire.setColumns(10);
					SeparateurDictionnaire.setBorder(new MatteBorder(0, 3, 2, 0, (Color) new Color(204, 0, 51)));
					SeparateurDictionnaire.setBackground(Color.WHITE);
					SeparateurDictionnaire.setBounds(326, 46, 2, 212);
					d.getContentPane().add(SeparateurDictionnaire);
					
					JTextField auteur3Dictionnaire = new JTextField(" ");
					auteur3Dictionnaire.setHorizontalAlignment(SwingConstants.LEFT);
					auteur3Dictionnaire.setFont(new Font("Tahoma", Font.PLAIN, 13));
					auteur3Dictionnaire.setColumns(10);
					auteur3Dictionnaire.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					auteur3Dictionnaire.setBackground(Color.WHITE);
					auteur3Dictionnaire.setBounds(405, 140, 188, 28);
					d.getContentPane().add(auteur3Dictionnaire);
					
					JTextField auteur5Dictionnaire = new JTextField(" ");
					auteur5Dictionnaire.setHorizontalAlignment(SwingConstants.LEFT);
					auteur5Dictionnaire.setFont(new Font("Tahoma", Font.PLAIN, 13));
					auteur5Dictionnaire.setColumns(10);
					auteur5Dictionnaire.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					auteur5Dictionnaire.setBackground(Color.WHITE);
					auteur5Dictionnaire.setBounds(403, 200, 188, 28);
					d.getContentPane().add(auteur5Dictionnaire);
					
					
					JButton btnSaveDictionnaire = new JButton("Engestrer");
					btnSaveDictionnaire.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
//								auteur5Dictionnaire,auteur4Dictionnaire,auteur3Dictionnaire,auteur2Dictionnaire,auteur1Dictionnaire,nbTomesDictionnaire,editeurDictionnaire	
//								LangueDictionnaire,nbExmpDictionnaire,anneeDictionnaire,titreDictionnaire,IsbnDictionnaire
								pst=con.prepareStatement("INSERT INTO document (ISBN,titre,editeur,anneeAdition,nombre_Exemplaire,Nombre_Desponeble,Type_Document,"
										+ "auteur1,auteur2,auteur3,auteur4,auteur5,"
										+"Langue,Nombre_Tomes,"
										+"Nombre_pages,Type,Tome,"
										+ "Periodiciter,mois,jour) "
										+ "VALUES ( ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
								
								pst.setString(1,IsbnDictionnaire.getText());
								pst.setString(2,titreDictionnaire.getText());
								pst.setString(3,editeurDictionnaire.getText());
								pst.setString(4,anneeDictionnaire.getText());
								pst.setString(5,nbExmpDictionnaire.getText());
								pst.setString(6,nbExmpDictionnaire.getText());
								pst.setString(7,"dictionnaire");
								pst.setString(8,auteur1Dictionnaire.getText());
								pst.setString(9,auteur2Dictionnaire.getText());
								pst.setString(10,auteur3Dictionnaire.getText());
								pst.setString(11,auteur4Dictionnaire.getText());
								pst.setString(12,auteur5Dictionnaire.getText());
								pst.setString(13,LangueDictionnaire.getText());
								pst.setString(14,nbTomesDictionnaire.getText());
								
								pst.setString(15," -");
								pst.setString(16," -");
								pst.setString(17," -");
								pst.setString(18," -");
								pst.setString(19," -");
								pst.setString(20," -");
								pst.executeUpdate();
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
							lblNombreDictionnaire.setText(""+new DocumentManager().getNombreDictionnaires(con));
							
							frame.setEnabled(true);
							d.dispose();
							TablauxDocuments("dictionnaires");
							//viderInputTexteFiliedAdherent();
							JOptionPane.showMessageDialog(null, "Ce dictionnaire \" "+titreDictionnaire.getText()+" \" est bien ajouter ");
						
							
//							if(type.equals("ajoute")) {	
//							}
//							else {
//								try {
//									pst=con.prepareStatement("update document set TitreD=?, typeD=?, nomauteur=?, prenomauteur=?, nbrexemplaire=?, edeteur=?, annesAdition=?"
//											+ "where id=12");
//									//hna khasseni n3raf njib l'id dyal document
//									pst.setString(1,Titre);
//									pst.setString(2,Type);
//									pst.setString(3,NonAteur);
//									pst.setString(4,PrenomAtr);
//									pst.setString(5,Nombreexemplaire);
//									pst.setString(6,Edeteur);
//									pst.setString(7,AnneeAdt);
//									pst.executeUpdate();
//									Var_titreDocument.setText(Titre);
//									Var_typeDocument.setText(Type);
//									Var_NombreDespoDocument.setText(Nombreexemplaire);
//									Var_EditeurDocument.setText(Edeteur);
//									Var_AnneeADDocument.setText(AnneeAdt);
//									Var_AtteurLivre.setText(PrenomAtr+" "+NonAteur);
//									
//									TablauxDocuments("documents");
//									viderInputTexteFilied();
//									frame.setEnabled(true);
//									d.dispose();
//									JOptionPane.showMessageDialog(null, "Ce document est bien modifier");
//								} catch (SQLException e1) {
//									JOptionPane.showMessageDialog(null, "erreur : la modification n'est pas appliquer !");
//									e1.printStackTrace();
//								}}
								
							
							
						}

					});
					
					btnSaveDictionnaire.setForeground(new Color(0, 100, 0));
					btnSaveDictionnaire.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
					btnSaveDictionnaire.setBounds(332, 278, 115, 26);
					d.getContentPane().add(btnSaveDictionnaire);
					
					JButton btnCanclleDictionnaire = new JButton("Annuller");
					btnCanclleDictionnaire.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//viderInputTexteFilied();
							frame.setEnabled(true);
							d.dispose();
						}
					});
					btnCanclleDictionnaire.setForeground( new Color(204, 0, 51));
					btnCanclleDictionnaire.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
					btnCanclleDictionnaire.setBounds(457, 278, 115, 26);
					d.getContentPane().add(btnCanclleDictionnaire);
					
					
					
					
					
					frame.setEnabled(false);
					d.setVisible(true);
					
					
				}
				
//la creation d'un formuller d'ajout d'une magazine :
				public void FormulleAjoteMagazine(String type) {
					JFrame d = new JFrame();
					d.getContentPane().setBackground(new Color(255, 255, 255));
					d.setForeground(Color.RED);
					d.setFont(new Font("Dialog", Font.BOLD, 18));
					d.setBackground(Color.DARK_GRAY);
					d.setBounds(100, 100, 417, 525);
					d.setSize(619, 385);
					d.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					        if (
					        	JOptionPane.showConfirmDialog(d, 
				            "Voulez-vous vraiment fermer cette fenêtre?", "Fermer la boîte de dialogue?", 
				            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					        frame.setEnabled(true);
					        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					        }
				    }
					});
					
					d.getContentPane().setLayout(null);
					d.setTitle("Le formulaire :");
					d.setLocationRelativeTo(null);
					
					
					JLabel labaleTitre = new JLabel("Titre :");
					labaleTitre.setVerticalAlignment(SwingConstants.BOTTOM);
					labaleTitre.setToolTipText("le titre de notre nouvele document");
					labaleTitre.setHorizontalAlignment(SwingConstants.LEFT);
					labaleTitre.setForeground(Color.BLACK);
					labaleTitre.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labaleTitre.setBounds(10, 60, 106, 25);
					d.getContentPane().add(labaleTitre);
					
					JLabel labaleTypedoc = new JLabel("Periodicite :");
					labaleTypedoc.setVerticalAlignment(SwingConstants.BOTTOM);
					labaleTypedoc.setToolTipText("le titre de notre nouvele document");
					labaleTypedoc.setHorizontalAlignment(SwingConstants.LEFT);
					labaleTypedoc.setForeground(Color.BLACK);
					labaleTypedoc.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labaleTypedoc.setBounds(10, 150, 118, 25);
					d.getContentPane().add(labaleTypedoc);
					
					JLabel labalPrenomAut = new JLabel("La liste des auteurs :");
					labalPrenomAut.setToolTipText("le titre de notre nouvele document");
					labalPrenomAut.setHorizontalAlignment(SwingConstants.CENTER);
					labalPrenomAut.setForeground(Color.BLACK);
					labalPrenomAut.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 17));
					labalPrenomAut.setBounds(326, 46, 269, 25);
					d.getContentPane().add(labalPrenomAut);
					
					JLabel labalNbreExmp = new JLabel("Mois d\u2019\u00E9dition :");
					labalNbreExmp.setVerticalAlignment(SwingConstants.BOTTOM);
					labalNbreExmp.setToolTipText("le titre de notre nouvele document");
					labalNbreExmp.setHorizontalAlignment(SwingConstants.LEFT);
					labalNbreExmp.setForeground(Color.BLACK);
					labalNbreExmp.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalNbreExmp.setBounds(10, 180, 115, 25);
					d.getContentPane().add(labalNbreExmp);
					
					JLabel labalEditeure = new JLabel("Edeteur :");
					labalEditeure.setVerticalAlignment(SwingConstants.BOTTOM);
					labalEditeure.setToolTipText("le titre de notre nouvele document");
					labalEditeure.setHorizontalAlignment(SwingConstants.LEFT);
					labalEditeure.setForeground(Color.BLACK);
					labalEditeure.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalEditeure.setBounds(10, 90, 106, 25);
					d.getContentPane().add(labalEditeure);
					
					JLabel labalAneeDadition = new JLabel("Ann\u00E9e :");
					labalAneeDadition.setVerticalAlignment(SwingConstants.BOTTOM);
					labalAneeDadition.setToolTipText("le titre de notre nouvele document");
					labalAneeDadition.setHorizontalAlignment(SwingConstants.LEFT);
					labalAneeDadition.setForeground(Color.BLACK);
					labalAneeDadition.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					labalAneeDadition.setBounds(10, 120, 118, 25);
					d.getContentPane().add(labalAneeDadition);
					

					JLabel lblNewLab_ = new JLabel("ajouter    Magazine");
					lblNewLab_.setVerticalAlignment(SwingConstants.BOTTOM);
					lblNewLab_.setForeground( new Color(204, 0, 51));
					lblNewLab_.setFont(new Font("Urdu Typesetting", Font.BOLD, 25));
					lblNewLab_.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLab_.setBackground(Color.GRAY);
					lblNewLab_.setBounds(10, 0, 595, 36);
					d.getContentPane().add(lblNewLab_);
					
					JLabel lblIsbn = new JLabel("ISBN :");
					lblIsbn.setVerticalAlignment(SwingConstants.BOTTOM);
					lblIsbn.setToolTipText("le titre de notre nouvele document");
					lblIsbn.setHorizontalAlignment(SwingConstants.LEFT);
					lblIsbn.setForeground(Color.BLACK);
					lblIsbn.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblIsbn.setBounds(10, 30, 106, 25);
					d.getContentPane().add(lblIsbn);
					
					JLabel lblAuteur = new JLabel("Auteur 2 :");
					lblAuteur.setVerticalAlignment(SwingConstants.BOTTOM);
					lblAuteur.setToolTipText("le titre de notre nouvele document");
					lblAuteur.setHorizontalAlignment(SwingConstants.LEFT);
					lblAuteur.setForeground(Color.BLACK);
					lblAuteur.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblAuteur.setBounds(338, 110, 84, 25);
					d.getContentPane().add(lblAuteur);
					
					JLabel lblAuteur_1 = new JLabel("Auteur 3 :");
					lblAuteur_1.setVerticalAlignment(SwingConstants.BOTTOM);
					lblAuteur_1.setToolTipText("le titre de notre nouvele document");
					lblAuteur_1.setHorizontalAlignment(SwingConstants.LEFT);
					lblAuteur_1.setForeground(Color.BLACK);
					lblAuteur_1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblAuteur_1.setBounds(338, 140, 84, 25);
					d.getContentPane().add(lblAuteur_1);
					
					JLabel lblAuteur_2 = new JLabel("Auteur 4 :");
					lblAuteur_2.setVerticalAlignment(SwingConstants.BOTTOM);
					lblAuteur_2.setToolTipText("le titre de notre nouvele document");
					lblAuteur_2.setHorizontalAlignment(SwingConstants.LEFT);
					lblAuteur_2.setForeground(Color.BLACK);
					lblAuteur_2.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblAuteur_2.setBounds(338, 170, 84, 25);
					d.getContentPane().add(lblAuteur_2);
					
					JLabel lblAuteur_3 = new JLabel("Auteur 5 :");
					lblAuteur_3.setVerticalAlignment(SwingConstants.BOTTOM);
					lblAuteur_3.setToolTipText("le titre de notre nouvele document");
					lblAuteur_3.setHorizontalAlignment(SwingConstants.LEFT);
					lblAuteur_3.setForeground(Color.BLACK);
					lblAuteur_3.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblAuteur_3.setBounds(338, 200, 84, 25);
					d.getContentPane().add(lblAuteur_3);
					 
					JLabel lblNombrePages = new JLabel("Jour d\u2019\u00E9dition :");
					lblNombrePages.setVerticalAlignment(SwingConstants.BOTTOM);
					lblNombrePages.setToolTipText("le titre de notre nouvele document");
					lblNombrePages.setHorizontalAlignment(SwingConstants.LEFT);
					lblNombrePages.setForeground(Color.BLACK);
					lblNombrePages.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblNombrePages.setBounds(10, 210, 115, 25);
					d.getContentPane().add(lblNombrePages);
					
					JLabel lblAuteur_5 = new JLabel("Auteur 1:");
					lblAuteur_5.setVerticalAlignment(SwingConstants.BOTTOM);
					lblAuteur_5.setHorizontalAlignment(SwingConstants.LEFT);
					lblAuteur_5.setToolTipText("le titre de notre nouvele document");
					lblAuteur_5.setForeground(Color.BLACK);
					lblAuteur_5.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblAuteur_5.setBounds(338, 80, 84, 25);
					d.getContentPane().add(lblAuteur_5);
					
					
					
					JLabel lblNombrePages_1 = new JLabel("Nombre des copies :");
					lblNombrePages_1.setVerticalAlignment(SwingConstants.BOTTOM);
					lblNombrePages_1.setToolTipText("le titre de notre nouvele document");
					lblNombrePages_1.setHorizontalAlignment(SwingConstants.LEFT);
					lblNombrePages_1.setForeground(Color.BLACK);
					lblNombrePages_1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
					lblNombrePages_1.setBounds(10, 240, 115, 25);
					d.getContentPane().add(lblNombrePages_1);
				
					
					JTextField IsbnMagazine = new JTextField();
					IsbnMagazine.setBackground(Color.GRAY);
					IsbnMagazine.setHorizontalAlignment(SwingConstants.LEFT);
					IsbnMagazine.setFont(new Font("Tahoma", Font.PLAIN, 13));
					IsbnMagazine.setColumns(10);
					IsbnMagazine.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					IsbnMagazine.setBackground(new Color(255, 255,255));
					IsbnMagazine.setBounds(128, 30, 188, 28);
					d.getContentPane().add(IsbnMagazine);
					
					JTextField titreMagazine = new JTextField();
					titreMagazine.setHorizontalAlignment(SwingConstants.LEFT);
					titreMagazine.setFont(new Font("Tahoma", Font.PLAIN, 13));
					titreMagazine.setColumns(10);
					titreMagazine.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					titreMagazine.setBackground(Color.WHITE);
					titreMagazine.setBounds(128, 60, 188, 28);
					d.getContentPane().add(titreMagazine);
					
					
					
					JTextField anneeMagazine = new JTextField();
					anneeMagazine.setHorizontalAlignment(SwingConstants.LEFT);
					anneeMagazine.setFont(new Font("Tahoma", Font.PLAIN, 13));
					anneeMagazine.setColumns(10);
					anneeMagazine.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					anneeMagazine.setBackground(Color.WHITE);
					anneeMagazine.setBounds(126, 120, 188, 28);
					d.getContentPane().add(anneeMagazine);
					
					
					JTextField jourMagazine = new JTextField();
					jourMagazine.setHorizontalAlignment(SwingConstants.LEFT);
					jourMagazine.setFont(new Font("Tahoma", Font.PLAIN, 13));
					jourMagazine.setColumns(10);
					jourMagazine.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					jourMagazine.setBackground(Color.WHITE);
					jourMagazine.setBounds(126, 210, 188, 28);
					d.getContentPane().add(jourMagazine);
					
					JTextField PeriodiciteMagazine = new JTextField();
					PeriodiciteMagazine.setHorizontalAlignment(SwingConstants.LEFT);
					PeriodiciteMagazine.setFont(new Font("Tahoma", Font.PLAIN, 13));
					PeriodiciteMagazine.setColumns(10);
					PeriodiciteMagazine.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					PeriodiciteMagazine.setBackground(Color.WHITE);
					PeriodiciteMagazine.setBounds(126, 150, 188, 28);
					d.getContentPane().add(PeriodiciteMagazine);
					
					JTextField auteur4Magazine = new JTextField(" ");
					auteur4Magazine.setHorizontalAlignment(SwingConstants.LEFT);
					auteur4Magazine.setFont(new Font("Tahoma", Font.PLAIN, 13));
					auteur4Magazine.setColumns(10);
					auteur4Magazine.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					auteur4Magazine.setBackground(Color.WHITE);
					auteur4Magazine.setBounds(403, 170, 188, 28);
					d.getContentPane().add(auteur4Magazine);
					
					
					
					JTextField editeurMagazine = new JTextField();
					editeurMagazine.setHorizontalAlignment(SwingConstants.LEFT);
					editeurMagazine.setFont(new Font("Tahoma", Font.PLAIN, 13));
					editeurMagazine.setColumns(10);
					editeurMagazine.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					editeurMagazine.setBackground(Color.WHITE);
					editeurMagazine.setBounds(126, 90, 188, 28);
					d.getContentPane().add(editeurMagazine);
					
					JTextField moisMagazine = new JTextField();
					moisMagazine.setHorizontalAlignment(SwingConstants.LEFT);
					moisMagazine.setFont(new Font("Tahoma", Font.PLAIN, 13));
					moisMagazine.setColumns(10);
					moisMagazine.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					moisMagazine.setBackground(Color.WHITE);
					moisMagazine.setBounds(126, 180, 188, 28);
					d.getContentPane().add(moisMagazine);
					
					
					JTextField auteur1Magazine = new JTextField();
					auteur1Magazine.setHorizontalAlignment(SwingConstants.LEFT);
					auteur1Magazine.setFont(new Font("Tahoma", Font.PLAIN, 13));
					auteur1Magazine.setColumns(10);
					auteur1Magazine.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					auteur1Magazine.setBackground(Color.WHITE);
					auteur1Magazine.setBounds(405, 80, 188, 28);
					d.getContentPane().add(auteur1Magazine);
					
					
					JTextField auteur2Magazine = new JTextField(" ");
					auteur2Magazine.setHorizontalAlignment(SwingConstants.LEFT);
					auteur2Magazine.setFont(new Font("Tahoma", Font.PLAIN, 13));
					auteur2Magazine.setColumns(10);
					auteur2Magazine.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					auteur2Magazine.setBackground(Color.WHITE);
					auteur2Magazine.setBounds(405, 110, 188, 28);
					d.getContentPane().add(auteur2Magazine);
					
					
					
					textField_12 = new JTextField();
					textField_12.setHorizontalAlignment(SwingConstants.LEFT);
					textField_12.setFont(new Font("Tahoma", Font.PLAIN, 13));
					textField_12.setColumns(10);
					textField_12.setBorder(new MatteBorder(0, 3, 2, 0, (Color) new Color(204, 0, 51)));
					textField_12.setBackground(Color.WHITE);
					textField_12.setBounds(326, 46, 2, 212);
					d.getContentPane().add(textField_12);
					
					JTextField NbExepMagazine = new JTextField();
					NbExepMagazine.setHorizontalAlignment(SwingConstants.LEFT);
					NbExepMagazine.setFont(new Font("Tahoma", Font.PLAIN, 13));
					NbExepMagazine.setColumns(10);
					NbExepMagazine.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					NbExepMagazine.setBackground(Color.WHITE);
					NbExepMagazine.setBounds(126, 240, 188, 28);
					d.getContentPane().add(NbExepMagazine);
					
					JTextField auteur3Magazine = new JTextField(" ");
					auteur3Magazine.setHorizontalAlignment(SwingConstants.LEFT);
					auteur3Magazine.setFont(new Font("Tahoma", Font.PLAIN, 13));
					auteur3Magazine.setColumns(10);
					auteur3Magazine.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					auteur3Magazine.setBackground(Color.WHITE);
					auteur3Magazine.setBounds(405, 140, 188, 28);
					d.getContentPane().add(auteur3Magazine);
					
					JTextField auteur5Magazine = new JTextField(" ");
					auteur5Magazine.setHorizontalAlignment(SwingConstants.LEFT);
					auteur5Magazine.setFont(new Font("Tahoma", Font.PLAIN, 13));
					auteur5Magazine.setColumns(10);
					auteur5Magazine.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(204, 0, 51)));
					auteur5Magazine.setBackground(Color.WHITE);
					auteur5Magazine.setBounds(403, 200, 188, 28);
					d.getContentPane().add(auteur5Magazine);
					
					
					JButton btnSaveMagazine = new JButton("Engestrer");
					btnSaveMagazine.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							try {
								pst=con.prepareStatement("INSERT INTO document (ISBN,titre,editeur,anneeAdition,nombre_Exemplaire,Nombre_Desponeble,Type_Document,"
														+ "auteur1,auteur2,auteur3,auteur4,auteur5,"
														+"Langue,Nombre_Tomes,"
														+"Nombre_pages,Type,Tome,"
														+ "Periodiciter,mois,jour) "
														+ "VALUES ( ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

								
								pst.setString(1,IsbnMagazine.getText());
								pst.setString(2,titreMagazine.getText());
								pst.setString(3,editeurMagazine.getText());
								pst.setString(4,anneeMagazine.getText());
								pst.setString(5,NbExepMagazine.getText());
								pst.setString(6,NbExepMagazine.getText());
								pst.setString(7,"magazine");
								pst.setString(8,auteur1Magazine.getText());
								pst.setString(9,auteur2Magazine.getText());
								pst.setString(10,auteur3Magazine.getText());
								pst.setString(11,auteur4Magazine.getText());
								pst.setString(12,auteur5Magazine.getText());
								pst.setString(13," -");
								pst.setString(14," -");
								pst.setString(15," -");
								pst.setString(16," -");
								pst.setString(17," -");
								pst.setString(18,PeriodiciteMagazine.getText());
								pst.setString(19,moisMagazine.getText());
								pst.setString(20,jourMagazine.getText());
								pst.executeUpdate();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							lblNombreDictionnaire_1.setText(""+new DocumentManager().getNombreMagazine(con));
							
							frame.setEnabled(true);
							d.dispose();
							TablauxDocuments("magazines");
							
							JOptionPane.showMessageDialog(null, "Ce magazine \" "+titreMagazine.getText()+" \" est bien ajouter ");
						
							
							
//							if(type.equals("ajoute")) {	
//							}
//							else {
//								try {
//									pst=con.prepareStatement("update document set TitreD=?, typeD=?, nomauteur=?, prenomauteur=?, nbrexemplaire=?, edeteur=?, annesAdition=?"
//											+ "where id=12");
//									//hna khasseni n3raf njib l'id dyal document
//									pst.setString(1,Titre);
//									pst.setString(2,Type);
//									pst.setString(3,NonAteur);
//									pst.setString(4,PrenomAtr);
//									pst.setString(5,Nombreexemplaire);
//									pst.setString(6,Edeteur);
//									pst.setString(7,AnneeAdt);
//									pst.executeUpdate();
//									Var_titreDocument.setText(Titre);
//									Var_typeDocument.setText(Type);
//									Var_NombreDespoDocument.setText(Nombreexemplaire);
//									Var_EditeurDocument.setText(Edeteur);
//									Var_AnneeADDocument.setText(AnneeAdt);
//									Var_AtteurLivre.setText(PrenomAtr+" "+NonAteur);
//									
//									TablauxDocuments("documents");
//									viderInputTexteFilied();
//									frame.setEnabled(true);
//									d.dispose();
//									JOptionPane.showMessageDialog(null, "Ce document est bien modifier");
//								} catch (SQLException e1) {
//									JOptionPane.showMessageDialog(null, "erreur : la modification n'est pas appliquer !");
//									e1.printStackTrace();
//								}}
								
						}

					});
					
					btnSaveMagazine.setForeground(new Color(0, 100, 0));
					btnSaveMagazine.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
					btnSaveMagazine.setBounds(326, 290, 115, 26);
					d.getContentPane().add(btnSaveMagazine);
					
					JButton btnCanclleMagazineInp = new JButton("Annuller");
					btnCanclleMagazineInp.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//viderInputTexteFilied();
							frame.setEnabled(true);
							d.dispose();
						}
					});
					btnCanclleMagazineInp.setForeground( new Color(204, 0, 51));
					btnCanclleMagazineInp.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
					btnCanclleMagazineInp.setBounds(457, 290, 115, 26);
					d.getContentPane().add(btnCanclleMagazineInp);
					
					
					
					
					
					
					
					
					
					frame.setEnabled(false);
					d.setVisible(true);
				
					
				}
				
		
	/*---------------------- L'AFONCTION D'INITIALIASTION DE LA FRAME PRINCIPALE DE NOTRE APPLICATION -----------------------*/
	/**
	 * Initialize the contents of the frame.
	 */
							
	@SuppressWarnings("unchecked")
	private void initialize() {
		//la liste que je vais mettre dans le spiner :
				String[] tabType= {"Tous","La liste des Livres","La liste des Dictionnaires","La liste des Magazines"};
				UIManager.put("ComboBox.focus", new Color(0, 0, 0, 0));
				UIManager.put("nimbusBase", Color.green);
				UIManager.put("nimbusBlueGrey", Color.green);
				UIManager.put("control", Color.green);
				
		
		
				frame = new JFrame();
				frame.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\lse econnes java project\\thebook.png"));
				frame.setBounds(100, 100, 995, 629);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(new CardLayout(0, 0));
				frame.setLocationRelativeTo(null);

		
		//l'initialisation des panels:	
			
				
				
				
				//panel principale
				panel_principale = new JPanel();
				frame.getContentPane().add(panel_principale, "name_168305453074200");
				panel_principale.setLayout(null);
				
				
				//panel de la page home:
				panel_page_home = new JPanel();
				panel_page_home.setBounds(245, 67, 736, 525);
				panel_principale.add(panel_page_home);
				panel_page_home.setLayout(null);
			
				
				
				

				//panel de gestion des livres:
				panel_gestion_livres = new JPanel();
				panel_gestion_livres.setBounds(245, 67,736, 525);
				panel_principale.add(panel_gestion_livres);
				panel_gestion_livres.setLayout(null);
				
				//panel de gestion DES adherentS :
				panel_gestion_adherents = new JPanel();
				panel_gestion_adherents.setBounds(245, 67, 736, 525);
				panel_principale.add(panel_gestion_adherents);
				panel_gestion_adherents.setLayout(null);
					
				//panel de la page detail adherent :
				panel_detaileAdherent = new JPanel();
				panel_detaileAdherent.setBounds(245, 67, 736, 525);
				panel_principale.add(panel_detaileAdherent);
				lbl_CNE = new JLabel("CNE    :");
				
				//le panel de la gestion des emprunts	
				panel_gestion_Emprunt = new JPanel();
				panel_gestion_Emprunt.setBounds(245, 67, 736, 525);
				panel_principale.add(panel_gestion_Emprunt);
				panel_gestion_Emprunt.setLayout(null);	
				
				
				
				
				
				
				//panel de detail livre :
				panel_info_livre = new JPanel();
				panel_info_livre.setForeground(new Color(255, 255, 255));
				panel_info_livre.setBounds(245, 67, 736, 525);
				panel_principale.add(panel_info_livre);
				panel_info_livre.setLayout(null);
				
				panel_gestion_livres.setVisible(false);
				panel_info_livre.setVisible(false);
				panel_gestion_Emprunt.setVisible(false);
				panel_detaileAdherent.setVisible(false);
				panel_gestion_adherents.setVisible(false);
				
				panel_gestion_livres.setVisible(false);
				
				
				
				
				
				
				
				
				
				
				
						
					
						
						
					
					
						
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ a modification
						
						
//fin fin fin fin fin @@@@@@@@@@@@@@@@@@@@@@@@@	
					
					
						
		/*--- LES PANEL DES DETAILLES ---*/			
					
					
		
						
	/*#----------------------------- LA REPRESENTATION DE CONTENU DES PANEL DE NOTRE APPLIACTION -----------------------------#*/
						
						
						

						
						
//1) ####################### === > le contenu de la page home :	
					
								
//@@@@@@@@@@@@@@@@@@@@@@@@@ page home modifier
						JLabel lblNewLabel_7 = new JLabel("E");
						lblNewLabel_7.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7.setForeground(new Color(204, 0, 0));
						lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7.setBounds(681, 39, 45, 37);
						panel_page_home.add(lblNewLabel_7);
						
						JLabel lblNewLabel_7_5 = new JLabel("N");
						lblNewLabel_7_5.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_5.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_5.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_5.setBounds(667, 95, 69, 51);
						panel_page_home.add(lblNewLabel_7_5);
						
						JLabel lblNewLabel_7_1 = new JLabel("S");
						lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_1.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_1.setBounds(681, 170, 45, 51);
						panel_page_home.add(lblNewLabel_7_1);
						
						JLabel lblNewLabel_7_2 = new JLabel("I");
						lblNewLabel_7_2.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_2.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_2.setBounds(681, 231, 45, 51);
						panel_page_home.add(lblNewLabel_7_2);
						
						JLabel lblNewLabel_7_3 = new JLabel("A");
						lblNewLabel_7_3.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_3.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_3.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_3.setBounds(681, 292, 45, 51);
						panel_page_home.add(lblNewLabel_7_3);
						
						JLabel lblNewLabel_7_4 = new JLabel("S");
						lblNewLabel_7_4.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_4.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_4.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_4.setBounds(681, 370, 45, 51);
						panel_page_home.add(lblNewLabel_7_4);
						
						JLabel lblNewLabel_7_6 = new JLabel("B");
						lblNewLabel_7_6.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_6.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_6.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_6.setBounds(101, 467, 45, 37);
						panel_page_home.add(lblNewLabel_7_6);
						
						JLabel lblNewLabel_7_7 = new JLabel("I");
						lblNewLabel_7_7.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_7.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_7.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_7.setBounds(133, 467, 45, 37);
						panel_page_home.add(lblNewLabel_7_7);
						
						JLabel lblNewLabel_7_8 = new JLabel("B");
						lblNewLabel_7_8.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_8.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_8.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_8.setBounds(170, 467, 45, 37);
						panel_page_home.add(lblNewLabel_7_8);
						
						JLabel lblNewLabel_7_9 = new JLabel("L");
						lblNewLabel_7_9.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_9.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_9.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_9.setBounds(208, 467, 45, 37);
						panel_page_home.add(lblNewLabel_7_9);
						
						JLabel lblNewLabel_7_10 = new JLabel("I");
						lblNewLabel_7_10.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_10.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_10.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_10.setBounds(254, 467, 45, 37);
						panel_page_home.add(lblNewLabel_7_10);
						
						JLabel lblNewLabel_7_11 = new JLabel("O");
						lblNewLabel_7_11.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_11.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_11.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_11.setBounds(304, 467, 45, 37);
						panel_page_home.add(lblNewLabel_7_11);
						
						JLabel lblNewLabel_7_12 = new JLabel("T");
						lblNewLabel_7_12.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_12.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_12.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_12.setBounds(359, 467, 45, 37);
						panel_page_home.add(lblNewLabel_7_12);
						
						JLabel lblNewLabel_7_13 = new JLabel("H");
						lblNewLabel_7_13.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_13.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_13.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_13.setBounds(414, 467, 45, 37);
						panel_page_home.add(lblNewLabel_7_13);
						
						JLabel lblNewLabel_7_14 = new JLabel("E");
						lblNewLabel_7_14.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_14.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_14.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_14.setBounds(469, 467, 45, 37);
						panel_page_home.add(lblNewLabel_7_14);
						
						JLabel lblNewLabel_7_15 = new JLabel("Q");
						lblNewLabel_7_15.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_15.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_15.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_15.setBounds(524, 467, 45, 37);
						panel_page_home.add(lblNewLabel_7_15);
						
						JLabel lblNewLabel_7_16 = new JLabel("U");
						lblNewLabel_7_16.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_16.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_16.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_16.setBounds(579, 467, 45, 37);
						panel_page_home.add(lblNewLabel_7_16);
						
						JLabel lblNewLabel_7_17 = new JLabel("E");
						lblNewLabel_7_17.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_7_17.setForeground(new Color(204, 0, 0));
						lblNewLabel_7_17.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
						lblNewLabel_7_17.setBounds(629, 467, 45, 37);
						panel_page_home.add(lblNewLabel_7_17);		
							
						JLabel lblNewLabel_6 = new JLabel("");
						lblNewLabel_6.setBounds(-15, -139, 812, 720);
						panel_page_home.add(lblNewLabel_6);
						lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_6.setIcon(new ImageIcon("D:\\lse econnes java project\\books-stack-of-three.png"));
									
								//cacher les panels :
								//hado mohimon bax ndara9 dak xi 
								panel_gestion_livres.setVisible(false);
								panel_info_livre.setVisible(false);
								panel_gestion_adherents.setVisible(false);
								panel_gestion_Emprunt.setVisible(false);
						
//fin fin fin fin fin @@@@@@@@@@@@@@@@@@@@@@@@@	
						
								
								
								
								
								
								
								
//2)###########################===> le contenu de panel de la gestion des livres:
								
								
								scrollPane_livres = new JScrollPane();
								scrollPane_livres.setBounds(10, 189, 705, 314);
								panel_gestion_livres.add(scrollPane_livres);
								//scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, UIManager.getColor("Tree.selectionBackground")));
								
								table = new JTable();
								table.setForeground(new Color(0, 0, 0));
								table.setRowHeight(25);
								table.addMouseListener(new MouseAdapter() {
									//ta hna khasse ndir switch case bax n3raf axman type khetaroh
									@Override
									public void mouseClicked(MouseEvent e) {
										////////////////////////////////
										
										DefaultTableModel model=(DefaultTableModel) table.getModel();
										int selectRowIndex=table.getSelectedRow();
										
										//Var_NumeroTele, Var_adressePer;
										docSelectionner=model.getValueAt(selectRowIndex, 0).toString();
										
										//lbl_V3DEtail, lbl_V1DEtail,lbl_V1DEtailData,lbl_V2DEtail,lbl_V2DEtaildData,lbl_V3DEtailData;
										Var_ISBNDocument.setText(model.getValueAt(selectRowIndex, 1).toString());
										Var_titreDocument.setText(model.getValueAt(selectRowIndex, 2).toString());
										Var_EditeurDocument.setText(model.getValueAt(selectRowIndex, 3).toString());
										Var_AnneeADDocument.setText(model.getValueAt(selectRowIndex, 4).toString());
										Var_NombreExemplaire.setText(model.getValueAt(selectRowIndex, 5).toString());
										Var_NombreDespoDocument.setText(model.getValueAt(selectRowIndex, 20).toString());
										
										Var_AtteurLivre.setText(" 1- "+model.getValueAt(selectRowIndex, 6).toString());
										Var_AtteurLivre_1.setText(" 2- "+model.getValueAt(selectRowIndex, 7).toString());
										Var_AtteurLivre_2.setText(" 3- "+model.getValueAt(selectRowIndex, 8).toString());
										Var_AtteurLivre_3.setText(" 4- "+model.getValueAt(selectRowIndex, 9).toString());
										Var_AtteurLivre_4.setText(" 5- "+model.getValueAt(selectRowIndex, 10).toString());
										
										if(model.getValueAt(selectRowIndex, 11).toString().equals("dictionnaire")) {
											lbl_V1DEtailData.setText(model.getValueAt(selectRowIndex, 16).toString());
											lbl_V2DEtaildData.setText(model.getValueAt(selectRowIndex, 15).toString());
											lbl_V3DEtail.setText(" ");
											lbl_V3DEtailData.setText(" ");
										}
										else if(model.getValueAt(selectRowIndex, 11).toString().equals("livre")) {
											lbl_V1DEtail.setText("Nombre  des  pages  :");
											lbl_V1DEtailData.setText(model.getValueAt(selectRowIndex, 14).toString());
											
											lbl_V2DEtail.setText("Type    :");
											lbl_V2DEtaildData.setText(model.getValueAt(selectRowIndex, 12).toString());
											
											lbl_V3DEtail.setText("Tome  :");
											lbl_V3DEtailData.setText(model.getValueAt(selectRowIndex, 13).toString());
											
										}
										else {
											lbl_V1DEtail.setText("Periodicite  :");
											lbl_V1DEtailData.setText(model.getValueAt(selectRowIndex, 17).toString());
											
											lbl_V2DEtail.setText("Mois    :");
											lbl_V2DEtaildData.setText(model.getValueAt(selectRowIndex, 18).toString());
											
											
											lbl_V3DEtailData.setText(model.getValueAt(selectRowIndex, 19).toString());
										}			
										panel_gestion_livres.setVisible(false);
										panel_info_livre.setVisible(true);
										table.getSelectionModel().clearSelection();
										
									}
								});
								scrollPane_livres.setViewportView(table);
								table.setBackground(new Color(204, 0, 51));
								
								JPanel panel_actioon_table = new JPanel();
								panel_actioon_table.setBorder(new LineBorder(new Color(204, 0, 51)));
								panel_actioon_table.setBounds(70, 116, 604, 63);
								panel_gestion_livres.add(panel_actioon_table);
								panel_actioon_table.setBackground(new Color(220, 220, 220));
								panel_actioon_table.setLayout(null);
								
								JButton btnRechercheLivre = new JButton("");
								btnRechercheLivre.setBounds(537, 15, 38, 32);
								panel_actioon_table.add(btnRechercheLivre);
								btnRechercheLivre.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\search (2).png"));
								btnRechercheLivre.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(inputCherche_livre.getText().length() > 0) {
										try {
												DefaultTableModel dm = (DefaultTableModel)table.getModel();
												dm.getDataVector().removeAllElements();
												dm.fireTableDataChanged();
												String titreChereche=inputCherche_livre.getText();
												ResultSet rs1=con.prepareStatement("SELECT * FROM document where titre LIKE '%"+titreChereche+"%';").executeQuery();
												//pst.setString(1, titreChereche);
												table.setModel(DbUtils.resultSetToTableModel(rs1));
												
												table.removeColumn(table.getColumnModel().getColumn(19));
												table.removeColumn(table.getColumnModel().getColumn(18));
												table.removeColumn(table.getColumnModel().getColumn(17));
												table.removeColumn(table.getColumnModel().getColumn(16));
												table.removeColumn(table.getColumnModel().getColumn(15));
												table.removeColumn(table.getColumnModel().getColumn(14));
												table.removeColumn(table.getColumnModel().getColumn(13));
												table.removeColumn(table.getColumnModel().getColumn(12));
												table.removeColumn(table.getColumnModel().getColumn(10));
												table.removeColumn(table.getColumnModel().getColumn(9));
												table.removeColumn(table.getColumnModel().getColumn(8));
												table.removeColumn(table.getColumnModel().getColumn(7));
												table.removeColumn(table.getColumnModel().getColumn(6));
												table.removeColumn(table.getColumnModel().getColumn(0));
												
												
												removeZoneRLivre.setVisible(true);
												scrollPane_livres.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 4, new Color(204, 0, 51)));
												if(table.getModel().getRowCount() < 12) {
													
													scrollPane_livres.setBounds(10, 189, 705, 24+table.getModel().getRowCount()*25);
												}
												else {
													scrollPane_livres.setBounds(10, 189, 705, 314);
												}
											
											
										} catch (SQLException e1) {
											JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
											e1.printStackTrace();
										}
									}
										
								}
								});
								btnRechercheLivre.setFocusPainted(false);
								btnRechercheLivre.setContentAreaFilled(false);
								btnRechercheLivre.setBorderPainted(false);
								btnRechercheLivre.setFont(new Font("Tahoma", Font.BOLD, 17));
								
								JComboBox combobox_livres=new JComboBox(tabType);
								combobox_livres.setBounds(46, 15, 212, 32);
								panel_actioon_table.add(combobox_livres);
								combobox_livres.setFont(new Font("Tahoma", Font.BOLD, 15));
								combobox_livres.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(220, 20, 60)));
								combobox_livres.setForeground(new Color(220, 20, 60));
								combobox_livres.setFocusable(false);
								combobox_livres.setSelectedIndex(0);
								combobox_livres.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										int selectonier=combobox_livres.getSelectedIndex();//ou 
										String selectonierString=(String) combobox_livres.getSelectedItem();
										// le tretment que je dout faire apres le slectionment
										//JOptionPane.showMessageDialog(null, "maintenent vous avez slectioner : "+selectonierString);
										//documents   livres  dictionnaires  magazines
										if(selectonier==0)
											TablauxDocuments("documents");
										else if(selectonier==1)
											TablauxDocuments("livres");
										else if(selectonier==2)
											TablauxDocuments("dictionnaires");
										else if(selectonier==3)
											TablauxDocuments("magazines");
										
									}
								});
								combobox_livres.setBackground(Color.LIGHT_GRAY);								
								
								removeZoneRLivre = new JButton("");
								removeZoneRLivre.setBounds(511, 15, 25, 26);
								panel_actioon_table.add(removeZoneRLivre);
								removeZoneRLivre.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\remove.png"));
								removeZoneRLivre.setVisible(false);
								
								removeZoneRLivre.setOpaque(false);
								removeZoneRLivre.setFocusPainted(false);
								removeZoneRLivre.setBorderPainted(false);
								removeZoneRLivre.setContentAreaFilled(false);
								
								removeZoneRLivre.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										DefaultTableModel dm = (DefaultTableModel)table.getModel();
										dm.getDataVector().removeAllElements();
										dm.fireTableDataChanged(); // notifies the JTable that the model has changed
										
										TablauxDocuments("documents");
										removeZoneRLivre.setVisible(false);
										inputCherche_livre.setText("");
									}
								});
								removeZoneRLivre.setFont(new Font("Tahoma", Font.BOLD, 10));
								removeZoneRLivre.setBackground(new Color(0,0,0, 0));
								
								
								inputCherche_livre  = new JTextField();
								inputCherche_livre.setBounds(315, 15, 225, 32);
								panel_actioon_table.add(inputCherche_livre);
								inputCherche_livre.setForeground(Color.BLACK);
								inputCherche_livre.setFont(new Font("Tahoma", Font.BOLD, 12));
								inputCherche_livre.setBackground(new Color(220, 220, 220));
								inputCherche_livre .setColumns(100);
								inputCherche_livre.setBorder(BorderFactory.createMatteBorder(0, 1, 2, 0,new Color(204, 0, 51)));
								
								

								
								JButton btnJouterLivre = new JButton("");
								btnJouterLivre.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										FormulleAjoteLivre();
									}
								});

								btnJouterLivre.setFocusPainted(false);
								btnJouterLivre.setContentAreaFilled(false);
								btnJouterLivre.setBorderPainted(false);
								
								btnJouterLivre.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\plus (2).png"));
								btnJouterLivre.setBounds(6, 0, 44, 38);
								panel_gestion_livres.add(btnJouterLivre);
								JPanel livreCompteur = new JPanel();
								livreCompteur.setBackground(new Color(220, 20, 60));
								livreCompteur.setBounds(25, 11, 165, 95);
								panel_gestion_livres.add(livreCompteur);
								livreCompteur.setLayout(null);
								
								JLabel lblNewLabel = new JLabel("");
								lblNewLabel.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\book (1).png"));
								lblNewLabel.setBounds(79, 19, 70, 63);
								livreCompteur.add(lblNewLabel);
								
								JLabel lblNewLabel_2_1 = new JLabel("Livres");
								lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
								lblNewLabel_2_1.setForeground(Color.BLACK);
								lblNewLabel_2_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
								lblNewLabel_2_1.setBounds(47, 0, 118, 23);
								livreCompteur.add(lblNewLabel_2_1);
								
								
								System.out.println("===============>>>"+nombreDictionnaire);
								
								lblNombreDictionnaire_2 = new JLabel("");
								lblNombreDictionnaire_2.setText(""+nombreDictionnaire);
								lblNombreDictionnaire_2.setHorizontalAlignment(SwingConstants.CENTER);
								lblNombreDictionnaire_2.setForeground(Color.WHITE);
								lblNombreDictionnaire_2.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
								lblNombreDictionnaire_2.setBounds(10, 19, 55, 62);
								livreCompteur.add(lblNombreDictionnaire_2);
								
								
								
								
								JButton btnNewButton = new JButton("");
								btnNewButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										FormulleAjoteDictionnaire("ajoute");
									}
								});
								
								btnNewButton.setFocusPainted(false);
								btnNewButton.setContentAreaFilled(false);
								btnNewButton.setBorderPainted(false);
								
								btnNewButton.setBounds(262, 0, 44, 38);
								panel_gestion_livres.add(btnNewButton);
								btnNewButton.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\plus (6).png"));
								
								
								JPanel DictionnairCompteur = new JPanel();
								DictionnairCompteur.setLayout(null);
								DictionnairCompteur.setBackground(new Color(255, 215, 0));
								DictionnairCompteur.setBounds(283, 11, 165, 95);
								panel_gestion_livres.add(DictionnairCompteur);
								
/*-------------------------------*/lblNombreDictionnaire = new JLabel(""+nombreDictionnaire);
								lblNombreDictionnaire.setHorizontalAlignment(SwingConstants.CENTER);
								lblNombreDictionnaire.setForeground(Color.WHITE);
								lblNombreDictionnaire.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
								lblNombreDictionnaire.setBounds(9, 20, 55, 62);
								DictionnairCompteur.add(lblNombreDictionnaire);
								
								JLabel lblNewLabel_1 = new JLabel("");
								lblNewLabel_1.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\dictionary.png"));
								lblNewLabel_1.setBounds(74, 20, 66, 62);
								DictionnairCompteur.add(lblNewLabel_1);
								
								JLabel lblNewLabel_2 = new JLabel("Dictionnaire");
								lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
								lblNewLabel_2.setForeground(new Color(0, 0, 0));
								lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
								lblNewLabel_2.setBounds(45, 0, 118, 23);
								DictionnairCompteur.add(lblNewLabel_2);
								
								
								
								
								JButton btnNewButton_3 = new JButton("");
								btnNewButton_3.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										FormulleAjoteMagazine("ajoute");
									}
								});
								
								
								btnNewButton_3.setFocusPainted(false);
								btnNewButton_3.setContentAreaFilled(false);
								btnNewButton_3.setBorderPainted(false);
								
								btnNewButton_3.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\add (4).png"));
								btnNewButton_3.setBounds(529, 0, 44, 38);
								panel_gestion_livres.add(btnNewButton_3);
								
								JPanel MagazineCompleur = new JPanel();
								MagazineCompleur.setLayout(null);
								MagazineCompleur.setBackground(new Color(30, 144, 255));
								MagazineCompleur.setBounds(550, 11, 165, 95);
								panel_gestion_livres.add(MagazineCompleur);
								
								JLabel lblNewLabel_3 = new JLabel("");
								lblNewLabel_3.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\magazine.png"));
								lblNewLabel_3.setBounds(82, 22, 66, 60);
								MagazineCompleur.add(lblNewLabel_3);
								
								JLabel lblNewLabel_2_2 = new JLabel("Magazines");
								lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
								lblNewLabel_2_2.setForeground(Color.BLACK);
								lblNewLabel_2_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
								lblNewLabel_2_2.setBounds(55, 0, 118, 23);
								MagazineCompleur.add(lblNewLabel_2_2);
								
								lblNombreDictionnaire_1 = new JLabel(""+nombreMagazine);
								lblNombreDictionnaire_1.setHorizontalAlignment(SwingConstants.CENTER);
								lblNombreDictionnaire_1.setForeground(Color.WHITE);
								lblNombreDictionnaire_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
								lblNombreDictionnaire_1.setBounds(10, 22, 55, 62);
								MagazineCompleur.add(lblNombreDictionnaire_1);
										
										
										JPanel panel_action_table_emp = new JPanel();
										panel_action_table_emp.setBorder(new LineBorder(new Color(204, 0, 51)));
										panel_action_table_emp.setBounds(70, 116, 604, 63);
										panel_gestion_Emprunt.add(panel_action_table_emp);
										panel_action_table_emp.setBackground(new Color(220, 220, 220));
										panel_action_table_emp.setLayout(null);
										
										JButton btnRechercheEmp = new JButton("");
										btnRechercheEmp.setBounds(518, 10, 38, 43);
										btnRechercheEmp.setBackground(new Color(30, 144, 255));
										btnRechercheEmp.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\search (2).png"));
										btnRechercheEmp.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												if(inputChercheEmp.getText().length() > 0) {
												try {
														DefaultTableModel dm = (DefaultTableModel)table_empr.getModel();
														dm.getDataVector().removeAllElements();
														dm.fireTableDataChanged();
														String nomChereche=inputChercheEmp.getText();
														ResultSet rs1=con.prepareStatement("SELECT * FROM emprunt where Nom LIKE '%"+nomChereche+"%' or prenom  LIKE '%"
																			+nomChereche+"%'  or titre like '%"+nomChereche+"%' ;").executeQuery();
													
														//pst.setString(1, titreChereche);
														table_empr.setModel(DbUtils.resultSetToTableModel(rs1));
														RemoveZoneChercheEmp.setVisible(true);
														System.out.println(table_empr.getModel().getRowCount());
														scrollPane_empr.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 4, new Color(204, 0, 51)));
														if(table_empr.getModel().getRowCount() < 12) {
					
															scrollPane_empr.setBounds(10, 189, 705, 26+table_empr.getModel().getRowCount()*25);
														}
														else {
															scrollPane_empr.setBounds(10, 189, 705, 314);
														}		
												} catch (SQLException e1) {
													JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
													e1.printStackTrace();
												}
											}
												
										}
										});
										btnRechercheEmp.setBackground(new Color(204, 0, 51));
										btnRechercheEmp.setBorder(null);
										btnRechercheEmp.setFont(new Font("Tahoma", Font.BOLD, 15));
										btnRechercheEmp.setFocusPainted(false);
										btnRechercheEmp.setContentAreaFilled(false);
										btnRechercheEmp.setBorderPainted(false);
										panel_action_table_emp.add(btnRechercheEmp);
										
																				
																	
																				
																				
																				
																				
																				
																				
																				
																				
										
																				
																				
																				
										//3) #####################===> le contenu de la page de la gestion des emprunts 
																				
					JComboBox combobox_emprunts = new JComboBox(tabType);
					combobox_emprunts.setBounds(22, 20, 233, 29);
					panel_action_table_emp.add(combobox_emprunts);
					combobox_emprunts.setSelectedIndex(0);
					combobox_emprunts.setForeground(new Color(220, 20, 60));
					combobox_emprunts.setFont(new Font("Tahoma", Font.BOLD, 15));
					combobox_emprunts.setFocusable(false);
					combobox_emprunts.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(220, 220, 220)));
					combobox_emprunts.setBackground(new Color(220, 220, 220));
					combobox_emprunts.setFocusable(false);
					combobox_emprunts.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
					
						int selectonier=combobox_emprunts.getSelectedIndex();
						if(selectonier==0)
							TableauxEmprunts("Tous");
						else if(selectonier==1)
							TableauxEmprunts("La liste des livres");
						else if(selectonier==2)
							TableauxEmprunts("La liste des dictionnaires");
						else if(selectonier==3)
							TableauxEmprunts("La liste des magazines");
					}
					});
					combobox_emprunts.setBackground(Color.gray);
																						
																						
							RemoveZoneChercheEmp = new JButton("");
							RemoveZoneChercheEmp.setBounds(481, 14, 38, 39);
							panel_action_table_emp.add(RemoveZoneChercheEmp);
							RemoveZoneChercheEmp.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\remove.png"));
							RemoveZoneChercheEmp.setVisible(false);
							RemoveZoneChercheEmp.setOpaque(false);
							RemoveZoneChercheEmp.setFocusPainted(false);
							RemoveZoneChercheEmp.setBorderPainted(false);
							RemoveZoneChercheEmp.setContentAreaFilled(false);
							RemoveZoneChercheEmp.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								DefaultTableModel dm = (DefaultTableModel)table_empr.getModel();
								dm.getDataVector().removeAllElements();
								dm.fireTableDataChanged(); // notifies the JTable that the model has changed
								TableauxEmprunts("Tous");
								RemoveZoneChercheEmp.setVisible(false);
								inputChercheEmp.setText("");
								}
							});
							RemoveZoneChercheEmp.setFont(new Font("Tahoma", Font.BOLD, 10));
																						
																						
																						
							inputChercheEmp = new JTextField();
							inputChercheEmp.setBounds(292, 21, 227, 32);
							panel_action_table_emp.add(inputChercheEmp);
							inputChercheEmp.setForeground(new Color(0, 0, 0));
							inputChercheEmp.setFont(new Font("Tahoma", Font.PLAIN, 12));
							inputChercheEmp.setColumns(100);
							inputChercheEmp.setBorder(BorderFactory.createMatteBorder(0, 1, 2, 0, new Color(205, 0, 55)));
							inputChercheEmp.setBackground(new Color(220, 220, 220));
										
										JPanel livreCompteur1 = new JPanel();
										livreCompteur1.setBackground(new Color(220, 20, 60));
										livreCompteur1.setBounds(25, 11, 165, 95);
										panel_gestion_Emprunt.add(livreCompteur1);
										livreCompteur1.setLayout(null);
										
										JLabel lblNewLabel1 = new JLabel("");
										lblNewLabel1.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\image.png"));
										lblNewLabel1.setBounds(79, 19, 70, 63);
										livreCompteur1.add(lblNewLabel1);
										
										JLabel lblNewLabel_2_11 = new JLabel("Livres Empruntes");
										lblNewLabel_2_11.setHorizontalAlignment(SwingConstants.CENTER);
										lblNewLabel_2_11.setForeground(Color.BLACK);
										lblNewLabel_2_11.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
										lblNewLabel_2_11.setBounds(10, 0, 155, 23);
										livreCompteur1.add(lblNewLabel_2_11);
										
										lblNombreLivreEmprunter = new JLabel("15");
										lblNombreLivreEmprunter.setHorizontalAlignment(SwingConstants.CENTER);
										lblNombreLivreEmprunter.setForeground(Color.WHITE);
										lblNombreLivreEmprunter.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
										lblNombreLivreEmprunter.setBounds(10, 19, 55, 62);
										livreCompteur1.add(lblNombreLivreEmprunter);
										
										
										JPanel DictionnairCompteur1 = new JPanel();
										DictionnairCompteur1.setLayout(null);
										DictionnairCompteur1.setBackground(new Color(255, 215, 0));
										DictionnairCompteur1.setBounds(283, 11, 171, 95);
										panel_gestion_Emprunt.add(DictionnairCompteur1);
										
										lblNombreDictionnaireEmprunter = new JLabel("15");
										lblNombreDictionnaireEmprunter.setHorizontalAlignment(SwingConstants.CENTER);
										lblNombreDictionnaireEmprunter.setForeground(Color.WHITE);
										lblNombreDictionnaireEmprunter.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
										lblNombreDictionnaireEmprunter.setBounds(10, 20, 55, 62);
										DictionnairCompteur1.add(lblNombreDictionnaireEmprunter);
										
										JLabel lblNewLabel_11 = new JLabel("");
										lblNewLabel_11.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\dictionary.png"));
										lblNewLabel_11.setBounds(74, 20, 66, 62);
										DictionnairCompteur1.add(lblNewLabel_11);
										
										JLabel lblNewLabel_21 = new JLabel("Dictionnaire emprunter");
										lblNewLabel_21.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
										lblNewLabel_21.setForeground(new Color(0, 0, 0));
										lblNewLabel_21.setHorizontalAlignment(SwingConstants.CENTER);
										lblNewLabel_21.setBounds(-11, 0, 182, 23);
										DictionnairCompteur1.add(lblNewLabel_21);
										
										JPanel MagazineCompleur1 = new JPanel();
										MagazineCompleur1.setLayout(null);
										MagazineCompleur1.setBackground(new Color(30, 144, 255));
										MagazineCompleur1.setBounds(550, 11, 165, 95);
										panel_gestion_Emprunt.add(MagazineCompleur1);
										
										JLabel lblNewLabel_31 = new JLabel("");
										lblNewLabel_31.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\magazine.png"));
										lblNewLabel_31.setBounds(82, 22, 66, 60);
										MagazineCompleur1.add(lblNewLabel_31);
										
										JLabel lblNewLabel_2_21 = new JLabel("Magazines Emprunter");
										lblNewLabel_2_21.setHorizontalAlignment(SwingConstants.CENTER);
										lblNewLabel_2_21.setForeground(Color.BLACK);
										lblNewLabel_2_21.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
										lblNewLabel_2_21.setBounds(-14, 0, 187, 23);
										MagazineCompleur1.add(lblNewLabel_2_21);
										
										lblNombreMagazineEmprunter = new JLabel("15");
										lblNombreMagazineEmprunter.setHorizontalAlignment(SwingConstants.CENTER);
										lblNombreMagazineEmprunter.setForeground(Color.WHITE);
										lblNombreMagazineEmprunter.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
										lblNombreMagazineEmprunter.setBounds(10, 22, 55, 62);
										MagazineCompleur1.add(lblNombreMagazineEmprunter);
										
										scrollPane_empr = new JScrollPane();
										scrollPane_empr.setBounds(10, 189, 705, 314);
										panel_gestion_Emprunt.add(scrollPane_empr);
										
										table_empr = new JTable();
										scrollPane_empr.setViewportView(table_empr);
										table_empr.setRowHeight(25);
										table_empr.setForeground(Color.WHITE);
										table_empr.setBackground(new Color(204, 0, 51));
										table_empr.addMouseListener(new MouseAdapter() {
											@Override
											public void mouseClicked(MouseEvent e) {
												DefaultTableModel model=(DefaultTableModel) table_empr.getModel();
												int selectRowIndex=table_empr.getSelectedRow();
												//hado mn la base de donneee khasseni nmxi n9alab 3la l'emprunt b id dyalha w njib l id dyal adherent li m emprintiha
												// w 3ad n3amare les informations dyalo f la page detaille dyalo
												//Var_NomAdh.setText(model.getValueAt(selectRowIndex, 1).toString());
											//	Var_PrenomAdh.setText(model.getValueAt(selectRowIndex, 2).toString());
											//	Var_adressePer.setText(model.getValueAt(selectRowIndex, 3).toString());
												//Var_MailAdh.setText(model.getValueAt(selectRowIndex, 4).toString());
												//Var_NumeroTele.setText(model.getValueAt(selectRowIndex, 5).toString());
												
												panel_gestion_Emprunt.setVisible(false);
												panel_detaileAdherent.setVisible(true);
												table_empr.getSelectionModel().clearSelection();
												
											}
										});
										
								
								
								
								
								
								

						
										
										
										
										
										
										
										
										
								
						
								
								
								
								
								
								
								
//4)###################===>le contenu de la gestion des adherents :
								scrollPane_adhrents = new JScrollPane();
								//scrollPane_adhrents.setBounds(10, 189, 705, 314);
								panel_gestion_adherents.add(scrollPane_adhrents);
								
								table_adherents = new JTable();
								table_adherents.addMouseListener(new MouseAdapter() {
									
									@Override
									public void mouseClicked(MouseEvent e) {
										DefaultTableModel model=(DefaultTableModel) table_adherents.getModel();
										int selectRowIndex=table_adherents.getSelectedRow();
										
										//Var_NumeroTele, Var_adressePer;
										adhSelectionner=model.getValueAt(selectRowIndex, 0).toString();
										typeAdhSelected=model.getValueAt(selectRowIndex, 9).toString();
										Var_NomADhrent.setText(model.getValueAt(selectRowIndex, 1).toString());
										Var_PrenomAdhrents.setText(model.getValueAt(selectRowIndex, 2).toString());
										Var_CodeAdherent.setText(model.getValueAt(selectRowIndex, 3).toString());
										Var_adressePer.setText(model.getValueAt(selectRowIndex, 4).toString());
										Var_MailAdh.setText(model.getValueAt(selectRowIndex, 5).toString());
										Var_NumeroTele.setText(model.getValueAt(selectRowIndex, 6).toString());
										System.out.println(model.getValueAt(selectRowIndex, 9).toString());
										System.out.println("-----------------------------------");
										System.out.println(model.getValueAt(selectRowIndex, 7).toString());
										if(model.getValueAt(selectRowIndex, 9).toString().equals("etudiant"))
											Var_CNE.setText(model.getValueAt(selectRowIndex, 7).toString());
										else if(model.getValueAt(selectRowIndex, 9).toString().equals("professeur")) {
											lbl_CNE.setText("CNP   :");
											Var_CNE.setText(model.getValueAt(selectRowIndex, 8).toString());
											}
										else {
											lbl_CNE.setText(" ");
											Var_CNE.setText(" ");
										}
											
											
											TableauxDocumentAdh(adhSelectionner);
										
										panel_gestion_adherents.setVisible(false);
										panel_detaileAdherent.setVisible(true);
										table_adherents.getSelectionModel().clearSelection();
										
									}
								});
								scrollPane_adhrents.setViewportView(table_adherents);
								table_adherents.setRowHeight(25);
								table_adherents.setForeground(Color.BLACK);
								table_adherents.setBackground(new Color(204, 0, 51));
							
								
								
								
								
								
								JPanel panel_actioon_table1 = new JPanel();
								panel_actioon_table1.setBorder(new LineBorder(new Color(204, 0, 51)));
								panel_actioon_table1.setBounds(70, 116, 604, 63);
								panel_gestion_adherents.add(panel_actioon_table1);
								panel_actioon_table1.setBackground(new Color(220, 220, 220));
								panel_actioon_table1.setLayout(null);
								
								
								RemoveZoneChercheAdh = new JButton("");
								RemoveZoneChercheAdh.setBounds(496, 10, 38, 38);
								panel_actioon_table1.add(RemoveZoneChercheAdh);
								RemoveZoneChercheAdh.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\remove.png"));
								RemoveZoneChercheAdh.setVisible(false);
								RemoveZoneChercheAdh.setOpaque(false);
								RemoveZoneChercheAdh.setFocusPainted(false);
								RemoveZoneChercheAdh.setBorderPainted(false);
								RemoveZoneChercheAdh.setContentAreaFilled(false);
								RemoveZoneChercheAdh.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										DefaultTableModel dm = (DefaultTableModel)table_adherents.getModel();
										dm.getDataVector().removeAllElements();
										dm.fireTableDataChanged(); // notifies the JTable that the model has changed
										
										TableauxAdherents("Tous");
										RemoveZoneChercheAdh.setVisible(false);
										inputChercheAdh.setText("");
									}
								});
								RemoveZoneChercheAdh.setFont(new Font("Tahoma", Font.BOLD, 10));
								RemoveZoneChercheAdh.setFocusPainted(false);
								RemoveZoneChercheAdh.setContentAreaFilled(false);
								RemoveZoneChercheAdh.setBorderPainted(false);
								RemoveZoneChercheAdh.setBackground(new Color(0,0,0, 0));
								
								
								inputChercheAdh = new JTextField();
								inputChercheAdh.setBounds(309, 16, 225, 32);
								panel_actioon_table1.add(inputChercheAdh);
								inputChercheAdh.setForeground(new Color(0, 0, 0));
								inputChercheAdh.setFont(new Font("Tahoma", Font.BOLD, 12));
								inputChercheAdh.setColumns(100);
								inputChercheAdh.setBorder(BorderFactory.createMatteBorder(0, 1, 2, 0,new Color(204, 0, 51)));
								inputChercheAdh.setBackground(new Color(220, 220, 220));
								
								
								
								JButton btnRechercheAdh = new JButton("");
								btnRechercheAdh.setBounds(526, 8, 51, 49);
								panel_actioon_table1.add(btnRechercheAdh);
								btnRechercheAdh.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\search (2).png"));
								btnRechercheAdh.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(inputChercheAdh.getText().length() > 0) {
										try {
												DefaultTableModel dm = (DefaultTableModel)table_adherents.getModel();
												dm.getDataVector().removeAllElements();
												dm.fireTableDataChanged();
												String nomChereche=inputChercheAdh.getText();
												ResultSet rs1=con.prepareStatement("SELECT * FROM personne where nom LIKE '%"+nomChereche+"%' or prenom LIKE '%"+nomChereche+"%';").executeQuery();
												table_adherents.setModel(DbUtils.resultSetToTableModel(rs1));
												
												table_adherents.removeColumn(table_adherents.getColumnModel().getColumn(8));
												table_adherents.removeColumn(table_adherents.getColumnModel().getColumn(7));
												table_adherents.removeColumn(table_adherents.getColumnModel().getColumn(0));
												
												RemoveZoneChercheAdh.setVisible(true);
												System.out.println(table_adherents.getModel().getRowCount());
												
												
												scrollPane_adhrents.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 4, new Color(204, 0, 51)));
												if(table_adherents.getModel().getRowCount() < 12) {
													scrollPane_adhrents.setBounds(10, 189, 705, 24+table_adherents.getModel().getRowCount()*25);
												}
												else {
													scrollPane_adhrents.setBounds(10, 189, 705, 314);
												}		
										} catch (SQLException e1) {
											JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
											e1.printStackTrace();
										}
									}
										
								}
								});
								btnRechercheAdh.setFocusPainted(false);
								btnRechercheAdh.setContentAreaFilled(false);
								btnRechercheAdh.setBorderPainted(false);
								btnRechercheAdh.setFont(new Font("Tahoma", Font.BOLD, 15));
								
								
								JComboBox combobox_adhrents=new JComboBox(new String[] {"Tous", "La liste des etudiants", "La liste des professeurs", "autre"});
								combobox_adhrents.setBounds(47, 15, 212, 32);
								panel_actioon_table1.add(combobox_adhrents);
								combobox_adhrents.setFont(new Font("Tahoma", Font.BOLD, 15));
								combobox_adhrents.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(220, 20, 60)));
								combobox_adhrents.setForeground(new Color(204, 0, 51));
								combobox_adhrents.setFocusable(false);
								combobox_adhrents.setSelectedIndex(0);
								combobox_adhrents.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										int selectonier=combobox_adhrents.getSelectedIndex();
										String selectonierString=(String) combobox_adhrents.getSelectedItem();
										if(selectonier==0)
											TableauxAdherents("Tous");
										else if(selectonier==1)
											TableauxAdherents("La liste des etudiants");
										else if(selectonier==2)
											TableauxAdherents("La liste des professeurs");
										else if(selectonier==3)
											TableauxAdherents("autre");
										
									}
								});
								combobox_adhrents.setBackground(new Color(210, 210, 210));

								JButton btnNewButton_111 = new JButton("");
								btnNewButton_111.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										FormuleAddPersonne("ajouter");
									}
								});
								
								btnNewButton_111.setFocusPainted(false);
								btnNewButton_111.setContentAreaFilled(false);
								btnNewButton_111.setBorderPainted(false);
								
								btnNewButton_111.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\plus (2).png"));
								btnNewButton_111.setBounds(6, 0, 44, 38);
								panel_gestion_adherents.add(btnNewButton_111);
								JPanel livreCompteur11 = new JPanel();
								livreCompteur11.setBackground(new Color(220, 20, 60));
								livreCompteur11.setBounds(25, 11, 165, 95);
								panel_gestion_adherents.add(livreCompteur11);
								livreCompteur11.setLayout(null);
								
								JLabel lblNewLabel11 = new JLabel("");
								lblNewLabel11.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\teacher.png"));
								lblNewLabel11.setBounds(79, 19, 70, 63);
								livreCompteur11.add(lblNewLabel11);
								
								JLabel lblNewLabel_2_111 = new JLabel("Personne");
								lblNewLabel_2_111.setHorizontalAlignment(SwingConstants.CENTER);
								lblNewLabel_2_111.setForeground(Color.BLACK);
								lblNewLabel_2_111.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
								lblNewLabel_2_111.setBounds(50, 0, 118, 23);
								livreCompteur11.add(lblNewLabel_2_111);
								
//@$$$$$$$$$$$$$$$$$$$$4$$$$$$$$$$$$$$$$$	lblNombrePeressones
								lblNombrePeressones = new JLabel("");
								lblNombrePeressones.setHorizontalAlignment(SwingConstants.CENTER);
								lblNombrePeressones.setForeground(Color.WHITE);
								lblNombrePeressones.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
								lblNombrePeressones.setBounds(10, 19, 55, 62);
								livreCompteur11.add(lblNombrePeressones);
								
								
								
								
								JButton btnNewButton11 = new JButton("");
								btnNewButton11.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										FormulAddEtudiant("ajouter");
									}
								});
								
								btnNewButton11.setFocusPainted(false);
								btnNewButton11.setContentAreaFilled(false);
								btnNewButton11.setBorderPainted(false);
								
								btnNewButton11.setBounds(262, 0, 44, 38);
								panel_gestion_adherents.add(btnNewButton11);
								btnNewButton11.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\plus (6).png"));
								
								
								JPanel DictionnairCompteur11 = new JPanel();
								DictionnairCompteur11.setLayout(null);
								DictionnairCompteur11.setBackground(new Color(255, 215, 0));
								DictionnairCompteur11.setBounds(289, 11, 165, 95);
								panel_gestion_adherents.add(DictionnairCompteur11);
								
								lblNombreEtudiants = new JLabel("");
								lblNombreEtudiants.setHorizontalAlignment(SwingConstants.CENTER);
								lblNombreEtudiants.setForeground(Color.WHITE);
								lblNombreEtudiants.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
								lblNombreEtudiants.setBounds(9, 20, 55, 62);
								DictionnairCompteur11.add(lblNombreEtudiants);
								
								JLabel lblNewLabel_111 = new JLabel("");
								lblNewLabel_111.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\student.png"));
								lblNewLabel_111.setBounds(78, 20, 66, 62);
								DictionnairCompteur11.add(lblNewLabel_111);
								
								JLabel lblNewLabel_211 = new JLabel("Etudiant");
								lblNewLabel_211.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
								lblNewLabel_211.setForeground(new Color(0, 0, 0));
								lblNewLabel_211.setHorizontalAlignment(SwingConstants.CENTER);
								lblNewLabel_211.setBounds(45, 0, 118, 23);
								DictionnairCompteur11.add(lblNewLabel_211);
								
								
								
								
								JButton btnNewButton_311 = new JButton("");
								btnNewButton_311.setForeground(Color.GRAY);
								btnNewButton_311.setBackground(Color.GRAY);
								btnNewButton_311.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										FormulaireAddProfesseur("ajouter");
									}
								});
								btnNewButton_311.setFocusPainted(false);
								btnNewButton_311.setContentAreaFilled(false);
								btnNewButton_311.setBorderPainted(false);
								
								btnNewButton_311.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\add (4).png"));
								btnNewButton_311.setBounds(529, 0, 44, 38);
								panel_gestion_adherents.add(btnNewButton_311);
								
								JPanel MagazineCompleur11 = new JPanel();
								MagazineCompleur11.setLayout(null);
								MagazineCompleur11.setBackground(new Color(30, 144, 255));
								MagazineCompleur11.setBounds(550, 11, 165, 95);
								panel_gestion_adherents.add(MagazineCompleur11);
								
								JLabel lblNewLabel_311 = new JLabel("");
								lblNewLabel_311.setIcon(new ImageIcon("D:\\lse econnes java project\\projetct\\education.png"));
								lblNewLabel_311.setBounds(83, 22, 66, 60);
								MagazineCompleur11.add(lblNewLabel_311);
								
								JLabel lblNewLabel_2_211 = new JLabel("Professeur");
								lblNewLabel_2_211.setHorizontalAlignment(SwingConstants.CENTER);
								lblNewLabel_2_211.setForeground(Color.BLACK);
								lblNewLabel_2_211.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
								lblNewLabel_2_211.setBounds(55, 0, 118, 23);
								MagazineCompleur11.add(lblNewLabel_2_211);
								
								lblNombreProfisseurs = new JLabel("15");
								lblNombreProfisseurs.setHorizontalAlignment(SwingConstants.CENTER);
								lblNombreProfisseurs.setForeground(Color.WHITE);
								lblNombreProfisseurs.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
								lblNombreProfisseurs.setBounds(10, 22, 55, 62);
								MagazineCompleur11.add(lblNombreProfisseurs);
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
		
								
								
		      ///////////////////////////// ----------------- LES PAGES DES DETAILLES ------------- \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\						
								
						
								
								
 //1-1###################====>la partie de detaille livre :
								
								
								String texteR="Lire Le Grand Meaulnes c'est aller à la découverte d'aventures qui exigent d'incessants retours en arrière, comme si l'aiguillon du bonheur devait toujours se refléter dans le miroir troublant et tremblant de l'enfance scruté par le regard fiévreux de l'adolescence.\r\n"
										       +"Le merveilleux de ce roman réside dans un secret mouvement de balancier où le temps courtise son abolition.";
								
								JButton btnModifierLivre = new JButton("Modifier");
								btnModifierLivre.setBounds(431, 469, 131, 34);
								panel_info_livre.add(btnModifierLivre);
								btnModifierLivre.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										//hna ra n3ayat l dak l formuler bax ndawax ---> docSelectionner
										
										FormulleAjoteLivre();
										// la remplissage des champs de pup up
										textField_7.setText(Var_titreDocument.getText());
										textField_8.setText(lbl_V1DEtailData.getText());
										textField_11.setText(Var_NombreDespoDocument.getText());
										textField_12.setText(Var_EditeurDocument.getText());
										textField_13.setText(Var_AnneeADDocument.getText());
										textField_9.setText(Var_AtteurLivre.getText());
										
									}
								});
								btnModifierLivre.setIcon(new ImageIcon("D:\\lse econnes java project\\modifier.png"));
								btnModifierLivre.setBackground(Color.LIGHT_GRAY);
								btnModifierLivre.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.ORANGE));
								btnModifierLivre.setForeground(new Color(255, 140, 0));
								btnModifierLivre.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
								
								JButton btnSupprimerLivre = new JButton("Supprimer");
								btnSupprimerLivre.setBounds(582, 469, 131, 34);
								panel_info_livre.add(btnSupprimerLivre);
								btnSupprimerLivre.setIcon(new ImageIcon("D:\\lse econnes java project\\supprimer.png"));
								btnSupprimerLivre.setBackground(Color.LIGHT_GRAY);
								//btnSupprimerLivre.setBackground(Color.RED);
								btnSupprimerLivre.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
								btnSupprimerLivre.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										
								             if(JOptionPane.showConfirmDialog(null,"attention vous avez supprimer un document,est ce que tu et sure?"
								                     ,"supprimer document ?",JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
								            	 System.out.println("docSelectionner===>"+docSelectionner);
								            	 new DocumentManager().supprimerLivre(docSelectionner,con);
								 	 				
									            	lblNombreDictionnaire_2.setText(""+new DocumentManager().getNombreLivres(con));
									         		lblNombreDictionnaire.setText(""+new DocumentManager().getNombreDictionnaires(con));
									         		lblNombreDictionnaire_1.setText(""+new DocumentManager().getNombreMagazine(con));
									         		
								            	 
								            	 	TablauxDocuments("documents");
								 	 				panel_gestion_livres.setVisible(true);
								 	 				panel_info_livre.setVisible(false);
								             }
										
									}
								});
								btnSupprimerLivre.setForeground(Color.RED);
								btnSupprimerLivre.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
								
								JButton btnNewButton111 = new JButton("Retour");
								btnNewButton111.setBounds(641, 0, 95, 34);
								panel_info_livre.add(btnNewButton111);
								btnNewButton111.setHorizontalAlignment(SwingConstants.LEFT);
								btnNewButton111.setIcon(new ImageIcon("D:\\lse econnes java project\\retour.png"));
								btnNewButton111.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										lblNombreDictionnaire_2.setText(""+new DocumentManager().getNombreLivres(con));
										lblNombreDictionnaire.setText(""+new DocumentManager().getNombreDictionnaires(con));
										lblNombreDictionnaire_1.setText(""+new DocumentManager().getNombreMagazine(con));
										
										TablauxDocuments("documents");
										panel_detaileAdherent.setVisible(false);
										panel_gestion_adherents.setVisible(false);
										panel_page_home.setVisible(false);
										panel_gestion_livres.setVisible(true);
										panel_info_livre.setVisible(false);
									}
								});
								btnNewButton111.setForeground(new Color(111, 111, 240));
								btnNewButton111.setFont(new Font("Tahoma", Font.BOLD, 15));
								btnNewButton111.setBackground(Color.LIGHT_GRAY);
								btnNewButton111.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(111, 111, 240)));
								
								JLabel lbl_NB_desponoble = new JLabel("Nombre    desponible :");
								lbl_NB_desponoble.setBounds(18, 194, 231, 28);
								panel_info_livre.add(lbl_NB_desponoble);
								lbl_NB_desponoble.setVerticalAlignment(SwingConstants.BOTTOM);
								lbl_NB_desponoble.setHorizontalAlignment(SwingConstants.LEFT);
								lbl_NB_desponoble.setForeground(new Color(220, 20, 60));
								lbl_NB_desponoble.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								
								JLabel lbl_Titre_livre = new JLabel("Titre :");
								lbl_Titre_livre.setBounds(18, 50, 70, 28);
								panel_info_livre.add(lbl_Titre_livre);
								lbl_Titre_livre.setForeground(new Color(220, 20, 60));
								lbl_Titre_livre.setVerticalAlignment(SwingConstants.BOTTOM);
								lbl_Titre_livre.setHorizontalAlignment(SwingConstants.LEFT);
								lbl_Titre_livre.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								
								lbl_V1DEtail = new JLabel("Nombre      Tomes :");
								lbl_V1DEtail.setBounds(18, 227, 213, 28);
								panel_info_livre.add(lbl_V1DEtail);
								lbl_V1DEtail.setVerticalAlignment(SwingConstants.BOTTOM);
								lbl_V1DEtail.setHorizontalAlignment(SwingConstants.LEFT);
								lbl_V1DEtail.setForeground(new Color(220, 20, 60));
								lbl_V1DEtail.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								
								JLabel lbl_Editeur_Livre = new JLabel("Editeur :");
								lbl_Editeur_Livre.setBounds(18, 86, 95, 28);
								panel_info_livre.add(lbl_Editeur_Livre);
								lbl_Editeur_Livre.setVerticalAlignment(SwingConstants.BOTTOM);
								lbl_Editeur_Livre.setHorizontalAlignment(SwingConstants.LEFT);
								lbl_Editeur_Livre.setForeground(new Color(220, 20, 60));
								lbl_Editeur_Livre.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								
								JLabel lb_Annee_Add = new JLabel("L'annee   d'adition :");
								lb_Annee_Add.setBounds(18, 122, 199, 28);
								panel_info_livre.add(lb_Annee_Add);
								lb_Annee_Add.setVerticalAlignment(SwingConstants.BOTTOM);
								lb_Annee_Add.setHorizontalAlignment(SwingConstants.LEFT);
								lb_Annee_Add.setForeground(new Color(220, 20, 60));
								lb_Annee_Add.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								
								Var_titreDocument = new JLabel("Titre 1");
								Var_titreDocument.setBounds(91, 50, 380, 28);
								panel_info_livre.add(Var_titreDocument);
								Var_titreDocument.setVerticalAlignment(SwingConstants.BOTTOM);
								Var_titreDocument.setHorizontalAlignment(SwingConstants.LEFT);
								Var_titreDocument.setForeground(new Color(30, 144, 255));
								Var_titreDocument.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								
								lbl_V1DEtailData = new JLabel("Type1");
								lbl_V1DEtailData.setBounds(222, 227, 393, 28);
								panel_info_livre.add(lbl_V1DEtailData);
								lbl_V1DEtailData.setVerticalAlignment(SwingConstants.BOTTOM);
								lbl_V1DEtailData.setHorizontalAlignment(SwingConstants.LEFT);
								lbl_V1DEtailData.setForeground(new Color(30, 144, 255));
								lbl_V1DEtailData.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								
								Var_NombreDespoDocument = new JLabel("33");
								Var_NombreDespoDocument.setBounds(249, 195, 190, 28);
								panel_info_livre.add(Var_NombreDespoDocument);
								Var_NombreDespoDocument.setVerticalAlignment(SwingConstants.BOTTOM);
								Var_NombreDespoDocument.setHorizontalAlignment(SwingConstants.LEFT);
								Var_NombreDespoDocument.setForeground(new Color(30, 144, 255));
								Var_NombreDespoDocument.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								
								Var_EditeurDocument = new JLabel("editeur1");
								Var_EditeurDocument.setBounds(123, 86, 499, 28);
								panel_info_livre.add(Var_EditeurDocument);
								Var_EditeurDocument.setVerticalAlignment(SwingConstants.BOTTOM);
								Var_EditeurDocument.setHorizontalAlignment(SwingConstants.LEFT);
								Var_EditeurDocument.setForeground(new Color(30, 144, 255));
								Var_EditeurDocument.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								
								Var_AnneeADDocument = new JLabel("26/01/2000");
								Var_AnneeADDocument.setBounds(219, 122, 298, 28);
								panel_info_livre.add(Var_AnneeADDocument);
								Var_AnneeADDocument.setVerticalAlignment(SwingConstants.BOTTOM);
								Var_AnneeADDocument.setHorizontalAlignment(SwingConstants.LEFT);
								Var_AnneeADDocument.setForeground(new Color(30, 144, 255));
								Var_AnneeADDocument.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								
								JLabel lbl_Titre_livre_1 = new JLabel("ISBN :");
								lbl_Titre_livre_1.setVerticalAlignment(SwingConstants.BOTTOM);
								lbl_Titre_livre_1.setHorizontalAlignment(SwingConstants.LEFT);
								lbl_Titre_livre_1.setForeground(new Color(220, 20, 60));
								lbl_Titre_livre_1.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								lbl_Titre_livre_1.setBounds(18, 15, 95, 28);
								panel_info_livre.add(lbl_Titre_livre_1);
								
								Var_ISBNDocument = new JLabel("&\u00E9\"'(");
								Var_ISBNDocument.setVerticalAlignment(SwingConstants.BOTTOM);
								Var_ISBNDocument.setHorizontalAlignment(SwingConstants.LEFT);
								Var_ISBNDocument.setForeground(new Color(30, 144, 255));
								Var_ISBNDocument.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								Var_ISBNDocument.setBounds(91, 15, 380, 28);
								panel_info_livre.add(Var_ISBNDocument);
								
								JLabel lbl_NB_Exemplaire_1 = new JLabel("Nombre    des    exemplaires :");
								lbl_NB_Exemplaire_1.setVerticalAlignment(SwingConstants.BOTTOM);
								lbl_NB_Exemplaire_1.setHorizontalAlignment(SwingConstants.LEFT);
								lbl_NB_Exemplaire_1.setForeground(new Color(220, 20, 60));
								lbl_NB_Exemplaire_1.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								lbl_NB_Exemplaire_1.setBounds(18, 158, 281, 28);
								panel_info_livre.add(lbl_NB_Exemplaire_1);
								
								Var_NombreExemplaire = new JLabel("33");
								Var_NombreExemplaire.setVerticalAlignment(SwingConstants.BOTTOM);
								Var_NombreExemplaire.setHorizontalAlignment(SwingConstants.LEFT);
								Var_NombreExemplaire.setForeground(new Color(30, 144, 255));
								Var_NombreExemplaire.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								Var_NombreExemplaire.setBounds(305, 158, 190, 28);
								panel_info_livre.add(Var_NombreExemplaire);
								
								JPanel panel_1 = new JPanel();
								panel_1.setForeground(new Color(255, 255, 255));
								panel_1.setBackground(new Color(128, 128, 128));
								panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(220, 20, 60), new Color(160, 160, 160)), "La liste des auteurs :", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 250, 250)));
								panel_1.setBounds(18, 320, 678, 134);
								panel_info_livre.add(panel_1);
								panel_1.setLayout(null);
								
								JScrollPane scrollPane = new JScrollPane();
								scrollPane.setBounds(10, 15, 656, 109);
								panel_1.add(scrollPane);
								
								JPanel panel = new JPanel();
								scrollPane.setViewportView(panel);
								panel.setBackground(new Color(220, 20, 60));
								panel.setForeground(Color.BLUE);
								panel.setLayout(null);
								
								Var_AtteurLivre_2 = new JLabel("ADNANE  EZ-ZAIM");
								Var_AtteurLivre_2.setBounds(10, 70, 285, 28);
								panel.add(Var_AtteurLivre_2);
								Var_AtteurLivre_2.setHorizontalAlignment(SwingConstants.LEFT);
								Var_AtteurLivre_2.setForeground(new Color(255, 255, 255));
								Var_AtteurLivre_2.setFont(new Font("Urdu Typesetting", Font.BOLD, 18));
								
								Var_AtteurLivre = new JLabel("ADNANE  EZ-ZAIM");
								Var_AtteurLivre.setBounds(10, 10, 428, 28);
								panel.add(Var_AtteurLivre);
								Var_AtteurLivre.setHorizontalAlignment(SwingConstants.LEFT);
								Var_AtteurLivre.setForeground(new Color(255, 255, 255));
								Var_AtteurLivre.setFont(new Font("Urdu Typesetting", Font.BOLD, 18));
								
								Var_AtteurLivre_1 = new JLabel("ADNANE  EZ-ZAIM");
								Var_AtteurLivre_1.setBounds(10, 40, 428, 28);
								panel.add(Var_AtteurLivre_1);
								Var_AtteurLivre_1.setHorizontalAlignment(SwingConstants.LEFT);
								Var_AtteurLivre_1.setForeground(new Color(255, 255, 255));
								Var_AtteurLivre_1.setFont(new Font("Urdu Typesetting", Font.BOLD, 18));
								
								 Var_AtteurLivre_3 = new JLabel("ADNANE  EZ-ZAIM");
								 Var_AtteurLivre_3.setBounds(292, 10, 428, 28);
								 panel.add(Var_AtteurLivre_3);
								 Var_AtteurLivre_3.setHorizontalAlignment(SwingConstants.LEFT);
								 Var_AtteurLivre_3.setForeground(new Color(255, 255, 255));
								 Var_AtteurLivre_3.setFont(new Font("Urdu Typesetting", Font.BOLD, 18));
								 
								 Var_AtteurLivre_4 = new JLabel("ADNANE  EZ-ZAIM");
								 Var_AtteurLivre_4.setBounds(292, 40, 428, 28);
								 panel.add(Var_AtteurLivre_4);
								 Var_AtteurLivre_4.setHorizontalAlignment(SwingConstants.LEFT);
								 Var_AtteurLivre_4.setForeground(new Color(255, 255, 255));
								 Var_AtteurLivre_4.setFont(new Font("Urdu Typesetting", Font.BOLD, 18));
								
								 lbl_V2DEtail = new JLabel("Langue :");
								 lbl_V2DEtail.setVerticalAlignment(SwingConstants.BOTTOM);
								 lbl_V2DEtail.setHorizontalAlignment(SwingConstants.LEFT);
								 lbl_V2DEtail.setForeground(new Color(220, 20, 60));
								 lbl_V2DEtail.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								 lbl_V2DEtail.setBounds(18, 263, 109, 28);
								 panel_info_livre.add(lbl_V2DEtail);
								 
								 lbl_V2DEtaildData = new JLabel("Type1");
								 lbl_V2DEtaildData.setVerticalAlignment(SwingConstants.BOTTOM);
								 lbl_V2DEtaildData.setHorizontalAlignment(SwingConstants.LEFT);
								 lbl_V2DEtaildData.setForeground(new Color(30, 144, 255));
								 lbl_V2DEtaildData.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								 lbl_V2DEtaildData.setBounds(105, 263, 393, 28);
								 panel_info_livre.add(lbl_V2DEtaildData);
								
								  lbl_V3DEtail = new JLabel("Jour :");
								 lbl_V3DEtail.setVerticalAlignment(SwingConstants.BOTTOM);
								 lbl_V3DEtail.setHorizontalAlignment(SwingConstants.LEFT);
								 lbl_V3DEtail.setForeground(new Color(220, 20, 60));
								 lbl_V3DEtail.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								 lbl_V3DEtail.setBounds(18, 295, 84, 28);
								 panel_info_livre.add(lbl_V3DEtail);
								 
								  lbl_V3DEtailData = new JLabel("Type1");
								 lbl_V3DEtailData.setVerticalAlignment(SwingConstants.BOTTOM);
								 lbl_V3DEtailData.setHorizontalAlignment(SwingConstants.LEFT);
								 lbl_V3DEtailData.setForeground(new Color(30, 144, 255));
								 lbl_V3DEtailData.setFont(new Font("Urdu Typesetting", Font.BOLD, 26));
								 lbl_V3DEtailData.setBounds(91, 295, 393, 28);
								 panel_info_livre.add(lbl_V3DEtailData);
									
									
									
									
									
									
													
								
									
						
									
									
									
									
									
									
									
									
									
									
									
									
									
								
								
								
								

//2-2 ###################### ===>le contenu de la page detaille Adhrents :
		
						
						JButton btnNewButton_1111 = new JButton("Retour");
						btnNewButton_1111.setBounds(620, 5, 95, 34);
						btnNewButton_1111.setIcon(new ImageIcon("D:\\lse econnes java project\\retour.png"));
						btnNewButton_1111.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								lblNombrePeressones.setText(""+new DocumentManager().getNombrePerssonnes(con));
								lblNombreEtudiants.setText(""+new DocumentManager().getNombreEtudiant(con));
								lblNombreProfisseurs.setText(""+new DocumentManager().getNombreProfesseur(con));
								
								panel_page_home.setVisible(false);
								panel_info_livre.setVisible(false);
								panel_gestion_livres.setVisible(false);
								panel_detaileAdherent.setVisible(false);
								panel_gestion_adherents.setVisible(true);
							}
						});
						btnNewButton_1111.setHorizontalAlignment(SwingConstants.LEFT);
						btnNewButton_1111.setForeground(new Color(111, 111, 240));
						btnNewButton_1111.setFont(new Font("Tahoma", Font.BOLD, 15));
						btnNewButton_1111.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(111, 111, 240)));
						btnNewButton_1111.setBackground(Color.LIGHT_GRAY);
						panel_detaileAdherent.add(btnNewButton_1111);
						
			//////  ------------------------ REDEMENTIONER L'IMAGE DE L'ADHERENT ------------------------  \\\\\\
						
											BufferedImage master = null;
											try {
												master = ImageIO.read(new File("C:\\Users\\ACER\\Downloads\\user (1).png"));
											} catch (IOException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
									
										    BufferedImage mask = new BufferedImage(150,150, BufferedImage.TYPE_INT_ARGB);
									
										    Graphics2D g2d = mask.createGraphics();
										    applyQualityRenderingHints(g2d);
										    g2d.fillOval(0, 0, 150 - 1, 150 - 1);
										    g2d.dispose();
									
										    BufferedImage masked = new BufferedImage(150, 150, BufferedImage.TYPE_INT_ARGB);
										    g2d = masked.createGraphics();
										    applyQualityRenderingHints(g2d);
										    int x = (150 - master.getWidth())/2 ;
										    int y = (150 - master.getHeight())/2 ;//// 2
										    g2d.drawImage(master, x, y, null);
										    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
										    g2d.drawImage(mask, 0, 0, null);
										    g2d.drawRenderedImage(masked, null);
										    g2d.dispose();
						
						JLabel lbl_Auteurs_1_1 = new JLabel("Nombre     des     documents      emprunter    :");
						lbl_Auteurs_1_1.setBounds(25, 5, 290, 28);
						lbl_Auteurs_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
						lbl_Auteurs_1_1.setHorizontalAlignment(SwingConstants.LEFT);
						lbl_Auteurs_1_1.setForeground(new Color(204, 0, 51));
						lbl_Auteurs_1_1.setFont(new Font("Urdu Typesetting", Font.BOLD, 18));
						panel_detaileAdherent.add(lbl_Auteurs_1_1);
						
						Var_NombreEmp = new JLabel("");
						Var_NombreEmp.setBounds(319, 5, 65, 28);
						Var_NombreEmp.setVerticalAlignment(SwingConstants.BOTTOM);
						Var_NombreEmp.setHorizontalAlignment(SwingConstants.LEFT);
						Var_NombreEmp.setForeground(new Color(0, 0, 0));
						Var_NombreEmp.setFont(new Font("Urdu Typesetting", Font.BOLD, 20));
						panel_detaileAdherent.add(Var_NombreEmp);
						
						JPanel User_info_panal_1 = new JPanel();
						User_info_panal_1.setBounds(28, 40, 681, 196);
						User_info_panal_1.setBackground(new Color(204, 0, 51));
						panel_detaileAdherent.add(User_info_panal_1);
						User_info_panal_1.setLayout(null);
						
						JLabel lbl_AdresseMail = new JLabel("Adresse         mail :");
						lbl_AdresseMail.setBounds(10, 60, 127, 28);
						User_info_panal_1.add(lbl_AdresseMail);
						lbl_AdresseMail.setVerticalAlignment(SwingConstants.BOTTOM);
						lbl_AdresseMail.setHorizontalAlignment(SwingConstants.LEFT);
						lbl_AdresseMail.setForeground(new Color(255, 255, 255));
						lbl_AdresseMail.setFont(new Font("Urdu Typesetting", Font.BOLD, 19));
						
						JLabel lb_AdressePreso = new JLabel("Adresse     personnel :");
						lb_AdressePreso.setBounds(10, 88, 161, 28);
						User_info_panal_1.add(lb_AdressePreso);
						lb_AdressePreso.setVerticalAlignment(SwingConstants.BOTTOM);
						lb_AdressePreso.setHorizontalAlignment(SwingConstants.LEFT);
						lb_AdressePreso.setForeground(new Color(255, 255, 255));
						lb_AdressePreso.setFont(new Font("Urdu Typesetting", Font.BOLD, 19));
						
						JLabel lbl_Auteurs_1 = new JLabel("Numero      Telephone  :");
						lbl_Auteurs_1.setBounds(10, 116, 175, 28);
						User_info_panal_1.add(lbl_Auteurs_1);
						lbl_Auteurs_1.setVerticalAlignment(SwingConstants.BOTTOM);
						lbl_Auteurs_1.setHorizontalAlignment(SwingConstants.LEFT);
						lbl_Auteurs_1.setForeground(new Color(255, 255, 255));
						lbl_Auteurs_1.setFont(new Font("Urdu Typesetting", Font.BOLD, 19));
						
						Var_MailAdh = new JLabel("adnane.ezzaim@gmail.com");
						Var_MailAdh.setBounds(134, 59, 537, 28);
						User_info_panal_1.add(Var_MailAdh);
						Var_MailAdh.setVerticalAlignment(SwingConstants.BOTTOM);
						Var_MailAdh.setHorizontalAlignment(SwingConstants.LEFT);
						Var_MailAdh.setForeground(new Color(0, 0, 0));
						Var_MailAdh.setFont(new Font("Urdu Typesetting", Font.BOLD, 17));
						
						 Var_adressePer = new JLabel("vile,   rue 100,  Maroc");
						 Var_adressePer.setBounds(176, 87, 490, 28);
						 User_info_panal_1.add(Var_adressePer);
						 Var_adressePer.setVerticalAlignment(SwingConstants.BOTTOM);
						 Var_adressePer.setHorizontalAlignment(SwingConstants.LEFT);
						 Var_adressePer.setForeground(new Color(0, 0, 0));
						 Var_adressePer.setFont(new Font("Urdu Typesetting", Font.BOLD, 17));
						
						  Var_NumeroTele = new JLabel("0601198272");
						  Var_NumeroTele.setBounds(183, 117, 476, 28);
						  User_info_panal_1.add(Var_NumeroTele);
						  Var_NumeroTele.setVerticalAlignment(SwingConstants.BOTTOM);
						  Var_NumeroTele.setHorizontalAlignment(SwingConstants.LEFT);
						  Var_NumeroTele.setForeground(new Color(0, 0, 0));
						  Var_NumeroTele.setFont(new Font("Urdu Typesetting", Font.BOLD, 22));
						  
						  JLabel lbl_AdresseMail_1 = new JLabel("Nom    :");
						  lbl_AdresseMail_1.setVerticalAlignment(SwingConstants.BOTTOM);
						  lbl_AdresseMail_1.setHorizontalAlignment(SwingConstants.LEFT);
						  lbl_AdresseMail_1.setForeground(Color.WHITE);
						  lbl_AdresseMail_1.setFont(new Font("Urdu Typesetting", Font.BOLD, 19));
						  lbl_AdresseMail_1.setBounds(10, 4, 61, 28);
						  User_info_panal_1.add(lbl_AdresseMail_1);
						  
						  Var_NomADhrent = new JLabel("adnane.ezzaim@gmail.com");
						  Var_NomADhrent.setVerticalAlignment(SwingConstants.BOTTOM);
						  Var_NomADhrent.setHorizontalAlignment(SwingConstants.LEFT);
						  Var_NomADhrent.setForeground(Color.BLACK);
						  Var_NomADhrent.setFont(new Font("Urdu Typesetting", Font.BOLD, 17));
						  Var_NomADhrent.setBounds(66, 3, 590, 28);
						  User_info_panal_1.add(Var_NomADhrent);
						  
						  JLabel lbl_AdresseMail_2 = new JLabel("Prenom    :");
						  lbl_AdresseMail_2.setVerticalAlignment(SwingConstants.BOTTOM);
						  lbl_AdresseMail_2.setHorizontalAlignment(SwingConstants.LEFT);
						  lbl_AdresseMail_2.setForeground(Color.WHITE);
						  lbl_AdresseMail_2.setFont(new Font("Urdu Typesetting", Font.BOLD, 19));
						  lbl_AdresseMail_2.setBounds(10, 32, 79, 28);
						  User_info_panal_1.add(lbl_AdresseMail_2);
						  
						  Var_PrenomAdhrents = new JLabel("adnane.ezzaim@gmail.com");
						  Var_PrenomAdhrents.setVerticalAlignment(SwingConstants.BOTTOM);
						  Var_PrenomAdhrents.setHorizontalAlignment(SwingConstants.LEFT);
						  Var_PrenomAdhrents.setForeground(Color.BLACK);
						  Var_PrenomAdhrents.setFont(new Font("Urdu Typesetting", Font.BOLD, 17));
						  Var_PrenomAdhrents.setBounds(91, 31, 572, 28);
						  User_info_panal_1.add(Var_PrenomAdhrents);
						  
						  Var_CNE = new JLabel("N136174200");
						  Var_CNE.setVerticalAlignment(SwingConstants.BOTTOM);
						  Var_CNE.setHorizontalAlignment(SwingConstants.LEFT);
						  Var_CNE.setForeground(Color.BLACK);
						  Var_CNE.setFont(new Font("Urdu Typesetting", Font.BOLD, 22));
						  Var_CNE.setBounds(66, 166, 590, 28);
						  User_info_panal_1.add(Var_CNE);
						  
						  lbl_CNE = new JLabel("CNE    :");
						  lbl_CNE.setVerticalAlignment(SwingConstants.BOTTOM);
						  lbl_CNE.setHorizontalAlignment(SwingConstants.LEFT);
						  lbl_CNE.setForeground(Color.WHITE);
						  lbl_CNE.setFont(new Font("Urdu Typesetting", Font.BOLD, 19));
						  lbl_CNE.setBounds(10, 165, 68, 28);
						  User_info_panal_1.add(lbl_CNE);
						  
						  Var_CodeAdherent = new JLabel("N136174200");
						  Var_CodeAdherent.setVerticalAlignment(SwingConstants.BOTTOM);
						  Var_CodeAdherent.setHorizontalAlignment(SwingConstants.LEFT);
						  Var_CodeAdherent.setForeground(Color.BLACK);
						  Var_CodeAdherent.setFont(new Font("Urdu Typesetting", Font.BOLD, 22));
						  Var_CodeAdherent.setBounds(183, 142, 496, 28);
						  User_info_panal_1.add(Var_CodeAdherent);
						  
						  JLabel lbl_CodeAdh = new JLabel("Code     de      l'adherent    :");
						  lbl_CodeAdh.setVerticalAlignment(SwingConstants.BOTTOM);
						  lbl_CodeAdh.setHorizontalAlignment(SwingConstants.LEFT);
						  lbl_CodeAdh.setForeground(Color.WHITE);
						  lbl_CodeAdh.setFont(new Font("Urdu Typesetting", Font.BOLD, 19));
						  lbl_CodeAdh.setBounds(10, 141, 171, 28);
						  User_info_panal_1.add(lbl_CodeAdh);
						
						scrollPane_DetailAhd = new JScrollPane();
						scrollPane_DetailAhd.setBounds(28, 279, 681, 180);
						panel_detaileAdherent.add(scrollPane_DetailAhd);
						
						table_EmpADhDetaile = new JTable();
						table_EmpADhDetaile.setForeground(new Color(0, 0, 0));
						table_EmpADhDetaile.setRowHeight(25);
						scrollPane_DetailAhd.setViewportView(table_EmpADhDetaile);
						table_EmpADhDetaile.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								
								int selectRowIndex=table_EmpADhDetaile.getSelectedRow();
								try {
									
									
									if(JOptionPane.showConfirmDialog(null,"Rendre ce livre ?"
						                     ,"Rendre Livre ?",JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
								
						 	 				DefaultTableModel model1=(DefaultTableModel) table_EmpADhDetaile.getModel();
											int selectRowIndex1=table_EmpADhDetaile.getSelectedRow();
											//Var_NumeroTele, Var_adressePer;
											System.out.println("ha hiya hadi "+model1.getValueAt(selectRowIndex, 8).toString());
											int i=Integer.parseInt(model1.getValueAt(selectRowIndex, 8).toString());
											i++;
											
											pst=con.prepareStatement("update document set Nombre_Desponeble=? where id="+model1.getValueAt(selectRowIndex, 4).toString());
											pst.setString(1,i+"");
											pst.executeUpdate();
											System.out.println("le nombre desbo"+i);
											
											System.out.println("hs hiya hadi"+model1.getValueAt(selectRowIndex1, 0).toString());
											pst=con.prepareStatement("delete from emprunt where id="+model1.getValueAt(selectRowIndex1, 0).toString());
						 	 				pst.executeUpdate();
											System.out.println("ha hiya wsselat t al hna");
											TablauxDocuments("documents");
						 	 				
						 	 				table_EmpADhDetaile.getSelectionModel().clearSelection();
											TableauxDocumentAdh(adhSelectionner);
//											panel_gestion_adherents.setVisible(false);
//											panel_detaileAdherent.setVisible(true);
//											table_adherents.getSelectionModel().clearSelection();
						             }
						             
						             
				
						        }catch (Exception e1){
						        	JOptionPane.showMessageDialog(null,"erreur f emprunt \n"+e1.getMessage());
						        }
								
							}
						});
						table_EmpADhDetaile.setBackground(new Color(255, 255, 255));
						
						JButton ajouterEmprunt = new JButton("ajouter emprunt");
						ajouterEmprunt.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								AjouterEmpruntFormule();
							}
						});
						ajouterEmprunt.setFont(new Font("Tahoma", Font.BOLD, 13));
						ajouterEmprunt.setBackground(new Color(211, 211, 211));
						ajouterEmprunt.setForeground(new Color(34, 139, 34));
						ajouterEmprunt.setBounds(566, 240, 139, 34);
						ajouterEmprunt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 100, 0)));
						panel_detaileAdherent.add(ajouterEmprunt);
						
						JButton btnModifierLivre_1 = new JButton("Modifier");
						btnModifierLivre_1.setBounds(584, 469, 131, 34);
						btnModifierLivre_1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(typeAdhSelected.equals("personne"))
									FormuleAddPersonne("d");
								
							}
						});
						panel_detaileAdherent.setLayout(null);
						btnModifierLivre_1.setIcon(new ImageIcon("D:\\lse econnes java project\\modifier.png"));
						btnModifierLivre_1.setForeground(new Color(255, 140, 0));
						btnModifierLivre_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
						btnModifierLivre_1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.ORANGE));
						btnModifierLivre_1.setBackground(Color.LIGHT_GRAY);
						panel_detaileAdherent.add(btnModifierLivre_1);
						
						JButton btnSupprimerLivre_1 = new JButton("Supprimer");
						btnSupprimerLivre_1.setBounds(444, 469, 131, 34);
						btnSupprimerLivre_1.setIcon(new ImageIcon("D:\\lse econnes java project\\supprimer.png"));
						btnSupprimerLivre_1.setBackground(Color.LIGHT_GRAY);
						//btnSupprimerLivre.setBackground(Color.RED);
						btnSupprimerLivre_1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
						btnSupprimerLivre_1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									
						             if(JOptionPane.showConfirmDialog(null,"attention vous avez supprimer un adherent,vous etes sure?"
						                     ,"supprimer adadherent ?",JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
						            	 if(table_EmpADhDetaile.getModel().getRowCount()!=0) {
						            		 JOptionPane.showMessageDialog(null, " errer : il doit rendre d'abord tous les livres !");
						            	 }
						            	 else {
						            		
						            		new DocumentManager().supprimerPersonne(adhSelectionner,con);
						            		
						            		lblNombrePeressones.setText(""+new DocumentManager().getNombrePerssonnes(con));
						            		lblNombreEtudiants.setText(""+new DocumentManager().getNombreEtudiant(con));
						            		lblNombreProfisseurs.setText(""+new DocumentManager().getNombreProfesseur(con));
						            		
						            		TableauxAdherents("Tous");
						            		
							 	 			panel_gestion_adherents.setVisible(true);
							 	 			panel_detaileAdherent.setVisible(false);
							 	 			
						            	 }
						 	 				
						             }
				
						        }catch (Exception e1){JOptionPane.showMessageDialog(null,"erreur de supprimer \n"+e1.getMessage());} 
								
							}
						});
						btnSupprimerLivre_1.setForeground(Color.RED);
						btnSupprimerLivre_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
						panel_detaileAdherent.add(btnSupprimerLivre_1);
						
		
		
		
						
						
						
						
						
						
						
						
		
//===>le panale qui va contient les information de USER		
			
		JPanel User_info_panal = new JPanel();
		User_info_panal.setBounds(544, 0, 447, 48);
		User_info_panal.setBackground(new Color(204, 0, 51));
		panel_principale.add(User_info_panal);

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		////////////////////////////////////////#----------->> LE CONTENU DE LA PAGE PRINCIPALE  <<---------#\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		
		
		
		
		

 //#########################====>> LA PAGE HOME : <<====#################################
								JLabel Ensais_Logo = new JLabel("ENSIAS");
								Ensais_Logo.setForeground(new Color(204, 0, 51));
								Ensais_Logo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 28));
								Ensais_Logo.setBounds(235, 13, 122, 44);
								panel_principale.add(Ensais_Logo);
								
								JButton btnHome = new JButton("Home");
								btnHome.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										panel_gestion_livres.setVisible(false);
										panel_info_livre.setVisible(false);
										panel_gestion_adherents.setVisible(false);
										panel_detaileAdherent.setVisible(false);
										panel_gestion_Emprunt.setVisible(false);
										panel_page_home.setVisible(true);
										
										
										btnHome.setBackground(new Color(240, 240, 240));
										btnHome.setForeground(new Color(204, 0, 0));
										btnNewButton111.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(204, 0, 0)));
										btnHome.setOpaque(true);
										btnHome.setFocusPainted(true);
										btnHome.setBorderPainted(true);
										btnHome.setContentAreaFilled(true);
									}
								});
								btnHome.setHorizontalAlignment(SwingConstants.LEFT);
								btnHome.setForeground(new Color(255, 255, 255));
								btnHome.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
								btnHome.setBounds(0, 114, 232, 34);
								btnHome.setOpaque(false);
								btnHome.setFocusPainted(false);
								btnHome.setBorderPainted(false);
								btnHome.setContentAreaFilled(false);
								
								panel_principale.add(btnHome);
								btnHome.setBackground(new Color(204, 0, 0));
								
								JPanel panel_aside = new JPanel();
								panel_aside.setBackground(new Color(204, 0, 51));
								panel_aside.setBounds(0, 0, 232, 592);
								panel_principale.add(panel_aside);
								panel_aside.setLayout(null);
								
								JLabel Biblio_Logo = new JLabel("Bibliotheque ");
								Biblio_Logo.setBounds(9, 5, 214, 64);
								panel_aside.add(Biblio_Logo);
								Biblio_Logo.setIcon(new ImageIcon("C:\\Users\\ACER\\Downloads\\literature_thebook_3708 (1) (1).png"));
								Biblio_Logo.setHorizontalAlignment(SwingConstants.RIGHT);
								Biblio_Logo.setForeground(Color.WHITE);
								Biblio_Logo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
								Biblio_Logo.setBackground(new Color(0, 204, 102));
								
								JButton btnGestionLivres = new JButton("Gestion Livres");
								btnGestionLivres.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										panel_info_livre.setVisible(false);
										panel_page_home.setVisible(false);
										panel_gestion_adherents.setVisible(false);
										panel_detaileAdherent.setVisible(false);
										panel_gestion_Emprunt.setVisible(false);
										panel_gestion_livres.setVisible(true);
									}
								});
								btnGestionLivres.setBounds(0, 161, 232, 34);
								panel_aside.add(btnGestionLivres);
								btnGestionLivres.setOpaque(false);
								btnGestionLivres.setHorizontalAlignment(SwingConstants.LEFT);
								btnGestionLivres.setForeground(Color.WHITE);
								btnGestionLivres.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
								btnGestionLivres.setFocusPainted(false);
								btnGestionLivres.setContentAreaFilled(false);
								btnGestionLivres.setBorderPainted(false);
								btnGestionLivres.setBackground(new Color(204, 0, 0));
								
								JButton btnGestionEmprunt = new JButton("Gestion Emprinte");
								btnGestionEmprunt.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										panel_info_livre.setVisible(false);
										panel_page_home.setVisible(false);
										panel_gestion_adherents.setVisible(false);
										panel_detaileAdherent.setVisible(false);
										panel_gestion_livres.setVisible(false);
										panel_gestion_Emprunt.setVisible(true);
									}
								});
								btnGestionEmprunt.setOpaque(false);
								btnGestionEmprunt.setHorizontalAlignment(SwingConstants.LEFT);
								btnGestionEmprunt.setForeground(Color.WHITE);
								btnGestionEmprunt.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
								btnGestionEmprunt.setFocusPainted(false);
								btnGestionEmprunt.setContentAreaFilled(false);
								btnGestionEmprunt.setBorderPainted(false);
								btnGestionEmprunt.setBackground(new Color(204, 0, 0));
								btnGestionEmprunt.setBounds(0, 208, 232, 34);
								panel_aside.add(btnGestionEmprunt);
								
								JButton btnGestionAdhs = new JButton("Gestion adherents");
								btnGestionAdhs.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										panel_gestion_Emprunt.setVisible(false);
										panel_info_livre.setVisible(false);
										panel_page_home.setVisible(false);
										panel_gestion_livres.setVisible(false);
										panel_detaileAdherent.setVisible(false);
										panel_gestion_adherents.setVisible(true);
									}
								});
								btnGestionAdhs.setOpaque(false);
								btnGestionAdhs.setHorizontalAlignment(SwingConstants.LEFT);
								btnGestionAdhs.setForeground(Color.WHITE);
								btnGestionAdhs.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
								btnGestionAdhs.setFocusPainted(false);
								btnGestionAdhs.setContentAreaFilled(false);
								btnGestionAdhs.setBorderPainted(false);
								btnGestionAdhs.setBackground(new Color(204, 0, 0));
								btnGestionAdhs.setBounds(0, 255, 232, 34);
								panel_aside.add(btnGestionAdhs);
								
								
					//#########################################################################################\\		
									
									
									
	}
	
	
	public static void applyQualityRenderingHints(Graphics2D g2d) {

	    g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

	}
}
