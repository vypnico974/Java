package com.octest.servelts;

//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.Part;

import com.octest.bdd.Noms;
import com.octest.beans.Auteur;
import com.octest.beans.Utilisateur;
//import com.octest.forms.ConnectionForm;
import com.octest.dao.*;
import com.octest.dao.DaoException;

@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final int TAILLE_TAMPON = 10240;
	public static final String CHEMIN_FICHIERS = "C:/Z_DEV/"; // A changer

	private UtilisateurDao utilisateurDao;

	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		/*
		 * response.setContentType("text/html"); response.setCharacterEncoding("UTF-8");
		 * 
		 * PrintWriter out = response.getWriter(); out.println("<!DOCTYPE html>");
		 * out.println("<html>"); out.println("<head>");
		 * out.println("<meta charset=\"utf-8\" />");
		 * out.println("<title>Test</title>"); out.println("</head>");
		 * out.println("<body>"); out.println("<p>Bonjour !</p>");
		 * out.println("</body>"); out.println("</html>");
		 */
		String[] titres = { "titre1", "titre2", "titre3" };
		request.setAttribute("titres", titres);

		String message = "Test variable message!";
		request.setAttribute("variable", message);
		request.setAttribute("heure", "jour");

		String name = request.getParameter("name");
		request.setAttribute("name", name);
		String[] noms = { "Mathieu", "Robert", "François" };
		request.setAttribute("noms", noms);

		Auteur auteur = new Auteur();
		auteur.setPrenom("Mathieu");
		auteur.setNom("Nebra");
		auteur.setActif(true);

		request.setAttribute("auteur", auteur);

		HttpSession session = request.getSession();

		String prenom = (String) session.getAttribute("prenom");

		session.invalidate(); // suppression session

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("prenom")) {
					request.setAttribute("prenom", cookie.getValue());
				}
			}
		}

		// Noms tableNoms = new Noms();
		// request.setAttribute("utilisateurs", tableNoms.recupererUtilisateurs());

		//request.setAttribute("utilisateurs", utilisateurDao.lister());
		
	     try {
	            request.setAttribute("utilisateurs", utilisateurDao.lister());
	        }
	        catch (Exception e) {
	            request.setAttribute("erreur", e.getMessage());
	        }

		this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");

		HttpSession session = request.getSession();

		session.setAttribute("nom", nom);
		session.setAttribute("prenom", prenom);

		Cookie cookie = new Cookie("prenom", prenom);
		cookie.setMaxAge(60 * 60 * 24 * 30);
		response.addCookie(cookie);

		// String nom2 = request.getParameter("nom2");
		// request.setAttribute("nom2", nom2);

		// ConnectionForm form = new ConnectionForm();

		// form.verifierIdentifiants(request);

		// request.setAttribute("form", form);

		// On récupère le champ description comme d'habitude
		// String description = request.getParameter("description");
		// request.setAttribute("description", description );

		// On récupère le champ du fichier
		// Part part = request.getPart("fichier");

		// On vérifie qu'on a bien reçu un fichier
		// String nomFichier = getNomFichier(part);

		// Si on a bien un fichier
		// if (nomFichier != null && !nomFichier.isEmpty()) {
		// String nomChamp = part.getName();
		// Corrige un bug du fonctionnement d'Internet Explorer
		// nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
		// .substring(nomFichier.lastIndexOf('\\') + 1);

		// On écrit définitivement le fichier sur le disque
		// ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);

		// request.setAttribute(nomChamp, nomFichier);
		// }

		// Utilisateur utilisateur = new Utilisateur();
		// utilisateur.setNom(request.getParameter("nom"));
		// utilisateur.setPrenom(request.getParameter("prenom"));

		// Noms tableNoms = new Noms();
		// tableNoms.ajouterUtilisateur(utilisateur);

		// request.setAttribute("utilisateurs", tableNoms.recupererUtilisateurs());

	//	Utilisateur utilisateur = new Utilisateur();
		//utilisateur.setNom(request.getParameter("nom"));
		//utilisateur.setPrenom(request.getParameter("prenom"));

		//utilisateurDao.ajouter(utilisateur);

		//request.setAttribute("utilisateurs", utilisateurDao.lister());
		
	    try {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNom(request.getParameter("nom"));
            utilisateur.setPrenom(request.getParameter("prenom"));
            
            utilisateurDao.ajouter(utilisateur);
            request.setAttribute("utilisateurs", utilisateurDao.lister());
        }
        catch (Exception e) {
            request.setAttribute("erreur", e.getMessage());
        }

		this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
	}
	/*
	 * private void ecrireFichier( Part part, String nomFichier, String chemin )
	 * throws IOException { BufferedInputStream entree = null; BufferedOutputStream
	 * sortie = null; try { entree = new BufferedInputStream(part.getInputStream(),
	 * TAILLE_TAMPON); sortie = new BufferedOutputStream(new FileOutputStream(new
	 * File(chemin + nomFichier)), TAILLE_TAMPON);
	 * 
	 * byte[] tampon = new byte[TAILLE_TAMPON]; int longueur; while ((longueur =
	 * entree.read(tampon)) > 0) { sortie.write(tampon, 0, longueur); } } finally {
	 * try { sortie.close(); } catch (IOException ignore) { } try { entree.close();
	 * } catch (IOException ignore) { } } }
	 * 
	 * private static String getNomFichier( Part part ) { for ( String
	 * contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
	 * if ( contentDisposition.trim().startsWith( "filename" ) ) { return
	 * contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1
	 * ).trim().replace( "\"", "" ); } } return null; }
	 */
}