//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package dad.geofx.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currency {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("plural")
    @Expose
    private String plural;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("symbol_native")
    @Expose
    private String symbolNative;

    public Currency() {
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

    public String getPlural() {
        return this.plural;
    }

    public void setPlural(String plural) {
        this.plural = plural;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbolNative() {
        return this.symbolNative;
    }

    public void setSymbolNative(String symbolNative) {
        this.symbolNative = symbolNative;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Currency.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("code");
        sb.append('=');
        sb.append(this.code == null ? "<null>" : this.code);
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(this.name == null ? "<null>" : this.name);
        sb.append(',');
        sb.append("plural");
        sb.append('=');
        sb.append(this.plural == null ? "<null>" : this.plural);
        sb.append(',');
        sb.append("symbol");
        sb.append('=');
        sb.append(this.symbol == null ? "<null>" : this.symbol);
        sb.append(',');
        sb.append("symbolNative");
        sb.append('=');
        sb.append(this.symbolNative == null ? "<null>" : this.symbolNative);
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
        result = result * 31 + (this.symbol == null ? 0 : this.symbol.hashCode());
        result = result * 31 + (this.code == null ? 0 : this.code.hashCode());
        result = result * 31 + (this.plural == null ? 0 : this.plural.hashCode());
        result = result * 31 + (this.symbolNative == null ? 0 : this.symbolNative.hashCode());
        return result;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof Currency)) {
            return false;
        } else {
            Currency rhs = (Currency)other;
            return (this.name == rhs.name || this.name != null && this.name.equals(rhs.name)) && (this.symbol == rhs.symbol || this.symbol != null && this.symbol.equals(rhs.symbol)) && (this.code == rhs.code || this.code != null && this.code.equals(rhs.code)) && (this.plural == rhs.plural || this.plural != null && this.plural.equals(rhs.plural)) && (this.symbolNative == rhs.symbolNative || this.symbolNative != null && this.symbolNative.equals(rhs.symbolNative));
        }
    }
}
