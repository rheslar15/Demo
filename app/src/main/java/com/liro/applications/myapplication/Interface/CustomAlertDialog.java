package com.liro.applications.myapplication.Interface;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

/**
 * Created by HeslarR on 4/10/2017.
 */

public class CustomAlertDialog extends AlertDialog {
    private Context context;
    private Builder alertDialogBuilder;
    private AlertDialog AlertDialog;

    public CustomAlertDialog(@NonNull Context context, String Title, String Message, String FirstButtonText,
                             String SecondButtonText, boolean Cancelable, DialogInterface.OnClickListener PositiveButtonListener, DialogInterface.OnClickListener NegativeButtonListener, boolean SecondButtonVisible) {
        super(context);
        this.context = context;
        alertDialogBuilder = new Builder(this.context);
        alertDialogBuilder.setTitle(Title);
        alertDialogBuilder.setMessage(Message);
        alertDialogBuilder.setCancelable(Cancelable);
        alertDialogBuilder.setPositiveButton(FirstButtonText, PositiveButtonListener);
        if (SecondButtonVisible) {
            alertDialogBuilder.setNegativeButton(SecondButtonText, NegativeButtonListener);
        }
    }

    public void CreateAndShow()
    {
        AlertDialog = alertDialogBuilder.create();
        AlertDialog.show();

    }

}
