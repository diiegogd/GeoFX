//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package dad.geofx.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeZone {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("current_time")
    @Expose
    private String currentTime;
    @SerializedName("gmt_offset")
    @Expose
    private Integer gmtOffset;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("is_daylight_saving")
    @Expose
    private Boolean isDaylightSaving;

    public TimeZone() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentTime() {
        return this.currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public Integer getGmtOffset() {
        return this.gmtOffset;
    }

    public void setGmtOffset(Integer gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getIsDaylightSaving() {
        return this.isDaylightSaving;
    }

    public void setIsDaylightSaving(Boolean isDaylightSaving) {
        this.isDaylightSaving = isDaylightSaving;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TimeZone.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(this.id == null ? "<null>" : this.id);
        sb.append(',');
        sb.append("currentTime");
        sb.append('=');
        sb.append(this.currentTime == null ? "<null>" : this.currentTime);
        sb.append(',');
        sb.append("gmtOffset");
        sb.append('=');
        sb.append(this.gmtOffset == null ? "<null>" : this.gmtOffset);
        sb.append(',');
        sb.append("code");
        sb.append('=');
        sb.append(this.code == null ? "<null>" : this.code);
        sb.append(',');
        sb.append("isDaylightSaving");
        sb.append('=');
        sb.append(this.isDaylightSaving == null ? "<null>" : this.isDaylightSaving);
        sb.append(',');
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.setCharAt(sb.length() - 1, ']');
        } else {
            sb.append(']');
        }

        return sb.toString();
    }

    public int hashCode() {
        int result = 1;
        result = result * 31 + (this.currentTime == null ? 0 : this.currentTime.hashCode());
        result = result * 31 + (this.id == null ? 0 : this.id.hashCode());
        result = result * 31 + (this.code == null ? 0 : this.code.hashCode());
        result = result * 31 + (this.isDaylightSaving == null ? 0 : this.isDaylightSaving.hashCode());
        result = result * 31 + (this.gmtOffset == null ? 0 : this.gmtOffset.hashCode());
        return result;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof TimeZone)) {
            return false;
        } else {
            TimeZone rhs = (TimeZone)other;
            return (this.currentTime == rhs.currentTime || this.currentTime != null && this.currentTime.equals(rhs.currentTime)) && (this.id == rhs.id || this.id != null && this.id.equals(rhs.id)) && (this.code == rhs.code || this.code != null && this.code.equals(rhs.code)) && (this.isDaylightSaving == rhs.isDaylightSaving || this.isDaylightSaving != null && this.isDaylightSaving.equals(rhs.isDaylightSaving)) && (this.gmtOffset == rhs.gmtOffset || this.gmtOffset != null && this.gmtOffset.equals(rhs.gmtOffset));
        }
    }
}
