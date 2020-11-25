package br.edu.faculdadedelta.projetovendajsf.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.projetovendajsf.dao.VendaDAO;
import br.edu.faculdadedelta.projetovendajsf.modelo.Venda;

@ManagedBean
@SessionScoped
public class VendaController {

	private Venda venda = new Venda();
	private VendaDAO dao = new VendaDAO();
	
	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	
	public void limparCampos() {
		venda = new Venda();
	}
	
	public String salvar() {
		try {
			if (venda.getId() == null) {
				// incluir
				dao.incluir(venda);
				FacesMessage mensagem = new FacesMessage("Inclusão realizada com sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				limparCampos();
			} else {
				// alterar
				dao.alterar(venda);
				FacesMessage mensagem = new FacesMessage("Alteração realizada com sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro ao realizar a operação. "
					+ "Tente novamente mais tarde. " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		return "cadastroVenda.xhtml";
	}
	
	public List<Venda> getLista() {
		List<Venda> listaRetorno = new ArrayList<Venda>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro ao realizar a operação. "
					+ "Tente novamente mais tarde. " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		return listaRetorno;
	}
	
	public String editar() {
		return "cadastroVenda.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(venda);
			FacesMessage mensagem = new FacesMessage("Exclusão realizada com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro ao realizar a operação. "
					+ "Tente novamente mais tarde. " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		return "listaVenda.xhtml";
	}
}
