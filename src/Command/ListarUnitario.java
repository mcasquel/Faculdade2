package Command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Pais;
import Service.PaisService;

public class ListarUnitario implements Command{

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pId = request.getParameter("id");
		Pais pais = new Pais();
		pais.setId(Integer.parseInt(pId));
		PaisService serv = new PaisService();
		serv.selectPais(pais);
		request.setAttribute("pais", pais);
		RequestDispatcher view = request.getRequestDispatcher("Pais.jsp");
		view.forward(request, response);
		
	}

}