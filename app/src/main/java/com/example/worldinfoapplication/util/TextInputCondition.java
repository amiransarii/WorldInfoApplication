package com.example.worldinfoapplication.util;

import android.content.Context;
import android.util.Patterns;

/**
 * check the condition of edit input like
 * email checking and password length
 */
public class TextInputCondition {

    //check email is valid or not
     public static  boolean isValidEmailInput(String email){
          if(AppStringUtils.isEmpty(email)){
              return false;
          }
          return Patterns.EMAIL_ADDRESS.matcher(email).matches();
     }


}
