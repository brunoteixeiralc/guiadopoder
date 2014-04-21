package br.com.vector.guiadopoder.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import br.com.vector.guiadopoder.custom.CustomDialog;
import br.com.vector.guiadopoder.model.Cargo;

import com.example.guiadopoder.R;


@SuppressLint("HandlerLeak")@SuppressWarnings("unused")
public class Funcionario extends Fragment {
	
	private View view;
	private ActionBar actionBar;
	private MenuItem item;
	private Cargo cargoSelecionado;
	private TextView nome;
	private TextView aniversario;
	private TextView telefone;
	private TextView fax;
	private TextView email;
	private ImageView imgAlarm;
	private View line;
	private LinearLayout llNome;
	private LinearLayout llEmail;
	private LinearLayout llAniversario;
	private LinearLayout llTelefone;
	private LinearLayout llFax;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.main_funcionario, container, false);  
		
		cargoSelecionado = (Cargo) getArguments().getSerializable("cargo");

		if(cargoSelecionado.getFuncionarios() != null){
			
			ScrollView  mainLayout = (ScrollView) view.findViewById(R.id.mainFuncionario);
			@SuppressWarnings("static-access")
			LayoutInflater li =  (LayoutInflater)Funcionario.this.getActivity().getApplicationContext().getSystemService(Funcionario.this.getActivity().LAYOUT_INFLATER_SERVICE);
			
			for (final br.com.vector.guiadopoder.model.Funcionario funcionario : cargoSelecionado.getFuncionarios()) {
				
				View tempView = li.inflate(R.layout.funcionario, null);
				
				llNome = (LinearLayout) tempView.findViewById(R.id.llNome);
				llAniversario = (LinearLayout) tempView.findViewById(R.id.llAniversario);
				llEmail = (LinearLayout) tempView.findViewById(R.id.llEmail);
				llFax = (LinearLayout) tempView.findViewById(R.id.llFax);
				llTelefone = (LinearLayout) tempView.findViewById(R.id.llNumero);
				
				line = tempView.findViewById(R.id.line);
				line.setBackgroundColor(lineColor(cargoSelecionado.getPoder()));
				
				nome = (TextView) tempView.findViewById(R.id.nome);
				aniversario = (TextView) tempView.findViewById(R.id.aniversario);
				telefone = (TextView) tempView.findViewById(R.id.numero);
				fax = (TextView) tempView.findViewById(R.id.fax);
				email = (TextView) tempView.findViewById(R.id.email);
				imgAlarm = (ImageView) tempView.findViewById(R.id.imgAlarm);
				
				nome.setText(funcionario.getNome());
				aniversario.setText(funcionario.getAniversario());
				telefone.setText(funcionario.getTelefones().get(0));
				telefone.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
	
						String numero = funcionario.getTelefones().get(0).replace("(", "").replace(")", "");
						
						Intent intent = new Intent(Intent.ACTION_CALL);
						intent.setData(Uri.parse("tel:" + numero));
						startActivity(intent);
						
					}
				});
				fax.setText(funcionario.getFax());
				email.setText(funcionario.getEmail());
				email.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						Intent intent = new Intent(Intent.ACTION_SEND);
						intent.setType("plain/text");
						intent.putExtra(Intent.EXTRA_EMAIL, new String[] { email.getText().toString() });
						intent.putExtra(Intent.EXTRA_SUBJECT, "");
						intent.putExtra(Intent.EXTRA_TEXT, "");
						startActivity(Intent.createChooser(intent, ""));
						
					}
				});
				
				imgAlarm.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
							
						  CustomDialog dialog = new CustomDialog(Funcionario.this.getActivity(), "Anivers‡rio salvo",cargoSelecionado.getPoder());
						  dialog.show();
						
					}
				});
				
				mainLayout.addView(tempView);
				
			}
		}

		setHasOptionsMenu(true);
		
		actionBar = ((ActionBarActivity)Funcionario.this.getActivity()).getSupportActionBar();
		actionBar.setTitle(cargoSelecionado.getCargo());
		
		
		
		return view;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		
		item = menu.findItem(R.id.action_search);
		item.setVisible(false);
	     
		super.onCreateOptionsMenu(menu, inflater);
	
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
}

