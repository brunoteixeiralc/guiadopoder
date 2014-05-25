package br.com.vector.guiadopoder;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import br.com.vector.guiadopoder.adapter.AsyncTaskVector;
import br.com.vector.guiadopoder.model.Cargo;
import br.com.vector.guiadopoder.model.Funcionario;
import br.com.vector.guiadopoder.model.Orgao;
import br.com.vector.guiadopoder.model.Poder;
import br.com.vector.guiadopoder.model.Setor;

import com.example.guiadopoder.R;

public class SplashScreenActivity extends Activity {
	
	private ArrayList<Funcionario> funcionarios;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
       
		try {
        	
        	new AsyncTaskVector(SplashScreenActivity.this).execute();

        } catch (Exception e) {
            e.printStackTrace();
            gerarToast(e.getMessage());
        }
	}
	
	public void retornoAsyncTaskVector(ArrayList<Poder> list){

		Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
		intent.putExtra("listVector", list);
		getAllFuncionarios(list);
		intent.putExtra("listVectorFuncionarios", funcionarios);
		SplashScreenActivity.this.startActivity(intent);	
	
	}
	
	private void gerarToast(CharSequence message) {
	     int duration = Toast.LENGTH_LONG;
	     Toast toast = Toast
	             .makeText(getApplicationContext(), message, duration);
	     toast.show();
	    }
	
	private void getAllFuncionarios(List<Poder> poderes){
		
		funcionarios = new ArrayList<Funcionario>();
		Poder poderAtual = new Poder();
		for (Poder poder : poderes) {
			poderAtual = poder;
			for (Setor setorAtual : poder.getSetores()) {
				for (Orgao orgaoAtual : setorAtual.getOrgaos()) {
					for (Cargo cargoAtual : orgaoAtual.getCargos()) {
						for (Funcionario funcionarioAtual : cargoAtual.getFuncionarios()) {
							funcionarioAtual.setPoder(poderAtual);
							funcionarios.add(funcionarioAtual);
						}
					}
				}
			}
		}
		
		
	}
	
}
