package ua.com.igorka.oa.android.examples;

import android.app.Activity;
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

public class CalcKeyboardFragment1 extends Fragment {

    OnCalcButtonPressedListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnCalcButtonPressedListener)activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.getClass().getSimpleName() + " must implement " + OnCalcButtonPressedListener.class.getSimpleName() + " interface.");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grid_layout_fragment, container, false);
        ((Button)view.findViewById(R.id.button_result)).setText("===");
        Log.i(getClass().getSimpleName(), getActivity().getClass().getSimpleName());
        initButtonsListeners(view);
        return view;
    }

    private void initButtonsListeners(final View view) {
        ((Button)view.findViewById(R.id.button_result)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCalcButtonPressed(((Button) view.findViewById(R.id.button_result)).getText().toString());
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    /*
    * Этот интерфейс должен быть реализован в активити в которую будет добавлятся этот фрагмент.
    * Таким образом фрагмент сможет передавать информацию в активити.
    * */
    public interface OnCalcButtonPressedListener {
        public void onCalcButtonPressed(String keyValue);
    }
}
