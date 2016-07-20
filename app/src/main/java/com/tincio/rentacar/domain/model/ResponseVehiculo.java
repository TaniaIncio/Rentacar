
package com.tincio.rentacar.domain.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseVehiculo {

    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("data")
    @Expose
    private List<Vehiculo> data = new ArrayList<Vehiculo>();
    @SerializedName("nextPage")
    @Expose
    private Object nextPage;
    @SerializedName("totalObjects")
    @Expose
    private Integer totalObjects;

    /**
     * 
     * @return
     *     The offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * 
     * @param offset
     *     The offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * 
     * @return
     *     The data
     */
    public List<Vehiculo> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(List<Vehiculo> data) {
        this.data = data;
    }

    /**
     * 
     * @return
     *     The nextPage
     */
    public Object getNextPage() {
        return nextPage;
    }

    /**
     * 
     * @param nextPage
     *     The nextPage
     */
    public void setNextPage(Object nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * 
     * @return
     *     The totalObjects
     */
    public Integer getTotalObjects() {
        return totalObjects;
    }

    /**
     * 
     * @param totalObjects
     *     The totalObjects
     */
    public void setTotalObjects(Integer totalObjects) {
        this.totalObjects = totalObjects;
    }

}
