package br.com.vector.guiadopoder.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


public class Estadual extends Fragment {
	
	private View view;
	private ListView listaView;
	private ListAdapter adapter;
	private ActionBar actionBar;
	private MenuItem item;
	private EditText editsearch;
	private List<Area> listArea;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.list, container, false); 
		
		actionBar = ((ActionBarActivity)Estadual.this.getActivity()).getSupportActionBar();
		actionBar.setTitle("Poder Estadual");
		
		setHasOptionsMenu(true);
		
		listaView = (ListView) view.findViewById(R.id.list);
		listaView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			
			}
			
		});
		
		//TODO TEST
		listArea = new ArrayList<Area>();
		
		Area area = new Area();
		area.setNome("Governadoria do estado do Acre - AC");
		area.setEndereco("Avenida Brasil, 402, Centro 69.900-100 Rio Branco/AC");
		area.setEndWeb("http://www.ac.gov.br");
		area.setTelefone("");
		area.setCargos(new ArrayList<Cargos>());
		Cargos cargos = new Cargos();
		cargos.setCargo("Governador");
		Cargos cargos2 = new Cargos();
		cargos2.setCargo("Vice-Governador");
		Cargos cargos3 = new Cargos();
		cargos3.setCargo("Chefe");
		area.getCargos().add(cargos);
		area.getCargos().add(cargos2);
		area.getCargos().add(cargos3);
		
		
		Area area1 = new Area();
		area1.setNome("Governadoria do estado do Amap‡ - AP");
		area1.setEndereco("Pal‡cio dos Sententri‹o, Rua General Rondon, 259 - Centro 68.906-130 Macap‡/AP");
		area1.setEndWeb("http://www.amapa.gov.br");
		area1.setTelefone("");
		area1.setCargos(new ArrayList<Cargos>());
		Cargos cargo4 = new Cargos();
		cargo4.setCargo("Governador");
		Cargos cargos5 = new Cargos();
		cargos5.setCargo("Vice-Governadora");
		Cargos cargos6 = new Cargos();
		cargos6.setCargo("Chefe");
		area1.getCargos().add(cargo4);
		area1.getCargos().add(cargos5);
		area1.getCargos().add(cargos6);
		
		Area area2 = new Area();
		area2.setNome("Governadoria do estado do Amazonas - AM");
		area2.setEndereco("Av. Brasil, Compensa II 69.036-110 Manaus/AM");
		area2.setEndWeb("http://www.amazonas.am.gov.br");
		area2.setTelefone("");
		area2.setCargos(new ArrayList<Cargos>());
		Cargos cargo7 = new Cargos();
		cargo7.setCargo("Governador");
		Cargos cargos8 = new Cargos();
		cargos8.setCargo("Vice-Governador");
		Cargos cargos9 = new Cargos();
		cargos9.setCargo("Secret‡rio de Estado");
		area2.getCargos().add(cargo7);
		area2.getCargos().add(cargos8);
		area2.getCargos().add(cargos9);
		
		listArea.add(area);
		listArea.add(area1);
		listArea.add(area2);
		
		adapter = new ListAdapter(Estadual.this.getActivity(), listArea,"Estadual");
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
		            InputMethodManager imm = (InputMethodManager) Estadual.this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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

