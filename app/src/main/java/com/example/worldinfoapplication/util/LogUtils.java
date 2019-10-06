package com.example.worldinfoapplication.util;

import android.util.Log;

import com.example.worldinfoapplication.BaseActivity;

import java.io.Serializable;

/**
 * <code>LogUtils</code> class is a Log-related utility class
 *
 * <pre>
 *
 * Log output class. Wrapper of {@link Log}
 * Because the emulator, actual device log level is INFO or more,
 * for VERBOSE and DEBUG log, not the log level, but
 * {@link BaseActivity # TEST_FLG} should be checked to determine if output is possible or not.
 *
 * <pre/>
 *
 * @author Amir Ansari
 */
public class LogUtils implements Serializable {
    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    //initialize the tag
    private String tag;

    //check log message need to show or not
     private boolean isShowInfoLog;

    /**
   *private constructor
    */

   private LogUtils(){
   }

   /* parameter constructor*/
   public LogUtils(String tag,boolean isShowInfoLog){
       this.tag=tag;
       this.isShowInfoLog=isShowInfoLog;
       //LogUtils
   }

    /**
     * Output detailed log during the test only
     *
     * @param msg the error message to be displayed.
     * @see Log # v (String, String)
     * @see BaseActivity # TEST_FLG
     */

    public void v(Object msg){
        if(isShowInfoLog)
            Log.v(tag,msg==null?"":msg.toString());
    }

    /**
     *
     * Output debug log during the test only
     *
     * @param msg the error message to be displayed.
     * @see Log # d (String, String)
     * @see BaseActivity # TEST_FLG
     */


    public  void d(Object msg){
        if(isShowInfoLog){
            String log=msg==null?"":msg.toString();
            int blockSize=4000;
            int logSize=log.length();

            if(logSize>blockSize){
                // Cannot see properly if the size is 4kb or more (buffer upper limit?). Hence, output in chunks

                int blockCount=logSize/blockSize;
                for (int i=0;i<blockCount;i++){
                    int nextPos=blockSize*(i+1);
                    if(nextPos>=logSize){
                        //the last output
                        Log.d(tag,log.substring(4000*i));
                    }else {
                        //intermediate output
                        Log.d(tag,log.substring(4000*i,nextPos));
                    }
                }

            }
            else {
                //print original output
                Log.d(tag,log);
            }
        }

    }

/**
 * Output debug log during the test only
 *
 * @param msg the error message to be displayed.
 * @param tr to handle exception
 * @see Log # d (String, String, Throwable)
 * @see BaseActivity # TEST_FLG
 */

   public void d(Object msg,Throwable tr){
       if(isShowInfoLog){
           Log.d(tag,msg==null?" ":msg.toString(),tr);
       }
   }

    /**
     * Output log if log level is INFO or more.
     *
     * @param msg the error message to be displayed.
     * @see Log # i (String, String)
     * @see Log#INFO
     */
     public void i(Object msg){
          if(isShowInfoLog){
              Log.i(tag,msg==null?"":msg.toString());
          }
      }

    /**
     * Output log if log level is INFO or more.
     *
     * @param msg the error message to be displayed.
     * @param tr to handle exception
     * @see Log#i(String, String, Throwable)
     * @see Log#INFO
     */

    public void i(Object msg,Throwable tr){
        if(isShowInfoLog){
            Log.i(tag,msg==null?"":msg.toString());
        }
    }

    /**
     * Output log if log level is WARN or more.
     *
     * @param msg the error message to be displayed.
     * @see Log#w(String, String)
     * @see Log#WARN
     */
    public  void w(Object msg){
         if(isShowInfoLog){
             Log.w(tag,msg==null?" ":msg.toString());
         }
     }

    /**
     * Output log if log level is WARN or more.
     *
     * @param msg the error message to be displayed.
     * @param tr to handle exception
     * @see Log#w(String, String, Throwable)
     * @see Log#WARN
     */

    public  void w(Object msg,Throwable tr){
        if(isShowInfoLog){
            Log.w(tag,msg==null?" ":msg.toString());
        }
    }

    /**
     * Output log if log level is ERROR or more.
     *
     * @param msg the error message to be displayed.
     * @see Log#e(String, String)
     * @see Log#ERROR
     */
     public  void e(Object msg){
         if(isShowInfoLog){
             Log.e(tag,msg==null?"":msg.toString());
         }
     }

    /**
     * Output log if log level is ERROR or more.
     *
     * @param msg the error message to be displayed.
     * @param tr to handle exception
     * @see Log#e(String, String, Throwable)
     * @see Log#ERROR
     */
          public void e(Object msg,Throwable tr){
              if(isShowInfoLog){
                  Log.e(tag,msg==null?"":msg.toString());
              }
          }


}
