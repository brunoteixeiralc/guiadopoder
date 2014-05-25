package br.com.vector.guiadopoder.fragment;

import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import br.com.vector.guiadopoder.adapter.ListAdapterFuncionarioBuscar;
import br.com.vector.guiadopoder.model.Funcionario;

import com.example.guiadopoder.R;

public class BuscarPorNome extends Fragment {
	
	private View view;
	private ActionBar actionBar;
	private MenuItem item;
	private ListAdapterFuncionarioBuscar adapter;
	private ListView listaView;
	private EditText editsearch;
	private Fragment fragment;
	private List<Funcionario> listFuncionario;
	
	@SuppressWarnings("unchecked")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.list, container, false); 
		
		listFuncionario = (List<Funcionario>) getArguments().get("funcionarios");
		
		actionBar = ((ActionBarActivity)BuscarPorNome.this.getActivity()).getSupportActionBar();
		actionBar.setTitle("Buscar por nome");
		
		setHasOptionsMenu(true);
		
		listaView = (ListView) view.findViewById(R.id.list);
		listaView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					
					Funcionario funcionario = (Funcionario) parent.getAdapter().getItem(position);
				
					Bundle bundle = new Bundle();
					bundle.putSerializable("funcionario", funcionario);
					fragment = new br.com.vector.guiadopoder.fragment.Funcionario();
					fragment.setArguments(bundle);
				 	FragmentTransaction ft = BuscarPorNome.this.getActivity().getSupportFragmentManager().beginTransaction();
				    ft.replace(R.id.content_frame, fragment);
				    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				    ft.addToBackStack(null);
				    ft.commit(); 
			
			}
			
		});

		adapter = new ListAdapterFuncionarioBuscar(BuscarPorNome.this.getActivity(), listFuncionario);
		listaView.setAdapter(adapter);
	
		return view;
	}
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		
		item = menu.findItem(R.id.action_search);
		item.setVisible(true);
	     
	     editsearch = (EditText) MenuItemCompat.getActionView(item);
	     @SuppressWarnings("deprecation")
		 LayoutParams lparams = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.MATCH_PARENT);
	     editsearch.setLayoutParams(lparams);
	     editsearch.setHint("Buscar");
	     
	     editsearch.addTextChangedListener(textWatcher);
	        
		 MenuItemCompat.setOnActionExpandListener(item, new OnActionExpandListener() {
		        @Override
		        public boolean onMenuItemActionCollapse(MenuItem item) {
		            editsearch.setText("");
		            editsearch.clearFocus();
		            return true; 
		        }

		        @Override
		        public boolean onMenuItemActionExpand(MenuItem item) {
		        	editsearch.requestFocus();
		            InputMethodManager imm = (InputMethodManager) BuscarPorNome.this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
		            return true;  
		        }
		    });
		
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	 private TextWatcher textWatcher = new TextWatcher() {

	        @Override
	        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
	                int arg3) {
	 
	        }
	 
	        @Override
	        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
	                int arg3) {
	 
	        }
			@Override
			public void afterTextChanged(Editable s) {

	            String text = editsearch.getText().toString()
	                    .toLowerCase(Locale.getDefault());
	            adapter.filter(text);
				
			}
	 
	    };
}

