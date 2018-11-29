package com.example.hp.myfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements InteractActivity {

    public String _dataFromFrg;
    public boolean _isTwoPane = false;
    FragmentManager _FM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _FM = getFragmentManager();

        if(findViewById(R.id.frameContainer2) != null){
            Toast.makeText(this,"ada2",Toast.LENGTH_LONG).show();
            _isTwoPane = true;

            angka_random Random = angka_random.newInstance("0","0");
            _FM.beginTransaction()
                    .replace(R.id.frameContainer, Random)
                    .commit();

            result FrResult = result.newInstance("0","0");
            _FM.beginTransaction()
                    .replace(R.id.frameContainer2, FrResult)
                    .commit();
        }
        else
        {
            _isTwoPane = false;
            angka_random FrRandom = angka_random.newInstance("0","0");
            _FM.beginTransaction()
                    .replace(R.id.frameContainer, FrRandom)
                    .commit();
        }

    }

    @Override
    public void onFragmentInteraction(String Data) {
        _dataFromFrg = Data;
        if(_isTwoPane)
        { result FrResult = result.newInstance(Data,"0");
            _FM.beginTransaction()
                    .replace(R.id.frameContainer2, FrResult)
                    .commit();}
        else
        {
            result FrResult = result.newInstance(Data,"0");
            _FM.beginTransaction()
                    .replace(R.id.frameContainer, FrResult)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
