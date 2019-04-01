
package com.example.cadastro;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cadastro.dominio.entidades.Cliente;

import java.util.List;


public class NewRecyclerView extends RecyclerView.Adapter<NewRecyclerView.ViewHolder> {

    private List<Cliente> myItems;

    public NewRecyclerView(List<Cliente> items) {
        this.myItems = items;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.linha_cliente, parent, false),parent.getContext()); // TODO
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cliente cliente = myItems.get(position);
        holder.textNome.setText(cliente.Nome);
        holder.textTelefone.setText(cliente.Telefone);
    }

    public interface ItemListener {
        void onItemClick(Cliente item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // TODO - Your view members
        public TextView textNome;
        public TextView textTelefone;

        public ViewHolder(View itemView,final Context context) {
            super(itemView);
            textNome = itemView.findViewById(R.id.textNome);
            textTelefone = itemView.findViewById(R.id.textTelefone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }


}
                                