package model;

import java.util.ArrayList;

public class Estoque {
	private ArrayList<Produto> lista = new ArrayList<>();
	
	public void adicionarProduto(Produto p) {
		lista.add(p);
	}
	
	public ArrayList<Produto> getLista() {
		return lista;
	}

}
