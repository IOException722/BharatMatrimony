package matrimony.bharatmatrimony;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by abhay on 10/9/15.
 */
public class Registrations extends AppCompatActivity implements RegistrationStep1.OnFragmentInteractionListener,
                                                                RegistrationStep2.OnFragmentInteractionListener,
                                                                RegistraionStep3.OnFragmentInteractionListener{
    ArrayList<String> mFragmentTagList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_steps);
        mFragmentTagList = new ArrayList<>();
        clearAllFragments();
        RegistrationStep1 fragmentRegStep1 = RegistrationStep1.newInstance("String1", "String2");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.reg_steps, fragmentRegStep1, "Rstep1");
        transaction.addToBackStack(null);
        mFragmentTagList.add("Rstep1");
        transaction.commit();
    }


    private void clearAllFragments(){
        // declare an iterator
        Iterator<String> iterator = mFragmentTagList.iterator();
        while(iterator.hasNext()){
            String tag = iterator.next();
            Log.v("tag is", tag);
            FragmentManager manager = getSupportFragmentManager();

            Fragment fragment = manager.findFragmentByTag(tag);
            // check if returned fragment exists

            if(fragment != null){

                FragmentTransaction transaction = manager.beginTransaction();
                transaction.remove(fragment);
                transaction.commit();
            }

            // remove tag from arraylist
            iterator.remove();
        }// end of while

        // check if all fragment tags have been removed, else clear the list
        if(mFragmentTagList.size() != 0){
            mFragmentTagList.clear();
        }
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
