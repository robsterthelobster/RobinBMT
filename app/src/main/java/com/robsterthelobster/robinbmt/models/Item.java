
package com.robsterthelobster.robinbmt.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Item {

    @SerializedName("reasons")
    @Expose
    private Reasons reasons;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("flags")
    @Expose
    private Flags flags;
    @SerializedName("referralId")
    @Expose
    private String referralId;

    /**
     * 
     * @return
     *     The reasons
     */
    public Reasons getReasons() {
        return reasons;
    }

    /**
     * 
     * @param reasons
     *     The reasons
     */
    public void setReasons(Reasons reasons) {
        this.reasons = reasons;
    }

    /**
     * 
     * @return
     *     The venue
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * 
     * @param venue
     *     The venue
     */
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    /**
     * 
     * @return
     *     The flags
     */
    public Flags getFlags() {
        return flags;
    }

    /**
     * 
     * @param flags
     *     The flags
     */
    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    /**
     * 
     * @return
     *     The referralId
     */
    public String getReferralId() {
        return referralId;
    }

    /**
     * 
     * @param referralId
     *     The referralId
     */
    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }

}
