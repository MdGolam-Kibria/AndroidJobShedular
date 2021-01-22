package com.example.androidjobshedular;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    public static PriorityQueue<String> priorityQueue;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static String[] arr = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "W", "x", "y", "z"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priorityQueue = new PriorityQueue<>();

    }

    public void start(View view) {
        /*PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add("1 a");
        queue.add("2 a");
        queue.add("3 a");

        String val = queue.remove();

        Log.d("TAG",val);
        String val1 = queue.remove();

        Log.d("TAG",val1);
        String val2 = queue.remove();

        Log.d("TAG",val2);*/

//        String val3 = queue.remove();
//
//        Log.d("TAG",val3);


        scheduleJob();
    }
     private void scheduleJob() {

        String s2 = "MAKING OF A NATION- BANGLADESH রচনা করেছেন নুরুল ইসলাম।";







        String[] s3 = s2.split("\\s");



        for (int i = 0; i < s3.length; i++) {
            priorityQueue.add(s3[i]);
        }

        for (int i = 0; i < priorityQueue.size(); i++) {
            String val = priorityQueue.remove();
            Log.d("VAL",val);
        }



        final JobScheduler jobScheduler = (JobScheduler) getSystemService(
                Context.JOB_SCHEDULER_SERVICE);

        // The JobService that we want to run
        final ComponentName name = new ComponentName(this, ExampleJobService.class);

        // Schedule the job
        final int result = jobScheduler.schedule(getJobInfo(123, 1, name));

        // If successfully scheduled, log this thing
        if (result == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Scheduled job successfully!");
        }
    }

    private JobInfo getJobInfo(final int id, final long hour, final ComponentName name) {
        //  final long interval = TimeUnit.HOURS.toMillis(hour); // run every hour
        final boolean isPersistent = true; // persist through boot
        final int networkType = JobInfo.NETWORK_TYPE_ANY; // Requires some sort of connectivity

        final JobInfo jobInfo;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            jobInfo = new JobInfo.Builder(id, name)
                    .setMinimumLatency(1000)
                    .setRequiredNetworkType(networkType)
                    .setPersisted(isPersistent)
                    .build();
        } else {
            jobInfo = new JobInfo.Builder(id, name)
                    .setPeriodic(1000)
                    .setRequiredNetworkType(networkType)
                    .setPersisted(isPersistent)
                    .build();
        }

        return jobInfo;
    }
}