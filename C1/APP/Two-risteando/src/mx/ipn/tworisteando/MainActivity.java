package mx.ipn.tworisteando;

import mx.ipn.tworisteando.adapter.PlaceAutoCompleteAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends FragmentActivity {
	private AutoCompleteTextView searchPlaceEditText;
	public final static String ENTERED_PLACE = "mx.ipn.tworisteando.ENTERED_PLACE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        searchPlaceEditText = (AutoCompleteTextView) findViewById(R.id.search);
        // If the Up button was used, get the context to show the previous search.
        String previouslyEnteredPlace = getIntent().getStringExtra(MainActivity.ENTERED_PLACE);
        if(previouslyEnteredPlace != null)
        	searchPlaceEditText.setText(previouslyEnteredPlace);
        // Add a listener to the EditText search action.
        searchPlaceEditText.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    onSearch();
                    handled = true;
                }
                return handled;
            }
        });
        searchPlaceEditText.setAdapter(new PlaceAutoCompleteAdapter(this, android.R.layout.simple_list_item_1));
    }
    
    public void onSearchButtonClick(View view) {
    	onSearch();
    }
    
    private void onSearch() {
		// Go to the confirm place activity.
    	Intent intent = new Intent(this, ConfirmPlaceActivity.class);
    	intent.putExtra(ENTERED_PLACE, searchPlaceEditText.getText().toString());
        startActivity(intent);
	}
}
