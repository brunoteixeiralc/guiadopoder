package br.com.vector.guiadopoder.fragment;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.TextView;
import android.widget.Toast;
import br.com.vector.guiadopoder.MainActivity;
import br.com.vector.guiadopoder.custom.CustomDialog;
import br.com.vector.guiadopoder.model.Cargo;
import br.com.vector.guiadopoder.utils.NotificationUtils;

import com.example.guiadopoder.R;


@SuppressLint("HandlerLeak")
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
	
	private static final int MENSAGEM_SET_ALARME = 1;
	private Handler handler = new SetAlarmHandle();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.funcionario, container, false);  
		
		cargoSelecionado = (Cargo) getArguments().getSerializable("cargo");
		
		llNome = (LinearLayout) view.findViewById(R.id.llNome);
		llAniversario = (LinearLayout) view.findViewById(R.id.llAniversario);
		llEmail = (LinearLayout) view.findViewById(R.id.llEmail);
		llFax = (LinearLayout) view.findViewById(R.id.llFax);
		llTelefone = (LinearLayout) view.findViewById(R.id.llNumero);
		
		line = view.findViewById(R.id.line);
		line.setBackgroundColor(lineColor(cargoSelecionado.getPoder()));
		nome = (TextView) view.findViewById(R.id.nome);
		aniversario = (TextView) view.findViewById(R.id.aniversario);
		telefone = (TextView) view.findViewById(R.id.numero);
		fax = (TextView) view.findViewById(R.id.fax);
		email = (TextView) view.findViewById(R.id.email);
		imgAlarm = (ImageView) view.findViewById(R.id.imgAlarm);
		
		if(cargoSelecionado.getFuncionarios() != null){
			
			for (final br.com.vector.guiadopoder.model.Funcionario funcionario : cargoSelecionado.getFuncionarios()) {
				
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
							
						  Calendar c = Calendar.getInstance();
					      //c.set(c.get(Calendar.YEAR),Integer.parseInt(aniversario.getText().toString().substring(3,5)), Integer.parseInt(aniversario.getText().toString().substring(0,2)));
					      c.set(c.get(Calendar.YEAR),Calendar.APRIL, 18);
					      c.set(Calendar.HOUR, 9);
					      c.set(Calendar.MINUTE, 26);
					      c.set(Calendar.SECOND, 0);
					      c.set(Calendar.AM_PM, Calendar.PM);
					      
						  Message message = new Message();
						  message.what = MENSAGEM_SET_ALARME;
						  handler.sendMessageAtTime(message, System.currentTimeMillis());
							
						  CustomDialog dialog = new CustomDialog(Funcionario.this.getActivity(), "Anivers‡rio salvo",cargoSelecionado.getPoder());
						  dialog.show();
						
					}
				});
				
			}
		}else{
			
			llNome.setVisibility(View.GONE);
			llAniversario.setVisibility(View.GONE);
			llEmail.setVisibility(View.GONE);
			llFax.setVisibility(View.GONE);
			llTelefone.setVisibility(View.GONE);
			
		}

		setHasOptionsMenu(true);
		
		actionBar = ((ActionBarActivity)Funcionario.this.getActivity()).getSupportActionBar();
		actionBar.setTitle(cargoSelecionado.getCargo());
		
		
		
		return view;
	}
	
	private class SetAlarmHandle extends Handler{
		
		@Override
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			case MENSAGEM_SET_ALARME:
				Toast.makeText(Funcionario.this.getActivity(), "Alarm set", Toast.LENGTH_LONG).show();
				NotificationUtils.showNotification(Funcionario.this.getActivity(), "Check out realizado!!", MainActivity.class);
				break;

			default:
				break;
			}
		}
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

