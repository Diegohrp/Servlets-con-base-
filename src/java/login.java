/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alumno
 */
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String apodito = request.getParameter("apodito");
        String password = request.getParameter("contra");
        //Variables de conexion
        Connection  con = null;
        Statement sta = null;
        ResultSet rs = null;
        String user = "", contra = "", nombre = "";
        PrintWriter out = response.getWriter();
            
        
        /* TODO output your page here. You may use following sample code. */
        try {
            /**
             * Creamos la conexión con la bae de datos 
             */
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = (com.mysql.jdbc.Connection)DriverManager.getConnection("jdbc:mysql://localhost/lol", "root", "n0m3l0");
            sta = (com.mysql.jdbc.Statement)con.createStatement();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException error) {
            out.print(error.toString() + "no conecto");
        }
        try {
            /**
             * Buscamo en la base los datos del usuario
             */
            rs =(com.mysql.jdbc.ResultSetInternalMethods) sta.executeQuery("SELECT * FROM tu WHERE apodito ='" + apodito + "'");
            /**
             * 
             */
            while (rs.next()) {
                user = rs.getString("apodito");
                contra = rs.getString("contra");
                nombre = rs.getString("nombre");
            }
              if (password.equals(contra)) {
                out.println("Hola " + nombre);
            } else {
                out.println("");
                out.println("Tenemos un problema, tu regitro esta mal");
            }
        } catch (SQLException error) {
            out.print(error.toString());
        }
        /**
         * Cerramos la conexión 
         */
        con.close();
        rs.close();

        

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
