package com.utt.if26_thikev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.utt.if26_thikev.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

public class HomeActivity extends FragmentActivity {
	
	 private String url = "";
	 private webservice ws = new webservice();
	 private DonneesConnection dc = new DonneesConnection();
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Intent intent = getIntent();
        Bundle bund = intent.getExtras();

        url= "http://train.sandbox.eutech-ssii.com/messenger/login.php?email="+bund.getString("login")+"&password="+bund.getString("password");
        ws.accessWebService(url,this);
    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	// build hash set for list view
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
	
	 
}
