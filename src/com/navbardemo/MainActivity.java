package com.navbardemo;

import android.app.Activity;
import android.os.Bundle;

import com.navbardemo.util.NavBar;
import com.navbardemo.util.NavBarHelper;

public class MainActivity extends Activity {
	NavBarHelper navBarHelper = new NavBarHelper();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		navBarHelper.addMenu(new NavBar(findViewById(R.id.tab1),
				findViewById(R.id.tab1_content), R.id.tab1));
		navBarHelper.addMenu(new NavBar(findViewById(R.id.tab2),
				findViewById(R.id.tab2_content), R.id.tab2));
		navBarHelper.addMenu(new NavBar(findViewById(R.id.tab3),
				findViewById(R.id.tab3_content), R.id.tab3));

		// Hide any one Menu
		// navBarHelper.setVisible(R.id.tab2, false);
		// Show any one Menu
		// navBarHelper.setVisible(1, true);
	}
}
