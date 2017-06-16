package com.gits.developer.pesonakulonprogo.model.help;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kazt on 16/06/17.
 */

public class HelpModel implements Serializable {

    @SerializedName("id_bantuan")
    @Expose
    private String id_bantuan;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("desc")
    @Expose
    private String desc;

    public String getId_bantuan() {
        return id_bantuan;
    }

    public void setId_bantuan(String id_bantuan) {
        this.id_bantuan = id_bantuan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
