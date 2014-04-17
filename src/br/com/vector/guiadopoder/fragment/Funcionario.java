package br.com.vector.guiadopoder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.guiadopoder.R;


public class Funcionario extends Fragment {
	
	private View view;
	private ActionBar actionBar;
	private MenuItem item;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.list, container, false);  
		
		setHasOptionsMenu(true);
		
		actionBar = ((ActionBarActivity)Funcionario.this.getActivity()).getSupportActionBar();
		actionBar.setTitle("Nome");
		
		return view;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		
		item = menu.findItem(R.id.action_search);
		item.setVisible(false);
	     
		super.onCreateOptionsMenu(menu, inflater);
	
	}	
}

