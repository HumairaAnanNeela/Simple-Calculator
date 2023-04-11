package edu.ewubd.calculatoractivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {


    private TextView tv1, tv2;
    private Button btnDel, btnPoint, btn9, btn8, btn7, btn6, btn5, btn4, btn3, btn2, btn1, btn0, btnDiv, btnMul,
            btnSub, btnAdd, btnEqual, btnView, btnback, btnfront;
    private double n1, n2;
    private int length;
    private boolean add, sub, mul, div;
    String text;

    SharedPreferences sp;
    SharedPreferences.Editor spe;

    private List<String> expressions;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Shared Preferences create
        sp = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        // get pref-file editor for Write data
        spe = sp.edit();


        //Text View
        tv2 = findViewById(R.id.tv1);
        tv1 = findViewById(R.id.tv2);

        Button btnClr = findViewById(R.id.btnClr);
        Button btnDel = findViewById(R.id.btnDel);
        Button btnback = findViewById(R.id.btnback);
        Button btnfront = findViewById(R.id.btnfront);

        //operand buttons
        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnPoint = findViewById(R.id.btnPoint);

        //operator buttons
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSub = findViewById(R.id.btnSub);
        Button btnMulti = findViewById(R.id.btnMul);
        Button btnDiv = findViewById(R.id.btnDiv);
        Button btnEqual = findViewById(R.id.btnEqual);

        //button

        btnClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                tv2.setText(text);
                tv1.setText(text);
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tv2.getText().toString();
                if (text == null || text.isEmpty()) {
                    return;
                }
                tv2.setText(text.substring(0, text.length() - 1));
            }
        });


        //oparands
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tv2.getText().toString();

                tv2.setText(text + "0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tv2.getText().toString();

                tv2.setText(text + "1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tv2.getText().toString();

                tv2.setText(text + "2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tv2.getText().toString();


                tv2.setText(text + "3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tv2.getText().toString();

                tv2.setText(text + "4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tv2.getText().toString();

                tv2.setText(text + "5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tv2.getText().toString();

                tv2.setText(text + "6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tv2.getText().toString();

                tv2.setText(text + "7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tv2.getText().toString();

                tv2.setText(text + "8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tv2.getText().toString();

                tv2.setText(text + "9");
            }
        });
        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tv2.getText().toString();
                text = text + ".";
                tv2.setText(text);
            }
        });

        //operator
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text=tv2.getText().toString();
                tv2.setText(text+"+");


            }

        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text=tv2.getText().toString();
                tv2.setText(text+"*");

            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text=tv2.getText().toString();
                tv2.setText(text+"/");

            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=tv2.getText().toString();
                tv2.setText(text+"-");

            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = tv2.getText().toString();
                double result = evaluate(text);
                tv1.setText(String.valueOf(result));

                // Save expression to SharedPreferences
                saveExpression(text);

                // Clear expression string
                text = "";


            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (expressions == null) {
                    expressions = loadExpressions();
                    currentIndex = expressions.size() - 1;
                }
                if (currentIndex > 0) {
                    currentIndex--;
                    tv2.setText(expressions.get(currentIndex));
                } else {
                    tv2.setText("");
                }


            }
        });

        btnfront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (expressions == null) {
                    expressions = loadExpressions();
                    currentIndex = expressions.size() - 1;
                }
                if (currentIndex > 0) {
                    currentIndex--;
                    tv2.setText(expressions.get(currentIndex));
                } else {
                    tv2.setText("");
                }


            }
        });


    }

    public double evaluate(String text) {
        char prevChar = ' ';
        Stack<Double> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isDigit(c)) {
                // Parse the number
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while (i + 1 < text.length() && Character.isDigit(text.charAt(i + 1))) {
                    sb.append(text.charAt(i + 1));
                    i++;
                }
                double num = Double.parseDouble(sb.toString());
                operandStack.push(num);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                // Handle operators
                if (prevChar == '+' || prevChar == '-' || prevChar == '*' || prevChar == '/') {
                    // Multiple operators in a row, handle as an error
                    Toast.makeText(getApplicationContext(),"Entered multiple operator in a row!",Toast.LENGTH_SHORT).show();

                }
                while (!operatorStack.isEmpty() && hasPrecedence(c, operatorStack.peek())) {
                    char op = operatorStack.pop();
                    double b = operandStack.pop();
                    double a = operandStack.pop();
                    operandStack.push(applyOperator(a, b, op));
                }
                operatorStack.push(c);
            } else if (c == '(') {

                operatorStack.push(c);
            } else if (c == ')') {
                while (operatorStack.peek() != '(') {
                    char op = operatorStack.pop();
                    double b = operandStack.pop();
                    double a = operandStack.pop();
                    operandStack.push(applyOperator(a, b, op));
                }
                operatorStack.pop();
            } else {
                Toast.makeText(getApplicationContext(),"Invalid Characters" + c,Toast.LENGTH_SHORT).show();

            }
            prevChar = c;
        }

// Evaluate any remaining operators and operands
        while (!operatorStack.isEmpty()) {
            char op = operatorStack.pop();
            double b = operandStack.pop();
            double a = operandStack.pop();
            operandStack.push(applyOperator(a, b, op));
        }

        return  operandStack.pop();
    }



    //check whether the digit or operator is the last character
    public boolean checkOperator(char ch) {
        if (ch == '+' || ch == '-' || ch == 'x' || ch == '÷') {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    private static double applyOperator(double a, double b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
        }
        throw new IllegalArgumentException("Invalid operator");
    }


    private void saveExpression(String expression) {

        // Get the current count of saved expressions
        int count = sp.getInt("count", 0);

        if (count >= 10) {
            // Remove the oldest expression by shifting all expressions to the left
            for (int i = 1; i < 10; i++) {
                String prevKey = "expression_" + (i - 1);
                String currKey = "expression_" + i;
                String value = sp.getString(currKey, "");
                spe.putString(prevKey, value);
            }
            // Add the new expression at the end
            String lastKey = "expression_9";
            spe.putString(lastKey, expression);
        } else {
            // Add the new expression at the end
            String key = "expression_" + count;
            spe.putString(key, expression);
            spe.putInt("count", count + 1);
        }

        spe.apply();


        int cnt = sp.getInt("count", 0);

// Loop through the saved expressions and print them to the console
        for (int i = 0; i < cnt; i++) {
            String key = "expression_" + i;
            String txt = sp.getString(key, "");
            Log.d("SavedExpressions", key + ": " + txt);
        }
    }

    // Load expressions from SharedPreferences and return them as a List<String>
    private List<String> loadExpressions() {
        int count = sp.getInt("count", 0);
        List<String> expressions = new ArrayList<>();
        for (int i = count - 1; i >= 0; i--) {
            String expression = sp.getString("expression_" + i, "");
            if (!expression.isEmpty()) {
                expressions.add(expression);
            }
        }
        return expressions;
    }

}