package com.squidge.samanthacausey.weatherwithbobross.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squidge.samanthacausey.weatherwithbobross.R;
import com.squidge.samanthacausey.weatherwithbobross.data.City;

/**
 * Created by samanthacausey on 5/3/16.
 */
public class AddCityFragment extends DialogFragment {

    private static final String KEY_NEW_ITEM = "KEY_NEW_ITEM";
    public static final String TAG = "AddCityFragment";

    public interface CityAddListener {
        public void onCityAdded(String city);
    }

    private CityAddListener cityAddListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            cityAddListener =
                    (CityAddListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement CityAddListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Check out documentation for AlertDialogue, as well as Builder.
        builder.setTitle("Add a new city");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_add_city, null);
        final EditText etAddCity = (EditText) dialogView.findViewById(R.id.etAddCity);

        builder.setView(dialogView)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do nothing here because we override this button later to change the close behaviour.
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

        AlertDialog alert = builder.create();
        return alert;
    }

    @Override
    public void onStart() {
        super.onStart();
        final AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button positiveButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    EditText etAddCity = (EditText) d.findViewById(R.id.etAddCity);
                    if ("".equals(etAddCity.getText().toString())) {
                        etAddCity.setError("Field cannot be empty!");
                    } else {
                        cityAddListener.onCityAdded(etAddCity.getText().toString());
                        dismiss();
                    }
                }
            });
        }
    }
}