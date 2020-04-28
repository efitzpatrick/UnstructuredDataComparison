package com.dataprivacy.app;

import java.time.Duration;


public class ImSLD extends Algorithms {

    public void run(String[][] data, int k)
    {
        long sLDStartTime = System.nanoTime();
        //rearrange columns in ascending order of numbers of unique values in it
        // do the following over each column so make an array of length (number of columns)
        //for each column{
        /*users = FOREACH user_data GENERATE column name;
        uniq_users = DISTINCT users;
        grouped_users = GROUP uniq_users ALL;
        uniq_user_count = FOREACH grouped_users GENERATE COUNT(uniq_users);
        DUMP uniq_user_count; into array[column number]
        }
        //create  a new database thingy
        for 1:last column
        iterate over array with unique number in each columns. pick the lowest. put that column into
        the new database. it's now sorted!
        */

        //group by QID attributes
        //name = get the name of the first column
        //order_by_data = ORDER database BY name DESC;

        //create equivalence classes
        //filter sensitive attributes from equivalence classes
        //check if it is correct
        long sLDEndTime = System.nanoTime();
    }

    public String[][] setData()
    {
        return null;
    }



}
