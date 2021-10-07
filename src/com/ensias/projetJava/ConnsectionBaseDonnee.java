package com.ensias.projetJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnsectionBaseDonnee {
	private Connection con;
	private PreparedStatement pst;
	
	
	public ConnsectionBaseDonnee() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con=DriverManager.getConnection("jdbc:mysql://localhost/projet java", "root", "");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, " errer de connection a la base de donnee ");
		}
		catch(ClassNotFoundException cl){
		  JOptionPane.showMessageDialog(null, " errer de connection ");	
		}
	}
	
	
	
	
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	public PreparedStatement getPst() {
		return pst;
	}
	public void setPst(PreparedStatement pst) {
		this.pst = pst;
	}
	
	public void connection() {
		
		
		
	
}

}
