package ua.com.igorka.oa.android.examples;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Igor Kuzmenko on 11.03.2015.
 *
 */

public class CalcKeyboardFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grid_layout_fragment, container, false);
        ((Button)view.findViewById(R.id.button_result)).setText("======");
        Log.i(getClass().getSimpleName(), getActivity().getClass().getSimpleName());
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
