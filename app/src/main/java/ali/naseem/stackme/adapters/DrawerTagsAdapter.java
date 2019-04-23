package ali.naseem.stackme.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ali.naseem.stackme.R;
import ali.naseem.stackme.Utils;
import ali.naseem.stackme.models.Interest;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DrawerTagsAdapter extends RecyclerView.Adapter<DrawerTagsAdapter.ViewHolder> {

    private ArrayList<Interest> interests;
    private Context context;
    private int selected = -1;

    public DrawerTagsAdapter(ArrayList<Interest> interests, Context context) {
        this.interests = interests;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.interest, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Interest tag = interests.get(position);
        holder.name.setText(tag.getName().trim());
        if (tag.isSelected()) {
            holder.cardView.setCardBackgroundColor(getRandomMaterialColor());
            holder.name.setTextColor(Color.WHITE);
            selected = position;
        } else {
            holder.cardView.setCardBackgroundColor(Color.TRANSPARENT);
            holder.name.setTextColor(Color.BLACK);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int now = -1;
                if (!tag.isSelected()) {
                    holder.cardView.setCardBackgroundColor(getRandomMaterialColor());
                    holder.name.setTextColor(Color.WHITE);
                    tag.setSelected(true);
                    now = position;
                }
                Interest previous = interests.get(selected);
                previous.setSelected(false);
                Utils.getInstance().getDatabase().interestDao().update(previous);
                Utils.getInstance().getDatabase().interestDao().update(tag);
                notifyDataSetChanged();
            }
        });
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

    @Override
    public int getItemCount() {
        return interests.size();
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
