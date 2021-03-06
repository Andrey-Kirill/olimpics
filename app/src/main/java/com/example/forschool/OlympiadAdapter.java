package com.example.forschool;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class OlympiadAdapter extends RecyclerView.Adapter<OlympiadAdapter.OlympiadViewHolder> {

    private ArrayList<Olympiad> olympiads;
    private final Listener onOlympiadClickListener;

    public OlympiadAdapter(ArrayList<Olympiad> olympiads, Listener onOlympiadClickListener) {
        this.olympiads = olympiads;
        this.onOlympiadClickListener = onOlympiadClickListener;
    }

    @NonNull
    @Override
    public OlympiadAdapter.OlympiadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.olympiad_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOlympiadClickListener.onOlympiadClick((int) v.getTag());
            }
        });
        return new OlympiadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OlympiadAdapter.OlympiadViewHolder holder, int position) {
        Olympiad olympiad = olympiads.get(position);
        holder.bind(olympiad);
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return olympiads.size();
    }

    static final class OlympiadViewHolder extends RecyclerView.ViewHolder{

        private final TextView nameTextView;
        private final TextView organizerTextView;
        private final TextView subjectTextView;
        private final TextView levelTextView;
        private final ImageView img;

        public OlympiadViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            organizerTextView = itemView.findViewById(R.id.organizer);
            subjectTextView = itemView.findViewById(R.id.subject);
            levelTextView = itemView.findViewById(R.id.level);
            img  = itemView.findViewById(R.id.imageView5);

            //itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(Olympiad olympiad) {
            nameTextView.setText(olympiad.getShortName());
            organizerTextView.setText(olympiad.getOrganizer());
            subjectTextView.setText(olympiad.getSubject());
            levelTextView.setText(olympiad.getLevel());
            img.setImageBitmap(olympiad.getBitmap());
        }
        /*

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle(R.string.recycle_view_context_menu_title);
            contextMenu.add(0, 0, 0, R.string.recycle_view_context_menu_favorite);
            contextMenu.add(0, 1, 0, R.string.recycle_view_context_menu_share);
        }

         */

    }



    interface Listener {

        void onOlympiadClick(int position);

    }
}
