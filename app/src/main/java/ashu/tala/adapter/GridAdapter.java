package ashu.tala.adapter;

import android.content.Context;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import ashu.tala.R;
import ashu.tala.model.ItemsDTO;
import ashu.tala.model.LocationDTO;
import ashu.tala.model.ResultDTO;
import ashu.tala.model.VenueDTO;

/**
 * Created by apple on 04/04/18.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder>{

    private Context context;
    private ResultDTO resultDTO;
    private ItemClickListener mClickListener;

    public GridAdapter(Context context, ResultDTO resultDTO, ItemClickListener itemClickListener){
        this.context = context;
        this.resultDTO = resultDTO;
        this.mClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.venue_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VenueDTO venueDTO = resultDTO.getResponseDTO().getGroupDTO().get(0).getItemDTO().get(position).getVenue();

        holder.txtName.setText(venueDTO.getName());

        Glide.with(context)
                .load(venueDTO.getCategories().get(0).getIcon().getPrefix() + venueDTO.getCategories().get(0).getIcon().getSuffix())
                .placeholder(R.drawable.notfound)
                .into(holder.imgCat);

        double dis = getDistance(venueDTO.getLocation().getLat(), venueDTO.getLocation().getLng());
        dis = Double.parseDouble(String.format("%.2f", dis));

        holder.txtDistance.setText(dis+ " km");


    }

    double getDistance(double lat, double lon){
        Location locationA = new Location("point A");

        double latA = resultDTO.getResponseDTO().getGeocode().getCenter().getLat();
        double lngA = resultDTO.getResponseDTO().getGeocode().getCenter().getLng();

        locationA.setLatitude(latA);
        locationA.setLongitude(lngA);

        Location locationB = new Location("point B");

        locationB.setLatitude(lat);
        locationB.setLongitude(lon);

        return locationA.distanceTo(locationB) / 1000;

    }

    @Override
    public int getItemCount() {
        return resultDTO.getResponseDTO().getGroupDTO().get(0).getItemDTO().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName;
        TextView txtDistance;
        ImageView imgCat;

        ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtVenueName);
            txtDistance = (TextView) itemView.findViewById(R.id.venueDistance);
            imgCat = (ImageView) itemView.findViewById(R.id.imgCatId);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view,
                    resultDTO.getResponseDTO().getGroupDTO().get(0).getItemDTO().get(getAdapterPosition()).getVenue());
        }
    }

    String getItem(int id) {
        return resultDTO.getResponseDTO().getGroupDTO().get(0).getItemDTO().get(id).toString();
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, VenueDTO pos);
    }
}
