package ru.korenchuk.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initToolbar(isLandscape());

        addFragment(ListFragmentV2.newInstance());

        /*if (savedInstanceState == null) getSupportFragmentManager()
                .beginTransaction()
                //.add(R.id.notes_container, new NotesListFragment())
                .add(R.id.fragment_container, new NotesListFragment())
                .commit();
    }*/
    }
    private void addFragment(Fragment fragment ){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    /*private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void initToolbar(boolean isLandscape) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void openAboutFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("")
                .add(R.id.fragment_container, new AboutFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_about:
                openAboutFragment();
                break;
            case R.id.action_exit:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }*/
}