package com.secti.service;

import java.util.List;

import com.secti.model.Produto;
import com.secti.model.ProdutoReceita;
import com.secti.model.Receita;

public interface ReceitaService {
	
	public void salvar(Receita receita) throws Exception;

	public List<Receita> listarTodos();

	public void remover(Receita receita, Long id) throws Exception;

	public Receita buscarPorId(Receita receita, Long id);

	public void editar(Receita receita) throws Exception;
	
	public void salvarProdutoReceita(Receita receita, List<ProdutoReceita> produtos) throws Exception;
	
	public void editarProdutoReceita(Receita receita, List<ProdutoReceita> produtos,  List<Produto> produtosParaRemover) throws Exception;
}
