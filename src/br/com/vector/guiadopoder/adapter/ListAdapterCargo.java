package br.com.vector.guiadopoder.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.vector.guiadopoder.model.Cargo;

import com.example.guiadopoder.R;

public class ListAdapterCargo extends BaseAdapter {

	private List<Cargo> lista;
	private static LayoutInflater inflater = null;
	private String cor;
	private View view;
	private ArrayList<Cargo> arraylist;
	
	public ListAdapterCargo(Context context, List<Cargo> lista,String cor) {
		this.lista = lista;
		this.cor = cor;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.arraylist = new ArrayList<Cargo>();
        this.arraylist.addAll(lista);
		
	}
	
	static class ViewHolder{
  		
		View line;
  		TextView texto;
  	}

	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null;
		
		view = convertView;
		
		 if(view==null){
	        	
        	 holder = new ViewHolder();
        	 
        	 view = inflater.inflate(R.layout.list_row, null);
        	 view.setTag(holder);
        	 
        	 holder.line = (View) view.findViewById(R.id.line);
             holder.texto = (TextView)view.findViewById(R.id.texto);
             
        }else{
        	
        	holder = (ViewHolder) view.getTag();
        }
       
		 holder.texto.setText(lista.get(position).getNome());
		 holder.line.setBackgroundColor(Color.parseColor("#" + this.cor));
		 
        return view;
		
	}
	
	
	// Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        lista.clear();
        if (charText.length() == 0) {
        	lista.addAll(arraylist);
        }
        else
        {
            for (Cargo ar : arraylist)
            {
                if (ar.getNome().toLowerCase(Locale.getDefault()).contains(charText))
                {
                	lista.add(ar);
                }
            }
        }
        notifyDataSetChanged();
    }
    
   
}