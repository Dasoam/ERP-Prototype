package com.dadash.erpprototype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<MenuItem> menuItems;

    public MenuAdapter(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);
        holder.mealHeading.setText(menuItem.getMealType());
        holder.mealTiming.setText(menuItem.getTiming());
        holder.mealCode.setText(menuItem.getQrCode());
        holder.menuItems.setText(String.join(", ", menuItem.getItems()));

        // Handle QR icon and code click to display the QR code
        View.OnClickListener showQRCodeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRCodeDialogFragment dialog = QRCodeDialogFragment.newInstance(menuItem.getQrCode());
                dialog.show(((AppCompatActivity) v.getContext()).getSupportFragmentManager(), "QRCodeDialog");
            }
        };

        holder.qrIcon.setOnClickListener(showQRCodeListener);
        holder.mealCode.setOnClickListener(showQRCodeListener);
    }



    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView mealHeading, mealTiming, mealCode, menuItems;
        ImageView qrIcon;

        MenuViewHolder(View itemView) {
            super(itemView);
            mealHeading = itemView.findViewById(R.id.meal_heading);
            mealTiming = itemView.findViewById(R.id.meal_timing);
            mealCode = itemView.findViewById(R.id.meal_code);
            menuItems = itemView.findViewById(R.id.menu_items);
            qrIcon = itemView.findViewById(R.id.qr_icon);
        }
    }

}
