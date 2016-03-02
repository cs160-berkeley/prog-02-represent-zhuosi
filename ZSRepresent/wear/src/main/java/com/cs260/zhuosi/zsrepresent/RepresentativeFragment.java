package com.cs260.zhuosi.zsrepresent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhuosi on 3/1/16.
 */
public class RepresentativeFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    TextView nameText;
    ImageView partyImage,repImage;

    private OnFragmentInteractionListener mListener;

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
        View view =inflater.inflate(R.layout.fragment_representative, container, false);

        Bundle bundle = getArguments();
        int length = bundle.getInt("Length");
        int position = bundle.getInt("Position");

        if(position < length) {
            final String name = bundle.getString("Name");
            final String party = bundle.getString("Party") +"_logo";
            partyImage = (ImageView) view.findViewById(R.id.partyImage);
            repImage = (ImageView) view.findViewById(R.id.repImage);
            nameText = (TextView) view.findViewById(R.id.nameText);
            nameText.setText(name);
            Context context = getActivity();
            partyImage.setImageResource(context.getResources().getIdentifier("mipmap/" + party, null, context.getPackageName()));

            nameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //send message to ...
                    Toast.makeText(getActivity(), "Click!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), WatchToPhoneService.class);
                    intent.putExtra("string",name);
                    getActivity().startService(intent);
                }
            });

        }
        else if(position == length) {
            System.out.println("at the last view");

            view = inflater.inflate(R.layout.votedetail, container, false);
        }

        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public RepresentativeFragment() {
        // Required empty public constructor
    }

    public static RepresentativeFragment newInstance(String param1, String param2) {
        RepresentativeFragment fragment = new RepresentativeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

}
