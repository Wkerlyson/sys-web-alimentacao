package com.secti.util.report;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GeraRelatorio implements Serializable{

	private static final long serialVersionUID = 1L;

	public void gerarRelatorio(Map<String, Object> mapa, List<?> lista, String nomeArquivoJasper,
			String nomeDoArquivoDeSaida) throws JRException, IOException {
		
		InputStream content = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/reports/" + nomeArquivoJasper + ".jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(content, mapa, new JRBeanCollectionDataSource(lista));
		
		byte[] bytesReport = JasperExportManager.exportReportToPdf(jasperPrint);
		HttpServletResponse response = (HttpServletResponse)
		FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment;filename=" + nomeDoArquivoDeSaida + ".pdf;");
		response.setContentType("application/pdf");
		response.setContentLength(bytesReport.length);
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(bytesReport, 0, bytesReport.length);
		FacesContext.getCurrentInstance().responseComplete();
		outputStream.close();

	}

}
