package com.utt.if26_thikev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;


import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//PAS UTILISE POUR LE MOMENT
public class webService {

		private String URL = "http://xxx.fr/";

		Gson gson;

		public webService(String url){
			URL = url;
			gson = new Gson();
		}

		private InputStream sendRequest(URL url) throws Exception {
			Log.i("MyActivity", "MyClass.getView() — test " + "Je suis dans sendRequest =>"+url);
			try {
				// Ouverture de la connexion
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				Log.i("MyActivity", "MyClass.getView() — test " + "Je suis dans sendRequest1"+urlConnection);
				// Connexion à l'url
				urlConnection.connect();
				Log.i("MyActivity", "MyClass.getView() — test " + "Je suis dans sendRequest2");
				// Si le serveur nous répond avec un code OK
				if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					
					return urlConnection.getInputStream();
				}
			} catch (Exception e) {
				throw new Exception("erreur");
			}

			return null;

		}
		
		public String search(String search) throws JSONException{
			HttpGet httpget= new HttpGet("http://wwww.webservice.kevin-larue.fr/connexion.php?login=thikev&pass=if262012");
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse httpResponse = null;
			JSONArray finalResult= null;
			try{
				httpResponse = httpclient.execute(httpget);
				BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
				StringBuilder builder = new StringBuilder();
				for (String line = null; (line = reader.readLine()) != null;) {
				    builder.append(line).append("\n");
				}
				JSONTokener tokener = new JSONTokener(builder.toString());
				finalResult = new JSONArray(tokener);
				
			}
			catch(ClientProtocolException clientProtocolException){
				clientProtocolException.printStackTrace();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
			return finalResult.toString();
			
			
			
		}

		public ArrayList<DonneesConnection> getDonnee() {
			
			Log.i("MyActivity", "MyClass.getView() — test " + "Je suis la");
			try {
				// Envoie de la requête
				
				InputStream inputStream = sendRequest(new URL(URL));
				Log.i("MyActivity", "MyClass.getView() — test " + "Je suis la1");
				// Vérification de l'inputStream
				if(inputStream != null) {
					Log.i("MyActivity", "MyClass.getView() — test " + "Je suis la2");
					// Lecture de l'inputStream dans un reader
					InputStreamReader reader = new InputStreamReader(inputStream);
					Log.i("MyActivity", "MyClass.getView() — test " + "Je suis la3");
					// Return la liste désérialisé par le moteur gson
					return gson.fromJson(reader, new TypeToken<List<DonneesConnection>>(){}.getType());
				}
			} catch (Exception e) {
				Log.e("WebService", "Impossible de rapatrier les données");
			}
			return null;
		}

}
