package br.com.vector.guiadopoder.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guiadopoder.R;

public class CustomDialog extends Dialog {

	private String text;
	private TextView textView;
	private ImageView btnOk;
	private View line;
	private String poder;
	
	public CustomDialog(Context context,String text,String poder) {
		super(context);
		this.text = text;
		this.poder = poder;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.custom_dialog);
		line = this.findViewById(R.id.line);
		line.setBackgroundColor(lineColor(poder));
		textView = (TextView) this.findViewById(R.id.txtDialog);	
		textView.setText(text);
		btnOk = (ImageView) this.findViewById(R.id.btnOK);
		btnOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CustomDialog.this.dismiss();
				
			}
		});

	}
	
	private int lineColor(String poder){
		
		if(poder.equalsIgnoreCase("Poder Executivo")){
			return CustomDialog.this.getContext().getResources().getColor(R.color.yellow);
		}else if(poder.equalsIgnoreCase("Poder Legislativo")){
			return CustomDialog.this.getContext().getResources().getColor(R.color.green);
		}else if(poder.equalsIgnoreCase("Poder Judici‡rio")){
			return CustomDialog.this.getContext().getResources().getColor(R.color.red);
		}else{
			return CustomDialog.this.getContext().getResources().getColor(R.color.blue_light);
		}
		
	}
}
