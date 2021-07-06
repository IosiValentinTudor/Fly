package com.example.fly.Model;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.fly.R;

import org.jetbrains.annotations.NotNull;

public class Dialog extends AppCompatDialogFragment {
    EditText editName;
    DialogListener listener;

    @NonNull
    @NotNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);

        editName = view.findViewById(R.id.editName);

        builder.setView(view).setTitle("Do you want to save the current flight?")
        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
        .setPositiveButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String name = editName.getText().toString();
                listener.saveName(name);

            }
        });




        return builder.create();

    }


    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;

        } catch (ClassCastException e) {
           throw new ClassCastException(context.toString() + "must implement DialogListener");
        }
    }

    public interface DialogListener
    {
        void saveName(String name);
    }
}
