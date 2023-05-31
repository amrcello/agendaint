package view;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import controller.AgendaController;
import model.Compromisso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText editTextData;
    private EditText editTextHora;
    private EditText editTextDescricao;
    private Button buttonData;
    private Button buttonHora;
    private Button buttonSalvar;
    private Button buttonHoje;
    private Button buttonOutraData;
    private TextView textViewCompromissos;
    private AgendaController agendaController;

    private Calendar calendar;
    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat timeFormatter;

    private List<Compromisso> compromissos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextData = findViewById(R.id.editTextData);
        editTextHora = findViewById(R.id.editTextHora);
        editTextDescricao = findViewById(R.id.editTextDescricao);
        buttonData = findViewById(R.id.buttonData);
        buttonHora = findViewById(R.id.buttonHora);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonHoje = findViewById(R.id.buttonHoje);
        buttonOutraData = findViewById(R.id.buttonOutraData);
        textViewCompromissos = findViewById(R.id.textViewCompromissos);
        agendaController = new AgendaController();

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        timeFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
        calendar = Calendar.getInstance();
        compromissos = new ArrayList<>();

        buttonData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        buttonHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = editTextData.getText().toString();
                String hora = editTextHora.getText().toString();
                String descricao = editTextDescricao.getText().toString();
                if (data.isEmpty() || hora.isEmpty() || descricao.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    Compromisso compromisso = new Compromisso();
                    compromisso.setData(data);
                    compromisso.setHora(hora);
                    compromisso.setDescricao(descricao);
                    agendaController.salvarCompromisso(compromisso);
                    Toast.makeText(MainActivity.this, "Compromisso salvo com sucesso", Toast.LENGTH_SHORT).show();
                    resetFields();

                    // Log dos dados do compromisso
                    Log.d("Compromisso", "Data: " + comprom
