package Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Pais;
import Service.PaisService;

public class AlterarPais implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String area = request.getParameter("area");
		String populacao = request.getParameter("populacao");
		
		System.out.println("alterar dados pais");

		Pais pais = new Pais();
		pais.setId(Integer.parseInt(id));
		pais.setNome(nome);
		pais.setArea(Double.parseDouble(area));
		pais.setPopulacao(Long.parseLong(populacao));
		
		PaisService serv = new PaisService();
		serv.alterar(pais);
		response.sendRedirect("index.jsp"); 
		
	}

}