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
@WebServlet("/delete-product")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.sendRedirect("delete-product.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		
		try {
			String url = "jdbc:mysql://localhost:3306/ecommerce";
			String username ="root";
			String password = "root";
			// 1. initialize db connection 
			DBConnection conn = new DBConnection(url, username, password);
			
			String query = "DELETE FROM eproduct where id=?";
			
			// 2. create a statement
			PreparedStatement pstm = conn.getConnection().prepareStatement(query);
			
			// 3. set parameter
			pstm.setInt(1, Integer.parseInt(id));
			
			// execute query
			int noOfRowsAffected = pstm.executeUpdate();
			if(noOfRowsAffected>0)
				out.println("Product is deleted sucessfully !");
			else
				out.print("Product deletion failed !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
