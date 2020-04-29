package com.dataprivacy.app;

import org.apache.pig.data.Tuple;

import java.time.Duration;
import java.util.Iterator;


public class ImSLD extends HadoopAlgorithms {

    public void run(int k) {
        long sLDStartTime = System.nanoTime();
        //rearrange columns in ascending order of numbers of unique values in it
        // do the following over each column so make an array of length (number of columns)
        //for each column{
        Object[] a = new Object[14];
        int[] numunique = new int[14];


        try {
            for(int it = 0; it<14; it++){
                pigServer.registerQuery("users = FOREACH user_data GENERATE $"+it);
                pigServer.registerQuery("uniq_users = DISTINCT users");
                pigServer.registerQuery("grouped_users = GROUP uniq_users ALL");
                pigServer.registerQuery("uniq_user_count = FOREACH grouped_users GENERATE COUNT(uniq_users)");
                numunique[it] = pigServer.registerQuery("uniq_user_count");
            }
            for(int cc = 0; cc<15; cc++){
                int smallest = 0;
                for (int j = cc + 1; j < 15; j++)
                {
                    if (a[cc] > a[j])
                    {
                        smallest = j;
                    }
                }
            }
        }



            //create  a new database thingy
           /* for 1:last column
            iterate over array with unique number in each columns.pick the lowest.put that column into
            the new database.it 's now sorted!
            */


            //group by QID attributes
            //name = get the name of the first column
            pigServer.registerQuery("order_by_data = ORDER (last database name) BY name DESC");

            //create equivalence classes
            //filter sensitive attributes from equivalence classes
            //check if it is correct
            long sLDEndTime = System.nanoTime();
        }
        catch (Exception e) {
        e.printStackTrace();
    }
    }

    public String[][] setData()
    {
        return null;
    }

}
