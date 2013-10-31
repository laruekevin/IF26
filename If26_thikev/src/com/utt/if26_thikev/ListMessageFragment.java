package com.utt.if26_thikev;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

public class ListMessageFragment extends ListFragment {
	public static final String TAG = "ListViewFragment";
	private RelativeLayout rl;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		
		 rl = (RelativeLayout)inflater.inflate(R.layout.fragment_list_message, container, false);
        return rl;
    }
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
 
        String[] items = getResources().getStringArray(R.array.messages);
 
        //ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),android.R.layout.fragment_list_message, items);
        //setListAdapter(aa);
        
    }
    
}
