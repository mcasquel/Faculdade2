package Controller;

import Service.PaisService;
import Model.Pais;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/ManterPais.do","/inserir"})
public class ManterPais extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		Pais pais;
		PaisService serv;
		
		if(action.equals("/inserir")) {
			String pNome = request.getParameter("Nome");
			String pArea = request.getParameter("Area");
			String pPop = request.getParameter("Populacao");
			
			pais = new Pais(pNome,Long.parseLong(pPop),Double.parseDouble(pArea));
			
			serv = new PaisService();
			serv.criar(pais);
			
			serv.selectPais(pais);
			//enviar para jsp
			request.setAttribute("pais", pais);
			RequestDispatcher view = request.getRequestDispatcher("Pais.jsp");
			view.forward(request, response);
		}
		
		else if(action.equals("/ManterPais.do")) {
			
			String acao = request.getParameter("acao");
			
			if (acao.equals("listarUnico")){
				String pId = request.getParameter("id");
				pais = new Pais();
				pais.setId(Integer.parseInt(pId));
				serv = new PaisService();
				serv.selectPais(pais);
				
				request.setAttribute("pais", pais);
				RequestDispatcher view = request.getRequestDispatcher("Pais.jsp");
				view.forward(request, response);
			
			}else if(acao.equals("listarTodos")) {
				pais = new Pais();
				serv = new PaisService();
				ArrayList<Pais> p = serv.listarTodos();
				System.out.println("listar todos");
				request.setAttribute("arrayPais", p);
				RequestDispatcher view = request.getRequestDispatcher("lista_todos.jsp");
				view.forward(request, response);
				
			}
			
			else if(acao.equals("excluirPais")) {
				String id = request.getParameter("id");
				
				System.out.println(id);

				pais = new Pais();
				serv = new PaisService();
				pais.setId(Integer.parseInt(id));
				
				serv.deletar(pais);
				response.sendRedirect("index.jsp");  
				
			}
			
			else if(acao.equals("alterarPais")) {
				String id = request.getParameter("id");
				String nome = request.getParameter("nome");
				String area = request.getParameter("area");
				String populacao = request.getParameter("populacao");
				
				System.out.println("alterar dados pais");

				pais = new Pais();
				pais.setId(Integer.parseInt(id));
				pais.setNome(nome);
				pais.setArea(Double.parseDouble(area));
				pais.setPopulacao(Long.parseLong(populacao));
				
				serv = new PaisService();
				serv.alterar(pais);
				response.sendRedirect("index.jsp");  
				
			}
			
			
			
		}
		
	
	
	}

}