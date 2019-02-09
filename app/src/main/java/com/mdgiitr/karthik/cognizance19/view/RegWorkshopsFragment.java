package com.mdgiitr.karthik.cognizance19.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.RegEventsAdapter;
import com.mdgiitr.karthik.cognizance19.models.RegEventsModel;
import com.mdgiitr.karthik.cognizance19.models.RegEventsResponse;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegWorkshopsFragment extends Fragment {
    private RecyclerView recyclerView;
    private RegEventsAdapter adapter;
    private PreferenceHelper preferenceHelper;
    private ApiClient apiClient;
    private List<RegEventsModel> regWorkshopList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reg_workshops, container, false);

        recyclerView = view.findViewById(R.id.reg_workshops_recyclerview);

        preferenceHelper = new PreferenceHelper(getActivity());
        apiClient = new ApiClient();

        apiClient.fetchRegisteredEvents(preferenceHelper.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegEventsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegEventsResponse regEventsResponse) {
                        int size = regEventsResponse.getEvents().size();
                        regWorkshopList = new ArrayList<>();
                        for (int i = 0; i < size; i++) {
                            if (regEventsResponse.getEvents().get(i).getType().equals("workshop")) {
                                regWorkshopList.add(regEventsResponse.getEvents().get(i));
                            }
                        }
                        adapter = new RegEventsAdapter(getContext(), regWorkshopList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "Error in fetching Registered Workshops.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return view;
    }

}
