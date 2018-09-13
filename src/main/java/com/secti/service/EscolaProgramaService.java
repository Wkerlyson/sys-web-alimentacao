package com.secti.service;

import java.util.List;

import com.secti.model.Escola;
import com.secti.model.EscolaPrograma;

public interface EscolaProgramaService {
	public List<EscolaPrograma> listarProgramas(Escola escola);
	public void remover(EscolaPrograma escolaPrograma, Long id) throws Exception;
}
