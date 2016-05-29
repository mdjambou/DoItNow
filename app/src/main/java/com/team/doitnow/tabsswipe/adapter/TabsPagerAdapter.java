package com.team.doitnow.tabsswipe.adapter;

import com.team.doitnow.CompteSettingActivity;
import com.team.doitnow.ActuEventListActivity;
import com.team.doitnow.MainActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			//News
			return new MainActivity();
		case 1:
			// Movies My events
			return new ActuEventListActivity();
        case 2:
            //My settings
            return new CompteSettingActivity();
        }

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
