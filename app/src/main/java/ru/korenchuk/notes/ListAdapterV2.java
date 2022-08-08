package ru.korenchuk.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapterV2 extends RecyclerView.Adapter<ListAdapterV2.ViewHolder> {


    CardsSource dataSource;
    private OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ListAdapterV2(CardsSource dataSource) {
        this.dataSource = dataSource;
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
        private AppCompatImageView image;
        private CheckBox like;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (itemClickListener != null)
                        itemClickListener.onItemClick(view, position);
                }
            });
        }

        public void setData(CardData cardData){
            title.setText(cardData.getTitle());
            description.setText(cardData.getDescription());
        }


    }


}
