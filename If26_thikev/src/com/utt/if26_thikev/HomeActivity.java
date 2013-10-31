package com.utt.if26_thikev;


import org.json.JSONException;
import org.json.JSONObject;

import com.utt.if26_thikev.R;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends FragmentActivity {
	
	 private String url = "";
	 private webservice ws = new webservice();
	 private DonneesConnection dc = new DonneesConnection();
	 
	 private SendTweetFragment stFragment; 
	 private ListMessageFragment lmFragment;
	 
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Intent intent = getIntent();
        Bundle bund = intent.getExtras();

        url= DonneesConnection.urlRoot+"login.php?email="+bund.getString("login")+"&password="+bund.getString("password");
        ws.accessWebService(url,this);
    
        setupFragments();
        //showFragment(this.lmFragment);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	// Build hash set for list view
	 public void ListDrwaer(String jsonResult) {
		 try {
	            JSONObject jsonResponse = new JSONObject(jsonResult);

	            dc.setError(jsonResponse.getBoolean("error"));
	            dc.setToken(jsonResponse.getString("token"));

        } catch (JSONException e) {
	            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
	            Toast.LENGTH_SHORT).show();
	    }
		 
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(!dc.getError()){
            builder.setMessage("Bonjour : "+dc.getToken());
            builder.create().show();

        }
        else{
            builder.setMessage("erreur de connexion");
		    builder.create().show();
		    Intent intent = new Intent(this, MainActivity.class);
	        startActivity(intent);
    	}
	 }
	
	 // Setup the fragment to show here
	    private void setupFragments() {
	        final FragmentManager fm = getSupportFragmentManager();
	 
	        this.stFragment = (SendTweetFragment) fm.findFragmentById(R.id.frag_new_tweet);
	        if (this.stFragment == null) {
	            this.stFragment = new SendTweetFragment();
	        }
	        
	        this.lmFragment = (ListMessageFragment) fm.findFragmentById(R.id.frag_list_message);
	        if (this.lmFragment == null) {
	            this.lmFragment = new ListMessageFragment();
	        }
	 
	    }
	 
	    //Show fragment in parameters
	    private void showFragment(final Fragment fragment) {
	        if (fragment == null)
	            return;
	 
	        final FragmentManager fm = getSupportFragmentManager();
	        final FragmentTransaction ft = fm.beginTransaction();
	        // We can also animate the changing of fragment
	        ft.setCustomAnimations(android.R.anim.slide_in_left,
	                android.R.anim.slide_out_right);
	 
	        ft.replace(R.id.frameLayout, fragment);
	 
	        ft.commit();
	    }
	 
	    //Method which swap fragments -> Used in XML files
	    public void goToNewTweetFragment(View v) {
	        showFragment(this.stFragment);
	    }
	    
	    public void goToListMessageFragment(View v) {
	        showFragment(this.lmFragment);
	    }
	 
}
