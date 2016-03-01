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

    TextView nameText;
    ImageView partyImage,repImage;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_representative, container, false);

        Bundle bundle = getArguments();
        int length = bundle.getInt("Length");
        int position = bundle.getInt("Position");

        if(position < length) {
            final String name = bundle.getString("Name");
            final String party = bundle.getString("Party") +"_logo";
            partyImage = (ImageView) view.findViewById(R.id.partyImage);
            repImage = (ImageView) view.findViewById(R.id.repImage);
            nameText.setText(name);
//            Context context = getContext();
//            partyImage.setImageResource(context.getResources().getIdentifier("mipmap/" + party, null, context.getPackageName()));

//            Button button = (Button) view.findViewById(R.id.button);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //send message to ...
//                    Toast.makeText(getActivity(), "Click!", Toast.LENGTH_SHORT).show();
//                    String sendName = name;
//                    Intent sendMessageRandom = new Intent(getActivity(), WatchToPhoneService.class);
//                    sendMessageRandom.putExtra("string",sendName);
//                    getActivity().startService(sendMessageRandom);
//                }
//            });

        }
//        else if(position == length) {
//            String location = bundle.getString("Location");
//            textViewTitle = (TextView) view.findViewById(R.id.frag_textView_name);
//            textViewLocation = (TextView) view.findViewById(R.id.frag_textView_party);
//            textViewVS = (TextView) view.findViewById(R.id.frag_textView_vs);
//            textViewVoteObama = (TextView) view.findViewById(R.id.frag_textView_obamavote);
//            textViewVoteRomney = (TextView) view.findViewById(R.id.frag_textView_romneyvote);
//            Button button = (Button) view.findViewById(R.id.button);
//
//            textViewTitle.setText("2012 Presidential Vote");
//            textViewLocation.setText(location);
//            textViewVS.setVisibility(View.VISIBLE);
//            textViewVoteObama.setVisibility(View.VISIBLE);
//            textViewVoteRomney.setVisibility(View.VISIBLE);
//            button.setVisibility(View.INVISIBLE);
//            textViewVoteObama.setText("55%");
//            textViewVoteRomney.setText("45%");
//
//        }

        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

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
        void onFragmentInteraction(Uri uri);
    }
}
