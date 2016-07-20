package com.tincio.rentacar.presentation.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tincio.rentacar.R;
import com.tincio.rentacar.data.model.OpcionMenu;
import com.tincio.rentacar.presentation.util.Images;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by juan on 25/05/2016.
 */
public class OpcionMenuAdapter extends RecyclerView.Adapter<OpcionMenuAdapter.OpcionMenuViewHolder>{

    Context context;
    List<OpcionMenu> list;
    public OpcionMenuAdapter(List<OpcionMenu> list){
        this.list= list;
    }

    @Override
    public OpcionMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()  ).inflate(R.layout.row_opc_menu,parent, false  );
        OpcionMenuViewHolder viewHolder = new OpcionMenuViewHolder(view);
        return viewHolder;
    }

    OpcionMenu opc;
    @Override
    public void onBindViewHolder(OpcionMenuViewHolder holder, int position) {
        opc = list.get(position);
        holder.txtOpcNav.setText(opc.getNombre());
        holder.imgOpc.setImageDrawable(Images.getDrawableByName(context, opc.getImagen()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OpcionMenuViewHolder extends RecyclerView.ViewHolder{

        @Nullable
        @Bind(R.id.linear_opc_navigation)
        LinearLayout linearOpcNav;
        @Nullable @Bind(R.id.img_opc_nav)
        ImageView imgOpc;
        @Nullable @Bind(R.id.txt_opc_nav)
        TextView txtOpcNav;
        public OpcionMenuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            linearOpcNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickLIstener != null) {
                        onItemClickLIstener.setOnItemClickListener(list.get(getPosition()));
                    }
                }
            });
        }
    }

    public OnItemClickListener onItemClickLIstener;
    public interface OnItemClickListener{
        public void setOnItemClickListener(OpcionMenu opcionMenu);
    }

    public void setOnItemClickLIstener(OnItemClickListener onItemClickListener){
        this.onItemClickLIstener = onItemClickListener;
    }
}
