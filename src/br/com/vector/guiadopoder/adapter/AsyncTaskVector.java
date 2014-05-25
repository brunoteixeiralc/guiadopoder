package br.com.vector.guiadopoder.adapter;

import java.util.ArrayList;

import android.os.AsyncTask;
import br.com.vector.guiadopoder.SplashScreenActivity;
import br.com.vector.guiadopoder.model.Poder;
import br.com.vector.guiadopoder.rest.VectorREST;

public class AsyncTaskVector extends AsyncTask<Void, Void, ArrayList<Poder>> {
	
	private VectorREST vectorREST = new VectorREST();
	private SplashScreenActivity ctx;
	
	public AsyncTaskVector(SplashScreenActivity ctx) {
		this.ctx = ctx;
	}
	
	 @Override
     protected ArrayList<Poder> doInBackground(Void... params) {

		 ArrayList<Poder> listVector = null;

        try {
			listVector = (ArrayList<Poder>) vectorREST.getListaVector();
			System.out.println(listVector);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
         return listVector;
     }      

     @Override
     protected void onPostExecute(ArrayList<Poder> list) {
    	 ctx.retornoAsyncTaskVector(list);
    	 
     }

}
