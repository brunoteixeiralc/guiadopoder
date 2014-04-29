package br.com.vector.guiadopoder.fragment;

import java.util.ArrayList;
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
import br.com.vector.guiadopoder.adapter.ListAdapterFuncionario;
import br.com.vector.guiadopoder.model.Funcionario;

import com.example.guiadopoder.R;

public class BuscarPorNome extends Fragment {
	
	private View view;
	private ActionBar actionBar;
	private MenuItem item;
	private ListAdapterFuncionario adapter;
	private ListView listaView;
	private EditText editsearch;
	private Fragment fragment;
	private List<Funcionario> listFuncionario;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.list, container, false); 
		
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
		
		//TODO TEST
		listFuncionario = new ArrayList<Funcionario>();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Vera Zaverucha");
		funcionario.setAniversario("19/08");
		funcionario.setEmail("vera.zaverucha@ancine.gov.br");
		funcionario.setFax("(21)3037-6095");
		funcionario.setTelefones(new ArrayList<String>());
		funcionario.getTelefones().add("(21)3037-6330");
		funcionario.setPoder("Poder Executivo");
		
		Funcionario funcionario2 = new Funcionario();
		funcionario2.setNome("Cristovam Buarque");
		funcionario2.setAniversario("20/02");
		funcionario2.setEmail("cristovam@senador.gov.br");
		funcionario2.setFax("(61)3303-2874");
		funcionario2.setTelefones(new ArrayList<String>());
		funcionario2.getTelefones().add("(61)3303-2281");
		funcionario2.setPoder("Poder Legislativo");
		
		Funcionario funcionario3 = new Funcionario();
		funcionario3.setNome("Ministro Carlos Ayres Britto");
		funcionario3.setAniversario("18/11");
		funcionario3.setEmail("sergio.mendes@stf.jus.br");
		funcionario3.setFax("");
		funcionario3.setTelefones(new ArrayList<String>());
		funcionario3.getTelefones().add("(61)3217-4311");
		funcionario3.getTelefones().add("(61)3217-4312");
		funcionario3.setPoder("Poder Judici‡rio");
		

		Funcionario funcionario4 = new Funcionario();
		funcionario4.setNome("Pedro Marcos Lopes");
		funcionario4.setAniversario("13/09");
		funcionario4.setEmail("pedrolopes.df@governo.se.gov.br");
		funcionario4.setFax("(61)3325-2556");
		funcionario4.setTelefones(new ArrayList<String>());
		funcionario4.getTelefones().add("(61)3424-9400");
		funcionario4.getTelefones().add("(61)3424-9404");
		funcionario4.setPoder("Poder Estadual");
		
		listFuncionario.add(funcionario);
		listFuncionario.add(funcionario2);
		listFuncionario.add(funcionario3);
		listFuncionario.add(funcionario4);
		
		adapter = new ListAdapterFuncionario(BuscarPorNome.this.getActivity(), listFuncionario);
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

