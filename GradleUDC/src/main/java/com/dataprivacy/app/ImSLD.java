package com.dataprivacy.app;

import org.apache.pig.data.Tuple;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

public class ImSLD extends HadoopAlgorithms {

    public void run(String path, int k) throws IOException {
        long sLDStartTime = System.nanoTime();
        setData(path);

        //rearrange columns in ascending order of numbers of unique values in it
        // do the following over each column so make an array of length (number of columns)
        //for each column{
        Object[] a = new Object[14];
        int[] numunique = new int[14];
        for (int it = 0; it < 14; it++) {
            // users becomes just one column
            pigServer.registerQuery("users = FOREACH data GENERATE $" + it);
            // uniq_users is the distinct value of that one column
            pigServer.registerQuery("uniq_users = DISTINCT users");
            //maybe makes a new group with all the unique users
            pigServer.registerQuery("grouped_users = GROUP uniq_users ALL");
            //for that set of unique values, count them
            pigServer.registerQuery("uniq_user_count = FOREACH grouped_users GENERATE COUNT(uniq_users)");
            //assigning int arrray from 0 to 14 the number of unique values

            numunique[it] = Integer.parseInt(("uniq_user_count"));
        }
        for (int cc = 0; cc < 15; cc++) {
            int smallest = 0;
            for (int j = cc + 1; j < 15; j++) {
                if (numunique[cc] > numunique[j]) {
                    smallest = j;
                }
                //Iterating through that array trying to find the smallest number creating that column
                pigServer.registerQuery("users = FOREACH user_data GENERATE $" + j);
                //create a new database put the values in one at a time starting at zero

            }
        }

        //group by QID attribute: screenname
        pigServer.registerQuery("order_by_data = ORDER data BY screenname DESC");

        //create equivalence classes: sorted by QID, make the groupings of whatever the size is, then filter the sensitive attributes and anonymize it

        //filter sensitive attributes from equivalence classes
        pigServer.registerQuery("dropped_columns = FOREACH data GENERATE name, gender, location, foursqr_id");

        //check if it is correct
        long sLDEndTime = System.nanoTime();

    }
}

