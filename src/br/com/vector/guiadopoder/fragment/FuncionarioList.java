package br.com.vector.guiadopoder.fragment;

import java.util.Collections;
import java.util.Comparator;
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
import br.com.vector.guiadopoder.adapter.ListAdapterFuncionario;
import br.com.vector.guiadopoder.model.Cargo;
import br.com.vector.guiadopoder.model.Funcionario;

import com.example.guiadopoder.R;


public class FuncionarioList extends Fragment {
	
	private View view;
	private ActionBar actionBar;
	private MenuItem item;
	private ListAdapterFuncionario adapter;
	private ListView listaView;
	private EditText editsearch;
	private Fragment fragment;
	
	private br.com.vector.guiadopoder.model.Cargo cargoSelecionado;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.list, container, false);  
		
		cargoSelecionado = (Cargo) getArguments().get("cargo");
		
		setHasOptionsMenu(true);
		
		actionBar = ((ActionBarActivity)FuncionarioList.this.getActivity()).getSupportActionBar();
		actionBar.setTitle(cargoSelecionado.getNome());
		
		listaView = (ListView) view.findViewById(R.id.list);
		listaView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Funcionario funcionarioSelecionado = (Funcionario) parent.getAdapter().getItem(position);
				funcionarioSelecionado.setPoder(cargoSelecionado.getPoder());
				
				Bundle bundle = new Bundle();
				bundle.putSerializable("funcionario", funcionarioSelecionado);
				fragment = new br.com.vector.guiadopoder.fragment.Funcionario();
				fragment.setArguments(bundle);
			 	FragmentTransaction ft = FuncionarioList.this.getActivity().getSupportFragmentManager().beginTransaction();
			    ft.replace(R.id.content_frame, fragment);
			    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			    ft.addToBackStack(null);
			    ft.commit(); 
		
			}
			
		});
		
		if(cargoSelecionado.getFuncionarios() != null && cargoSelecionado.getFuncionarios().size() > 0){

			Collections.sort(cargoSelecionado.getFuncionarios(),new Comparator() {

				@Override
				public int compare(Object o1, Object o2) {
					 Funcionario p1 = (Funcionario) o1;
					 Funcionario p2 = (Funcionario) o2;
			         return p1.getNome().compareToIgnoreCase(p2.getNome());
				}
			});
			
			adapter = new ListAdapterFuncionario(FuncionarioList.this.getActivity(), cargoSelecionado.getFuncionarios(), cargoSelecionado.getPoder().getCor());
			
			listaView.setAdapter(adapter);
		}

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
		            InputMethodManager imm = (InputMethodManager) FuncionarioList.this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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
	            if(adapter != null)
	            	adapter.filter(text);
				
			}
	 
	    };
}

