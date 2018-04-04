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
public class ResponseDTO implements Serializable {
    @SerializedName("geocode")
    @Expose
    private GeocodeDTO geocode;
    @SerializedName("groups")
    @Expose
    private List<GroupDTO> groupDTO;

    public GeocodeDTO getGeocode() {
        return geocode;
    }

    public void setGeocode(GeocodeDTO geocode) {
        this.geocode = geocode;
    }

    public List<GroupDTO> getGroupDTO() {
        return groupDTO;
    }

    public void setGroupDTO(List<GroupDTO> groupDTO) {
        this.groupDTO = groupDTO;
    }
}
