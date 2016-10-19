package cortez.autoescolhedor;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LinearLayout myLinearLayout;
    LinkedList<EditText> viewList = new LinkedList<EditText>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myLinearLayout = (LinearLayout) findViewById(R.id.linear_layout_scroll);

       // EditText editText = new EditText(this);
       // editText.setText("OLAR");
      // myLinearLayout.addView(editText);

        //add novo texto
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab3);
        fab2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String selection;
                try {
                    selection = selectRandom();
                    Snackbar.make(view,selection + "!" + " oba!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addView(){
        EditText editText = new EditText(this);
        editText.setText("");
        viewList.add(editText);
        myLinearLayout.addView(editText);
    }
    private String selectRandom(){
        //nextint nao pega o ultimo numero. -10 é pra transportar o menor valor de 0 para 0-10.
        //somar 10 transporta o valor minimo de 0 p/ 0+10
        Random r = new Random();
        int size = viewList.size();
        int numb = 0;
        try {
            numb = r.nextInt(size);
        }catch (Exception e){
            e.printStackTrace();
            Toast t = Toast.makeText(this,"nenhuma opção", Toast.LENGTH_LONG);
            t.show();
            return null;
        }
         Toast t = Toast.makeText(this,"as " + numb, Toast.LENGTH_LONG);
         t.show();
        return viewList.get(numb).getText().toString();
    }
}
