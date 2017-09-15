package com.squidge.samanthacausey.weatherwithbobross.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squidge.samanthacausey.weatherwithbobross.MainActivity;
import com.squidge.samanthacausey.weatherwithbobross.R;
import com.squidge.samanthacausey.weatherwithbobross.data.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samanthacausey on 5/3/16.
 * Adapter: A subclass of RecyclerView.Adapter responsible for providing views that represent cities
 * in a data set.
 */

public class CityAdapter
        extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private Context context;
    private List<City> cities = new ArrayList<City>();
    public static final String KEY_CITY_NAME = "KEY_CITY_NAME";

    public CityAdapter(Context context) {
        this.context = context;
        cities = City.listAll(City.class);
    }

    /*Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an
    item.*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_row, parent, false);
        return new ViewHolder(rowView);
    }

    /*Called by RecyclerView to display the data at the specified position. Should update the contents
    * of the itemView to reflect the item at the given position. RecyclerView will not call thi method again
    * if the position of the item changes in the data set unless the item itself is invalidated or the new
    * position cannot be determined.*/
    @Override
    public void onBindViewHolder(final ViewHolder holder,
                                 final int position) {
        //UI elements from city_row.xml
        holder.tvCityName.setText(cities.get(position).getCityName());
        holder.btnDeleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCity(position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                ((MainActivity) context).showCityWeatherActivity(
                        cities.get(holder.getAdapterPosition()).getCityName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void addCity(City city) {
        city.save();
        cities.add(0, city);
        notifyDataSetChanged();

    }

    public void removeCity(int position) {
        cities.get(position).delete();
        cities.remove(position);
        notifyDataSetChanged();
    }

    /* A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    ViewHolders belong to the adapter.
    */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCityName;
        public Button btnDeleteCity;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCityName = (TextView) itemView.findViewById(R.id.tvCityName);
            btnDeleteCity = (Button) itemView.findViewById(R.id.btnDeleteCity);
        }
    }
}
