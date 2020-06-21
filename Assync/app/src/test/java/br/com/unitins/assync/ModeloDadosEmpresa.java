
package br.com.unitins.assync;

import java.util.HashMap;
import java.util.Map;


public class ModeloDadosEmpresa {

    private Integer idcliente;
    private String nome;
    private String descricao;
    private Integer clienteDesde;
    private String icone;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The idcliente
     */
    public Integer getIdcliente() {
        return idcliente;
    }

    /**
     * 
     * @param idcliente
     *     The idcliente
     */
    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    /**
     * 
     * @return
     *     The nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @param nome
     *     The nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * 
     * @return
     *     The descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * 
     * @param descricao
     *     The descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * 
     * @return
     *     The clienteDesde
     */
    public Integer getClienteDesde() {
        return clienteDesde;
    }

    /**
     * 
     * @param clienteDesde
     *     The cliente_desde
     */
    public void setClienteDesde(Integer clienteDesde) {
        this.clienteDesde = clienteDesde;
    }

    /**
     * 
     * @return
     *     The icone
     */
    public String getIcone() {
        return icone;
    }

    /**
     * 
     * @param icone
     *     The icone
     */
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
