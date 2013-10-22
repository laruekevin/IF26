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
	private String jsonResult;
	 private String url = "";
	 private DonneesConnection dc = new DonneesConnection();
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Intent intent = getIntent();
        Bundle bund = intent.getExtras();

        url = "http://train.sandbox.eutech-ssii.com/messenger/login.php?email="+bund.getString("login")+"&password="+bund.getString("password");
        accessWebService();
    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	
	// Async Task to access the web
	 private class JsonReadTask extends AsyncTask<String, Void, String> {
	  @Override
	  protected String doInBackground(String... params) {
	   HttpClient httpclient = new DefaultHttpClient();
	   HttpPost httppost = new HttpPost(params[0]);
	   try {
	    HttpResponse response = httpclient.execute(httppost);
	    jsonResult = inputStreamToString(
	      response.getEntity().getContent()).toString();
	   }
	 
	   catch (ClientProtocolException e) {
	    e.printStackTrace();
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	   return null;
	  }
	 
	  private StringBuilder inputStreamToString(InputStream is) {
	   String rLine = "";
	   StringBuilder answer = new StringBuilder();
	   BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	 
	   try {
	    while ((rLine = rd.readLine()) != null) {
	     answer.append(rLine);
	    }
	   }
	 
	   catch (IOException e) {
	    // e.printStackTrace();
	    Toast.makeText(getApplicationContext(),
	      "Error..." + e.toString(), Toast.LENGTH_LONG).show();
	   }
	   return answer;
	  }
	 
	  @Override
	  protected void onPostExecute(String result) {
	   ListDrwaer();
	  }
	 }// end async task
	 
	 public void accessWebService() {
	  JsonReadTask task = new JsonReadTask();
	  // passes values for the urls string array
	  task.execute(new String[] { url });
	 }
	 
	 // build hash set for list view
	 public void ListDrwaer() {
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
