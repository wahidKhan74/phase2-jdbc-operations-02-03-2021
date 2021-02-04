package com.ecommerce.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.connection.DBConnection;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/add-product")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.sendRedirect("add-product.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		
		try {
			String url = "jdbc:mysql://localhost:3306/ecommerce";
			String username ="root";
			String password = "root";
			// 1. initialize db connection 
			DBConnection conn = new DBConnection(url, username, password);
			
			String query = "INSERT INTO eproduct(name,price) values(?, ?);";
			
			// 2. create a statement
			PreparedStatement pstm = conn.getConnection().prepareStatement(query);
			
			// 3. set parameters
			pstm.setString(1, name);
			pstm.setDouble(2, Double.parseDouble(price));
			
			// execute query
			int noOfRowsAffected = pstm.executeUpdate();
			if(noOfRowsAffected>0)
				out.println("Product is created sucessfully !");
			else
				out.print("Product creation is failed !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
