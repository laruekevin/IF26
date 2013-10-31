package com.utt.if26_thikev;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class SendTweetFragment extends Fragment {

	Button send =null;
	EditText tweet = null;
	webservice ws = new webservice();
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	
    	
    	LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.fragment_send_tweet, container, false);
    	
    	send = (Button)ll.findViewById(R.id.newtweet_button);
    	tweet =  (EditText) ll.findViewById(R.id.newtweet_editText);

    	return ll;
    }
	
}
