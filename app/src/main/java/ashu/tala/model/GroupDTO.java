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
public class GroupDTO implements Serializable {

    @SerializedName("items")
    @Expose
    private List<ItemsDTO> itemDTO;

    public List<ItemsDTO> getItemDTO() {
        return itemDTO;
    }

    public void setItemDTO(List<ItemsDTO> itemDTO) {
        this.itemDTO = itemDTO;
    }
}
