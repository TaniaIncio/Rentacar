package com.tincio.rentacar.presentation.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.tincio.rentacar.R;
import com.tincio.rentacar.presentation.adapter.ListExpandablePreguntasAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreguntasFrecuentesFragment extends Fragment {

    @Bind(R.id.list_exp_preguntas)
    ExpandableListView listPreguntas;
    public static String TAG ="PreguntasFrecuentesFragment";
    private FirebaseAnalytics mFirebaseAnalytics;
    public PreguntasFrecuentesFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_preguntas_frecuentes, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //listGroup.add("A CARGO DEL CLIENTE");

        HashMap<String, List<String>> hasmMapGroup = new HashMap<>();

        List<String> listItem1 = new ArrayList<>();
        listItem1.add("Los precios de Piura Rent a Car  están expresados en nuevos soles.");
        listItem1.add("Los pagos pueden realizarse en efectivo o con tarjetas de crédito: Visa, Diners, American Express y Mastercard.");
        listItem1.add("En efectivo, usted podrá abonar con nuevos soles  o dólares. ");
        listItem1.add("Los pagos con cheque son sólo para empresas.");
        hasmMapGroup.put("Formas de Pago", listItem1);
        ////
        List<String> listItem2 = new ArrayList<>();
        listItem2.add("Mantenimiento  preventivo y correctivo del vehículo.");
        listItem2.add("Entrega del vehículo limpio y con tanque de combustible lleno.");
        listItem2.add("Sustitución del vehículo por averío o accidente:\n" +
                "A) El reemplazo se hará de forma inmediata en Piura, en horario comercial.\n" +
                "B) Fuera de Piura, dentro de las 24 hs.\n" +
                "C) La sustitución de los mismos, estará sujeta a la disponibilidad");
        listItem2.add("Asistencia las 24 hs.");
        listItem2.add("Ante una emergencia o asesoramiento, comuníquese las 24 hs. al \n" +
                "969867777");
        hasmMapGroup.put("A cargo de Piura Renta a Car", listItem2);
//
        List<String> listItem3 = new ArrayList<>();
        listItem3.add("Combustible");
        listItem3.add("Multas e infracciones de tránsito");
        listItem3.add("Excedentes");
        listItem3.add("Peajes");
        listItem3.add("Daños ocasionados al vehículo");
        hasmMapGroup.put("A cargo del cliente", listItem3);
        //
        List<String> listItem4 = new ArrayList<>();
        listItem4.add("Todos los vehículos de Piura Rent a Car, son alquilados con 250 km. libres por dìa acumulables sólo durante el periodo de alquiler, el valor del kilómetro excedente, será aplicado de acuerdo a la categoría de vehículo tomado. ");
        hasmMapGroup.put("Kilometraje Disponible", listItem4);
        ////
        List<String> listItem5 = new ArrayList<>();
        listItem5.add("APLICABLES para entregas y/o devoluciones dentro de la ciudad de Piura; aeropuerto, domicilio, hotel o terminal terrestre.");
        hasmMapGroup.put("Entrega y devoluciones sin cargo extra", listItem5);
//
        List<String> listItem6 = new ArrayList<>();
        listItem6.add("APLICABLES para entregas y/o devoluciones fuera de la ciudad de Piura, el costo varia dependiendo del número de días de alquiler y lugar de  entrega y/o devolución del vehículo. Las ciudades de entrega/devolución van desde Tumbes hasta Trujillo");
        hasmMapGroup.put("Entrega y devoluciones con cargo extra", listItem6);

        ////
       /* List<String> listItem7 = new ArrayList<>();
        listItem7.add("APLICABLES para entregas y/o devoluciones dentro de la ciudad de Piura; aeropuerto, domicilio, hotel o terminal terrestre.");
        hasmMapGroup.put("Extension del periodo de alquiler", listItem7);
//
        List<String> listItem8 = new ArrayList<>();
        listItem8.add("APLICABLES para entregas y/o devoluciones fuera de la ciudad de Piura, el costo varia dependiendo del número de días de alquiler y lugar de  entrega y/o devolución del vehículo. Las ciudades de entrega/devolución van desde Tumbes hasta Trujillo");
        hasmMapGroup.put("Términos y Condiciones del contrato", listItem8);

        List<String> listItem9 = new ArrayList<>();
        listItem9.add("APLICABLES para entregas y/o devoluciones fuera de la ciudad de Piura, el costo varia dependiendo del número de días de alquiler y lugar de  entrega y/o devolución del vehículo. Las ciudades de entrega/devolución van desde Tumbes hasta Trujillo");
        hasmMapGroup.put("Servicios opcionales", listItem9);

        ////
        List<String> listItem10 = new ArrayList<>();
        listItem10.add("APLICABLES para entregas y/o devoluciones dentro de la ciudad de Piura; aeropuerto, domicilio, hotel o terminal terrestre.");
        hasmMapGroup.put("Recomendaciones", listItem10);
//
        List<String> listItem11 = new ArrayList<>();
        listItem11.add("APLICABLES para entregas y/o devoluciones fuera de la ciudad de Piura, el costo varia dependiendo del número de días de alquiler y lugar de  entrega y/o devolución del vehículo. Las ciudades de entrega/devolución van desde Tumbes hasta Trujillo");
        hasmMapGroup.put("Franquicia y seguros", listItem11);*/

        List<String> listTitle = new ArrayList<>();//hasmMapGroup.keySet()
        listTitle.add("Formas de Pago");
        listTitle.add("A cargo de Piura Renta a Car");
        listTitle.add("A cargo del cliente");
        listTitle.add("Kilometraje Disponible");
        listTitle.add("Entrega y devoluciones sin cargo extra");
        listTitle.add("Entrega y devoluciones con cargo extra");

        ListExpandablePreguntasAdapter adapter = new ListExpandablePreguntasAdapter(hasmMapGroup,listTitle);
        listPreguntas.setAdapter(adapter);

    }
}
