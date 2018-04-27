package Service;
import Model.Pais;

import java.util.ArrayList;

import DAO.PaisDao;

public class PaisService {
	PaisDao dao;
	
	public PaisService() {
		 dao = new PaisDao();
	}
	
	public void criar(Pais pais) {
		dao.IncluirPais(pais);
	}
	
	public void alterar(Pais pais) {
		dao.AtualizarPais(pais);
	}
	
	public void deletar(Pais pais) {
		dao.DeletarPais(pais);
	}
	
	public void selectPais(Pais pais) {
		dao.CarregarPais(pais);
	}
	
	public void menorArea(Pais pais){
		dao.getMenorArea(pais);
	}
	
	public void habitantes(Pais pais){
		dao.getHabitantes(pais);
	}
	
	public ArrayList<Pais> listarTodos(){
		return dao.ListarTodos();
	}
	
	public String[] arrayPaises() {
		return dao.vetorPaises();
	}
}