/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.model;

import br.com.desafio.connection.DB_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author otto_
 */
@ManagedBean
@RequestScoped
public class ContatosBean {

    private List<ContatosBean> listContatos;
    private String nome;
    private String telefone;
    private int id;

    private Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public ContatosBean() {

    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ContatosBean{" + "nome=" + nome + ", telefone=" + telefone + ", id=" + id + ", map=" + map + '}';
    }

    public List<ContatosBean> listarTudo() {
        listContatos = new ArrayList<ContatosBean>();
        try {

            Connection connection = null;
            DB_Connection db = new DB_Connection();
            connection = db.get_connection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from contatos");

            while (resultSet.next()) {
                ContatosBean cB = new ContatosBean();
                cB.setId(resultSet.getInt("id"));
                cB.setNome(resultSet.getString("nome"));
                cB.setTelefone(resultSet.getString("telefone"));

                listContatos.add(cB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listContatos;
    }

    public void adicionar() {
        List<ContatosBean> listContatos = new ArrayList<ContatosBean>();
        try {

            Connection connection = null;
            DB_Connection db = new DB_Connection();
            connection = db.get_connection();

            PreparedStatement prepare = connection.prepareStatement("insert into contatos(id, nome, telefone) value(?, ?, ?)");
            prepare.setInt(1, getId());
            prepare.setString(2, getNome());
            prepare.setString(3, getTelefone());
            prepare.execute();
            prepare.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String editar() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String editar_id = params.get("action");

        try {

            DB_Connection db = new DB_Connection();
            Connection connection = db.get_connection();
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery("select * from contatos where id=" + editar_id);

            ContatosBean cB = new ContatosBean();
            rs.next();
            cB.setNome(rs.getString("nome"));
            cB.setTelefone(rs.getString("telefone"));
            cB.setId(rs.getInt("id"));
            map.put("contato", cB);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/update.xhtml?faces-redirect=true";

    }

    public String deletar() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String deletar_id = params.get("action");

        try {

            DB_Connection dB = new DB_Connection();
            Connection connection = dB.get_connection();
            Statement st = connection.createStatement();

            PreparedStatement ps = connection.prepareStatement("delete from contatos where id=?");
            ps.setString(1, deletar_id);
            System.out.println(ps);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/index.xhtml?faces-redirect=true";

    }

    public String atualizar() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String update_id = params.get("update_id");
        try {
            DB_Connection db = new DB_Connection();
            Connection connection = db.get_connection();

            PreparedStatement prepare = connection.prepareStatement("update contatos set nome=?, telefone=? where id=?");

            prepare.setString(1, nome);
            prepare.setString(2, telefone);
            prepare.setString(3, update_id);

            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public List<ContatosBean> buscarNome() {
        listContatos = new ArrayList<ContatosBean>();
        try {

            Connection connection = null;
            DB_Connection db = new DB_Connection();
            connection = db.get_connection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contatos WHERE nome LIKE '%" + nome + "%'");

            while (resultSet.next()) {
                ContatosBean cB = new ContatosBean();
                cB.setId(resultSet.getInt("id"));
                cB.setNome(resultSet.getString("nome"));
                cB.setTelefone(resultSet.getString("telefone"));

                listContatos.add(cB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listContatos;
    }

    public String navegarBuscar() {
        return "/buscar.xhtml?faces-redirect=true";
    }

}
