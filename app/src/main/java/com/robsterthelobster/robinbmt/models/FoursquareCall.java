
package com.robsterthelobster.robinbmt.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class FoursquareCall {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("response")
    @Expose
    private FoursquareResponse foursquareResponse;

    /**
     * 
     * @return
     *     The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * 
     * @param meta
     *     The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * 
     * @return
     *     The foursquareResponse
     */
    public FoursquareResponse getFoursquareResponse() {
        return foursquareResponse;
    }

    /**
     * 
     * @param foursquareResponse
     *     The foursquareResponse
     */
    public void setFoursquareResponse(FoursquareResponse foursquareResponse) {
        this.foursquareResponse = foursquareResponse;
    }

}
