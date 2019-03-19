package com.qbw.actionmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ActionManager.Listener {

    private int mNum;
    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTv = findViewById(R.id.tv_txt);
        findViewById(R.id.tvadd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActionManager.getInstance().triggerAction(new Model.ActionAdd());
            }
        });
        findViewById(R.id.tvminus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActionManager.getInstance().triggerAction(new Model.ActionMinus());
            }
        });
        ActionManager.getInstance().addInterestedAction(this, Model.ActionAdd.class);
        ActionManager.getInstance()
                     .addInterestedActions(this, new Class[]{Model.ActionMinus.class});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActionTriggered(Object action) {
        if (action instanceof Model.ActionAdd) {
            mNum++;
            mTv.setText("" + mNum);
        } else if (action instanceof Model.ActionMinus) {
            mNum--;
            mTv.setText("" + mNum);
        }
    }

    @Override
    protected void onDestroy() {
        ActionManager.getInstance().removeInterestedActions(this);
        super.onDestroy();
    }
}
