package edu.neu.fitness_38;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.neu.fitness_38.AllFood;
import edu.neu.fitness_38.R;
import edu.neu.fitness_38.FoodBean;

import java.util.ArrayList;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{

    private static final String json = AllFood.foodList;

    private List<FoodBean> foodBeans;

    private onItemClickListener listener;

    public FoodAdapter(List<FoodBean> foodBeans,onItemClickListener listener) {
        this.listener = listener;
        this.foodBeans = foodBeans;
    }
    public void refreshData(List<FoodBean> foodBeans) {
        this.foodBeans = foodBeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fooditem, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        FoodBean foodBean = foodBeans.get(position);
        holder.unit.setText("Unit:" + foodBean.getUnit());
        holder.name.setText("Name:" + foodBean.getFoodName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.click(foodBean);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodBeans == null ? 0 : foodBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, unit;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.foodName);
            unit = itemView.findViewById(R.id.unit);

        }
    }

    public interface onItemClickListener {
        void click(FoodBean foodBean);
    }
}
