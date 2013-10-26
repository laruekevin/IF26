package com.utt.if26_thikev;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
/**
 * Created by if26 on 22/10/13.
 */
public class copyright extends Fragment {
	
	ImageButton buttonCopyright = null;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	
    	LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.fragment_copyright, container, false);
    	
    	buttonCopyright = (ImageButton) ll.findViewById(R.id.id_copyright);
    	buttonCopyright.setOnClickListener(new View.OnClickListener() 
        {               
            @Override
            public void onClick(View arg0) 
            {
            	AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
            	dialogBuilder.setMessage("App made by Thibault Jacquemet & Kévin Larue.");
            	dialogBuilder.create().show();
            }
        });
    	
        return ll;   	
    }
    

}
