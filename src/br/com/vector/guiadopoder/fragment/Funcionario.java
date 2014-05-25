package br.com.vector.guiadopoder.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
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


@SuppressLint("HandlerLeak")@SuppressWarnings({ "unused", "deprecation" })
public class Funcionario extends Fragment {
	
	private View view;
	private ActionBar actionBar;
	private MenuItem item;
	private Cargo cargoSelecionado;
	private TextView nome;
	private TextView aniversario;
	private TextView telefone1;
	private TextView telefone2;
	private TextView telefone3;
	private TextView fax;
	private TextView email;
	private TextView sala;
	private ImageView imgAlarm;
	private ImageView imgAdd;
	private View line;
	private LinearLayout llTelefone1;
	private LinearLayout llTelefone2;
	private LinearLayout llTelefone3;
	private LinearLayout llFax;
	private LinearLayout llSala;
	private DateTime dt;
	 private br.com.vector.guiadopoder.notification.ScheduleClient scheduleClient;
	
	private List<br.com.vector.guiadopoder.model.Funcionario> funcionarios;
	private br.com.vector.guiadopoder.model.Funcionario funcionarioSelecionado;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.funcionario, container, false);  
		
	    // Create a new service client and bind our activity to this service
        scheduleClient = new br.com.vector.guiadopoder.notification.ScheduleClient(Funcionario.this.getActivity());
        scheduleClient.doBindService();
		
		funcionarioSelecionado = (br.com.vector.guiadopoder.model.Funcionario) getArguments().get("funcionario");
		
		funcionarios = new ArrayList<br.com.vector.guiadopoder.model.Funcionario>();
		
		llFax = (LinearLayout) view.findViewById(R.id.llFax);	
		llTelefone1 = (LinearLayout) view.findViewById(R.id.llNumero);	
		llTelefone2 = (LinearLayout) view.findViewById(R.id.llNumero2);	
		llTelefone3 = (LinearLayout) view.findViewById(R.id.llNumero3);	
		llSala = (LinearLayout) view.findViewById(R.id.llSala);
		
		line = view.findViewById(R.id.line);
		line.setBackgroundColor(Color.parseColor("#" + funcionarioSelecionado.getPoder().getCor()));
		
		nome = (TextView) view.findViewById(R.id.nome);
		aniversario = (TextView) view.findViewById(R.id.aniversario);
		telefone1 = (TextView) view.findViewById(R.id.numero);
		telefone2 = (TextView) view.findViewById(R.id.numero2);
		telefone3 = (TextView) view.findViewById(R.id.numero3);
		sala = (TextView) view.findViewById(R.id.sala);
		fax = (TextView) view.findViewById(R.id.fax);
		email = (TextView) view.findViewById(R.id.email);
		imgAlarm = (ImageView) view.findViewById(R.id.imgAlarm);
		imgAdd = (ImageView) view.findViewById(R.id.imgAdd);
				
		nome.setText(funcionarioSelecionado.getNome());
		
		dt = new DateTime(Long.parseLong(funcionarioSelecionado.getAniversario()));
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM");
		String dataFormatada = dt.toString(fmt);
		
		aniversario.setText(dataFormatada);
		
		if(funcionarioSelecionado.getTelefone() != null && !funcionarioSelecionado.getTelefone().isEmpty()){
			llTelefone1.setVisibility(View.VISIBLE);
			telefone1.setText(funcionarioSelecionado.getTelefone());
			telefone1.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
		
							String numero = funcionarioSelecionado.getTelefone().replace("(", "").replace(")", "");
							
							Intent intent = new Intent(Intent.ACTION_CALL);
							intent.setData(Uri.parse("tel:" + numero));
							startActivity(intent);
							
						}
			});
		}
		
		if(funcionarioSelecionado.getTelefone2() != null && !funcionarioSelecionado.getTelefone2().isEmpty()){
			llTelefone2.setVisibility(View.VISIBLE);
			telefone2.setText(funcionarioSelecionado.getTelefone2());
			telefone2.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
		
							String numero = funcionarioSelecionado.getTelefone2().replace("(", "").replace(")", "");
							
							Intent intent = new Intent(Intent.ACTION_CALL);
							intent.setData(Uri.parse("tel:" + numero));
							startActivity(intent);
							
						}
			});
		}
		
		if(funcionarioSelecionado.getTelefone3() != null && !funcionarioSelecionado.getTelefone3().isEmpty()){
			llTelefone3.setVisibility(View.VISIBLE);
			telefone3.setText(funcionarioSelecionado.getTelefone3());
			telefone3.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
		
							String numero = funcionarioSelecionado.getTelefone3().replace("(", "").replace(")", "");
							
							Intent intent = new Intent(Intent.ACTION_CALL);
							intent.setData(Uri.parse("tel:" + numero));
							startActivity(intent);
							
						}
			});
		}
		if(funcionarioSelecionado.getFax() != null && !funcionarioSelecionado.getFax().isEmpty()){
			llFax.setVisibility(View.VISIBLE);
			fax.setText(funcionarioSelecionado.getFax());
		}
		
		email.setText(funcionarioSelecionado.getEmail());
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
							
//				    	Calendar c = Calendar.getInstance();
//				    	c.set(new DateTime().getYear(), dt.getMonthOfYear(), dt.getDayOfMonth());
//				    	c.set(Calendar.HOUR_OF_DAY, 0);
//				    	c.set(Calendar.MINUTE, 0);
//				    	c.set(Calendar.SECOND, 0);
//				    	// Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
//				    	scheduleClient.setAlarmForNotification(c);
						
						CustomDialog dialog = new CustomDialog(Funcionario.this.getActivity(), "Aniversário salvo",funcionarioSelecionado.getPoder().getCor());
						dialog.show();
						
					}
				});
					
		imgAdd.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						Intent addContactIntent = new Intent(Contacts.Intents.Insert.ACTION, Contacts.People.CONTENT_URI);
						addContactIntent.putExtra(Contacts.Intents.Insert.NAME, nome.getText()); 
						addContactIntent.putExtra(Contacts.Intents.Insert.EMAIL, email.getText());
						if(llTelefone1.getVisibility() == View.VISIBLE){
							addContactIntent.putExtra(Contacts.Intents.Insert.PHONE, telefone1.getText());
							addContactIntent.putExtra(Contacts.Intents.Insert.PHONE_ISPRIMARY, "1");
						}
						if(llTelefone2.getVisibility() == View.VISIBLE){
							addContactIntent.putExtra(Contacts.Intents.Insert.PHONE, telefone2.getText());
						}
						if(llTelefone3.getVisibility() == View.VISIBLE){
							addContactIntent.putExtra(Contacts.Intents.Insert.PHONE, telefone3.getText());
						}
						startActivity(addContactIntent);
						
					}
				});

		if(funcionarioSelecionado.getComplemento() != null && !funcionarioSelecionado.getComplemento().isEmpty()){
			llSala.setVisibility(View.VISIBLE);
			sala.setText(funcionarioSelecionado.getComplemento());
		}
		
		setHasOptionsMenu(true);
		
		actionBar = ((ActionBarActivity)Funcionario.this.getActivity()).getSupportActionBar();
		if(cargoSelecionado != null)
			actionBar.setTitle(cargoSelecionado.getNome());
		else
			actionBar.setTitle(funcionarioSelecionado.getNome());
		
		
		return view;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		
		item = menu.findItem(R.id.action_search);
		item.setVisible(false);
	     
		super.onCreateOptionsMenu(menu, inflater);
	
	}	
}

