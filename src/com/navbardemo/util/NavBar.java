package com.navbardemo.util;

import android.view.View;

public class NavBar {
		View tab;
		View tabContent;
		int index;

		public NavBar(View tab, View tabContent, int index) {
			super();
			this.tab = tab;
			this.tabContent = tabContent;
			this.index = index;
		}

	}