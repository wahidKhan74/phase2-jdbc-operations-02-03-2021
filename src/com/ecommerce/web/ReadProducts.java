package com.ecommerce.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.connection.DBConnection;

/**
 * Servlet implementation class ReadProducts
 */
@WebServlet("/read-products")
public class ReadProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			String url = "jdbc:mysql://localhost:3306/ecommerce";
			String username ="root";
			String password = "root";
			// 1. initialize db connection 
			DBConnection conn = new DBConnection(url, username, password);
			
			// 2. create a statement
			Statement stm = conn.getConnection().createStatement();
			
			// execute query
			String query = "select * from eproduct";
			ResultSet rst = stm.executeQuery(query);
			
			while(rst.next()) {
				out.println("<p>" + rst.getInt("ID") +" , "+ rst.getString("name")+" , "
						+ rst.getDouble("price")+"</p>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
