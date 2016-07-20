package com.tincio.rentacar.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tincio.rentacar.R;
import com.tincio.rentacar.domain.model.Vehiculo;
import com.tincio.rentacar.presentation.util.Images;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by juan on 25/05/2016.
 */
public class VehiculosAdapter extends RecyclerView.Adapter<VehiculosAdapter.VehiculoViewHolder>{

    Context context;
    List<Vehiculo> list;
    public  VehiculosAdapter(List<Vehiculo> list){
        this.list= list;
    }

    @Override
    public VehiculoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()  ).inflate(R.layout.row_cardview_vehiculo,parent, false  );
        VehiculoViewHolder viewHolder = new VehiculoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VehiculoViewHolder holder, final int position) {
        Vehiculo opc = list.get(position);
        holder.txt_nombre_auto.setText(opc.getNombre());
        holder.txt_precio_auto.setText(opc.getTipoPrecio());
        holder.txt_tipo_auto.setText(opc.getTipoManejo());
     /*   if(holder.txt_precio_auto.getText().equals("UNIDAD EN PIURA")){
            holder.txt_precio_auto.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
        }*/
        holder.num_personas_auto.setText(opc.getNumPersonas());
        holder.num_puertas_auto.setText(opc.getNumPuertas());
        holder.aire_acondicionado_auto.setText(opc.getAireAcond());
        holder.txt_radio_auto.setText(opc.getTipoRadio());
        holder.num_maletas_auto.setText(opc.getNumMaletas());
        holder.txt_aro_auto.setText(opc.getNumAro());
        holder.txt_datos_extras.setText(opc.getDataExtra());
        holder.img_vehiculo.setImageDrawable(Images.getDrawableByName(context, opc.getFoto()));
        holder.btn_reservar_auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemButtonClick != null) {
                    onItemButtonClick.setOnItemButtonClick(list.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VehiculoViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.txt_nombre_auto)
        TextView txt_nombre_auto;
        @Bind(R.id.txt_precio_auto)
        TextView txt_precio_auto;
        @Bind(R.id.txt_tipo_auto)
        TextView txt_tipo_auto;
        @Bind(R.id.img_vehiculo)
        ImageView img_vehiculo;
        @Bind(R.id.btn_reservar_auto)
        Button btn_reservar_auto;
        @Bind(R.id.num_personas_auto)
        TextView num_personas_auto;

        @Bind(R.id.num_puertas_auto)
        TextView num_puertas_auto;
        @Bind(R.id.aire_acondicionado_auto)
        TextView aire_acondicionado_auto;
        @Bind(R.id.txt_radio_auto)
        TextView txt_radio_auto;
        @Bind(R.id.num_maletas_auto)
        TextView num_maletas_auto;
        @Bind(R.id.txt_aro_auto)
        TextView txt_aro_auto;
        @Bind(R.id.txt_datos_extras)
        TextView txt_datos_extras;
        public VehiculoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    OnItemButtonClick onItemButtonClick;
    public interface OnItemButtonClick{
        void setOnItemButtonClick(Vehiculo itemVehiculo);
    }

    public void setOnItemButtonClick(OnItemButtonClick onItemButtonClick){
        this.onItemButtonClick = onItemButtonClick;
    }
}


















