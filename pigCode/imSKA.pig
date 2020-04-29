--Sentive types: twitter_id, name, profilepictureurl, foursqr_id
-- processedData is a String[]
data = LOAD '/user/root/input/twitterusers' USING PigStorage(';') as ( id:long, firstlogin:long,  freetext:chararray, gender:chararray, groundtruth:int, language:chararray, lastactivity:long, lasttweet:chararray, location:chararray, name:chararray, profilepictureurl:chararray, screenname:chararray, timezone:chararray, foursqr_id:long);
--filter quasi-identifiers from input file
qid_data = FOREACH data GENERATE name, profilepictureurl, foursqr_id, id;
--rearrange columns in ascending order of numbers of unique values in it
/*B = GROUP A BY f1;
X = FOREACH B GENERATE COUNT(A);

D = GROUP C BY a1;
Result = FOREACH D GENERATE group, SUM(C.a3);*/

--sort the data in ascending order of numbers of unique values in it
qid_data = ORDER qid_data BY name, profilepictureurl, foursqr_id, id;

--group above data by all attributes in QID_DATA
group_qid = GROUP qid_data ALL;

--for each group in group_QID
E = FOREACH group_qid{
    RECORD_COUNT = GENERATE COUNT(qid_data);
    --eqClass = group_qid + count(group_qid)
    eqClass = qid_data + RECORD_COUNT;
};



--mergeEquivalenceClass = empty array size of eq_class
        --for each row in eq class
            --merged equivalence class = merged equivalence class + eq class
            --if merged equivalence class
                --ncp_eq = ncp(mrged equivalence class)

/*
DEFINE unique_value() RETURNS Y{
--make a bag of all of the values in the column
--group the column high to low by value
--count the number of unique values up every time the number changes

}

D = group C by domain;
E = foreach D {
    unique_segments = DISTINCT C.segment;
    generate group, COUNT(unique_segments) as segment_cnt;
};
* */