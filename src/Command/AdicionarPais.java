package Command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Pais;
import Service.PaisService;

public class AdicionarPais implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pNome = request.getParameter("Nome");
		String pArea = request.getParameter("Area");
		String pPop = request.getParameter("Populacao");
		
		Pais pais = new Pais(pNome,Long.parseLong(pPop),Double.parseDouble(pArea));
		PaisService serv = new PaisService();
		serv = new PaisService();
		serv.criar(pais);
		
		serv.selectPais(pais);
		//enviar para jsp
		request.setAttribute("pais", pais);
		RequestDispatcher view = request.getRequestDispatcher("Pais.jsp");
		view.forward(request, response);	
	}

}