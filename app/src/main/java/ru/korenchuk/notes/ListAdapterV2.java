package ru.korenchuk.notes;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapterV2 extends RecyclerView.Adapter<ListAdapterV2.ViewHolder> {


    CardsSource dataSource;
    private OnItemClickListener itemClickListener;
    private Fragment fragment;
    private int menuPosition;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public int getMenuPosition() {
        return menuPosition;
    }

    public ListAdapterV2(CardsSource dataSource, Fragment fragment) {
        this.dataSource = dataSource;
        this.fragment = fragment;
    }

    public void setNewData(List<CardData> dataSource){
        this.dataSource.setNewData(dataSource);
        notifyDataSetChanged();
    }

    private void registerContextMenu(View itemView){
        if (fragment != null){
            fragment.registerForContextMenu(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterV2.ViewHolder holder, int position) {
        holder.setData(dataSource.getCardData(position));
    }

    @NonNull
    @Override
    public ListAdapterV2.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);

        return new ListAdapterV2.ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);

            registerContextMenu(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (itemClickListener != null)
                        itemClickListener.onItemClick(view, position);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    menuPosition = getLayoutPosition();
                    itemView.showContextMenu(100, 100);
                    return true;
                }
            });
        }

        public void setData(CardData cardData){
            title.setText(cardData.getTitle());
            description.setText(cardData.getDescription());
        }


    }


}
