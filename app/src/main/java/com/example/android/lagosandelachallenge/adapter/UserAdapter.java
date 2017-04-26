package com.example.android.lagosandelachallenge.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.lagosandelachallenge.R;
import com.example.android.lagosandelachallenge.model.UserData;
import com.example.android.lagosandelachallenge.ui.UserDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ETORO on 26/04/2017.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    //
    private List<UserData> userList;

    public UserAdapter(List<UserData> userList) {

        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        Picasso.with(context).load(userList.get(position).getAvatarUrl()).into(holder.avatarImageView);
        holder.usernameTextView.setText(userList.get(position).getLogin());
        holder.locationTextView.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView avatarImageView;
        TextView usernameTextView;
        TextView locationTextView;


        public UserViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            avatarImageView = (ImageView) itemView.findViewById(R.id.list_avatar);
            usernameTextView = (TextView) itemView.findViewById(R.id.list_username);
            locationTextView = (TextView) itemView.findViewById(R.id.list_location);


        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            Intent intent = new Intent(context, UserDetails.class);
            UserData user = userList.get(getLayoutPosition());
            intent.putExtra("user", user);
            context.startActivity(intent);
        }
    }
}

