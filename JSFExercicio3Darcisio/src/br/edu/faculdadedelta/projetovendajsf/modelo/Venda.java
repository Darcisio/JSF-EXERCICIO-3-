package br.edu.faculdadedelta.projetovendajsf.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Venda {

	private Long id;
	private String descProduto;
	private Date inicioProcedimento;
	private Date fimProcedimento;
	private String paciente;
	private double qtdExames;
	private double valor;

	public Venda() {
	}

	public Venda(Long id, String descProduto, double valor, Date inicioProcedimento, Date fimProcedimento, String paciente) {
		this.id = id;
		this.descProduto = descProduto;
		this.inicioProcedimento = inicioProcedimento;
		this.fimProcedimento = fimProcedimento;
		this.paciente = paciente;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescProduto() {
		return descProduto;
	}

	public void setDescProduto(String produto) {
		this.descProduto = produto;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getInicioProcedimento() {
		return inicioProcedimento;
	}

	public void setInicioProcedimento(Date inicioProcedimento) {
		this.inicioProcedimento = inicioProcedimento;
	}
	
	public Date getFimProcedimento() {
		return fimProcedimento;
	}

	public void setFimProcedimento(Date fimProcedimento) {
		this.fimProcedimento = fimProcedimento;
	}
	
	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public double getQtdExames() {
		return qtdExames;
	}

	public void setQtdExames(double qtdExames) {
		this.qtdExames = qtdExames;
	}
	
	public double getDesconto() {
		double desconto = 0.25;
		double valorVenda = (valor * qtdExames) - desconto;
		
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataReferencia = null;
		try {
			dataReferencia = sf.parse("31/12/2019");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (valorVenda >= 3000) {
		    desconto = valorVenda * 0.01;	
		}
		if (inicioProcedimento.after(dataReferencia)) {
			desconto += valorVenda * 0.005;
		}
		
		return desconto;
	}
	
	public double getValorTotal() {
		return (qtdExames * valor) - getDesconto();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
