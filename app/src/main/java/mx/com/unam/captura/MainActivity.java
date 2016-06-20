package mx.com.unam.captura;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.View;
import android.widget.Toast;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar;
    private EditText nombreView,dateView,telefonoView,emailView,descView;
    private Button siguiente;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle parametros = getIntent().getExtras();

        nombreView = (EditText) findViewById(R.id.nombre);
        dateView = (EditText) findViewById(R.id.fecha);
        emailView = (EditText) findViewById(R.id.email);
        telefonoView = (EditText) findViewById(R.id.telefono);
        descView = (EditText) findViewById(R.id.descripcion);
        siguiente = (Button) findViewById(R.id.siguiente);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        if(parametros != null)
        {
            nombreView.setText( parametros.getString("nombre"));
            year = parametros.getInt("year");
            month = parametros.getInt("month");
            day = parametros.getInt("day");
            dateView.setText(new StringBuilder().append(day).append("/")
                    .append(month).append("/").append(year).toString());
            emailView.setText(parametros.getString("email"));
            telefonoView.setText(parametros.getString("telefono"));
            descView.setText(parametros.getString("desc"));

        }


        showDate(year, month, day);

        dateView.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DialogFragment datePickerFragment = new DatePickerFragment() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {
                            showDate(year,month,day);
                            emailView.requestFocus(); //moves the focus to something else after dialog is closed
                        }
                    };
                    datePickerFragment.show(MainActivity.this.getSupportFragmentManager(), "datePicker");
                }
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                siguiente();
            }
        });

    }

    private void showDate(int year, int month, int day) {
        this.year=year;
        this.month=month;
        this.day = day;
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month+1).append("/").append(year));
    }

    public void siguiente(){
        Intent i = new Intent(MainActivity.this,Detail.class);
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
