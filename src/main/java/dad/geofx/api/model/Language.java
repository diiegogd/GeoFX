//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package dad.geofx.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("native")
    @Expose
    private String _native;

    public Language() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNative() {
        return this._native;
    }

    public void setNative(String _native) {
        this._native = _native;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Language.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("code");
        sb.append('=');
        sb.append(this.code == null ? "<null>" : this.code);
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(this.name == null ? "<null>" : this.name);
        sb.append(',');
        sb.append("_native");
        sb.append('=');
        sb.append(this._native == null ? "<null>" : this._native);
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
        result = result * 31 + (this.name == null ? 0 : this.name.hashCode());
        result = result * 31 + (this._native == null ? 0 : this._native.hashCode());
        result = result * 31 + (this.code == null ? 0 : this.code.hashCode());
        return result;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof Language)) {
            return false;
        } else {
            Language rhs = (Language)other;
            return (this.name == rhs.name || this.name != null && this.name.equals(rhs.name)) && (this._native == rhs._native || this._native != null && this._native.equals(rhs._native)) && (this.code == rhs.code || this.code != null && this.code.equals(rhs.code));
        }
    }
}
