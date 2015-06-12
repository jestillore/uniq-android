package ph.com.uniqapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;
import ph.com.uniqapp.fragments.FavouritesFragment;
import ph.com.uniqapp.fragments.HomeFragment;
import ph.com.uniqapp.fragments.NotificationsFragment;


public class MainActivity extends MaterialNavigationDrawer implements MaterialAccountListener {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
            case R.id.action_about:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void init(Bundle bundle) {
        this.allowArrowAnimation();

        MaterialAccount account = new MaterialAccount(this.getResources(), getString(R.string.account_name), getString(R.string.account_email), R.drawable.troll, R.drawable.bamboo);
        this.addAccount(account);
        this.setAccountListener(this);

        MaterialSection home = this.newSection(getString(R.string.nav_home), R.drawable.ic_home, HomeFragment.newInstance());
        MaterialSection favourites = this.newSection(getString(R.string.nav_favourites), R.drawable.ic_favourites, FavouritesFragment.newInstance());
        MaterialSection notifications = this.newSection(getString(R.string.nav_notifications), R.drawable.ic_notifications, NotificationsFragment.newInstance());

        this.addSection(home);
        this.addSection(favourites);
        this.addSection(notifications);
    }

    @Override
    public void onAccountOpening(MaterialAccount materialAccount) {
        Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChangeAccount(MaterialAccount materialAccount) {

    }
}
