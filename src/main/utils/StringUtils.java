package main.utils;

/**
 * Created by 1 on 10/9/2015.
 */
public class StringUtils {
    public static boolean checkForNumbers(String name) {

        //checks if there are any numbers in the string
        //returns true if it finds any numbers
        //returns false if not

        for (int i = 0; i < name.length(); i++){
            char c = name.charAt(i);

            if (Character.isDigit(c)){
                return true;
            }
        }

        return false;
    }
}
