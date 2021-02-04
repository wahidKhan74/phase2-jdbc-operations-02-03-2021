package com.ecommerce.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.connection.DBConnection;

/**
 * Servlet implementation class AddProductBYST
 */
@WebServlet("/add-st-product")
public class AddProductBYST extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductBYST() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.sendRedirect("add-st-product.html");
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
			
			// 2. create a statement
			CallableStatement cstm = conn.getConnection().prepareCall("{call add_product(?,?)}");
			
			// 3. set parameters
			cstm.setString(1, name);
			cstm.setDouble(2, Double.parseDouble(price));
			
			// execute query
			int noOfRowsAffected = cstm.executeUpdate();
			if(noOfRowsAffected>0)
				out.println("Product is created sucessfully by stored procedure !");
			else
				out.print("Product creation is failed !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
