package com.example.recyclerview;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private final LinkedList<String > mWordList = new LinkedList<>();
    private int mCount = 0;
    private RecyclerView mRecycleView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0; i<20; i++)
        {
            mWordList.addLast("Word" + mCount++);
            Log.d("WordList", mWordList.getLast());
        }
        mRecycleView = (RecyclerView) findViewById(R.id.recycleView);
        mAdapter = new WordListAdapter(this, mWordList);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        int wordListSize = mWordList.size();
                        // dodaje nowe słowo
                        mWordList.addLast("Word " +wordListSize);
                        //info do adaptera, że zaszła zmiana w danych
                        mRecycleView.getAdapter().notifyItemInserted(wordListSize);
                        //scroll do miejsca dodania
                        mRecycleView.smoothScrollToPosition(wordListSize);
                    }
                });
    }


}
