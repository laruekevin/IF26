package com.utt.if26_thikev;

import com.utt.if26_thikev.HomeActivity;
import com.utt.if26_thikev.R;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends FragmentActivity implements OnClickListener  {
	EditText login = null;
    EditText password = null;
    Button buttonSubmit = null;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		login = (EditText)findViewById(R.id.login);
        password = (EditText)findViewById(R.id.password);
        buttonSubmit = (Button)findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(this);
	}

	 // Implement the OnClickListener callback
    public void onClick(View v) {
        /*AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        AlertDialog dialog = builder.create();
        dialog.setMessage("Login : "+login.getText()+" Password :  "+password.getText());
        dialog.show();*/
        Bundle bundle = new Bundle();
        bundle.putString("login",login.getText().toString());
        bundle.putString("password",password.getText().toString());
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
