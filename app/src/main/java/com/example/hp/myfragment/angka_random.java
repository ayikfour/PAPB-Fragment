package com.example.hp.myfragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class angka_random extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    boolean _randomize = false;
    private String mParam1;
    private String mParam2;
    private InteractActivity mListener;
    int angka1 = 0, angka2 = 0;
    Random a, b;
    Button _random;
    TextView _result1;
    TextView _result2;


    Handler handler = new Handler();
    // Define the code block to be executed
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            // Do something here on the main thread
            a = new Random();
            b = new Random();
            angka1 = a.nextInt(9);
            angka2 = b.nextInt(9);

            _result1.setText(String.valueOf(angka1));
            _result2.setText(String.valueOf(angka2));
            // Repeat this the same runnable code block again another 2 seconds
            // 'this' is referencing the Runnable object
            handler.postDelayed(this, 100);
        }
    };

    public angka_random() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AngkaRandom.
     */
    // TODO: Rename and change types and number of parameters
    public static angka_random newInstance(String param1, String param2) {
        angka_random fragment = new angka_random();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View ViewFrag = inflater.inflate(R.layout.fragment_angka_random, container, false);
        _random = ViewFrag.findViewById(R.id.buttonRandom);
        _random.setOnClickListener(this);

        _result1 = ViewFrag.findViewById(R.id.TXangka1);
        _result2 = ViewFrag.findViewById(R.id.TXangka2);

        return ViewFrag;
    }

    public void onCallActivity(String Data) {
        if (mListener != null) {
            mListener.onFragmentInteraction(Data);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof InteractActivity) {
            mListener = (InteractActivity) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onClick(View v) {
        _randomize = !_randomize;

        if (_randomize) {
            handler.post(runnableCode);
            _random.setText("STOP");
        } else {
            handler.removeCallbacks(runnableCode);
            String result;
            if (angka1 > angka2) {
                result = String.valueOf(angka1);
            } else {
                result = String.valueOf(angka2);
            }
            onCallActivity(String.valueOf(result));
            _random.setText("GENERATE");
        }
    }
}
