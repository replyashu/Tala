package ashu.tala.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import ashu.tala.R;
import ashu.tala.model.LocationDTO;
import ashu.tala.model.VenueDTO;

/**
 * Created by apple on 04/04/18.
 */

public class AddressActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtAddress;
    VenueDTO locationDTO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.venue_details);
        Intent intent = getIntent();
        locationDTO = (VenueDTO) intent.getExtras().getSerializable("address");
        txtAddress = (TextView) findViewById(R.id.txtAddress);

        StringBuilder sb = new StringBuilder();
        List<String> s = locationDTO.getLocation().getFormattedAddress();

        int len = s.size();

        for(int i = 0 ; i < len; i++){
            sb.append(s.get(i));
        }

        String url = locationDTO.getUrl();

        if(url == null){
            url = "Not available";
        }

        String hours = "";

        try{
            hours = locationDTO.getHours().getStatus();
        }
        catch (NullPointerException e){
            hours = "Timing not available";
        }
        sb.append("\n\nSite: " + url + "\n\nRating: " + locationDTO.getRating() + "\n\n " + hours);

        txtAddress.setText(sb.toString());

        txtAddress.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.txtAddress){
            Uri gmmIntentUri = Uri.parse("google.streetview:cbll=" + locationDTO.getLocation().getLat() + "," + locationDTO.getLocation().getLng());
//            String uri = "geo:" + locationDTO.getLat() + ","+ locationDTO.getLng() + "?z=zoom";
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, gmmIntentUri);
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            startActivity(intent);
        }
    }
}
