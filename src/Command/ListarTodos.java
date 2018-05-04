package Command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Pais;
import Service.PaisService;

public class ListarTodos implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Pais pais = new Pais();
		PaisService serv = new PaisService();
		ArrayList<Pais> p = serv.listarTodos();
		System.out.println("listar todos");
		request.setAttribute("arrayPais", p);
		RequestDispatcher view = request.getRequestDispatcher("lista_todos.jsp");
		view.forward(request, response);
		
	}

}