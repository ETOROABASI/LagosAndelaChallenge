package com.example.android.lagosandelachallenge.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.lagosandelachallenge.R;
import com.example.android.lagosandelachallenge.adapter.UserAdapter;
import com.example.android.lagosandelachallenge.adapter.UserAdapterDivider;
import com.example.android.lagosandelachallenge.model.UserList;
import com.example.android.lagosandelachallenge.retroServices.ApiBuilder;
import com.example.android.lagosandelachallenge.retroServices.ApiService;
import com.example.android.lagosandelachallenge.utilities.ConnectionTesting;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProgressDialog mProgress;
    private TextView emptyTextView;

    boolean isConnected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgress = new ProgressDialog(this);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_user_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        emptyTextView=(TextView)findViewById(R.id.empty_text_view);


        isConnected = ConnectionTesting.isNetworkAvailable(this);

        fetchUsers();


    }

    private void fetchUsers() {

        if (isConnected) {
            mProgress.setMessage(getString(R.string.progress_text) );
            mProgress.show();
            String searchParams = "language:java location:lagos";
            ApiService apiService = new ApiBuilder().getService();
            Call<UserList> userListCall = apiService.getUserList(searchParams);
            userListCall.enqueue(new Callback<UserList>() {
                @Override
                public void onResponse(Call<UserList> call, Response<UserList> response) {
                    if (response.isSuccessful()) {
                        UserList userList = response.body();
                        setAdapterData(userList);
                        mProgress.dismiss();
                    } else {
                        mProgress.dismiss();
                        Toast.makeText(MainActivity.this,
                                getString(R.string.bad_request),
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserList> call, Throwable t) {
                    mProgress.dismiss();
                    Toast.makeText(MainActivity.this,
                            getString(R.string.request_fail),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }else {

            Toast.makeText(this,getString(R.string.offline_text),Toast.LENGTH_LONG).show();

            return;
        }
    }

    private void setAdapterData(UserList userList) {
        UserAdapter adapter = new UserAdapter(userList.getItems());
        //mRecyclerView.addItemDecoration(new UserAdapterDivider());    //this adds the line that divides each item in the RecycleView
        mRecyclerView.setAdapter(adapter);
        if(adapter.getItemCount()==0) {

            emptyTextView.setVisibility(VISIBLE);

        }else{

            emptyTextView.setVisibility(GONE);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {

            fetchUsers();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
