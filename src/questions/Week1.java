package questions;


import java.util.*;

public class Week1 {
    static void getExprUtil(List<String> res, String curExp,
                            String input, int target, int position,
                            int curVal, int last)
    {
        if (position == input.length())
        {
            if (curVal == target)
                res.add(curExp);
            return;
        }
        String part = input.substring(position, position + 1);

        int cur = Integer.parseInt(part);
        if (position == 0){
            getExprUtil(res, curExp + part, input,
                    target, position + 1, cur, cur);


        }
        else
        {
            getExprUtil(res, curExp + "+" + part, input,
                    target, position + 1, curVal + cur, cur);
            getExprUtil(res, curExp + "-" + part, input,
                    target, position + 1, curVal - cur, -cur);
            getExprUtil(res, curExp + "*" + part, input,
                    target, position + 1, curVal - last + last * cur,
                    last * cur);
        }
    }
    static List<String> getExprs(String input, int target)
    {
        List<String> res = new ArrayList<String>();
        getExprUtil(res, "", input, target, 0, 0, 0);
        return res;
    }
    static void printResult(List<String> res)
    {
        for (int i = 0; i < res.size(); i++)
            System.out.println(res.get(i) + " ");
        System.out.println();
    }
    public static void main(String[] args)
    {
        String input = "123";
        int target = 6;
        List<String> res = getExprs(input, target);
        printResult(res);

        input = "343";
        target = 15;
        res = getExprs(input, target);
        printResult(res);

        input = "3334";
        target = 18;
        res = getExprs(input, target);
        printResult(res);
    }
}