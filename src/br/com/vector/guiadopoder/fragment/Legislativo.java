package br.com.vector.guiadopoder.fragment;

import java.util.ArrayList;
import java.util.List;

import br.com.vector.guiadopoder.adapter.ListAdapter;
import br.com.vector.guiadopoder.model.Lista;

import com.example.guiadopoder.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class Legislativo extends Fragment {
	
	private View view;
	private ListView listaView;
	private ListAdapter adapter;
	private List<Lista> listTexto;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.list, container, false);  
		listaView = (ListView) view.findViewById(R.id.list);
		listaView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			
			}
			
		});
		
		//TODO TEST
		listTexto = new ArrayList<Lista>();
		Lista lista = new Lista();
		lista.setTexto("LegislativoLegislativoLegislativoLegislativoLegislativoLegislativoLegislativoLegislativoLegislativo");
		listTexto.add(lista);

		Lista lista2 = new Lista();
		lista2.setTexto("LegislativoLegislativoLegislativoLegislativo");
		listTexto.add(lista2);
		
		Lista lista3 = new Lista();
		lista3.setTexto("LegislativoLegislativoLegislativo");
		listTexto.add(lista3);
		
		adapter = new ListAdapter(Legislativo.this.getActivity(), listTexto,"Legislativo");
		listaView.setAdapter(adapter);
		
		return view;
	}
}

