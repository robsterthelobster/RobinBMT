
package com.robsterthelobster.robinbmt.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Flags {

    @SerializedName("outsideRadius")
    @Expose
    private boolean outsideRadius;

    /**
     * 
     * @return
     *     The outsideRadius
     */
    public boolean isOutsideRadius() {
        return outsideRadius;
    }

    /**
     * 
     * @param outsideRadius
     *     The outsideRadius
     */
    public void setOutsideRadius(boolean outsideRadius) {
        this.outsideRadius = outsideRadius;
    }

}
