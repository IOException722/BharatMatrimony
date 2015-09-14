package matrimony.bharatmatrimony;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrationStep1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrationStep1#newInstance} factory method to
 * create an instance of this fragment.
 */

public class RegistrationStep1 extends Fragment {
    EditText mProfileCreatedFor, mSearch, mCountryCode, mMobNo, mName;
    DrawerLayout mDrawerLayout;
    MyAdapter myAdapter;
    EditText mDate;
    TextView  mMale, mFemale;
    LayoutInflater mInflater;
    LayoutInflater mInflaterToast;
    CheckBox mAgreement;
    Button mConinue_reg1;
    boolean ccodeflag, profileforflag;

    public int length;
    ArrayList<String>mDrawerList, mDrawerTemp1List, mDrawerTempList;
    ListView mListView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationStep1.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationStep1 newInstance(String param1, String param2) {
        RegistrationStep1 fragment = new RegistrationStep1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RegistrationStep1() {
        // Required empty public constructor
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
        final View view =  inflater.inflate(R.layout.registration_step1, container, false);

        mProfileCreatedFor = (EditText) view.findViewById(R.id.profile_created_for);
        mListView = (ListView) view.findViewById(R.id.drawer_list_reg_step1);
        mInflater = getActivity().getLayoutInflater();
        mInflaterToast = getActivity().getLayoutInflater();

        mSearch = (EditText)view.findViewById(R.id.search);
        mCountryCode = (EditText) view.findViewById(R.id.ccode);
        mMobNo = (EditText) view.findViewById(R.id.mno);
        mAgreement = (CheckBox)view.findViewById(R.id.agreement);
        mConinue_reg1 = (Button) view.findViewById(R.id.continue_reg1);

        mMale = (TextView) view.findViewById(R.id.gender_male);
        mFemale = (TextView) view.findViewById(R.id.gender_female);
        mName = (EditText) view.findViewById(R.id.name);

        mMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMale.setBackgroundColor(Color.BLUE);
                mFemale.setBackgroundColor(Color.WHITE);
            }
        });
        mFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFemale.setBackgroundColor(Color.BLUE);
                mMale.setBackgroundColor(Color.WHITE);
            }
        });
        mDate = (EditText) view.findViewById(R.id.dob);

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
             //   if(DatePickerFragment.mMonth >0&& DatePickerFragment.mDay>0 &&  DatePickerFragment.mYear>0 )
               // mDate.setText(DatePickerFragment.mDay+"/"+DatePickerFragment.mMonth+"/"+DatePickerFragment.mYear);
            }
        });
        mDrawerTemp1List = new ArrayList<String>();
        mDrawerTempList = new ArrayList<String>();
        mDrawerList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.profile_for)));
        mDrawerTemp1List.addAll(mDrawerList);
        ccodeflag = false;
        profileforflag = false;
        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mProfileCreatedFor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Inside", "Insideprofile");
                mDrawerList.clear();
                mDrawerTemp1List.clear();
                mDrawerList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.profile_for)));
                mDrawerTemp1List.addAll(mDrawerList);
                length = mDrawerList.size();
                myAdapter = new MyAdapter();
                mListView.setAdapter(myAdapter);
                profileforflag = true;
                myAdapter.notifyDataSetChanged();
                mDrawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                Log.v("inbefore", s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.v("inontecxt", s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.v("inafter", s.toString());
                searchDrawer(s);
            }
        });

        mCountryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerList.clear();
                mDrawerTemp1List.clear();
                myAdapter.notifyDataSetChanged();
                mDrawerList.add("+91-India");
                mDrawerList.add("+100-US");
                mDrawerList.add("+102-Nepal");
                mDrawerList.add("+101-Canada");
                mDrawerList.add("102-Switzerland");
                ccodeflag = true;
                mDrawerTemp1List.addAll(mDrawerList);
                mDrawerTempList.clear();
                length = mDrawerList.size();
                myAdapter.notifyDataSetChanged();
                mDrawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        mConinue_reg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mName.getText().toString().length()<2) {
                    View layout = mInflaterToast.inflate(R.layout.toast_layout, (ViewGroup) view.findViewById(R.id.toast_layout_root));
                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("Please enter you name !");
                    Toast toast = new Toast(getActivity().getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();
            /*View layout = inflater.inflate(R.layout.custom_toast,
                    (ViewGroup) view.findViewById(R.id.toast_layout_root));*/

           /* Toast toast  = Toast.makeText(getContext(), "Please enter your name!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);*/
                }
                if (mName.getText().toString().length()>=2) {
                    RegistrationStep2 regStep2 = new RegistrationStep2().newInstance("Hi", "Hello !");
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.drawer_layout, regStep2);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });


        mAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAgreement.setChecked(true);
               /* Intent  termAndCond = new Intent(Intent.ACTION_VIEW, Uri.parse("http://bharatmatrimony.com/terms.php"));
                startActivity(termAndCond);*/
            }
        });

        return view;
    }

    public void searchDrawer(Editable s)
    {
        boolean flag = false;
        for (int i = 0; i < mDrawerTemp1List.size(); i++)
        {
            if (mDrawerTemp1List.get(i).toLowerCase().contains(s.toString().toLowerCase()))
            {
                mDrawerTempList.add(mDrawerTemp1List.get(i).toString());
                Log.v("contained", mDrawerTempList.toString());
                flag = true;
            }
        }

        if (flag) {
            length = mDrawerTempList.size();
            mDrawerList.clear();
            mDrawerList.addAll(mDrawerTempList);
            myAdapter.notifyDataSetChanged();
            mDrawerTempList.clear();
        }

        else
        {
            mDrawerList.clear();
            length = mDrawerList.size();
            myAdapter.notifyDataSetChanged();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if(convertView == null){

                convertView = mInflater.inflate(R.layout.list_item,parent,false);
            }
            Log.v("yoo", mDrawerList.get(position).toString());
            TextView tv = (TextView) convertView.findViewById(R.id.pf_for);
           tv.setText(mDrawerList.get(position));

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (profileforflag)
                    {
                        mProfileCreatedFor.setText(mDrawerList.get(position));
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        profileforflag  = false;
                        myAdapter.notifyDataSetChanged();
                    }

                    else if (ccodeflag) {

                       mCountryCode.setText(mDrawerList.get(position));
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        ccodeflag = false;
                        myAdapter.notifyDataSetChanged();
                    }
                }
            });
            return convertView;
        }

    }



    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        public static int mYear, mMonth, mDay;
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
                month = month;
            mYear = year;
            mDay = day;
            mMonth = month;

            EditText dob = (EditText)getActivity().findViewById(R.id.dob);
            dob.setText(mDay+"/"+mMonth+"/"+mYear);
        }
    }

}
