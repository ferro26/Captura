package mx.com.unam.captura;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class Detail extends AppCompatActivity {

    String nombre, fecha,email,telefono,desc;
    int year,month,day;
    private Calendar calendar;
    TextView nombreView,dateView,telefonoView,emailView,descView;
    Button editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle parametros = getIntent().getExtras();


        nombre= parametros.getString("nombre");
        year = parametros.getInt("year");
        month = parametros.getInt("month");
        day = parametros.getInt("day");
        fecha = new StringBuilder().append(day).append("/")
                .append(month+1).append("/").append(year).toString();
        email = parametros.getString("email");
        telefono = parametros.getString("telefono");
        desc = parametros.getString("desc");

        editar = (Button) findViewById(R.id.editar);
        nombreView = (TextView) findViewById(R.id.textView2);
        dateView = (TextView) findViewById(R.id.textView4);
        telefonoView = (TextView) findViewById(R.id.textView6);
        emailView = (TextView) findViewById(R.id.textView8);
        descView = (TextView) findViewById(R.id.textView10);

        nombreView.setText(nombre);
        dateView.setText(fecha);
        telefonoView.setText(telefono);
        emailView.setText(email);
        descView.setText(desc);

        editar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                editar();
            }
        });

    }

    public void editar(){
        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("load",true);
        i.putExtra("nombre",nombreView.getText().toString());
        i.putExtra("year",year);
        i.putExtra("month",month);
        i.putExtra("day",day);
        i.putExtra("telefono",telefonoView.getText().toString());
        i.putExtra("email",emailView.getText().toString());
        i.putExtra("desc",descView.getText().toString());
        startActivity(i);
        finish();
    }
}
