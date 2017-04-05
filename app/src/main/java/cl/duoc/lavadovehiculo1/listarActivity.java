package cl.duoc.lavadovehiculo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listarActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private RecyclerView rvLavados;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        rvLavados = (RecyclerView)findViewById(R.id.rvLavados);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("lavaAuto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Lavado> dataSet = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Lavado lavado = postSnapshot.getValue(Lavado.class);
                    dataSet.add(lavado);
                }
                Log.d("Firebase", "Existen " + dataSet.size() + " lavados");
                cargarListaRecycler(dataSet);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void cargarListaRecycler(final ArrayList<Lavado> values) {

        rvLavados.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        rvLavados.setLayoutManager(mLayoutManager);
        mAdapter = new lavadoAdapter(values, this);

        rvLavados.setAdapter(mAdapter);
    }
}
