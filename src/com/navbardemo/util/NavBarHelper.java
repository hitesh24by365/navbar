package com.navbardemo.util;

import java.util.HashMap;

import android.graphics.drawable.RotateDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

public class NavBarHelper {
	private NavBar lastMenu = null;
	HashMap<Integer, NavBar> menus = new HashMap<Integer, NavBar>();

	public void addMenu(final NavBar menu) {
		menus.put(menu.index, menu);

		menu.tab.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				toggleView(menu);
			}
		});
	}

	public void toggleView(NavBar menu) {
		menu.tab.setVisibility(View.VISIBLE);
		if (menu.tabContent.isShown()) {
			collapse(menu.tabContent);
			toggleTabIndicator(menu.tab, 0);

		} else {
			expand(menu.tabContent);
			toggleTabIndicator(menu.tab, 2500);
			if (lastMenu != null) {
				if (!lastMenu.equals(menu)) {
					collapse(lastMenu.tabContent);
					toggleTabIndicator(lastMenu.tab, 0);
				}
			}
		}
		lastMenu = menu;
	}

	void toggleTabIndicator(View view, int level) {
		RotateDrawable rotateDrawable;
		rotateDrawable = ((RotateDrawable) ((TextView) view)
				.getCompoundDrawables()[0]);
		rotateDrawable.setLevel(level);
	}

	private void expand(final View v) {
		v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		final int targtetHeight = v.getMeasuredHeight();

		v.getLayoutParams().height = 0;
		v.setVisibility(View.VISIBLE);
		Animation a = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime,
					Transformation t) {
				v.getLayoutParams().height = interpolatedTime == 1 ? LayoutParams.WRAP_CONTENT
						: (int) (targtetHeight * interpolatedTime);
				v.requestLayout();
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		// 1dp/ms
		a.setDuration((int) (targtetHeight / v.getContext().getResources()
				.getDisplayMetrics().density));
		v.startAnimation(a);
	}

	private void collapse(final View v) {
		final int initialHeight = v.getMeasuredHeight();

		Animation a = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime,
					Transformation t) {
				if (interpolatedTime == 1) {
					v.setVisibility(View.GONE);
				} else {
					v.getLayoutParams().height = initialHeight
							- (int) (initialHeight * interpolatedTime);
					v.requestLayout();
				}
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		// 1dp/ms
		a.setDuration((int) (initialHeight / v.getContext().getResources()
				.getDisplayMetrics().density));
		v.startAnimation(a);
	}

	public void setVisible(int index, Boolean flag) {
		NavBar menu = menus.get(index);
		if (flag) {
			menu.tab.setVisibility(View.VISIBLE);
			menu.tabContent.setVisibility(View.VISIBLE);
		} else {
			menu.tab.setVisibility(View.GONE);
			menu.tabContent.setVisibility(View.GONE);
		}

	}
}
