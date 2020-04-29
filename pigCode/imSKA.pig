--Sentive types: twitter_id, name, profilepictureurl, foursqr_id
-- processedData is a String[]
data = LOAD '/user/root/input/twitterdata' USING PigStorage(';') as ( id:long, firstlogin:long,  freetext:chararray, gender:chararray, groundtruth:int, language:chararray, lastactivity:long, lasttweet:chararray, location:chararray, name:chararray, profilepictureurl:chararray, screenname:chararray, timezone:chararray, foursqr_id:long);
--filter quasi-identifiers from input file
qid_data = FOREACH data GENERATE id, name, profilepictureurl, foursqr_id;
--rearrange columns in ascending order of numbers of unique values in it
QIDS = foreach data GENERATE $0, $9, $10, $13;
Distinct_QIDS = DISTINCT QIDS;
number = COUNT(Distinct_QIDS);


--sort the data in ascending order of numbers of unique values in it
--group above data by all attributes in QID_DATA
--for each group in group_QID
    --eqClass = group_qid + count(group_qid)
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