-- processedData is a String[]
data = LOAD '/user/root/input/twitterdata' USING PigStorage(';') as ( id:long, firstlogin:long,  freetext:chararray, gender:chararray, groundtruth:int, language:chararray, lastactivity:long, lasttweet:chararray, location:chararray, name:chararray, profilepictureurl:chararray, screenname:chararray, timezone:chararray, foursqr_id:long);
--filter quasi-identifiers from input file
qid_data = FOREACH data GENERATE
--rearrange columns in ascending order of numbers of unique values in it
--sort the data in ascending order of numbers of unique values in it
--group above data by all attributes in QID_DATA
--for each group in group_QID
    --eqClass = group_qid + count(group_qid)
--mergeEquivalenceClass = empty array size of eq_class
        --for each row in eq class
            --merged equivalence class = merged equivalence class + eq class
            --if merged equivalence class
                --ncp_eq = ncp(mrged equivalence class)