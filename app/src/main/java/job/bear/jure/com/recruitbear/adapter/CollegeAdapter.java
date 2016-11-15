package job.bear.jure.com.recruitbear.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import job.bear.jure.com.recruitbear.CollegeFragment;

/**
 * Created by DELL on 2015/9/21.
 */
public class CollegeAdapter extends FragmentPagerAdapter {

    private final int college_count = 5;
    private String tabTitles[] = new String[]{"中山大学", "华南理工大学", "暨南大学", "广东工业大学", "广州大学"};
    private Context mContext;

    public CollegeAdapter(android.support.v4.app.FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return college_count;
    }

    @Override
    public Fragment getItem(int position) {
        return CollegeFragment.newInstance(position + 1);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}
