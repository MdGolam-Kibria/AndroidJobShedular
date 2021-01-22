package com.example.androidjobshedular;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.HandlerThread;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Date;

public class ExampleJobService extends JobService {
    private static final String TAG = ExampleJobService.class.getSimpleName();
    TextToSpeech textToSpeech;
    TextToSpeech textToSpeechBan;


    @Override
    public boolean onStartJob(final JobParameters params) {

        HandlerThread handlerThread = new HandlerThread("SomeOtherThread");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

//                textToSpeechBan = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
//                    @Override
//                    public void onInit(int i) {
//                        textToSpeechBan.setLanguage(Locale.forLanguageTag("bn_BD"));
//                    }
//                });
                /*PriorityQueue<String> priorityQueue = new PriorityQueue<>();
                priorityQueue.add("Bangla");
                priorityQueue.add("English");*/


                Date date = new Date();
                Toast.makeText(getApplicationContext(), "Check" + date, Toast.LENGTH_SHORT).show();
                Log.d(TAG, String.valueOf(date));
                if (MainActivity.priorityQueue.size() == 0) {
                    return;
                }

                //for (int i = 0; i < MainActivity.priorityQueue.size(); i++) {

                String removeName = MainActivity.priorityQueue.remove();
/*

                    if (isEnglish(removeName)) {

                        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                textToSpeech.setLanguage(Locale.ENGLISH);
                            }
                        });
                    } else {
                        textToSpeechBan = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                textToSpeechBan.setLanguage(Locale.forLanguageTag("bn_BD"));
                                textToSpeechBan.speak(removeName, TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });

                    }
*/


                Toast.makeText(getApplicationContext(), "" + removeName, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "WOW", Toast.LENGTH_SHORT).show();
                Log.e(TAG, removeName);
                  /*  try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                //}

                // }
                Log.e(TAG, "Running!!!!!!!!!!!!!");
                jobFinished(params, true);
            }
        });

        return true;
    }

    @Override
    public boolean onStopJob(final JobParameters params) {
        Log.d(TAG, "onStopJob() was called");
        return true;
    }


    private static boolean isEnglish(String currentString) {
        for (int i1 = 0; i1 < MainActivity.arr.length; i1++) {
            String currentLatter = MainActivity.arr[i1];
            String firstLatter = getFirstLetterFromEachWordInSentence(currentString);
            if (currentLatter.equals(firstLatter) || currentLatter.toUpperCase().equals(firstLatter) || currentLatter.toLowerCase().equals(firstLatter)) {
                return true;
            }
        }
        return false;
    }


    public static String getFirstLetterFromEachWordInSentence(final String string) {
        if (string == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Arrays.asList(string.split(" ")).size(); i++) {
            sb.append(Arrays.asList(string.split(" ")).get(i).charAt(0)).append(" ");
        }
        return sb.toString().trim();
    }

}
