package br.com.unitins.webservices;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paulo on 18/05/2016.
 */
public class ModeloDadosEmpresa {

    private Integer idcliente;
    private String nome;
    private String descricao;
    private String clienteDesde;
    private String icone;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getClienteDesde() {
        return clienteDesde;
    }

    public void setClienteDesde(String clienteDesde) {
        this.clienteDesde = clienteDesde;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
