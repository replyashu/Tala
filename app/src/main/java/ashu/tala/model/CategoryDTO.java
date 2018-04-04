package ashu.tala.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by apple on 04/04/18.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class CategoryDTO implements Serializable {

    @SerializedName("icon")
    @Expose
    private IconDTO icon;

    public IconDTO getIcon() {
        return icon;
    }

    public void setIcon(IconDTO icon) {
        this.icon = icon;
    }
}
