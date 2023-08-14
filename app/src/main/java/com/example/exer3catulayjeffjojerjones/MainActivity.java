package com.example.exer3catulayjeffjojerjones;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public TextView output;
    public EditText input, inputStudentNum;
    public Button add, show, del, clear, confirm;
    ArrayList<Integer> grades = new ArrayList<Integer>();
    int studentCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.outputNum);
        input = findViewById(R.id.inputNum);
        add = findViewById(R.id.addBTN);
        show = findViewById(R.id.showBTN);
        del = findViewById(R.id.delBTN);
        clear = findViewById(R.id.clearBTN);

        output.setMovementMethod(new ScrollingMovementMethod());

        show.setEnabled(false);
        del.setEnabled(false);
        clear.setEnabled(false);

        Toast.makeText(getApplicationContext(),
                "Enter Student Grade simultaneously",
                Toast.LENGTH_LONG).show();

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (output.getText().toString() == "Please Enter a Value"){
                    output.setText("");
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    float inputted = Float.parseFloat(input.getText().toString());
                    int round = Math.round(inputted);
                    int check = round;
                    if ((check < 50) || (check > 100)){
                        grades.add(Integer.parseInt(input.getText().toString()));
                        formatGradeValueDisplay();
                        input.setText("");
                        grades.remove(grades.size() - 1);
                        formatGradeValueDisplay();
                        Toast.makeText(getApplicationContext(),
                                "Invalid Grade",
                                Toast.LENGTH_LONG).show();
                    }
                    else {
                        int count;
                        //grades.add(Integer.parseInt(input.getText().toString()));
                        grades.add(check);
                        formatGradeValueDisplay();
                        input.setText("");
                        show.setEnabled(true);
                        del.setEnabled(true);
                    }
                }catch (Exception exception){
                    output.setText("Please Enter a Value");
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (grades != null){
                    grades.forEach(item -> analyze(item));
                    studentCount = 1;
                    show.setEnabled(false);
                    del.setEnabled(false);
                    clear.setEnabled(true);
                }
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (grades.size()> 0){
                        grades.remove(grades.size() - 1);
                        formatGradeValueDisplay();
                    }
                }catch (Exception exception){
                    output.setText("Nothing to Delete");
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText("");
                grades.clear();
                add.setEnabled(true);
                clear.setEnabled(false);
            }
        });
    }

    private void formatGradeValueDisplay(){
        String formatedValue = grades.toString().replaceAll("[\\[\\]]", "");
        String initialOutput = formatedValue + "\n\n";
        output.setText(initialOutput);
        Toast.makeText(getApplicationContext(),
                initialOutput,
                Toast.LENGTH_LONG).show();
    }

    private void analyze(int gradeValue){
        if ((gradeValue >= 97) && (gradeValue <= 100)){
            output.append("\nStudent #" + studentCount + " \nGrade: " + gradeValue + " \nEquivalent: 1.00 \nRemarks: Highly Excellent" + "\n");
            studentCount++;
        }
        else if ((gradeValue >= 94) && (gradeValue <= 96)){
            output.append("\nStudent #" + studentCount + " \nGrade: " + gradeValue + " \nEquivalent: 1.25 \nRemarks: Excellent" + "\n");
            studentCount++;
        }
        else if ((gradeValue >= 91) && (gradeValue <= 93)){
            output.append("\nStudent #" + studentCount + " \nGrade: " + gradeValue + " \nEquivalent: 1.50 \nRemarks: Very Superior" + "\n");
            studentCount++;
        }
        else if ((gradeValue >= 88) && (gradeValue <= 90)){
            output.append("\nStudent #" + studentCount + " \nGrade: " + gradeValue + " \nEquivalent: 1.75 \nRemarks: Superior" + "\n");
            studentCount++;
        }
        else if ((gradeValue >= 85) && (gradeValue <= 87)){
            output.append("\nStudent #" + studentCount + " \nGrade: " + gradeValue + " \nEquivalent: 2.00 \nRemarks: Very Good" + "\n");
            studentCount++;
        }
        else if ((gradeValue >= 82) && (gradeValue <= 84)){
            output.append("\nStudent #" + studentCount + " \nGrade: " + gradeValue + " \nEquivalent: 2.25 \nRemarks: Good" + "\n");
            studentCount++;
        }
        else if ((gradeValue >= 79) && (gradeValue <= 81)){
            output.append("\nStudent #" + studentCount + " \nGrade: " + gradeValue + " \nEquivalent: 2.50 \nRemarks: Satisfactory" + "\n");
            studentCount++;
        }
        else if ((gradeValue >= 76) && (gradeValue <= 78)){
            output.append("\nStudent #" + studentCount + " \nGrade: " + gradeValue + " \nEquivalent: 2.75 \nRemarks: Fair" + "\n");
            studentCount++;
        }
        else if (gradeValue == 75){
            output.append("\nStudent #" + studentCount + " \nGrade: " + gradeValue +" \nEquivalent: 3.00 \nRemarks: Passed" + "\n");
            studentCount++;
        }
        else if (gradeValue < 75){
            output.append("\nStudent #" + studentCount + " \nGrade: " + gradeValue + " \nEquivalent: Failed" + "\n");
            studentCount++;
        }
        else {
            output.append("\nStudent #" + studentCount + " \nGrade: " + gradeValue + " \nEquivalent: Invalid Grade" + "\n");
            studentCount++;
        }
    }
}