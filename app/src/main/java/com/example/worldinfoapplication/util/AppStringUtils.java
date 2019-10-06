package com.example.worldinfoapplication.util;

import android.text.Html;

import java.io.Serializable;

/**
 * <code>AppsStringUtils</code> utility class of [Character-string] relationship.
 * Contains method to manipulate strings. Provides following functionality :
 * <ol>Do the padding</ol>
 * <ol>Set the color to the character of the specified location</ol>
 * <ol>Cut in the number of characters that is specified a string</ol>
 * <ol>String is determined whether or not "0"</ol>
 *
 * @author Amir Ansari
 */

public class AppStringUtils implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID=1L;

    /**
     * Equal Sign
     */
    public static final String EQUAL="=";

    /**
     * Empty
     */
    public static final String EMPTY="";

    /**
     * DOT
     */
    public static final String DOT=".";

    /**
     * Comma
     */
    public static final String COMMA=",";

    /**
     * Colon
     */
    public static final String COLON=":";

    /**
     * Sign(+)
     */
    public static final String PLUS="+";

    /**
     * Sign(-)
     */
    public static final String MINUS="-";

    /**
     * Sign(?)
     */
    public static final String QUESTIONMARK="?";

    /**
     * Sign(&)
     */
    public static final String AMP="&";

    /**
     * Sign(/)
     */
    public static final String SLASH="/";

    /**
     * 0
     */
    public static final String ZERO=String.valueOf(0);

    /**
     * 1
     */
    public static final String ONE=String.valueOf(1);

    /**
     * Line Feed(new Line)
     */
    public static final String NEWLINE="\n";

    /**
     * Tick-Up
     */
    public static final String TICK_UP = "▲";

    /**
     * TICK-DOWN
     */
    public static final String TICK_DOWN="▼";

    /**
     * Protocol separator
     */
    public static final String PROTOCOL_SEPARATOR="://";

    /**
     * O
     */
    public static final String CIRCLE="o";

    /**
     * Private default static Constrictor
     */
    private AppStringUtils(){

    }

    /**
     * Inspect whether search target is [null or empty]
     * @param str string
     *  return if it [null or empty],true
     */
    public static boolean isEmpty(String str){
        return str==null || str.length()==0;
    }

    /**
     * Inspect to verify or not the [null or empty]
     * @param str string
     * return if it is not the [null or empty]
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    /**
     * <p>Checks if a string is a whitespace,empty(") or null</p>
     * StringUtils.isBlank(null)=true
     * StringUtils.isBlank("")=true
     * StringUtils.isBlank("")=true
     * StringUtils.isBlank("bob")=false;
     * StringUtils.isBlank("bob")=false;
     * @param str the string to check ,may be null
     *  return boolean
     */
     public static boolean isBlank(String str){
         int strLen=-1;
         if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
         for (int i=0;i<strLen;i++){
             if(Character.isWhitespace(str.charAt(i))==false){
                 return false;
             }
         }
           return true;
     }

    /**
     * <p>Checks if a String is not empty ( ""), not null and not whitespace only.</p>
     * StringUtils.isNotBlank (null) = false
     * StringUtils.isNotBlank ( "") = false
     * StringUtils.isNotBlank ( "") = false
     * StringUtils.isNotBlank ( "bob") = true
     * StringUtils.isNotBlank ( "bob") = true
     *
     * @param str The String To Check, May Be Null
     * @return <code>true</code> If The String Is not Empty ,not Null & not whitespace
     */
     public static boolean isNotBlank(String str){
         return !isBlank(str);
     }

    /**
     * Make the filling left of the specified string.
     *
     * @param str     Make the filling left of the specified string.
     * @param size    number of characters
     * @param padChar fill characters
     * @return left padded string
     */
      public static String letfPad(String str,int size,char padChar){
          if(str==null){
              return null;
          }
          int pads=size-str.length();
          if(pads<=0){
              return str;
          }
          return padding(pads, padChar).concat(str);
      }

    /**
     * Do the padding.
     *
     * @param repeat  number of repetitions
     * @param padChar padding characters
     * @return string
     * @throws IndexOutOfBoundsException
     */
     public static String padding(int repeat,char padChar) throws IndexOutOfBoundsException{
          if(repeat<0){
              throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
          }
          final char[] buf=new char[repeat];
          int length=buf.length;
          for (int i=0;i<length;i++){
              buf[i]=padChar;
          }
          return new String((buf));
     }

    /**
     * Set the color to the character of the specified location.
     *
     * @param tagert target the entire string
     * @param start  color setting to start position
     * @param end    color setting to end position
     * @param color  string set color
     * @return char sequence
     */

     public static CharSequence setTextColor(String tagert, int start, int end, String color) {
         // If there is no necessary information is null
         if (tagert == null || tagert.length() == 0 || start < 0 || end > tagert.length()
                 || start >= end || color == null) {
             return null;
         }
         // Split in the characters and their before and after the set color
         String a = tagert.substring(0, start);
         String b = tagert.substring(start, end);
         String c = tagert.substring(end);

         //Creating a  HTML tags with string

         StringBuilder sb = new StringBuilder();

         // Html created
         sb.append(a);
         sb.append("<font color=\"");
         sb.append(color);
         sb.append("\">");
         sb.append(b);
         sb.append("</font>");
         sb.append(c);

         return Html.fromHtml(sb.toString());

     }
    /**
     * <p> Removes Control Characters (Char <= 32) from both ends of this
     * string returning an empty string if The String is Empty ( "").
     * <p> The String is trimmed using {@link String # trim ()}.
     * Trim removes start and end characters <= 32.
     * To strip whitespace use * {@link # stripToEmpty (String)}. * </p>
     * StringUtils.trimToEmpty (null) = ""
     * StringUtils.trimToEmpty ( "") = ""
     * StringUtils.trimToEmpty ( "") = ""
     * StringUtils.trimToEmpty ( "abc") = "abc"
     * StringUtils.trimToEmpty ( "abc") = "abc"
     *
     * @param str The String To Be Trimmed, May Be Null
     * @return The Trimmed String, Or An Empty String If <code>null</code> Input
     */

     public static String trimToEmpty(String str){
         return str==null?EMPTY:str.trim();
     }

    /**
     * Cut in the number of characters that is specified a string.
     *
     * @param str  string
     * @param size size cut you want the number of characters
     * @return string
     */

    public static String trimTextSize(String str,int size){
        if(isBlank(str))
            return null;
        int pads=str.length()-size;
        if(pads<0){
            return str;
        }
        return str.substring(0,size);
    }


    /**
     * String is determined whether or not "0".
     * <pre>
     * Null = true
     * "" = True
     * "0" = true
     * "00" = true
     * "010" = false
     * </pre>
     *
     * @param str The String To Check, may be null
     * @return <code>true</code> If The string is null, empty Or whitespace
     */

    public static boolean isZero(String str){
        int strLen;
        char c=0;

        if(str==null || (strLen=str.length())==0 ||str.equals("0")){
            return true;
        }
        for (int i=0;i<strLen;i++){
            c=str.charAt(i);
            if(c>'0')
                return false;
        }
        return true;
    }

    /**
     * Returns true even in the case of null each other String
     *
     * @param str1
     * @param str2
     * @return
     */

     public static boolean equals(String str1,String str2){
         return str1==null? str2==null:str1.equals(str2);
     }

    /**
     * yyyy year mm month dd date conversion
     *
     * @param yyyymmdd date format
     * @return string
     */

    /**
     * If the argument is null/empty  then strings return  null or
     * If the argument is  not empty  then strings return the argument.
     *
     * @param str
     * @return
     */

    public static String defaultString(final  String str){
        return str==null?EMPTY:str;
    }

    /**
     * StringUtils replace  Replaces all occurrences of a String within another String.
     *
     * @param text         text to search and replace in, may be null
     * @param searchString the String to search for, may be null
     * @param replacement  the String to replace it with, may be null
     * @return string
     */

    public static String replace(final String  text,final String searchString, final String replacement){
          if(isEmpty(text) || isEmpty(searchString) || replacement==null)
              return text;

          int start=0;
          int end=text.indexOf(searchString,start);
          if(end<0){
              return text;
          }
        final int replLength = searchString.length();
        final StringBuilder buf = new StringBuilder(text.length());
        while (end >= 0) {
            buf.append(text.substring(start, end)).append(replacement);
            start = end + replLength;
            end = text.indexOf(searchString, start);
        }
        buf.append(text.substring(start));
        return buf.toString();

    }

    /**
     * StringUtils stripEnd Strips any of a set of characters from the end of a String.
     *
     * @param str        the String to remove characters from, may be null
     * @param stripChars the set of characters to remove, null treated as whitespace
     * @return string
     */

    public static String stripEnd(String str,String stripChars){
        int end;
         if(str==null || (end=str.length())==0){
             return str;
         }
         if(stripChars==null){
             for (; end!=0 && Character.isWhitespace(str.charAt(end-1));end--);
         }
         else {
             if(stripChars.length()==0)
                 return str;
             for (; end != 0 && stripChars.indexOf(str.charAt(end - 1)) != -1; end--) ;
         }
        return str.substring(0, end);
    }
    //check string contains any type of sign
    public static boolean containsNoSign(String str){
        double strVal=Double.parseDouble(str);
        if(str.contains(AppStringUtils.PLUS) ||str.contains(AppStringUtils.MINUS)){
            return false;
        }
        return true;
    }
    //<<INNO_B 230817 add>>
    /*
     * A null CharSequence will return false
     * */
    public static boolean containsIgnoreCase(String str, String searchStr) {
        return AppStringUtils.containsIgnoreCase(str,searchStr);
    }

}
