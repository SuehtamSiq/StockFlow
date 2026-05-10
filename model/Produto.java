package model;

import java.util.ArrayList;
import java.util.List;

// Classe que representa um produto ou material do sistema.
// Também funciona como estrutura BOM, armazenando materiais vinculados.
public class Produto {
	private String codigo;
	private String nome;
	private int unidade;
	private double custo;
	
	// Lista de materiais vinculados ao produto principal.
	private List<Produto> materiais = new ArrayList<>();

	// Adiciona um material à lista de materiais do produto.
	public void adicionarMaterial(Produto material) {
		this.materiais.add(material);
	}

	public List<Produto> getMateriais() {
		return materiais;
	}

	public Produto(String codigo, String nome, int unidade, double custo) {
		
		this.codigo = codigo;
		this.nome = nome;
		this.unidade = unidade;
		this.custo = custo;

		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getUnidade() {
		return unidade;
	}

	public void setUnidade(int unidade) {
		this.unidade = unidade;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}
}
	
	


