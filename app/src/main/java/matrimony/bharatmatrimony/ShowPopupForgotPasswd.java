package matrimony.bharatmatrimony;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ShowPopupForgotPasswd extends DialogFragment {

    ImageView mClose;
    EditText userDetail;
    Button mSubmit;
    boolean flag = false;

    public interface showForgotPasswdListener {
       public void sendUserDetail(String str);
    }

    showForgotPasswdListener mForgotPasswdListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mForgotPasswdListener = (showForgotPasswdListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View mView = inflater.inflate(R.layout.pop_up, null);
        builder.setView(mView);

        final AlertDialog ad = builder.create();
        mClose = (ImageView) mView.findViewById(R.id.closeBtn);
        mSubmit = (Button) mView.findViewById(R.id.forgot_password_submit_btn);
        userDetail = (EditText) mView.findViewById(R.id.enter_forgot_password_popup);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                Log.v("Insideflag", userDetail.getText().toString());
                ad.cancel();
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = userDetail.getText().toString();
                Log.v("entered", val.toString());
                mForgotPasswdListener.sendUserDetail(val);
                ad.cancel();
            }
        });
        return ad;
    }

}