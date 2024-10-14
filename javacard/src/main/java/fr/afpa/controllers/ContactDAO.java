package fr.afpa.controllers;

import fr.afpa.models.Contact;
import fr.afpa.tools.ConnectionPostgreSQL;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO extends DAO<Contact>{
    // FUNCTIONS

    // 1 - GETALLCONTACTS
    public ArrayList<Contact> getAll() {

        ArrayList<Contact> sqlAllContacts = new ArrayList<Contact>();

        try {
            Connection con = ConnectionPostgreSQL.getInstance();

            // création d'un "Statement" (objet qui permet d'exéctuer une requête SQL)
            Statement stm = con.createStatement();
            // récupération de toutes les lignes de résultat (objet de la classe
            // "ResultSet")
            ResultSet result = stm.executeQuery("SELECT * FROM contact");
            // on passe en revue toutes les lignes
            while (result.next()) {
                // récupération des valeurs des colonnes pour chaque contact
                String firstname = result.getString("first_name");
                String lastname = result.getString("last_name");
                String gender = result.getString("gender");
                LocalDate birthDate = LocalDate.parse(result.getString("birth_date"));
                String pseudo = result.getString("pseudo");
                String address = result.getString("address");
                String personalPhoneNumber = result.getString("personal_phone_number");
                String professionalPhoneNumber = result.getString("professional_phone_number");
                String email = result.getString("email");
                String linkedInLink = result.getString("linkedin_ink");
                String gitLink = result.getString("git_link");

                Contact contact = new Contact(firstname, lastname, gender, birthDate, pseudo, address,
                        personalPhoneNumber, professionalPhoneNumber, email, linkedInLink, gitLink);
                
                contact.setId(result.getInt("id"));

                sqlAllContacts.add(contact);
            }

            // fermeture des ressources
            stm.close();
            result.close();
            // con.close();

        } catch (Exception e) {
            System.err.println("Error");
            System.err.println(e.getMessage());
        }

        System.out.println(sqlAllContacts);
        return sqlAllContacts;

    }

    // 2 - GETCONTACTBYID
    public Contact find(int id) {

        try {
            // déclaration de l'url de connexion
            // instanciation d'un objet de la classe "Connexion" permettant de faire des
            // requêtes avec la base de données
            Connection con = ConnectionPostgreSQL.getInstance();

            // création d'un objet de la classe "Connection" en utilisant DriverManager
            String sql = "SELECT * FROM contact WHERE id = ?";

            // création d'un "Statement" pour effectuer la requête
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                // récupération des informations de l'enregistrement "Contact" ciblé
                // pas l'identifiant passé en paramètre de cette fonction
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String gender = result.getString("gender");
                LocalDate birthDate = LocalDate.parse(result.getString("birth_date"));
                String pseudo = result.getString("pseudo");
                String address = result.getString("address");
                String personalPhoneNumber = result.getString("personal_phone_number");
                String professionalPhoneNumber = result.getString("professional_phone_number");
                String email = result.getString("email");
                String linkedInLink = result.getString("linkedin_ink");
                String gitLink = result.getString("git_link");

                Contact contact = new Contact(firstName, lastName, gender, birthDate, pseudo, address,
                        personalPhoneNumber, professionalPhoneNumber, email, linkedInLink, gitLink);

                stm.close();
                // con.close();

                return contact;
            }
        }

        catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());

            return null;
        }
        return null;
    }

    // 3 - DELETESELECTED
        
        public boolean delete(int id) {
            try {
                Connection con = ConnectionPostgreSQL.getInstance();
    
                PreparedStatement stm = con.prepareStatement(
                        "DELETE FROM contact WHERE id = ?");
    
                stm.setInt(1, id);
    
                stm.execute();
    
                stm.close();
                // con.close();
    
            } catch (Exception e) {
                System.out.println("Error");
                System.out.println(e.getMessage());
    
                return false;
            }
    
            return true;
        }


    // 4 - DELETEBYSURNMEANDFIRSTNAME
    public boolean deleteByFirstAndLastName(String firstName, String lastName) {

        try {
            Connection con = ConnectionPostgreSQL.getInstance();

            PreparedStatement stm = con.prepareStatement(
                    "DELETE FROM contact WHERE first_name = ? AND last_name = ?");

            stm.setString(1, firstName);
            stm.setString(2, lastName);

            stm.execute();

            stm.close();
            // con.close();

        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    // public static boolean deleteByLastAndFirstName(Contact contact) {
    // return false;
    // }

    // 5 - ADDCONTACT
    public void insert(Contact contact) {
        try {
            Connection con = ConnectionPostgreSQL.getInstance();

            // Requête SQL avec des paramètres à la place des valeurs
            String sql = "INSERT INTO contact (first_name, last_name, gender, birth_date, pseudo, address, personal_phone_number, professional_phone_number, email, linkedin_ink, git_link) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stm = con.prepareStatement(sql);

            // Définir les paramètres avec les setString(), setInt() etc.
            stm.setString(1, contact.getFirstName());
            stm.setString(2, contact.getLastName());
            stm.setString(3, contact.getGender());
            stm.setDate(4, Date.valueOf(contact.getBirthDate()));
            stm.setString(5, contact.getNickname());
            stm.setString(6, contact.getAddress());
            stm.setString(7, contact.getPersonalPhoneNumber());
            stm.setString(8, contact.getProfessionalPhoneNumber());
            stm.setString(9, contact.getEmailAddress());
            stm.setString(10, contact.getLinkedinLink());
            stm.setString(11, contact.getGithubGitlabLink());

            stm.execute();
            stm.close();
            // con.close();

        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }

    }

    // 6-UPDATECONTACT

    public Contact update(Contact contact) {

        try {
            System.out.println(contact.getId());
            Connection con = ConnectionPostgreSQL.getInstance();

            PreparedStatement stm = con.prepareStatement(
                    "UPDATE contact SET first_name = ?, last_name = ?, gender = ?, birth_date = ?, pseudo = ?, address = ?, personal_phone_number = ?, professional_phone_number = ?,email = ?, linkedin_ink = ?, git_link = ? WHERE id = ?");

            stm.setString(1, contact.getFirstName());
            stm.setString(2, contact.getLastName());
            stm.setString(3, contact.getGender());
            stm.setDate(4, Date.valueOf(contact.getBirthDate()));
            stm.setString(5, contact.getNickname());
            stm.setString(6, contact.getAddress());
            stm.setString(7, contact.getPersonalPhoneNumber());
            stm.setString(8, contact.getProfessionalPhoneNumber());
            stm.setString(9, contact.getEmailAddress());
            stm.setString(10, contact.getLinkedinLink());
            stm.setString(11, contact.getGithubGitlabLink());
            stm.setInt(12, contact.getId());

            stm.execute();

            stm.close();
            // con.close();

            System.out.println(contact);

            return contact;
        }

        catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());

            return null;
        }
    }

}
