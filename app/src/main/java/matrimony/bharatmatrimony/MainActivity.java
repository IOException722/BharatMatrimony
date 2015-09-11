package matrimony.bharatmatrimony;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements ShowPopupForgotPasswd.showForgotPasswdListener,
                                                                RegistrationStep1.OnFragmentInteractionListener{
    EditText mUsername,mPassword;
    Button mLogin;
    TextView mForgotPassword, mRegister, mFbSignin;
    ArrayList<String> mFragmentTagList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login);
        mForgotPassword = (TextView) findViewById(R.id.forgot_password);
        mRegister = (TextView) findViewById(R.id.register);
        mFbSignin = (TextView) findViewById(R.id.fb_signin);

        mFragmentTagList = new ArrayList<>();
        mRegister.setTransformationMethod(null);
        mFbSignin.setTransformationMethod(null);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Registrations.class);
                    startActivity(intent);
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUsername.setVisibility(View.VISIBLE);
                mPassword.setVisibility(View.VISIBLE);
            }
        });

        mPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (mPassword.getRight() - mPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        mPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                        Log.v("Insideright", "Inside right");
                        return true;
                    }
                }

                return false;
            }
        });

        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopupForgotPasswd fragmentPopup = new ShowPopupForgotPasswd();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                fragmentPopup.show(transaction, "PopUp");
                mFragmentTagList.add("PopUp");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void sendUserDetail(String str) {
        mForgotPassword.setText("Email is : "+  str);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
