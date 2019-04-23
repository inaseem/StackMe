package ali.naseem.stackme.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ali.naseem.stackme.R;
import ali.naseem.stackme.datamodels.tags.Item;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.ViewHolder> {

    private List<Item> tags;
    private Context context;
    private int count=0;

    public TagsAdapter(List<Item> tags, Context context) {
        this.tags = tags;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.interest, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Item tag = tags.get(position);
        tag.setName(tag.getName().trim());
        holder.name.setText(tag.getName().trim());
        if (tag.isSelected()) {
            holder.cardView.setCardBackgroundColor(getRandomMaterialColor());
            holder.name.setTextColor(Color.WHITE);
            count++;
        } else {
            holder.cardView.setCardBackgroundColor(Color.TRANSPARENT);
            holder.name.setTextColor(Color.BLACK);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>=4){
                    if(tag.isSelected()){
                        holder.cardView.setCardBackgroundColor(Color.TRANSPARENT);
                        holder.name.setTextColor(Color.BLACK);
                        tag.setSelected(false);
                        count--;
                    }
                }else{
                    if (!tag.isSelected()) {
                        holder.cardView.setCardBackgroundColor(getRandomMaterialColor());
                        holder.name.setTextColor(Color.WHITE);
                        tag.setSelected(true);
                        count++;
                    } else {
                        holder.cardView.setCardBackgroundColor(Color.TRANSPARENT);
                        holder.name.setTextColor(Color.BLACK);
                        tag.setSelected(false);
                        count--;
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }


    private int getRandomMaterialColor() {
        int returnColor = Color.GRAY;
        int arrayId = context.getResources().getIdentifier("mdcolor_500", "array", context.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = context.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.interestName);
            cardView = itemView.findViewById(R.id.card);
        }
    }
}
