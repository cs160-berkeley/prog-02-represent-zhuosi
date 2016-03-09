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
    private final String detailId = "/DETAILID";

    TextView nameText;
    ImageView partyImage,repImage;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_representative, container, false);

        Bundle bundle = getArguments();
        int length = bundle.getInt("Length");
        final int position = bundle.getInt("Position");
        System.out.println("The Length is  " + length + "  position is  " + position);

        if(position < length) {
            final String name = bundle.getString("Name");
            final String party = bundle.getString("Party");
            final String picname = bundle.getString("Pic");
            partyImage = (ImageView) view.findViewById(R.id.partyImage);
            repImage = (ImageView) view.findViewById(R.id.repImage);
            nameText = (TextView) view.findViewById(R.id.nameText);

            nameText.setText(name);
            Context context = getActivity();
            partyImage.setImageResource(context.getResources().getIdentifier("mipmap/" + party, null, context.getPackageName()));
            repImage.setImageResource(context.getResources().getIdentifier("mipmap/" + picname, null, context.getPackageName()));

            nameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //send message to ...
                    Intent intent = new Intent(getActivity(), WatchToPhoneService.class);
                    intent.putExtra(detailId, Integer.toString(position));
                    getActivity().startService(intent);
                }
            });

        }
        else {
            System.out.println("at the last view");
            String location = bundle.getString("Location");
            String result = bundle.getString("Result");

            TextView showView = (TextView) view.findViewById(R.id.detailTitle);
            showView.setVisibility(View.VISIBLE);
            showView = (TextView) view.findViewById(R.id.detailLocation);
            showView.setText(location);
            showView.setVisibility(View.VISIBLE);
            showView = (TextView) view.findViewById(R.id.detailPercentage);
            showView.setText(result);
            showView.setVisibility(View.VISIBLE);

            ImageView imageView = (ImageView) view.findViewById(R.id.repImage);
            imageView.setVisibility(View.INVISIBLE);
            imageView = (ImageView) view.findViewById(R.id.partyImage);
            imageView.setVisibility(View.INVISIBLE);
            showView = (TextView) view.findViewById(R.id.nameText);
            showView.setVisibility(View.INVISIBLE);
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
