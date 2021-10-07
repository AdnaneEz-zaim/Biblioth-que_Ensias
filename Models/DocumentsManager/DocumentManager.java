package DocumentsManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;


public class DocumentManager {
	private static Connection con;
	Connection connection;
    Statement statement;

	private PreparedStatement pst;
	private ResultSet rs;
	String host,username,password;
	
	
	
	
public static Connection getCon() {
		return con;
	}


	public static void setCon(Connection con) {
		DocumentManager.con = con;
	}

	public PreparedStatement getPst() {
		return pst;
	}


	public void setPst(PreparedStatement pst) {
		this.pst = pst;
	}


	public ResultSet getRs() {
		return rs;
	}


	public void setRs(ResultSet rs) {
		this.rs = rs;
	}


	//la connection a la base de donnees
	public Connection getCnDataBase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(host, username, password);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
		}
		catch(ClassNotFoundException cl){
		  JOptionPane.showMessageDialog(null, " errer de connection ");	
		}
		
		return con;
	}
	
	
	// la fermeture de la base de donnees
	
	public Connection closeconnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	//l'execution des requite SQL
	public ResultSet SqlRequite(String sql) {
		getCnDataBase();
		ResultSet reulta=null;
		try {
			reulta = con.prepareStatement(sql).executeQuery();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
			e.printStackTrace();
		}
		return reulta;
	
	}
	
	
	

	
//**********************************************************************************************************************************\\
	//------------------------------ les detaille ---------------------------\\
	// la detaile des documents :
	public ResultSet getDetailleDocuments(Connection conect,String id) {
			ResultSet	reulta = null;
			try {
					reulta= conect.prepareStatement("select * from document where id="+id).executeQuery();
	
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
					e.printStackTrace();
				}
				
				return reulta;
		}
	
	public ResultSet getDetailleLivres(Connection conect,String id) {
				ResultSet	reulta = null;
				try {
						reulta= conect.prepareStatement("select * from livre id="+id).executeQuery();
		
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
						e.printStackTrace();
					}
					
					return reulta;
		}
//la liste des dictionaire 
			public ResultSet getDetailleDictionaires(Connection conect,String id) {
				ResultSet	reulta = null;
				try {
						reulta= conect.prepareStatement("select * from dictionnaire id="+id).executeQuery();
		
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
						e.printStackTrace();
					}
					
					return reulta;
		}
		
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\
	public ResultSet getDocuments(Connection conect) {
		ResultSet	reulta = null;
		try {
				reulta= conect.prepareStatement("select * from document").executeQuery();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
				e.printStackTrace();
			}
			
			return reulta;
	}
	
	public ResultSet getLivres(Connection conect) {
		ResultSet	reulta = null;
		try {
				reulta= conect.prepareStatement("select * from document where Type_Document='livre'").executeQuery();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
				e.printStackTrace();
			}
			
			return reulta;
}
//la liste des dictionaire 
	public ResultSet getDictionaires(Connection conect) {
		ResultSet	reulta = null;
		try {
				reulta= conect.prepareStatement("select * from document where Type_Document='dictionnaire'").executeQuery();
				System.out.println("rah wsslat l hna");

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
				e.printStackTrace();
			}
			
			return reulta;
}

	//la liste des Magazine
			public ResultSet getMagazines(Connection conect) {
				ResultSet	reulta = null;
				try {
						reulta= conect.prepareStatement("select * from document where Type_Document= 'magazine'").executeQuery();
						System.out.println("rah wsslat l hna");

					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
						e.printStackTrace();
					}
					
					return reulta;
		}	
	
public ResultSet  getListeDocumentsADh(Connection conect,String id) {
	ResultSet	reulta = null;
	try {
			reulta= conect.prepareStatement("select * from emprunt where idAdh="+id).executeQuery();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
			e.printStackTrace();
		}
		
		return reulta;
}


//------------------------------ les fonction des nombres des documents ---------------------------\\
//la liste des documents :
	public int getNombreDictionnaires(Connection conect) {
		int	nb = 0;
		try {
			nb=DbUtils.resultSetToTableModel(conect.prepareStatement("select * from document where Type_Document='dictionnaire'").executeQuery()).getRowCount();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
				e.printStackTrace();
			}
			
			return nb;
	}
	
	public int getNombreLivres(Connection conect) {
		int	nb = 0;
		try {
			nb=DbUtils.resultSetToTableModel(conect.prepareStatement("select * from document where Type_Document='livre'").executeQuery()).getRowCount();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
				e.printStackTrace();
			}
			
			return nb;
	}
	public int getNombreMagazine(Connection conect) {
		int	nb = 0;
		try {
			nb=DbUtils.resultSetToTableModel(conect.prepareStatement("select * from document where Type_Document= 'magazine'").executeQuery()).getRowCount();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
				e.printStackTrace();
			}
			
			return nb;
	}
	

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\

	
	
//--------------------------------------- L'ajoute -----------------------------------\\		
		
		public void AjouterLivre(String[] t,Connection conect) {
			try {
				//ta nzide hna l'auteur w l id
				JOptionPane.showMessageDialog(null, "9bal madir la requite");
				pst=conect.prepareStatement("INSERT INTO livre( ISBN, titre, editeur, anneeAdition, nombre_Exemplaire, nombre_Pages,type) VALUES (?, ?, ?, ?, ?, ?, ?);");
				pst.setString(1,t[0]);
				pst.setString(2,t[1]);
				pst.setString(3,t[2]);
				pst.setString(4,t[3]);
				pst.setString(5,t[4]);
				pst.setString(6,t[5]);
				pst.setString(7,t[6]);//ta l'auteur
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "mn ba3d ma darat requit madir la requite");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
		public void AjouterMagazine(Magazine l) {
			try {
				pst=con.prepareStatement("INSERT INTO Magazine( TitreD, typeD, nomauteur, prenomauteur, nbrexemplaire, edeteur, annesAdition) VALUES (?, ?, ?, ?, ?, ?, ?);");
				pst.setString(1,l.getTitre());
				pst.setString(2,l.getAnneeAdition());
				pst.setString(3,l.getEditeur());
				pst.setString(4,l.getISBN());
				//pst.setString(5,l.getTome());
				//pst.setString(6,l.getTypeLivres());
				pst.setString(7,""+l.getNbExemplaire());//ta l'auteur
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		public void AjouterDictionnaire(Dictionnaire l) {
			try {
				pst=con.prepareStatement("INSERT INTO Dictionnaire( TitreD, typeD, nomauteur, prenomauteur, nbrexemplaire, edeteur, annesAdition) VALUES (?, ?, ?, ?, ?, ?, ?);");
				pst.setString(1,l.getTitre());
				pst.setString(2,l.getAnneeAdition());
				pst.setString(3,l.getEditeur());
				pst.setString(4,l.getISBN());
				//pst.setString(5,l.getTome());
				//pst.setString(6,l.getTypeLivres());
				pst.setString(7,""+l.getNbExemplaire());//ta l'auteur
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		
//---------------------     la modification    ------------------------- \\
		
		
		//9bal ra ykon teste 3la la class dyal l object
		public PreparedStatement ModifierLivre( Livre l) {
			try {
				pst=con.prepareStatement("update Livre set TitreD=?, typeD=?, nomauteur=?, prenomauteur=?, nbrexemplaire=?, edeteur=?, annesAdition=?"
						+ "where id=12");
				//hna khasseni n3raf njib l'id dyal document
				pst.setString(1,l.getTitre());
				pst.setString(2,l.getAnneeAdition());
				pst.setString(3,l.getISBN());
				pst.setString(4,""+l.getEtatDoc());
				pst.setString(5,""+l.getNbExemplaire());
				pst.setString(6,""+l.getNumerodoc());
				pst.setString(7,l.getEditeur());
				pst.executeUpdate();
				//hado ba3d lmodification ta nxof ax ra ndir wax f controller wla hna 
				
//				Var_titreLivre.setText(Titre);
//				Var_typeLivre.setText(Type);
//				Var_NombreLivre.setText(Nombreexemplaire);
//				Var_EditeurLivre.setText(Edeteur);
//				Var_AnneeADDLivre.setText(AnneeAdt);
//				Var_AtteurLivre.setText(PrenomAtr+" "+NonAteur);
//				table_load();
//				viderInputTexteFilied();
//				frame.setEnabled(true);
//				d.dispose();
				JOptionPane.showMessageDialog(null, "Ce document est bien modifier");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "erreur : la modification n'est pas appliquer !");
				e1.printStackTrace();
			}
			
			return pst;
			
		}
		
		
//----> la modification d'un magazine	
		public void ModifierMagazine( Magazine l) {
			try {
				pst=con.prepareStatement("update Magazine set TitreD=?, typeD=?, nomauteur=?, prenomauteur=?, nbrexemplaire=?, edeteur=?, annesAdition=?"
						+ "where id=12");
				//hna khasseni n3raf njib l'id dyal document
				pst.setString(1,l.getTitre());
				pst.setString(2,l.getAnneeAdition());
				pst.setString(3,l.getISBN());
				pst.setString(4,""+l.getEtatDoc());
				pst.setString(5,""+l.getNbExemplaire());
				pst.setString(6,""+l.getNumerodoc());
				pst.setString(7,l.getEditeur());
				pst.executeUpdate();
				//hado ba3d lmodification ta nxof ax ra ndir wax f controller wla hna 
				
//				Var_titreLivre.setText(Titre);
//				Var_typeLivre.setText(Type);
//				Var_NombreLivre.setText(Nombreexemplaire);
//				Var_EditeurLivre.setText(Edeteur);
//				Var_AnneeADDLivre.setText(AnneeAdt);
//				Var_AtteurLivre.setText(PrenomAtr+" "+NonAteur);
//				table_load();
//				viderInputTexteFilied();
//				frame.setEnabled(true);
//				d.dispose();
				JOptionPane.showMessageDialog(null, "Ce document est bien modifier");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "erreur : la modification n'est pas appliquer !");
				e1.printStackTrace();
			}
		}
		
//-----> la modification d'un dictionnaire		
		
		public void ModifierDictionnaire( Dictionnaire l) {
			try {
				pst=con.prepareStatement("update dictionnaire set TitreD=?, typeD=?, nomauteur=?, prenomauteur=?, nbrexemplaire=?, edeteur=?, annesAdition=?"
						+ "where id=12");
				//hna khasseni n3raf njib l'id dyal document
				pst.setString(1,l.getTitre());
				pst.setString(2,l.getAnneeAdition());
				pst.setString(3,l.getISBN());
				pst.setString(4,""+l.getEtatDoc());
				pst.setString(5,""+l.getNbExemplaire());
				pst.setString(6,""+l.getNumerodoc());
				pst.setString(7,l.getEditeur());
				pst.executeUpdate();
				//hado ba3d lmodification ta nxof ax ra ndir wax f controller wla hna 
				
//				Var_titreLivre.setText(Titre);
//				Var_typeLivre.setText(Type);
//				Var_NombreLivre.setText(Nombreexemplaire);
//				Var_EditeurLivre.setText(Edeteur);
//				Var_AnneeADDLivre.setText(AnneeAdt);
//				Var_AtteurLivre.setText(PrenomAtr+" "+NonAteur);
//				table_load();
//				viderInputTexteFilied();
//				frame.setEnabled(true);
//				d.dispose();
				JOptionPane.showMessageDialog(null, "Ce document est bien modifier");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "erreur : la modification n'est pas appliquer !");
				e1.printStackTrace();
			}
		}	
		
//----------------------- la supprssion doucument ---------------------\\
		public void supprimerLivre(String id,Connection conect) {
			try {
				pst=conect.prepareStatement("delete from document where id="+id);
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public void supprimerDictionnaire(String id) {
			try {
				pst=con.prepareStatement("delete from Dictionnaire where id="+id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public void supprimerMagazine(String id) {
			try {
				pst=con.prepareStatement("delete from Magazine where id="+id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//-------------- les detailes de documents ----------------\\
		
		
			/*######   Livres   ######*/
		public ResultSet getDetailLivres(String id) {
			
			try {
				pst= con.prepareStatement("select * from livres where id="+id);
				rs = pst.executeQuery();
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
				e.printStackTrace();
			}
			
			return rs;
		}
		
		
		  /*######   Dictionnaire   ######*/
		public ResultSet getDetailDictionnaire(String id) {
			
			try {
				pst= con.prepareStatement("select * from Dictionnaires where id="+id);
				rs = pst.executeQuery();
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
				e.printStackTrace();
			}
			
			return rs;
		}
		
		
		  /*######   Magazine   ######*/
		public ResultSet getDetailMagazine(String id) {
			
			try {
				pst= con.prepareStatement("select * from Magazines where id="+id);
				rs = pst.executeQuery();
				
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
				e.printStackTrace();
			}
			//--->https://www.youtube.com/watch?v=mwax5rSn74c
			return rs;
		}
		
		
//####^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ la gestion des adherents ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\
		//tous la liste des adherents
		public ResultSet getListe(Connection conect) {
			ResultSet	reulta = null;
			try {
					reulta= conect.prepareStatement("select * from personne").executeQuery();

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
					e.printStackTrace();
				}
				
				return reulta;
		}
		

		public ResultSet getEtudiats(Connection conect) {
			ResultSet	reulta = null;
			try {
					reulta= conect.prepareStatement("select * from personne where type='etudiant'").executeQuery();

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
					e.printStackTrace();
				}
				
				return reulta;
	}
	    //la liste des personnes
		public ResultSet getPersonnes(Connection conect) {
			ResultSet	reulta = null;
			try {
					reulta= conect.prepareStatement("select * from personne where type='personne'").executeQuery();

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
					e.printStackTrace();
				}
				
				return reulta;
	}
		
		//la liste des professeurs 
		public ResultSet getProfesseur(Connection conect) {
					ResultSet	reulta = null;
					try {
							reulta= conect.prepareStatement("select * from personne where type='professeur'").executeQuery();

						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
							e.printStackTrace();
						}
						
						return reulta;
			}
//###^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ Le nombre des Adhrents ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\
				public int getNombrePerssonnes(Connection conect) {
					int	nb = 0;
					try {
						nb=DbUtils.resultSetToTableModel(conect.prepareStatement("select * from personne where type='personne'").executeQuery()).getRowCount();

						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
							e.printStackTrace();
						}
						
						return nb;
				}
				
				public int getNombreProfesseur(Connection conect) {
					int	nb = 0;
					try {
						nb=DbUtils.resultSetToTableModel(conect.prepareStatement("select * from personne where type='professeur'").executeQuery()).getRowCount();

						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
							e.printStackTrace();
						}
						
						return nb;
				}
				public int getNombreEtudiant(Connection conect) {
					int	nb = 0;
					try {
						nb=DbUtils.resultSetToTableModel(conect.prepareStatement("select * from personne where type='etudiant'").executeQuery()).getRowCount();

						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
							e.printStackTrace();
						}
						
						return nb;
				}
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ gestion des empruntes ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\
				public ResultSet getDocumentsEmpruntes(Connection conect) {
					ResultSet	reulta = null;
					try {
							reulta= conect.prepareStatement("select * from emprunt").executeQuery();

						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
							e.printStackTrace();
						}
						
						return reulta;
				}
				



				public ResultSet getLivresEmpruntes(Connection conect) {
					ResultSet	reulta = null;
					try {
							reulta= conect.prepareStatement("select * from emprunt where Type_Document='LIVRE'").executeQuery();

						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
							e.printStackTrace();
						}
						
						return reulta;
			}
			//la liste des dictionaire 
				public ResultSet getDictionairesEmpruntes(Connection conect) {
					ResultSet	reulta = null;
					try {
							reulta= conect.prepareStatement("select * from emprunt where Type_Document='DICTIONBAIRE'").executeQuery();

						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
							e.printStackTrace();
						}
						
						return reulta;
			}
				//la liste des Magazine
				public ResultSet getMagazinesEmpruntes(Connection conect) {
					ResultSet	reulta = null;
					try {
							reulta= conect.prepareStatement("select * from emprunt where Type_Document='MAGAZINE'").executeQuery();

						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
							e.printStackTrace();
						}
						
						return reulta;
			}
			
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ le nombre des document emprunter ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\	
				
				public int getNombreLivreEmp(Connection conect) {
					int	nb = 0;
					try {
						nb=DbUtils.resultSetToTableModel(conect.prepareStatement("select * from emprunt where Type_Document='LIVRE'").executeQuery()).getRowCount();

						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
							e.printStackTrace();
						}
						
						return nb;
				}
				
				public int getNombreDictionnaireEmp(Connection conect) {
					int	nb = 0;
					try {
						nb=DbUtils.resultSetToTableModel(conect.prepareStatement("select * from emprunt where Type_Document='DICTIONBAIRE'").executeQuery()).getRowCount();

						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
							e.printStackTrace();
						}
						
						return nb;
				}
				public int getNombreMagazineEmp(Connection conect) {
					int	nb = 0;
					try {
						nb=DbUtils.resultSetToTableModel(conect.prepareStatement("select * from emprunt where Type_Document='MAGAZINE'").executeQuery()).getRowCount();

						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
							e.printStackTrace();
						}
						
						return nb;
				}
//----------------------- la supprssion Adherent ---------------------\\
				public void supprimerPersonne(String id,Connection connect) {
					try {
						pst=connect.prepareStatement("delete from personne where id="+id);
						pst.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				public void supprimerProffesseur(String id) {
					try {
						con.prepareStatement("delete from professeur where id="+id).executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				public void supprimerEtudiant(String id) {
					try {
						con.prepareStatement("delete from etudiant where id="+id).executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
// ---------------------- la liste des document de la formule d'ajoute d'une emprunte ------------------------------ \\
				public ResultSet getDocumentsFormuleEmp(Connection conect) {
					ResultSet	reulta = null;
					try {
							reulta= conect.prepareStatement("select * from document where Nombre_Desponeble > '0' ").executeQuery();

						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
							e.printStackTrace();
						}
						
						return reulta;
				}
				
				public ResultSet getLivresFormuleEmp(Connection conect) {
					ResultSet	reulta = null;
					try {
							reulta= conect.prepareStatement("select * from document where Type_Document='livre' and Nombre_Desponeble > '0' ").executeQuery();

						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
							e.printStackTrace();
						}
						
						return reulta;
			}
			//la liste des dictionaire 
				public ResultSet getDictionairesFormuleEmp(Connection conect) {
					ResultSet	reulta = null;
					try {
							reulta= conect.prepareStatement("select * from document where Type_Document='dictionnaire' and Nombre_Desponeble > '0' ").executeQuery();
							System.out.println("rah wsslat l hna");

						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
							e.printStackTrace();
						}
						
						return reulta;
			}

				//la liste des Magazine
						public ResultSet getMagazinesFormuleEmp(Connection conect) {
							ResultSet	reulta = null;
							try {
									reulta= conect.prepareStatement("select * from document where Type_Document= 'magazine' and Nombre_Desponeble > '0' ").executeQuery();
									System.out.println("rah wsslat l hna");

								} catch (SQLException e) {
									JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
									e.printStackTrace();
								}
								
								return reulta;
					}	
				

}