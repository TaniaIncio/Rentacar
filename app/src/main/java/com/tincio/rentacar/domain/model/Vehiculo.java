
package com.tincio.rentacar.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehiculo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("tipo_precio")
    @Expose
    private String tipoPrecio;
    @SerializedName("tipo_manejo")
    @Expose
    private String tipoManejo;
    @SerializedName("num_personas")
    @Expose
    private String numPersonas;
    @SerializedName("num_puertas")
    @Expose
    private String numPuertas;
    @SerializedName("aire_acond")
    @Expose
    private String aireAcond;
    @SerializedName("tipo_radio")
    @Expose
    private String tipoRadio;
    @SerializedName("num_maletas")
    @Expose
    private String numMaletas;
    @SerializedName("anio")
    @Expose
    private String anio;
    @SerializedName("num_aro")
    @Expose
    private String numAro;
    @SerializedName("data_extra")
    @Expose
    private String dataExtra;
    @SerializedName("foto")
    @Expose
    private String foto;
    private boolean seleccionado;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre
     *     The nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return
     *     The tipoPrecio
     */
    public String getTipoPrecio() {
        return tipoPrecio;
    }

    /**
     * 
     * @param tipoPrecio
     *     The tipo_precio
     */
    public void setTipoPrecio(String tipoPrecio) {
        this.tipoPrecio = tipoPrecio;
    }

    /**
     * 
     * @return
     *     The tipoManejo
     */
    public String getTipoManejo() {
        return tipoManejo;
    }

    /**
     * 
     * @param tipoManejo
     *     The tipo_manejo
     */
    public void setTipoManejo(String tipoManejo) {
        this.tipoManejo = tipoManejo;
    }

    /**
     * 
     * @return
     *     The numPersonas
     */
    public String getNumPersonas() {
        return numPersonas;
    }

    /**
     * 
     * @param numPersonas
     *     The num_personas
     */
    public void setNumPersonas(String numPersonas) {
        this.numPersonas = numPersonas;
    }

    /**
     * 
     * @return
     *     The numPuertas
     */
    public String getNumPuertas() {
        return numPuertas;
    }

    /**
     * 
     * @param numPuertas
     *     The num_puertas
     */
    public void setNumPuertas(String numPuertas) {
        this.numPuertas = numPuertas;
    }

    /**
     * 
     * @return
     *     The aireAcond
     */
    public String getAireAcond() {
        return aireAcond;
    }

    /**
     * 
     * @param aireAcond
     *     The aire_acond
     */
    public void setAireAcond(String aireAcond) {
        this.aireAcond = aireAcond;
    }

    /**
     * 
     * @return
     *     The tipoRadio
     */
    public String getTipoRadio() {
        return tipoRadio;
    }

    /**
     * 
     * @param tipoRadio
     *     The tipo_radio
     */
    public void setTipoRadio(String tipoRadio) {
        this.tipoRadio = tipoRadio;
    }

    /**
     * 
     * @return
     *     The numMaletas
     */
    public String getNumMaletas() {
        return numMaletas;
    }

    /**
     * 
     * @param numMaletas
     *     The num_maletas
     */
    public void setNumMaletas(String numMaletas) {
        this.numMaletas = numMaletas;
    }

    /**
     * 
     * @return
     *     The anio
     */
    public String getAnio() {
        return anio;
    }

    /**
     * 
     * @param anio
     *     The anio
     */
    public void setAnio(String anio) {
        this.anio = anio;
    }

    /**
     * 
     * @return
     *     The numAro
     */
    public String getNumAro() {
        return numAro;
    }

    /**
     * 
     * @param numAro
     *     The num_aro
     */
    public void setNumAro(String numAro) {
        this.numAro = numAro;
    }

    /**
     * 
     * @return
     *     The dataExtra
     */
    public String getDataExtra() {
        return dataExtra;
    }

    /**
     * 
     * @param dataExtra
     *     The data_extra
     */
    public void setDataExtra(String dataExtra) {
        this.dataExtra = dataExtra;
    }

    /**
     * 
     * @return
     *     The foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * 
     * @param foto
     *     The foto
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
