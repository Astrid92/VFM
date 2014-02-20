package mx.ipn.tworisteando.adapter;

import mx.ipn.tworisteando.R;
import mx.ipn.tworisteando.model.Place;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PlaceAdapter extends ArrayAdapter<Place> {
	private LayoutInflater inflater;
	private int resourceId = 0;
	private Place[] places;
	
	public PlaceAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourceId = textViewResourceId;
        places = new Place[0];
    }
	
	@Override
	public int getCount() {
		return places.length;
	}
	
	@Override
	public Place getItem(int i) {
		return places[i];
	}
	
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View view;
      TextView textTitle;
      view = inflater.inflate(resourceId, parent, false);
      textTitle = (TextView)view.findViewById(R.id.title);
      Place place = getItem(position);
      textTitle.setText(place.getName());
      return view;
    }
    
    public void updatePlaces(Place[] places) {
    	if(places != null) {
    		this.places = places;
    		notifyDataSetChanged();
    	}
    }
}
