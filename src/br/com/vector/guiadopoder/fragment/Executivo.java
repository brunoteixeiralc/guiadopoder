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


public class Executivo extends Fragment {
	
	private View view;
	private ListView listaView;
	private ListAdapter adapter;
	private List<Area> listArea;
	private ActionBar actionBar;
	private MenuItem item;
	private EditText editsearch;
	private Fragment fragment;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.list, container, false);  
		
		setHasOptionsMenu(true);
		
		actionBar = ((ActionBarActivity)Executivo.this.getActivity()).getSupportActionBar();
		actionBar.setTitle("Poder Executivo");
		
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
			 	FragmentTransaction ft = Executivo.this.getActivity().getSupportFragmentManager().beginTransaction();
			    ft.replace(R.id.content_frame, fragment);
			    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			    ft.addToBackStack(null);
			    ft.commit(); 
			}
			
		});
		
		//TODO TEST
		listArea = new ArrayList<Area>();
		
		Area area = new Area();
		area.setNome("Presidência da República");
		area.setEndereco("Palácio do Planalto - Praça dos Três Poderes - 70.150-900 Brasília/DF");
		area.setEndWeb("http://www.planalto.gov.br");
		area.setTelefone("(61) 3411-1221");
		area.setCargos(new ArrayList<Cargos>());
		Cargos cargo = new Cargos();
		cargo.setCargo("Presidenta");
		area.getCargos().add(cargo);
		
		Area area1 = new Area();
		area1.setNome("Vice Presidência da República");
		area1.setEndereco("Palácio do Planalto, Anexo II, Térreo 70.083-900 Brasília/DF");
		area1.setEndWeb("http://www.planalto.gov.br");
		area1.setTelefone("(61) 3411-1221");
		area1.setCargos(new ArrayList<Cargos>());
		Cargos cargo2 = new Cargos();
		cargo2.setCargo("Vice-Presidente");
		area1.getCargos().add(cargo2);
		
		Area area2 = new Area();
		area2.setNome("Casa Civil da Presidência da República");
		area1.setEndereco("Palácio do Planalto, 4 andar, Térreo 70.150-900 Brasília/DF");
		area2.setEndWeb("http://www.planalto.gov.br");
		area2.setTelefone("(61) 3411-1221");
		area2.setCargos(new ArrayList<Cargos>());
		Cargos cargo3 = new Cargos();
		cargo3.setCargo("Ministra de Estado");
		area2.getCargos().add(cargo3);
		
		listArea.add(area);
		listArea.add(area1);
		listArea.add(area2);
		
		adapter = new ListAdapter(Executivo.this.getActivity(), listArea,"Executivo");
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
		            InputMethodManager imm = (InputMethodManager) Executivo.this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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

