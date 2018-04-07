package io.jinxlabs.doctalk.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.jinxlabs.doctalk.R;
import io.jinxlabs.doctalk.data.businesslogic.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{

    private List<User> users;

    public UserAdapter() {
        users = new ArrayList<>();
    }

    public void addUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public void clear() {
        this.users.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = users.get(position);
        holder.userName.setText(user.getLogin());
        Picasso.get().load(user.getAvatarUrl()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar) ImageView avatar;
        @BindView(R.id.user_name) TextView userName;

        public UserHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
