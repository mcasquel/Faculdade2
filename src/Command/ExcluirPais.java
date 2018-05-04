package Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Pais;
import Service.PaisService;

public class ExcluirPais implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		System.out.println(id);

		Pais pais = new Pais();
		PaisService serv = new PaisService();
		pais.setId(Integer.parseInt(id));
		
		serv.deletar(pais);
		response.sendRedirect("index.jsp");  
		
	}

}