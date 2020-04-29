package com.dataprivacy.app;

import org.apache.pig.ExecType;
import org.apache.pig.PigServer;

import java.io.IOException;
import java.util.Properties;

public abstract class HadoopAlgorithms extends Algorithms {
    PigServer pigServer;

    public abstract void run(String path, int k) throws IOException;

    public void startServer(String path) {
        try {
            pigServer = new PigServer(ExecType.MAPREDUCE);
            setData(path);
            Properties props = new Properties();
            props.setProperty("fs.default.name", "hdfs://namenode:9000");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        pigServer.shutdown();
    }

    public void setData(String path) {
        try {
            pigServer.registerQuery("data = " +
                    "LOAD '" + path + "' USING PigStorage(';')" +
                    "as ( id:long, firstlogin:long,  freetext:chararray, gender:chararray, groundtruth:int, language:chararray, lastactivity:long, lasttweet:chararray, location:chararray, name:chararray," +
                    " profilepictureurl:chararray, screenname:chararray, timezone:chararray, foursqr_id:long);");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


