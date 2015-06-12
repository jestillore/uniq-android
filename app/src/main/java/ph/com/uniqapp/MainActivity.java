package ph.com.uniqapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;
import ph.com.uniqapp.fragments.BuddiesFragment;
import ph.com.uniqapp.fragments.CategoriesFragment;
import ph.com.uniqapp.fragments.FavouritesFragment;
import ph.com.uniqapp.fragments.HomeFragment;
import ph.com.uniqapp.fragments.MessagesFragment;
import ph.com.uniqapp.fragments.MyPostsFragment;
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
        MaterialSection buddies = this.newSection(getString(R.string.nav_buddies), R.drawable.ic_buddies, BuddiesFragment.newInstance());
        MaterialSection messages = this.newSection(getString(R.string.nav_messages), R.drawable.ic_messages, MessagesFragment.newInstance());
        MaterialSection myPosts = this.newSection(getString(R.string.nav_my_posts), R.drawable.ic_my_posts, MyPostsFragment.newInstance());
        MaterialSection categories = this.newSection(getString(R.string.nav_categories), R.drawable.ic_categories, CategoriesFragment.newInstance());

        notifications.setNotifications(2);

        this.addSection(home);
        this.addSection(favourites);
//        this.addSection(notifications);
//        this.addSection(buddies);
//        this.addSection(messages);
        this.addSection(myPosts);
        this.addSection(categories);
    }

    @Override
    public void onAccountOpening(MaterialAccount materialAccount) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onChangeAccount(MaterialAccount materialAccount) {

    }
}
