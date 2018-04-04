package ashu.tala.network;

import ashu.tala.model.ResultDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by apple on 04/04/18.
 */

public interface NetworkInterface {

    @GET("venues/explore?near=BLR&oauth_token=U1LSENPRB214H1BMU3MXDXTUPRAB1FQVHRLZZM5NIWFAZM1W&v=20180402")
    Call<ResultDTO> getListOfEvents();
}
