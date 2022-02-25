package Week1to8;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/*
Week 8
You are provided with the chemical formula, return maximum number of atoms
represented in chemical formula.

 */

public class Week8 {
    public static String countOfAtoms(String formula) {
        Stack<Map<String,Integer>> stack= new Stack<>();
        Map<String,Integer> map= new HashMap<>();
        int i=0,n=formula.length();
        while(i<n){
            char c=formula.charAt(i);i++;
            if(c=='('){
                stack.push(map);
                map=new HashMap<>();
            }
            else if(c==')'){
                int val=0;
                while(i<n && Character.isDigit(formula.charAt(i)))
                    val=val*10+formula.charAt(i++)-'0';



                if(val==0) val=1;
                if(!stack.isEmpty()){
                    Map<String,Integer> temp= map;
                    map=stack.pop();
                    for(String key: temp.keySet())
                        map.put(key,map.getOrDefault(key,0)+temp.get(key)*val);
                }
            }
            else{
                int start=i-1;
                while(i<n && Character.isLowerCase(formula.charAt(i))){
                    i++;
                }
                String s= formula.substring(start,i);
                int val=0;
                while(i<n && Character.isDigit(formula.charAt(i))) val=val*10+ formula.charAt(i++)-'0';
                if(val==0) val=1;
                map.put(s,map.getOrDefault(s,0)+val);
            }
        }
        StringBuilder sb= new StringBuilder();
        List<String> list= new ArrayList<>(map.keySet());
        Collections.sort(list);
        for(String key: list){
            sb.append(key);
            if(map.get(key)>1) sb.append(map.get(key));
        }
        return sb.toString();
    }




    public static void main(String[] args) {
        String str = "Mg (OH)2";
        System.out.println(countOfAtoms(str));
        int n=str.length();

        char indexnum = str.charAt(n-1);
        char indexh = str.charAt(n-3);
        char indexo = str.charAt(n-4);


        System.out.println("the index of "+indexh + "*" + indexnum);
        System.out.println("the index of "+indexo + "*" + indexnum);



    }



}


