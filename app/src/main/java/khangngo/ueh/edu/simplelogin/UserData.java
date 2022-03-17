package khangngo.ueh.edu.simplelogin;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserData {
    private Context context;
    private ArrayList<User> users = new ArrayList<User>();
    public UserData(Context context){
        this.context = context;
    }

    public void getUsersDataFromDB(){
        FirebaseDatabase database = FirebaseDatabase.getInstance(context.getString(R.string.db_url));
        DatabaseReference dbref = database.getReference("Users");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    User user = postSnapshot.getValue(User.class);
                    users.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("Error", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
    }

    public boolean checkLogin(String username, String password){
        for (int i = 0; i < users.size(); i++){
            String u = users.get(i).getUsername();
            String p = users.get(i).getPassword();
            if (u.equals(username) && p.equals(password)){
                return true;
            }
        }
        return false;
    }
}
