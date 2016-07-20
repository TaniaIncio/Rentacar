package com.tincio.rentacar.presentation.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tincio.rentacar.R;
import com.tincio.rentacar.domain.RestVehiculos;
import com.tincio.rentacar.domain.model.Vehiculo;
import com.tincio.rentacar.presentation.activity.MainActivity;
import com.tincio.rentacar.presentation.adapter.VehiculoSeleccionadoAdapter;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservacionesFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.txt_fecha_inicio)
    TextView txtFechaInicio;
    @Bind(R.id.txt_fecha_fin)
    TextView txtFechaFin;
    @Bind(R.id.txt_hora_inicio)
    TextView txtHoraInicio;
    @Bind(R.id.txt_hora_fin)
    TextView txtHoraFin;
    @Bind(R.id.txt_nacionalidad)
    TextView txtNacionalidad;
    @Bind(R.id.btn_enviar)
    Button btnEnviar;
    @Bind(R.id.txt_tipo_garantia)
    TextView txtGarantia;
    @Bind(R.id.txt_requiere_factura)
    TextView txtRequiereFactura;
    @Bind(R.id.txt_vehiculo_seleccionado)
    EditText txtVehiculoSeleccionado;
    @Bind(R.id.txt_observaciones)
    EditText txtObservaciones;
    @Bind(R.id.txt_nombres)
    EditText txtNombres;
    @Bind(R.id.txt_telefonos)
    EditText txtTelefonos;
    SharedPreferences preferences;
    public static String TAG ="ReservacionesFragment";
    String nombreVehiculo;
    //add firebase
    private FirebaseAnalytics mFirebaseAnalytics;

    public ReservacionesFragment() {

    }
    public ReservacionesFragment newInstance(String nombreVehiculo) {
        ReservacionesFragment fragment = new ReservacionesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("nombreVehiculo",nombreVehiculo);
        fragment.setArguments(bundle);
        this.nombreVehiculo = nombreVehiculo;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics =  FirebaseAnalytics.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservaciones, container, false);
        ButterKnife.bind(this, view);

        txtObservaciones.setScroller(new Scroller(getActivity() ));
        txtObservaciones.setVerticalScrollBarEnabled(true);
        txtObservaciones.setMovementMethod(new ScrollingMovementMethod());
        preferences= getActivity().getSharedPreferences("preferences", getActivity().MODE_PRIVATE);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        txtFechaInicio.setOnClickListener(this);
        txtFechaFin.setOnClickListener(this);
        txtHoraInicio.setOnClickListener(this);
        txtHoraFin.setOnClickListener(this);
        txtNacionalidad.setOnClickListener(this);
        btnEnviar.setOnClickListener(this);
        txtGarantia.setOnClickListener(this);
        txtRequiereFactura.setOnClickListener(this);
        txtVehiculoSeleccionado.setOnClickListener(this);
        txtVehiculoSeleccionado.setText(nombreVehiculo);
        Bundle bundle = getArguments();
        nombreVehiculo= (bundle!=null? bundle.getString("nombreVehiculo"):"");
        txtVehiculoSeleccionado.setText(nombreVehiculo);
    }

    @Override
    public void onClick(View v) {
       if(v== txtNacionalidad){
           showNacionalidadDialog();
       }else if(v== btnEnviar){
           if(validarInputs())
                sendEmail();
       }else if(v== txtGarantia){
           showTipoGarantiaDialog();
       }else if(v== txtRequiereFactura){
           showRequiereFacturaDialog();
       }else if(v== txtHoraInicio){
           showTimerPicker("HI");
       }else if(v== txtHoraFin){
           showTimerPicker("HF");
       }else if(v== txtFechaInicio){
           showDatePicker("FI");
       }else if(v== txtFechaFin){
           showDatePicker("FF");
       }else if(v== txtVehiculoSeleccionado){
           showVehiculoSeleccionadoDialog();
       }
    }


    private void sendEmail(){
        StringBuilder stringReserva = new StringBuilder();
        stringReserva.append("Nombres: ");
        stringReserva.append(txtNombres.getText()+"\n");
        stringReserva.append("Nacionalidad: ");
        stringReserva.append(txtNacionalidad.getText()+"\n");
        stringReserva.append("Tipo de garantía: ");
        stringReserva.append(txtGarantia.getText()+"\n");
        stringReserva.append("Requiere factura: ");
        stringReserva.append(txtRequiereFactura.getText()+"\n");
        stringReserva.append("Fecha inicio: ");
        stringReserva.append(txtFechaInicio.getText()+"\n");
        stringReserva.append("Hora inicio: ");
        stringReserva.append(txtHoraInicio.getText()+"\n");
        stringReserva.append("Fecha fin: ");
        stringReserva.append(txtFechaFin.getText()+"\n");
        stringReserva.append("Hora fin: ");
        stringReserva.append(txtHoraFin.getText()+"\n");
        stringReserva.append("Telef. fijos y/o celulares: ");
        stringReserva.append(txtTelefonos.getText()+"\n");
        stringReserva.append("Vehiculo(s) seleccionado(s): ");
        stringReserva.append(txtVehiculoSeleccionado.getText()+"\n\n");
        stringReserva.append("Observaciones: ");
        stringReserva.append(txtObservaciones.getText()+"\n");

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + getEmailSucursal()));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Datos de Pre-Reserva - APP");
        emailIntent.putExtra(Intent.EXTRA_TEXT, stringReserva.toString());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "No email clients installed.", Toast.LENGTH_SHORT).show();
        }

    }

    private void showNacionalidadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Nacionalidad");
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_nacionalidad, null);
        builder.setView(view);
        final RadioButton rbPeruano = (RadioButton)view.findViewById(R.id.rb_peruano);
        final RadioButton rbExtranjero = (RadioButton)view.findViewById(R.id.rb_extranjero);

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(rbPeruano.isChecked())
                            txtNacionalidad.setText(rbPeruano.getText().toString());
                        else
                            txtNacionalidad.setText(rbExtranjero.getText().toString());
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    //para tipo de garantia
    private void showTipoGarantiaDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("TIpo Garantía");
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_tipogarantia, null);
        builder.setView(view);
        final RadioButton rbVisa = (RadioButton)view.findViewById(R.id.rb_visa);
        final RadioButton rbMastercard = (RadioButton)view.findViewById(R.id.rb_mastercard);
        final RadioButton rbDiners = (RadioButton)view.findViewById(R.id.rb_diners);
        final RadioButton rbAmericanExpres = (RadioButton)view.findViewById(R.id.rb_americanexpress);
        final RadioButton rbCheque = (RadioButton)view.findViewById(R.id.rb_cheque);

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(rbVisa.isChecked())
                            txtGarantia.setText(rbVisa.getText().toString());
                        else {
                            if (rbMastercard.isChecked())
                                txtGarantia.setText(rbVisa.getText().toString());
                            else{
                                if (rbDiners.isChecked())
                                    txtGarantia.setText(rbDiners.getText().toString());
                                else{
                                    if (rbAmericanExpres.isChecked())
                                        txtGarantia.setText(rbAmericanExpres.getText().toString());
                                    else{
                                        txtGarantia.setText(rbCheque.getText().toString());
                                    }
                                }
                            }
                        }
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    private void showRequiereFacturaDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Requiere Factura");
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_requiere_factura, null);
        builder.setView(view);
        final RadioButton rbSi = (RadioButton)view.findViewById(R.id.rb_si);
        final RadioButton rbNo = (RadioButton)view.findViewById(R.id.rb_no);

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(rbSi.isChecked())
                            txtRequiereFactura.setText(rbSi.getText().toString());
                        else
                            txtRequiereFactura.setText(rbNo.getText().toString());
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    private void showDatePicker(final String tipo){
        try{
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            if(tipo.equals("FI"))
                                txtFechaInicio.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            else
                                txtFechaFin.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void showTimerPicker(final String tipo){
        try{
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            if(tipo.equals("HI"))
                                txtHoraInicio.setText(hourOfDay + ":" + minute);
                            else
                                txtHoraFin.setText(hourOfDay + ":" + minute);

                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //mostrar dialogo de vehiculos a seleccionar
    private void showVehiculoSeleccionadoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Vehiculo");
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_vehichulo_seleccionado_list, null);
        builder.setView(view);
        RecyclerView rcvLista = (RecyclerView) view.findViewById(R.id.rcv_vehiculos_seleccionados);
        RestVehiculos rest = new RestVehiculos();
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        rcvLista.setLayoutManager(lm);
        final VehiculoSeleccionadoAdapter adapter = new VehiculoSeleccionadoAdapter(rest.getVehiculos(getActivity()), getActivity());
        rcvLista.setAdapter(adapter);
        adapter.setOnItemClickListener(new VehiculoSeleccionadoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Vehiculo mcategoria) {

            }
        });
        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<Vehiculo> lista=adapter.getListaVehiculosSeleccionados();
                        StringBuilder string = new StringBuilder();
                        for (Vehiculo obj :lista){
                            string.append(obj.getNombre());
                            string.append("\n");
                        }
                        txtVehiculoSeleccionado.setText(string);
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public String getEmailSucursal(){
        try{
            if(preferences.getString("sucursal","").equals("Piura")){
               return getString(R.string.email_piura);
            }else{
                if(preferences.getString("sucursal","").equals("Tumbes")){
                    return getString(R.string.email_tumbes);
                }else
                    return getString(R.string.email_talara);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public boolean validarInputs(){
        try{
            if(this.txtNombres.getText().toString().trim().equals("")){
                Toast.makeText(getActivity(),"Debe ingresar sus nombres", Toast.LENGTH_SHORT).show();
                return false;
            }else{
                if(this.txtGarantia.getText().toString().trim().equals("")){
                    Toast.makeText(getActivity(),"Debe elegir el tipo de garantia", Toast.LENGTH_SHORT).show();
                    return false;
                }else{
                    if(this.txtRequiereFactura.getText().toString().trim().equals("")){
                        Toast.makeText(getActivity(),"Ingrese si requiere factura", Toast.LENGTH_SHORT).show();
                        return false;
                    }else{
                        if(this.txtFechaInicio.getText().toString().trim().equals("")){
                            Toast.makeText(getActivity(),"Ingrese fecha de inicio", Toast.LENGTH_SHORT).show();
                            return false;
                        }else{
                            if(this.txtHoraInicio.getText().toString().trim().equals("")){
                                Toast.makeText(getActivity(),"Ingrese hora de inicio", Toast.LENGTH_SHORT).show();
                                return false;
                            }else{
                                if(this.txtFechaFin.getText().toString().trim().equals("")){
                                    Toast.makeText(getActivity(),"Ingrese fecha fin", Toast.LENGTH_SHORT).show();
                                    return false;
                                }else{
                                    if(this.txtHoraFin.getText().toString().trim().equals("")){
                                        Toast.makeText(getActivity(),"Ingrese hora fin", Toast.LENGTH_SHORT).show();
                                        return false;
                                    }else{
                                        if(this.txtTelefonos.getText().toString().trim().equals("")){
                                            Toast.makeText(getActivity(),"Ingrese su numero de telefono", Toast.LENGTH_SHORT).show();
                                            return false;
                                        }else{
                                            if(this.txtVehiculoSeleccionado.getText().toString().trim().equals("")){
                                                Toast.makeText(getActivity(),"Debe seleccionar un vehiculo", Toast.LENGTH_SHORT).show();
                                                return false;
                                            }else{

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
