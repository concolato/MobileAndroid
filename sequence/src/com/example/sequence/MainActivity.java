package com.example.sequence;

import android.annotation.SuppressLint;
import android.app.Activity;
//import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;
//import android.util.Log;
import android.content.Context;

public class MainActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        final EditText intText = (EditText) findViewById(R.id.number_edittext);
        final Button sortButton = (Button) findViewById(R.id.sort_button);

        sortButton.setOnClickListener(new View.OnClickListener() {
            //@TargetApi(Build.VERSION_CODES.GINGERBREAD)

            @SuppressLint("NewApi")
			public void onClick(View v) {
                String numbersEntered = "";
                int[] NumArray = new int[0];
                //String TAG = "MyActivity";

                Context context = getApplicationContext();
                numbersEntered = intText.getText().toString();

                if (numbersEntered != null && !numbersEntered.isEmpty()) {
                    String[] vals = numbersEntered.split(" ");
                    NumArray = new int[vals.length];

                    //Log.v(TAG, numbersEntered);
                    for (int i = 0; i < vals.length; i++) {
                        NumArray[i] = Integer.parseInt(vals[i]);
                    }

                    displaySortedNumbers(context, NumArray);
                } else {
                    intText.setText("");
                    errorMsg(context);
                }
            }
        });
    }

    public static void displaySortedNumbers(Context context, int[] unSortedNums){
        int[] sortedArray;
        sortedArray = new int[]{};

        try{
            //Sort and convert array back into string
            sortedArray = bubbleSort(unSortedNums);
            String sortedString = Arrays.toString(sortedArray);
            // Display
            Toast.makeText(context, sortedString, Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context, "Sorting Failed: "+e, Toast.LENGTH_SHORT).show();
        }
    }

    public static void errorMsg(Context context){
        String empty = "Please enter numbers.";
        Toast.makeText(context, empty, Toast.LENGTH_LONG).show();
    }

    //Return sorted array
    private static int[] bubbleSort(int[] integerArray){
        int num = integerArray.length;
        int holder = 0;

        for(int i=0; i < num; i++){
            for(int j=1; j < (num-i); j++){

                if(integerArray[j-1] > integerArray[j]){
                    //swap the elements and begin to sort!
                    holder = integerArray[j-1];
                    integerArray[j-1] = integerArray[j];
                    integerArray[j] = holder;
                }

            }
        }//end for loop

        return integerArray;
    }
}
