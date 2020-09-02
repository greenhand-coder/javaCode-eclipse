package homework3;

import java.util.Scanner;
import java.util.Stack;

public class EvalueateExpression {

    private Stack<Character> cStack = new Stack<Character>();
    private Stack<Integer> iStack = new Stack<Integer>();

    // 符号等级
    static int cLevel(char c) {
        switch (c) {
        case '(':
            return 0;
        case '+':
            return 1;
        case '-':
            return 1;
        case '*':
            return 2;
        case '/':
            return 2;
        case ')' :
        	return 0;
        }
        return 0;
    }

    // 对栈进行运算
    private void dealStack() {
        char c = cStack.pop();
        int num1 = iStack.pop();
        int num2 = iStack.pop();
        switch (c) {
        case '+':
            iStack.push(num1 + num2);
            break;
        case '-':
            iStack.push(num1 - num2);
            break;
        case '*':
            iStack.push(num1 * num2);
            break;
        case '/':
            iStack.push(num2 / num1);
            break;
        }
    }

    // 返回str的表达式的值
    public int calc(char[] exp) {
        while (!cStack.isEmpty()) {
            cStack.pop();
        }
        while (!iStack.isEmpty()) {
            iStack.pop();
        }
        cStack.push('(');

        for (int i = 0; i < exp.length; i++) {
            if (exp[i] == ' ') {
                continue;
            } 
            else if (exp[i] > '0' && exp[i] <= '9') {
                int num = exp[i] - '0';
                if (exp[i + 1] >= '0' && exp[i + 1] <= '9') {
                    
                    num = num * 10 + exp[i+1] - '0' ;
                    i++;
                }
                iStack.push(num);
            }
            else if (exp[i] == '(') {
                cStack.push(exp[i]);
            } 
            else if (exp[i] == '+' || exp[i] == '-' || exp[i] == '*' || exp[i] == '/') {
                while (cLevel(exp[i]) < cLevel(cStack.peek())) {
                    dealStack();
                }
                cStack.push(exp[i]);
            }
            /*   static int cLevel(char c) {
        switch (c) {
        case '(':
            return 0;
        case '+':
            return 1;
        case '-':
            return 1;
        case '*':
            return 2;
        case '/':
            return 2;
        case ')' :
        	return 0;
        }
        return 0;
    }*/
            else if (exp[i] == ')') {
                while (!cStack.peek().equals('(')) {
                    dealStack();
                }
                cStack.pop();
            }
        }
        return iStack.pop();
    }

  
}