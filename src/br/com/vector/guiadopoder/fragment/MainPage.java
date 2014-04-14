package br.com.vector.guiadopoder.fragment;

import com.example.guiadopoder.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MainPage extends Fragment {
	
	private View view;
	private ActionBar actionBar;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.main_page, container,false);
		
		actionBar = ((ActionBarActivity)MainPage.this.getActivity()).getSupportActionBar();
		actionBar.setTitle("Guia Pr‡tico do Poder");
		
		return view;
	}
	
}

