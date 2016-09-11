package com.robsterthelobster.robinbmt;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robsterthelobster.robinbmt.dummy.SampleVenueItem;
import com.robsterthelobster.robinbmt.models.FoursquareCall;
import com.robsterthelobster.robinbmt.models.FoursquareResponse;
import com.robsterthelobster.robinbmt.models.Group;
import com.robsterthelobster.robinbmt.models.Item;
import com.robsterthelobster.robinbmt.models.Venue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment representing a list of Items.
 * <p />
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PlacesFragment extends Fragment {

    private final String BASEURL = "https://api.foursquare.com/v2/";
    private final String BASEIMAGEURL = "https://irs0.4sqi.net/img/general/";

    List<SampleVenueItem> sampleContent = new ArrayList<SampleVenueItem>();
    @BindView(R.id.list) RecyclerView recyclerView;

    // TODO: Customize parameters
    private int mColumnCount = 1;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    private OnListFragmentInteractionListener mListener;

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PlacesFragment newInstance(int columnCount) {
        PlacesFragment fragment = new PlacesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlacesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);

        fetchData();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void fetchData(){
        Date date = new Date();
        String today = new SimpleDateFormat("yyyyMMdd").format(date);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoursquareApi foursquareApi = retrofit.create(FoursquareApi.class);

        Call<FoursquareCall> call = foursquareApi.getVenues(
                BuildConfig.FOURSQUARE_CID,
                BuildConfig.FOURSQUARE_SECRET,
                "40.7,-74",
                "boba",
                1,
                today
        );

        call.enqueue(new Callback<FoursquareCall>() {
            @Override
            public void onResponse(Call<FoursquareCall> call, Response<FoursquareCall> response) {
                Log.d("Retrofit URL", call.request().url().toString());
                FoursquareResponse foursquareResponse = response.body().getFoursquareResponse();
                if(foursquareResponse == null){
                    Log.d("Retrofit", "Null response.");
                    return;
                }
                Group recommendedGroup = foursquareResponse.getGroups().get(0);

                for(Item item : recommendedGroup.getItems()){
                    Venue venue = item.getVenue();
                    String photoUrl = venue.getPhotos()
                            .getGroups()
                            .get(0)
                            .getItems()
                            .get(0)
                            .getSuffix();

                    photoUrl = BASEIMAGEURL + "original" + photoUrl;
                    sampleContent.add(new SampleVenueItem(venue.getName(), photoUrl));
                }
                Log.d("Content size", sampleContent.size() + "");
                recyclerView.setAdapter(new PlaceRecyclerViewAdapter(sampleContent, mListener));
            }

            @Override
            public void onFailure(Call<FoursquareCall> call, Throwable t) {
                Log.d("RetrofitCall", "Failed call");
            }
        });
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(SampleVenueItem item);
    }
}
