package com.wind.quanlysinhvien;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class LoadResultSearchSaved
 */
@WebServlet("/LoadResultSearchSaved")
public class LoadResultSearchSaved extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadResultSearchSaved() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		HttpSession sesion = request.getSession();
		response.getWriter().write(
				sesion.getAttribute("contentFile").toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Come Here");
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		InputStream is = null;
		try {
			FileItemIterator items = upload.getItemIterator(request);
			while (items.hasNext()) {
				FileItemStream item = items.next();
				if (!item.isFormField()) {
					// no la upload file
					is = item.openStream();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(is, "UTF-8"));
					StringBuilder contentFile = new StringBuilder();
					String line = null;
					while ((line = br.readLine()) != null) {
						contentFile.append(line);
					}
					String content = contentFile.toString();
					System.out.println(content);
					HttpSession session = request.getSession();
					session.setAttribute("contentFile", content);
					response.sendRedirect("timkiem");
				}
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
