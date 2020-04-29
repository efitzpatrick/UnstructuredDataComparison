package com.dataprivacy.app;

import org.apache.pig.data.Tuple;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
public class ImSLD extends HadoopAlgorithms {

    public void run(int k) {
        long sLDStartTime = System.nanoTime();
        try {
        //rearrange columns in ascending order of numbers of unique values in it
        // do the following over each column so make an array of length (number of columns)
        //for each column{
        Object[] a = new Object[14];
        int[] numunique = new int[14];
            for(int it = 0; it<14; it++){
                pigServer.registerQuery("users = FOREACH data GENERATE $"+it);
                pigServer.registerQuery("uniq_users = DISTINCT users");
                pigServer.registerQuery("grouped_users = GROUP uniq_users ALL");
                pigServer.registerQuery("uniq_user_count = FOREACH grouped_users GENERATE COUNT(uniq_users)");
                numunique[it] = Integer.parseInt(("uniq_user_count"));
            for(int cc = 0; cc<15; cc++){
                int smallest = 0;
                for (int j = cc + 1; j < 15; j++)
                {
                    if (numunique[cc] > numunique[j])
                    {
                        smallest = j;
                    }
                    pigServer.registerQuery("users = FOREACH user_data GENERATE $"+j);

                }
            }

        //group by QID attribute: screenname
        pigServer.registerQuery("order_by_data = ORDER data BY screenname DESC");

        //create equivalence classes

        //filter sensitive attributes from equivalence classes
            pigServer.registerQuery("dropped_columns = FOREACH data GENERATE name, gender, location, foursqr_id");

            //check if it is correct
        long sLDEndTime = System.nanoTime();
    }

    public String[][] setData()
    {
        return null;
    }
        catch (IOException e){
            throw new IOException();
            }

}

