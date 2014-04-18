package br.com.vector.guiadopoder.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
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
	private String poder;
	private View view;
	private ArrayList<Cargo> arraylist;
	
	public ListAdapterCargo(Context context, List<Cargo> lista,String poder) {
		this.lista = lista;
		this.poder = poder;
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
       
		 holder.texto.setText(lista.get(position).getCargo());
		 holder.line.setBackgroundColor(lineColor(poder));
		 
        return view;
		
	}
	
	private int lineColor(String poder){
		
		if(poder.equalsIgnoreCase("Poder Executivo")){
			return view.getResources().getColor(R.color.yellow);
		}else if(poder.equalsIgnoreCase("Poder Legislativo")){
			return view.getResources().getColor(R.color.green);
		}else if(poder.equalsIgnoreCase("Poder Judici‡rio")){
			return view.getResources().getColor(R.color.red);
		}else{
			return view.getResources().getColor(R.color.blue_light);
		}
		
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
                if (ar.getCargo().toLowerCase(Locale.getDefault()).contains(charText))
                {
                	lista.add(ar);
                }
            }
        }
        notifyDataSetChanged();
    }
    
   
}