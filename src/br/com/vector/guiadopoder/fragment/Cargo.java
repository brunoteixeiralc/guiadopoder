package br.com.vector.guiadopoder.fragment;

import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.LayoutParams;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import br.com.vector.guiadopoder.adapter.ListAdapterCargo;
import br.com.vector.guiadopoder.model.Area;

import com.example.guiadopoder.R;


public class Cargo extends Fragment {
	
	private View view;
	private ActionBar actionBar;
	private MenuItem item;
	private Area areaSelecionada;
	private ListAdapterCargo adapter;
	private ListView listaView;
	private EditText editsearch;
	private TextView endereco;
	private LinearLayout llEndereco;
	private LinearLayout llNumero;
	private TextView numero;
	private LinearLayout llSite;
	private TextView site;
	private Fragment fragment;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.list, container, false);  
		
		areaSelecionada = (Area) getArguments().getSerializable("area");
		
		llEndereco = (LinearLayout) view.findViewById(R.id.llEndereco);
		llEndereco.setVisibility(View.VISIBLE);
		
		endereco = (TextView) view.findViewById(R.id.endereco);
		endereco.setText(areaSelecionada.getEndereco());
		
		llNumero = (LinearLayout) view.findViewById(R.id.llNumero);
		llNumero.setVisibility(View.VISIBLE);
		
		numero = (TextView) view.findViewById(R.id.numero);
		numero.setText(areaSelecionada.getTelefone());
		numero.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String numero =  areaSelecionada.getTelefone().replace("(", "").replace(")", "");
				
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:" + numero));
				startActivity(intent);
				
			}
		});
		
		if(numero.getText().toString().isEmpty()){
			llNumero.setVisibility(View.GONE);
		}
		
		llSite = (LinearLayout) view.findViewById(R.id.llSite);
		llSite.setVisibility(View.VISIBLE);
		
		site = (TextView) view.findViewById(R.id.site);
		site.setText(areaSelecionada.getEndWeb());
		site.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Uri uri = Uri.parse(areaSelecionada.getEndWeb());
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				
			}
		});
		
		setHasOptionsMenu(true);
		
		actionBar = ((ActionBarActivity)Cargo.this.getActivity()).getSupportActionBar();
		actionBar.setTitle(areaSelecionada.getNome());
		
		listaView = (ListView) view.findViewById(R.id.list);
		listaView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				br.com.vector.guiadopoder.model.Cargo cargo = (br.com.vector.guiadopoder.model.Cargo) parent.getAdapter().getItem(position);
				cargo.setPoder(areaSelecionada.getPoder());
			
				Bundle bundle = new Bundle();
				bundle.putSerializable("cargo", cargo);
				fragment = new Funcionario();
				fragment.setArguments(bundle);
			 	FragmentTransaction ft = Cargo.this.getActivity().getSupportFragmentManager().beginTransaction();
			    ft.replace(R.id.content_frame, fragment);
			    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			    ft.addToBackStack(null);
			    ft.commit(); 
				
			}
			
		});
		
		adapter = new ListAdapterCargo(Cargo.this.getActivity(), areaSelecionada.getCargos(),areaSelecionada.getPoder());
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
		            InputMethodManager imm = (InputMethodManager) Cargo.this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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

