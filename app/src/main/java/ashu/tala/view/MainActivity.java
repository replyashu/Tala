package ashu.tala.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.StringTokenizer;

import ashu.tala.Presenter;
import ashu.tala.R;
import ashu.tala.adapter.GridAdapter;
import ashu.tala.model.ItemsDTO;
import ashu.tala.model.LocationDTO;
import ashu.tala.model.ResultDTO;
import ashu.tala.model.VenueDTO;
import ashu.tala.network.NetworkClass;

public class MainActivity extends AppCompatActivity implements MainView, GridAdapter.ItemClickListener, View.OnClickListener{


    RecyclerView recyclerView;
    Presenter presenter;
    GridAdapter adapter;
    Button sortByDistance;
    Button sortByName;
    ResultDTO resultDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter(this, this);

        presenter.callEvents();

        recyclerView = (RecyclerView) findViewById(R.id.recycleVenue);
        sortByDistance = (Button) findViewById(R.id.btnSortByDistance);
        sortByName = (Button) findViewById(R.id.btnSortByName);

        sortByName.setOnClickListener(this);
        sortByDistance.setOnClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void populateEvents(ResultDTO lists) {
        adapter = new GridAdapter(getApplicationContext(),lists, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void sortByName(ResultDTO lists) {

    }

    @Override
    public void sortByDistance(ResultDTO lists) {

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onItemClick(View view, VenueDTO locationDTO) {

        Intent intent = new Intent(this, AddressActivity.class);
        intent.putExtra("address", locationDTO);

        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSortByName){
            presenter.sortByName(resultDTO);
        }
        else if(v.getId() == R.id.btnSortByDistance){
            presenter.sortByDistance(resultDTO);
        }
    }
}
