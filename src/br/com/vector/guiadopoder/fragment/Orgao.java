package br.com.vector.guiadopoder.fragment;

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
import br.com.vector.guiadopoder.adapter.ListAdapterOrgao;
import br.com.vector.guiadopoder.model.Setor;

import com.example.guiadopoder.R;


public class Orgao extends Fragment {
	
	private View view;
	private ActionBar actionBar;
	private MenuItem item;
	private ListAdapterOrgao adapter;
	private ListView listaView;
	private EditText editsearch;
	private Setor setorSelecionado;
	private Fragment fragment;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.list, container, false);  
		
		setorSelecionado = (Setor) getArguments().get("setor");
		
		setHasOptionsMenu(true);
		
		actionBar = ((ActionBarActivity)Orgao.this.getActivity()).getSupportActionBar();
		actionBar.setTitle(setorSelecionado.getNome());
		
		listaView = (ListView) view.findViewById(R.id.list);
		listaView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				br.com.vector.guiadopoder.model.Orgao orgaoSelecionado = (br.com.vector.guiadopoder.model.Orgao) parent.getAdapter().getItem(position);
				orgaoSelecionado.setPoder(setorSelecionado.getPoder());
				
				Bundle bundle = new Bundle();
				bundle.putSerializable("orgao", orgaoSelecionado);
				fragment = new br.com.vector.guiadopoder.fragment.Cargo();
				fragment.setArguments(bundle);
			 	FragmentTransaction ft = Orgao.this.getActivity().getSupportFragmentManager().beginTransaction();
			    ft.replace(R.id.content_frame, fragment);
			    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			    ft.addToBackStack(null);
			    ft.commit(); 
			}
			
		});
		
		if(setorSelecionado.getOrgaos() != null && setorSelecionado.getOrgaos().size() > 0){
			adapter = new ListAdapterOrgao(Orgao.this.getActivity(), setorSelecionado.getOrgaos(),setorSelecionado.getPoder().getCor());
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
		            InputMethodManager imm = (InputMethodManager) Orgao.this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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

