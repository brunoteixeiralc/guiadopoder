package br.com.vector.guiadopoder;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import br.com.vector.guiadopoder.adapter.DrawerListAdapter;
import br.com.vector.guiadopoder.fragment.BuscarPorNome;
import br.com.vector.guiadopoder.fragment.MainPage;
import br.com.vector.guiadopoder.fragment.Setor;
import br.com.vector.guiadopoder.model.DrawerItem;
import br.com.vector.guiadopoder.model.Funcionario;
import br.com.vector.guiadopoder.model.Poder;

import com.example.guiadopoder.R;

public class MainActivity extends ActionBarActivity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerListAdapter menuListAdapter;
	private Fragment fragment;
	List<DrawerItem> dataList;
	private ActionBar actionBar;
	private MenuItem item;
	private ArrayList<Poder> poderes;
	private Poder executivo,legislativo,judiciario,estadual;
	private ArrayList<Funcionario> funcionarios;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		poderes = new ArrayList<Poder>();
		poderes = (ArrayList<Poder>) this.getIntent().getSerializableExtra("listVector");
		
		funcionarios = new ArrayList<Funcionario>();
		funcionarios = (ArrayList<Funcionario>) this.getIntent().getSerializableExtra("listVectorFuncionarios");
		
		validarPoder(poderes);
		
		 dataList = new ArrayList<DrawerItem>();
		 mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		 mDrawerList = (ListView)findViewById(R.id.left_drawer);
		 mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		
		 dataList.add(new DrawerItem("Poderes")); 
		 dataList.add(new DrawerItem("Executivo", R.drawable.ic_refresh,executivo.getCor()));
		 dataList.add(new DrawerItem("Legislativo", R.drawable.ic_refresh,legislativo.getCor()));
		 dataList.add(new DrawerItem("Judiciário", R.drawable.ic_refresh,judiciario.getCor()));
		 dataList.add(new DrawerItem("Estadual", R.drawable.ic_refresh,estadual.getCor()));
		 dataList.add(new DrawerItem(R.drawable.search, "Filtrar por nome"));
		 dataList.add(new DrawerItem(R.drawable.logo_vector_about));
		 

		 menuListAdapter = new DrawerListAdapter(this, R.layout.drawer_list_item,
					dataList);
		 
		 mDrawerList.setAdapter(menuListAdapter);
		 mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		 mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_navigation_drawer, R.string.drawer_open, R.string.drawer_close){
		 	public void onDrawerClosed(View view) {
		         // TODO Auto-generated method stub
		         super.onDrawerClosed(view);
		     }
		
		     public void onDrawerOpened(View drawerView) {
		         // TODO Auto-generated method stub
		         super.onDrawerOpened(drawerView);
		     }
		 };
		 
		 mDrawerLayout.setDrawerListener(mDrawerToggle);
		 mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		 
		 actionBar = getSupportActionBar();
		 actionBar.setHomeButtonEnabled(true);
	     actionBar.setDisplayHomeAsUpEnabled(true);
	     actionBar.setHomeButtonEnabled(true);
	        
	     actionBar.setTitle("Guia Prático do Poder");
	        
	     selectItem(0);
	     
	     mDrawerLayout.openDrawer(mDrawerList);

	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
    	
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
	}
	
	private void selectItem(int position) {
		
		fragment = null;
        Bundle bundle = new Bundle();
    	switch (position) {
    	
        case 0:
        	fragment = new MainPage();
            break;
        case 1:
        	bundle.putSerializable("poder", executivo);
        	fragment = new Setor();
        	fragment.setArguments(bundle);
            break;
        case 2:
        	bundle.putSerializable("poder", legislativo);
        	fragment = new Setor();
        	fragment.setArguments(bundle);
            break;
        case 3:
        	bundle.putSerializable("poder", judiciario);
        	fragment = new Setor();
        	fragment.setArguments(bundle);
            break;
        case 4:
        	bundle.putSerializable("poder", estadual);
        	fragment = new Setor();
        	fragment.setArguments(bundle);
            break;
        case 5:
        	bundle.putSerializable("funcionarios", funcionarios);
        	fragment = new BuscarPorNome();
        	fragment.setArguments(bundle);
            break;
        case 6:
        	Uri uri = Uri.parse("http://vectorrelgov.com.br/");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
            break;
        default:
            break;
        }
 
        if (fragment != null) {
        	
		    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		    ft.replace(R.id.content_frame, fragment);
		    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		    ft.addToBackStack(null);
		    ft.commit(); 
		
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			mDrawerLayout.closeDrawer(mDrawerList);
        }
		
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
	          return true;
	        }

	        return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    
	    item = menu.findItem(R.id.action_search);
	    item.setVisible(false);
	    
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
			mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	private void validarPoder(List<Poder> poderes){
		
		for (Poder poder : poderes) {
			if(poder.getNome().trim().equalsIgnoreCase("Executivo")){
				executivo = poder;
			}else if(poder.getNome().trim().equals("Legislativo")){
				legislativo = poder;
			}else if(poder.getNome().trim().equalsIgnoreCase("Estadual")){
				estadual = poder;
			}else{
				judiciario = poder;
			}
		}
		
	}

}
