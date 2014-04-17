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
import br.com.vector.guiadopoder.adapter.ListAdapter;
import br.com.vector.guiadopoder.model.Area;
import br.com.vector.guiadopoder.model.Cargos;

import com.example.guiadopoder.R;


public class Legislativo extends Fragment {
	
	private View view;
	private ListView listaView;
	private ListAdapter adapter;
	private ActionBar actionBar;
	private MenuItem item;
	private EditText editsearch;
	private List<Area> listArea;
	private Fragment fragment;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.list, container, false);  
		
		setHasOptionsMenu(true);
		
		actionBar = ((ActionBarActivity)Legislativo.this.getActivity()).getSupportActionBar();
		actionBar.setTitle("Poder Legislativo");
		
		listaView = (ListView) view.findViewById(R.id.list);
		listaView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Area area = (Area) parent.getAdapter().getItem(position);
				area.setPoder(actionBar.getTitle().toString());
				
				Bundle bundle = new Bundle();
				bundle.putSerializable("area", area);
				fragment = new Cargo();
				fragment.setArguments(bundle);
			 	FragmentTransaction ft = Legislativo.this.getActivity().getSupportFragmentManager().beginTransaction();
			    ft.replace(R.id.content_frame, fragment);
			    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			    ft.addToBackStack(null);
			    ft.commit(); 
			
			}
			
		});
		
		//TODO TEST
		listArea = new ArrayList<Area>();
		
		Area area = new Area();
		area.setNome("Congresso Nacional");
		area.setEndereco("Palácio do Congresso Nacional, Edifício Principal, Praça dos Três Poderes- 70.160-900 Brasília/DF");
		area.setEndWeb("http://www.senado.gov.br");
		area.setTelefone("(61) 3303-4141");
		area.setCargos(new ArrayList<Cargos>());
		Cargos cargo = new Cargos();
		cargo.setCargo("Presidente");
		Cargos cargo2 = new Cargos();
		cargo2.setCargo("Primeira Vice-Presidente");
		Cargos cargo3 = new Cargos();
		cargo3.setCargo("2A Vice-Presidente");
		area.getCargos().add(cargo);
		area.getCargos().add(cargo2);
		area.getCargos().add(cargo3);
		
		
		Area area1 = new Area();
		area1.setNome("Senado Federal");
		area1.setEndereco("Palácio do Congresso Nacional, Edifício Principal, Praça dos Três Poderes- 70.165-900 Brasília/DF");
		area1.setEndWeb("http://www.senado.gov.br");
		area1.setTelefone("(61) 3303-4141");
		area1.setCargos(new ArrayList<Cargos>());
		Cargos cargo4 = new Cargos();
		cargo4.setCargo("Presidente");
		Cargos cargo5 = new Cargos();
		cargo5.setCargo("Primeira Vice-Presidente");
		Cargos cargo6 = new Cargos();
		cargo6.setCargo("2A Vice-Presidente");
		area1.getCargos().add(cargo4);
		area1.getCargos().add(cargo5);
		area1.getCargos().add(cargo6);
		
		Area area2 = new Area();
		area2.setNome("Câmara dos Deputados");
		area2.setEndereco("Palácio do Congresso Nacional, Edifício Principal, Praça dos Três Poderes- 70.165-900 Brasília/DF");
		area2.setEndWeb("http://www.senado.gov.br");
		area2.setTelefone("(61) 3216-0000");
		area2.setCargos(new ArrayList<Cargos>());
		Cargos cargo7 = new Cargos();
		cargo7.setCargo("Presidente");
		Cargos cargo8 = new Cargos();
		cargo8.setCargo("Primeira Vice-Presidente");
		Cargos cargo9 = new Cargos();
		cargo9.setCargo("2A Vice-Presidente");
		area2.getCargos().add(cargo7);
		area2.getCargos().add(cargo8);
		area2.getCargos().add(cargo9);
		
		listArea.add(area);
		listArea.add(area1);
		listArea.add(area2);
		
		adapter = new ListAdapter(Legislativo.this.getActivity(), listArea,"Legislativo");
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
		            InputMethodManager imm = (InputMethodManager) Legislativo.this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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

