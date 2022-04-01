package com.ty.ty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class calaulator extends AppCompatActivity implements View.OnClickListener {

    TextView textViewInputNumbers;
    TextView outputNumbers ;

    Button btn_one ;
    Button btn_two;
    Button btn_three;
    Button btn_four;
    Button btn_five;
    Button btn_six;
    Button btn_seven;
    Button btn_eight;
    Button btn_nine;
    Button buttonClear;
    Button buttonleft;
    Button buttonPercent;
    Button buttonDivision;
    Button buttonMultiplication;
    Button buttonSubtraction;
    Button buttonAddition;
    Button buttonEqual;
    Button buttonDot;
    Button right;
    Button btn_zero;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table1);
        initialViwer();
        setonclicklisteners();



    }

    private void initialViwer (){
        outputNumbers = findViewById(R.id.outptu_numbers);
        textViewInputNumbers = findViewById(R.id.textView_input_numbers);
        btn_zero = findViewById(R.id.btn_zero);
        btn_one = findViewById(R.id.btn_one);
        btn_two = findViewById(R.id.btn_two);
        btn_three = findViewById(R.id.btn_three);
        btn_four = findViewById(R.id.btn_four);
        btn_five = findViewById(R.id.btn_five);
        btn_six = findViewById(R.id.btn_six);
        btn_seven = findViewById(R.id.btn_seven);
        btn_eight = findViewById(R.id.btn_eight);
        btn_nine = findViewById(R.id.btn_nine);
        buttonClear = findViewById(R.id.btn_c);
        buttonleft = findViewById(R.id.btn_left);
         buttonPercent = findViewById(R.id.btn_percent);
         buttonDivision = findViewById(R.id.btn_divide);
         buttonMultiplication = findViewById(R.id.btn_multiply);
         right = findViewById(R.id.btn_right);
         buttonSubtraction = findViewById(R.id.btn_substract);
         buttonAddition = findViewById(R.id.btn_add);
         buttonEqual = findViewById(R.id.btn_equal);
         buttonDot = findViewById(R.id.btn_point);
    }

    private  void setonclicklisteners (){

        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        btn_five.setOnClickListener(this);
        btn_six.setOnClickListener(this);
        btn_seven.setOnClickListener(this);
        btn_eight.setOnClickListener(this);
        btn_nine.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonAddition.setOnClickListener(this);
        buttonDivision.setOnClickListener(this);
        buttonSubtraction.setOnClickListener(this);
        buttonMultiplication.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
        buttonPercent.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonleft.setOnClickListener(this);
        right.setOnClickListener(this);
        btn_zero.setOnClickListener(this);



    }



    @Override
    public void onClick(View view) {





        if ( view.getId() != R.id.btn_equal&& view.getId() != R.id.btn_c){

           Button b = (Button) view;
         String bt =   b.getText().toString();
         textViewInputNumbers.setText(textViewInputNumbers.getText()+bt);

       }
       else if (view.getId() == R.id.btn_c){

           textViewInputNumbers.setText("");
           outputNumbers.setText("");
        }
       else if (view.getId() == R.id.btn_equal){


         String resultstring =  textViewInputNumbers.getText().toString();

        if (  (!resultstring.isEmpty() ) && isTrueExpression(resultstring)){

            List list = addToList(resultstring);
            List postExpression = getPostExpression(list);
            double result = getResultByUsePostExpression(postExpression);
            String strDouble =String.valueOf(result);
            outputNumbers.setText(strDouble);
        }

        else{
            Toast.makeText(getApplicationContext(), "算式不太对，再检查检查。", Toast.LENGTH_SHORT).show();


        }


       }


    }

    private   double getResultByUsePostExpression(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).matches("\\d+\\.{0,1}\\d*")) {
                stack.push(list.get(i));
            }
            else if (list.get(i).equals("%")){
                double num = Double.parseDouble(stack.pop());
                num= num / 100.00 ;
                 stack.push(""+ num);

            }
            else {
                double num1 = Double.parseDouble(stack.pop());
                double num2 = Double.parseDouble(stack.pop());
                double result = 0;
                switch (list.get(i)) {
                    case "+":
                        result = num2 + num1;
                        break;
                    case "-":
                        result = num2 - num1;
                        break;
                    case "*":
                        result = num2 * num1;
                        break;
                    case "/":
                        result = num2 / num1;
                        break;
                }
                stack.push("" + result);
            }
        }
        return Double.parseDouble(stack.pop());
    }

    private   List getPostExpression(List<String> list) {
        Stack<String> operators = new Stack<>();
        Stack<String> median = new Stack<>();
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            while (true) {
                if (list.get(i).matches("\\d+\\.{0,1}\\d*" )) {
                    median.push(list.get(i));
                    break;
                } else if (isOperator(list.get(i).charAt(0))||isPercent(list.get(i).charAt(0))) {
                    if (operators.empty() || operators.peek().equals("(")) {
                        operators.push(list.get(i));
                        break;
                    } else if (getPriority(list.get(i)) > getPriority(operators.peek())) {
                        operators.push(list.get(i));
                        break;
                    } else {
                        median.push(operators.pop());
                    }
                } else if (isBracket(list.get(i).charAt(0))) {
                    if (list.get(i).equals("(")) {
                        operators.push(list.get(i));
                        break;
                    } else {
                        while (!operators.empty() && !operators.peek().equals("(")) {
                            median.push(operators.pop());
                        }
                        if (!operators.empty()) {
                            operators.pop();
                        }
                        break;
                    }
                }
            }
        }
        while (!operators.empty()) {
            median.push(operators.pop());
        }
        while (!median.empty()) {
            list1.add(median.pop());
        }
        Collections.reverse(list1);
        return list1;
    }

    private  int getPriority(String str) {
        if (str.equals("+") || str.equals("-")) {
            return 1;
        } else if (str.equals("*") || str.equals("/")) {
            return 2;
        }
        else if (str.equals("%")){
            return 3;
        }else {
            return 0;
        }
    }

    private List addToList(String expression) {
        List<String> list = new ArrayList<>();
        int i = 0;
        do {
            char ch = expression.charAt(i);
            String str = "";

                if (isOperator(ch) || isBracket(ch)||isPercent(ch)) {
                    list.add(str += ch);
                    i++;
                } else if (isNumber(ch)) {
                    str += ch;
                    i++;
                    while (i < expression.length() && (isNumber(expression.charAt(i)) || isPoint(expression.charAt(i)))) {
                        str += expression.charAt(i);
                        i++;
                    }
                    list.add(str);
                }

        } while (i < expression.length());
        return list;
    }

    private boolean isTrueExpression(String expression) {
        // 如果一开始就是点(.)肯定就不是合法的表达式  合法的算术表达式含有数字、小数点、括号、运算符
        if ( isPercent(expression.charAt(0)) || isOperator(expression.charAt(0)) || isOperator(expression.charAt(expression.length() - 1)) || isPoint(expression.charAt(0)) || isPoint(expression.charAt(expression.length() - 1))) {
            return false;
        } else {

            // 控制括号成对
            Stack<Character> stack = new Stack<>();

            // 循环判断表达式的每一个字符是符合法
            int i = 0;
            while (i < expression.length()) {
                char ch = expression.charAt(i);




                    // 调用方法得到此字符的前/后一个的有效字符
                    int pre = getPreIndexOfNotBlankSpace(expression, i);
                    int next = getNextIndexOfNotBlankSpace(expression, i);

                    if (isPercent(ch)) {
                        if (isPercent(expression.charAt(pre))||  isPoint(expression.charAt(pre)) || isPoint(expression.charAt(next)) || isNumber(expression.charAt(next)) || (isOperator(expression.charAt(pre))) ) {
                            return false;
                        }
                    }

                    // 如果是左括号( 那么下一个有效字符必须是左括号或者数字
                    if (ch == '(') {
                        if ( isNumber(expression.charAt(pre)) || isPoint(expression.charAt(next)) || expression.charAt(next) == '*' || expression.charAt(next) == '/' || expression.charAt(next) == ')') {
                            return false;
                        }
                        // 入栈
                        stack.push(ch);
                    }
                    // 如果是) 如果栈空则说明不合法 否则弹出一个(   )后面只可以是运算符或者)
                    if (ch == ')') {
                        if (stack.empty() || (!isOperator(expression.charAt(next)) && expression.charAt(next) != ')')) {
                            return false;
                        } else {
                            stack.pop();
                        }
                    }



                    // 如果是运算符，前一个或后一个不能是小数点、操作符
                    if (isOperator(ch)) {
                        if (isPoint(expression.charAt(pre)) || isPoint(expression.charAt(next)) || expression.charAt(next) == ')' || isOperator(expression.charAt(next))) {
                            return false;
                        }
                    }

                if (ch == '/') {
                    if (expression.charAt(next) == '0') {
                        return false;
                    }
                }


                // 如果是小数点前后都必须是数字
                    if (isPoint(ch)) {
                        if (!isNumber(expression.charAt(pre)) || !isNumber(expression.charAt(next))) {
                            return false;
                        }
                    }
                    i++;

            }

            if (!stack.empty()){
                return false;
            }

            // 如果字符都合法最后判断括号是否成对
        }
        return true;
    }

    private  boolean isOperator(char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            return true;
        } else {
            return false;
        }
    }
    private boolean isPercent(char ch) {
        return ch == '%';
    }


    // 判断这个字符是不是小数点
    private boolean isPoint(char ch) {
        return ch == '.';
    }

    // 判断这个字符是不是括号
    private  boolean isBracket(char ch) {
        return ch == '(' || ch == ')';
    }
    private  boolean isNumber(char ch) {
        if (ch >= '0' && ch <= '9') {
            return true;
        } else {
            return false;
        }
    }

    // 获取后一个有效字符下标
    private int getPreIndexOfNotBlankSpace(String expression, int i) {
        // 如果下表是最后一个字符下标那么就返回最后一个字符下标
        if (i == 0) {
            return 0;
        }  else {
            i--;

            return i;
        }
    }

    // 获取前一个有效字符的下标
    private int getNextIndexOfNotBlankSpace(String expression, int i) {
        // 如果下标为0 那么前一个有效字符就是0
        if (i == expression.length() - 1) {
            return expression.length() - 1;
        }  else {
            i++;

            return i;
        }

    }

}
