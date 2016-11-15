package job.bear.jure.com.recruitbear;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import job.bear.jure.com.recruitbear.adapter.JobAdapter;
import job.bear.jure.com.recruitbear.adapter.JobInfoAdapter;
import job.bear.jure.com.recruitbear.beans.Job;
import job.bear.jure.com.recruitbear.beans.Result;
import job.bear.jure.com.recruitbear.utils.HttpCallbackListener;
import job.bear.jure.com.recruitbear.utils.HttpUtils;

/**
 * Created by DELL on 2015/9/21.
 */
public class CollegeFragment extends Fragment {

    private final static String TAG = CollegeFragment.class.getSimpleName();

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private String url;
    private LinearLayoutManager manager;
    private RecyclerView mRecyclerView;
    private JobInfoAdapter mAdapter;
    private List<Job> mJobs;
    private ListView mListView;


    public static CollegeFragment newInstance(int page) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        CollegeFragment collegeFragment = new CollegeFragment();
        collegeFragment.setArguments(args);

        return collegeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPage = getArguments().getInt(ARG_PAGE);
        Log.e(TAG, mPage + "");
        Log.e(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.e(TAG, "onCreateView");

        View view = inflater.inflate(R.layout.fragment_college_info, container, false);
       /* mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.setHasFixedSize(true);  //设置可以确定每个Item的高度是确定的，这个设置可以提高性能
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());  //设置每个Item的默认添加删除动画*/

        mListView = (ListView) view.findViewById(R.id.list);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.e(TAG, "onActivityCreated");
    }



    @Override
    public void onResume() {
        super.onResume();

        Log.e(TAG, "onResume");

        mJobs = new ArrayList<>();
        initData(mPage);

    }


    public void initData(int mPage) {
      /*  switch (mPage) {
            case 1:
                url = "http://mobile.haitou.cc/client3/info?ver=1.0&school=32&part=gz&source=xjh&page=1&kind=after&token=6f81ddb520";
                break;
            case 2:
                url = "http://mobile.haitou.cc/client3/info?ver=1.0&school=34&part=gz&source=xjh&page=1&kind=after&token=6f81ddb520";
                break;
            case 3:
                url = "http://mobile.haitou.cc/client3/info?ver=1.0&school=33&part=gz&source=xjh&page=1&kind=after&token=6f81ddb520";
                break;
            case 4:
                url = "http://mobile.haitou.cc/client3/info?ver=1.0&school=36&part=gz&source=xjh&page=1&kind=after&token=6f81ddb520";
                break;
            case 5:
                url = "http://mobile.haitou.cc/client3/info?ver=1.0&school=35&part=gz&source=xjh&page=1&kind=after&token=6f81ddb520";
                break;
        }*/

        if (mPage == 1){
            //科大
            url = "http://mobile.haitou.cc/client3/info?ver=1.0&school=32&part=gz&source=xjh&page=1&kind=after&token=6f81ddb520";
        }else if (mPage == 2){
            //工大
            url = "http://mobile.haitou.cc/client3/info?ver=1.0&school=34&part=gz&source=xjh&page=1&kind=after&token=6f81ddb520";
        }else if (mPage == 3){
            //安大
            url = "http://mobile.haitou.cc/client3/info?ver=1.0&school=33&part=gz&source=xjh&page=1&kind=after&token=6f81ddb520";

        }

        HttpUtils.sendHttpRequest(url, new HttpCallbackListener() {
            @Override
            public void onSuccess(String response) {

                Result result = JSON.parseObject(response, Result.class);

                mJobs = result.getInfo();
                Log.e(TAG, "onSuccess " + mJobs.toString());

                for(int i = 0;i<mJobs.size();i++){
                    String company = mJobs.get(i).getCompany();
                    String holdTime = mJobs.get(i).getHoldTime();
                    String address = mJobs.get(i).getAddress();

                    Log.e(TAG,"company" + company + ",holdTime" + holdTime + ",address" + address);

                    int id = mJobs.get(i).getId();

                    mJobs.get(i).setCompany(company);
                    mJobs.get(i).setHoldTime(holdTime);
                    mJobs.get(i).setAddress(address);

                    mJobs.get(i).setId(id);

                    String name = mJobs.get(i).getName();
                    mJobs.get(i).setName(name);


                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new JobInfoAdapter(mJobs,getActivity());

                        mListView.setAdapter(mAdapter);
                    }
                });

            }

            @Override
            public void onError(Exception e) {

            }
        });


    }


}
