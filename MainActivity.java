package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    private TextView txtview1,txtfinal;
    private Button btnbirthdate,btnsubmit,btnreset,btnexit;
    private int year, month, day;
    private Spinner spinner,spinner2,spinner3;
    private RadioGroup radioGroup1, radioGroup2;
    private RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButton5;
    private EditText editText1,editText2,editText3,editText4,checkEditText;
    private LinearLayout linearLayout1;
    private CheckBox checkBox;
    private boolean txtfinalAdded=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkEditText = new EditText(this);
        checkEditText.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        checkEditText.setBackgroundColor(Color.parseColor("#C7CFEA"));
        checkEditText.setTextColor(Color.parseColor("#7A78B8"));
        checkEditText.setPadding(10,10,10,10);


        txtfinal = new TextView(this);
        txtfinal.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        txtfinal.setPadding(10,10,10,10);
        txtfinal.setTextSize(20);
        txtfinal.setBackgroundColor(Color.parseColor("#E2DFED"));

        txtview1= findViewById(R.id.txtview1);

        btnbirthdate= findViewById(R.id.btnbirthdate);
        btnexit= findViewById(R.id.btnexit);
        btnreset= findViewById(R.id.btnreset);
        btnsubmit= findViewById(R.id.btnsubmit);

        radioButton1= findViewById(R.id.rdbtn1);
        radioButton2= findViewById(R.id.rdbtn2);
        radioButton3= findViewById(R.id.rdbtn3);
        radioButton4= findViewById(R.id.rdbtn4);
        radioButton5= findViewById(R.id.rdbtn5);

        editText1= findViewById(R.id.edittxt1);
        editText2= findViewById(R.id.edittxt2);
        editText3= findViewById(R.id.edittxt3);
        editText4= findViewById(R.id.edittxt4);

        radioGroup1 = findViewById(R.id.rdgrp1);
        radioGroup2 = findViewById(R.id.rdgrp2);

        checkBox= findViewById(R.id.chckbx);

        linearLayout1= findViewById(R.id.linearlyt);

        spinner= findViewById(R.id.spinneril);
        spinner2= findViewById(R.id.spinnerfac);
        spinner3= findViewById(R.id.spinnerdep);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.faculties, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.iller, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);

        btnbirthdate.setOnClickListener(this);
        btnexit.setOnClickListener(this);
        btnsubmit.setOnClickListener(this);
        btnreset.setOnClickListener(this);

        spinner2.setOnItemSelectedListener(this);

        checkBox.setOnCheckedChangeListener(this);

    }


    @Override
    public void onClick(View view) {
        if(view.getId()==btnbirthdate.getId()){
            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(MainActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            String date = day + "/" + (month+1) + "/" + year;
                            txtview1.setText(date);
                        }
                    }, year, month, day);
            datePickerDialog.show();
        }

        if(view.getId()==btnexit.getId()){
            finishAffinity();
        }
        if(view.getId()==btnreset.getId()){
            radioGroup1.clearCheck();
            radioGroup2.clearCheck();
            checkBox.setEnabled(true);
            if(checkBox.isChecked()){
                checkBox.toggle();
            }
            editText1.setText("");
            editText2.setText("");
            editText3.setText("");
            editText4.setText("");
            txtview1.setText("");
            spinner3.setAdapter(null);
            spinner.setSelection(0);
            spinner2.setSelection(0);
            linearLayout1.removeView(txtfinal);
        }

        if(view.getId()==btnsubmit.getId()){
            if(!txtfinalAdded){
                if(editText1.getText().toString().equals("")||
                        editText2.getText().toString().equals("")||
                        editText3.getText().toString().equals("")||
                        editText4.getText().toString().equals("")||
                        txtview1.getText().toString().equals("")||
                        (!radioButton1.isChecked()&&!radioButton2.isChecked())||
                        (!radioButton3.isChecked()&&!radioButton4.isChecked()&&!radioButton5.isChecked())){
                    Toast.makeText(this, "Fill all fields before submitting! ", Toast.LENGTH_SHORT).show();
                }else if(editText1.getText().toString().length()!=11){
                    Toast.makeText(this, "Student Id must be 11 digits! ", Toast.LENGTH_SHORT).show();
                }else if(Double.parseDouble(editText4.getText().toString())>4||Double.parseDouble(editText4.getText().toString())<0){
                    Toast.makeText(this, "Enter a valid gpa!", Toast.LENGTH_SHORT).show();
                }else{
                    linearLayout1.addView(txtfinal);
                    txtfinalAdded = true;

                    SpannableString ID = new SpannableString("ID: ");
                    SpannableString Name = new SpannableString("Name: ");
                    SpannableString LastName = new SpannableString("Last Name: ");
                    SpannableString BirthDate = new SpannableString("Birth Name: ");
                    SpannableString BirthPlace = new SpannableString("Birthplace: ");
                    SpannableString Gender = new SpannableString("Gender: ");
                    SpannableString Faculty = new SpannableString("Faculty: ");
                    SpannableString Department = new SpannableString("Department: ");
                    SpannableString Gpa = new SpannableString("Gpa: ");
                    SpannableString Scholarship = new SpannableString("Scholarship: ");

                    Spannable id = new SpannableString(editText1.getText().toString());
                    Spannable name = new SpannableString(editText2.getText().toString());
                    Spannable lastName = new SpannableString(editText3.getText().toString());
                    Spannable birthDate = new SpannableString(txtview1.getText().toString());
                    Spannable birthPlace = new SpannableString(spinner.getSelectedItem().toString());
                    Spannable gender = new SpannableString(((RadioButton) findViewById(radioGroup1.getCheckedRadioButtonId())).getText().toString());
                    Spannable faculty = new SpannableString(spinner2.getSelectedItem().toString());
                    SpannableString department= new SpannableString(spinner3.getSelectedItem().toString());
                    SpannableString gpa= new SpannableString(editText4.getText().toString());
                    SpannableString scholarship= new SpannableString(((RadioButton) findViewById(radioGroup2.getCheckedRadioButtonId())).getText().toString());

                    ID.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,ID.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Name.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,Name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    LastName.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,LastName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    BirthDate.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,BirthDate.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    BirthPlace.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,BirthPlace.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Gender.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,Gender.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Faculty.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,Faculty.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Department.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,Department.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Gpa.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,Gpa.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Scholarship.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,Scholarship.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    id.setSpan(new ForegroundColorSpan(Color.RED),0,id.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    name.setSpan(new ForegroundColorSpan(Color.RED),0,name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lastName.setSpan(new ForegroundColorSpan(Color.RED),0,lastName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    birthDate.setSpan(new ForegroundColorSpan(Color.RED),0,birthDate.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    birthPlace.setSpan(new ForegroundColorSpan(Color.RED),0,birthPlace.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    gender.setSpan(new ForegroundColorSpan(Color.RED),0,gender.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    faculty.setSpan(new ForegroundColorSpan(Color.RED),0,faculty.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    department.setSpan(new ForegroundColorSpan(Color.RED),0,department.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    gpa.setSpan(new ForegroundColorSpan(Color.RED),0,gpa.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    scholarship.setSpan(new ForegroundColorSpan(Color.RED),0,scholarship.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    if(checkBox.isChecked()){
                        if(checkEditText.getText().toString().equals("")){
                            Toast.makeText(this, "Please enter additional info!", Toast.LENGTH_SHORT).show();
                        }else{
                            SpannableString AddInfo= new SpannableString("Additional Info: ");
                            SpannableString addInfo= new SpannableString(checkEditText.getText().toString());
                            AddInfo.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,AddInfo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            addInfo.setSpan(new ForegroundColorSpan(Color.RED),0,addInfo.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                            checkBox.setEnabled(false);
                            txtfinal.setText("");
                            txtfinal.append(ID);
                            txtfinal.append(id);
                            txtfinal.append("\n");
                            txtfinal.append(Name);
                            txtfinal.append(name);
                            txtfinal.append("\n");
                            txtfinal.append(LastName);
                            txtfinal.append(lastName);
                            txtfinal.append("\n");
                            txtfinal.append(BirthDate);
                            txtfinal.append(birthDate);
                            txtfinal.append("\n");
                            txtfinal.append(BirthPlace);
                            txtfinal.append(birthPlace);
                            txtfinal.append("\n");
                            txtfinal.append(Gender);
                            txtfinal.append(gender);
                            txtfinal.append("\n");
                            txtfinal.append(Faculty);
                            txtfinal.append(faculty);
                            txtfinal.append("\n");
                            txtfinal.append(Department);
                            txtfinal.append(department);
                            txtfinal.append("\n");
                            txtfinal.append(Gpa);
                            txtfinal.append(gpa);
                            txtfinal.append("\n");
                            txtfinal.append(Scholarship);
                            txtfinal.append(scholarship);
                            txtfinal.append("\n");
                            txtfinal.append(AddInfo);
                            txtfinal.append(addInfo);

                        }
                    }else{
                        checkBox.setEnabled(false);
                        txtfinal.setText("");
                        txtfinal.append(ID);
                        txtfinal.append(id);
                        txtfinal.append("\n");
                        txtfinal.append(Name);
                        txtfinal.append(name);
                        txtfinal.append("\n");
                        txtfinal.append(LastName);
                        txtfinal.append(lastName);
                        txtfinal.append("\n");
                        txtfinal.append(BirthDate);
                        txtfinal.append(birthDate);
                        txtfinal.append("\n");
                        txtfinal.append(BirthPlace);
                        txtfinal.append(birthPlace);
                        txtfinal.append("\n");
                        txtfinal.append(Gender);
                        txtfinal.append(gender);
                        txtfinal.append("\n");
                        txtfinal.append(Faculty);
                        txtfinal.append(faculty);
                        txtfinal.append("\n");
                        txtfinal.append(Department);
                        txtfinal.append(department);
                        txtfinal.append("\n");
                        txtfinal.append(Gpa);
                        txtfinal.append(gpa);
                        txtfinal.append("\n");
                        txtfinal.append(Scholarship);
                        txtfinal.append(scholarship);
                        txtfinal.append("\n");
                    }
                }
            }else{
                Toast.makeText(this, "Reset before submitting again!", Toast.LENGTH_SHORT).show();
            }

        }

    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        if(spinner2.getSelectedItem().equals("Law")){
            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Law, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner3.setAdapter(adapter3);
        }
        if(spinner2.getSelectedItem().equals("Engineering and Natural Sciences")){
            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Engineer, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner3.setAdapter(adapter3);
        }
        if(spinner2.getSelectedItem().equals("Applied Sciences")){
            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.applied, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner3.setAdapter(adapter3);
        }
        if(spinner2.getSelectedItem().equals("Architecture")){
            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Architecture, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner3.setAdapter(adapter3);
        }
        if(spinner2.getSelectedItem().equals("Business")){
            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Business, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner3.setAdapter(adapter3);
        }
        if(spinner2.getSelectedItem().equals("Communication")){
            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Communication, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner3.setAdapter(adapter3);
        }
        if(spinner2.getSelectedItem().equals("Health Sciences")){
            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Health, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner3.setAdapter(adapter3);
        }
        if(spinner2.getSelectedItem().equals("Social Sciences")){
            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Social, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner3.setAdapter(adapter3);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(checkBox.isChecked()){
            linearLayout1.addView(checkEditText);
        }else{
            linearLayout1.removeView(checkEditText);
        }

    }
}