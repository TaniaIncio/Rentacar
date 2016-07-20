package com.tincio.rentacar.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.tincio.rentacar.R;
import com.tincio.rentacar.domain.model.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class VehiculoSeleccionadoAdapter extends RecyclerView.Adapter<VehiculoSeleccionadoAdapter.ViewHolderVehiculo> {

    List<Vehiculo> lista;
    Context context =null;
    OnItemClickListener mItemClickListener;
    public VehiculoSeleccionadoAdapter(List<Vehiculo> lista, Context context){
        this.lista= lista;
        this.context = context;
    }

    @Override
    public ViewHolderVehiculo onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_vehichulo_seleccionado_row,parent,false);
        ViewHolderVehiculo viewHolder = new ViewHolderVehiculo(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderVehiculo holder, int position) {
        if(holder!=null){
            holder.chkSeleccion.setText(lista.get(position).getNombre());
          /*  holder.chkSeleccion.setChecked(lista.get(position).isSeleccionado());
            holder.chkSeleccion.setEnabled(lista.get(position).isActivado());*/
            holder.chkSeleccion.setChecked(lista.get(position).isSeleccionado());
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolderVehiculo extends RecyclerView.ViewHolder implements View.OnClickListener{

        CheckBox chkSeleccion;
        public ViewHolderVehiculo(View itemView) {
            super(itemView);
            chkSeleccion = (CheckBox)itemView.findViewById(R.id.chk_vehichulo_seleccionado);
            chkSeleccion.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mItemClickListener!=null){
                mItemClickListener.onItemClick(v,getPosition(), lista.get(getPosition()));
                Vehiculo obj = lista.get(getPosition());
                obj.setSeleccionado(chkSeleccion.isChecked());
                lista.set(getPosition(),obj);
            }
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position, Vehiculo mcategoria);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void setListaCategorias(ArrayList<Vehiculo> lista){
        try{
            this.lista = lista;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Vehiculo> getListaVehiculosSeleccionados(){
        ArrayList<Vehiculo> listaSel = new ArrayList<>();
        try{
            for (Vehiculo obj: this.lista){
                if(obj.isSeleccionado())
                    listaSel.add(obj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaSel;
    }

}