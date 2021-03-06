package com.liro.applications.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.liro.applications.myapplication.Interface.CustomAlertDialog;
import com.liro.applications.myapplication.Models.AuthenticationRes;
import com.liro.applications.myapplication.Services.MockRestService;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static String UserName;
    private static String PassWord;
    final Context context = this;
    private static final boolean AUTO_HIDE = false;
    private MainActivity MainActivity;
    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            getmControlsView().setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible = false;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            // show app bar on startup
            show();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        mVisible = true;
        setmControlsView(findViewById(R.id.fullscreen_content_controls));
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // show the content on startup
        //show();
        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        EditText view = (EditText)findViewById(R.id.editUserName);
        view.requestFocus();
        //view.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        //view.setTextIsSelectable(true);
        //view.setRawInputType(InputType.TYPE_CLASS_TEXT);
        view.addTextChangedListener(new TextWatcher() {
            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                UserName = s.toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                //EditText view = (EditText)findViewById(R.id.editPassword);
                //view.requestFocus();

            }
        });

        view.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //performSearch();
                    EditText view = (EditText)findViewById(R.id.editPassword);
                    view.requestFocus();
                    return true;
                }
                return false;
            }
        });

        EditText view1 = (EditText)findViewById(R.id.editPassword);

        view1.addTextChangedListener(new TextWatcher() {
            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                PassWord = s.toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                //EditText view = (EditText)findViewById(R.id.editPassword);
                //view.requestFocus();

            }
        });

        Button Btn = (Button)findViewById(R.id.dummy_button);

        Btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                // login in to system
                if (UserName.isEmpty() || PassWord.isEmpty())
                {
                    CustomAlertDialog CustomAlertDialog = new CustomAlertDialog(context, "Warning", "Please Enter a User Name and Password", "Ok","", false, mButtonListener1, mButtonListener2, false

                    );

                    CustomAlertDialog.CreateAndShow();
                }
                else {
                    if (UserName.isEmpty()) {
                        CustomAlertDialog CustomAlertDialog = new CustomAlertDialog(context, "Warning", "Please Enter a User Name", "Ok","", false, mButtonListener1, mButtonListener2, false

                        );

                        CustomAlertDialog.CreateAndShow();
                    }
                    else if (PassWord.isEmpty()) {
                        CustomAlertDialog CustomAlertDialog = new CustomAlertDialog(context, "Warning", "Please Enter a Password", "Ok","", false, mButtonListener1, mButtonListener2, false

                        );

                        CustomAlertDialog.CreateAndShow();
                    }
                    else {
                        AuthenticationRes result = MockRestService.GetUser(UserName, PassWord);
                    }


                }

                //CustomAlertDialog.show();
                //AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //alertDialogBuilder.setTitle("Warning");
                //alertDialogBuilder.setMessage("Click yes to exit");
                //alertDialogBuilder.setCancelable(false);
                //alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    //public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        //FullscreenActivity.this.finish();
                    //}
                //});
                //alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    //public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        //dialog.cancel();
                    //}
               // });

                //AlertDialog alertDialog = alertDialogBuilder.create();
                //alertDialog.show();
            }
        });
    }


    public DialogInterface.OnClickListener mButtonListener1 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            startActivity(new Intent(FullscreenActivity.this, MainActivity.class));

        }


    };


    public DialogInterface.OnClickListener mButtonListener2 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }


    };



    private void launchLoginActivity() {
        Intent mainActivity = new Intent(this, FullscreenActivity.class);

        startActivity(mainActivity);
        //this.overridePendingTransition(R.anim.abc_fade_in, R.anim.activity_no_transition);
    }

    private void launchMainActivity() {
        //MainActivity = new MainActivity();
        Intent mainActivity = new Intent(this, MainActivity.class);

        startActivity(mainActivity);
        //this.overridePendingTransition(R.anim.abc_fade_in, R.anim.activity_no_transition);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getmControlsView().setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public View getmControlsView() {
        return mControlsView;
    }

    public void setmControlsView(View mControlsView) {
        this.mControlsView = mControlsView;
    }
}
