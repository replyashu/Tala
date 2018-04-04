package ashu.tala;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ashu.tala.model.GroupDTO;
import ashu.tala.model.ItemsDTO;
import ashu.tala.model.ResultDTO;
import ashu.tala.network.NetworkClass;
import ashu.tala.network.NetworkInterface;
import ashu.tala.view.MainView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by apple on 02/04/18.
 */

public class Presenter implements Callback<ResultDTO> {

    private MainView mainView;
    private Context context;
    private ProgressDialog progressBar;

    public Presenter(Context context, MainView mainView){
        this.context = context;
        this.mainView = mainView;
    }

    public void onDestroy(){
        mainView = null;
    }

    public void callEvents(){
        makeNetworkCalls();
    }

    void makeNetworkCalls(){
        NetworkClass networkClass = new NetworkClass();
        Retrofit  retrofit = networkClass.start();

        NetworkInterface networkInterface = retrofit.create(NetworkInterface.class);

        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);
        progressBar.setMessage("Fetching Venues ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
        Call<ResultDTO> resultDTOCall = networkInterface.getListOfEvents();
        resultDTOCall.enqueue(this);
    }

    public void sortByName(ResultDTO resultDTO){

        mainView.sortByName(resultDTO);
    }

    public void sortByDistance(ResultDTO resultDTO){
//        Collections.sort(resultDTO.getResponseDTO().getGroupDTO().get(0).getItemDTO(), new Comparator<ItemsDTO>() {
//            @Override
//            public int compare(ItemsDTO o1, ItemsDTO o2) {
//                return);
//            }
//        });
        mainView.sortByDistance(resultDTO);
    }




    @Override
    public void onResponse(Call<ResultDTO> call, Response<ResultDTO> response) {

        ResultDTO resultDTO = (ResultDTO) response.body();

        if(mainView != null) {
            mainView.populateEvents(resultDTO);
            progressBar.dismiss();
        }
    }

    @Override
    public void onFailure(Call<ResultDTO> call, Throwable t) {

    }


}
