package com.comparator.qa.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class JsonComparator {

    public static boolean jsonCompareObject(JSONObject jsonBaseObj, JSONObject jsonDumpObj) throws Exception {
        Iterator<String> itr = jsonBaseObj.keys();
        while(itr.hasNext())
        {
            String key = itr.next();
            if(!jsonDumpObj.has(key))
                return false;
            if(jsonBaseObj.get(key) instanceof JSONArray)
            {
                JSONArray arr1 = (JSONArray) jsonBaseObj.get(key);
                JSONArray arr2 = (JSONArray) jsonDumpObj.get(key);
                if(arr1.length()!=arr2.length())
                    return false;
                for (int i = 0; i < arr1.length(); i++)
                {
                    JSONObject baseObj = (JSONObject) arr1.get(i);
                    for(int j=0; j<arr2.length(); j++) {
                        JSONObject dumpObj = (JSONObject) arr2.get(j);
                        if(jsonCompareObject(baseObj, dumpObj)) {
                            arr2.remove(j);
                            break;
                        }
                    }
                }
                if(arr2.length() !=0)
                    return false;
            }
            else if (jsonBaseObj.get(key) instanceof JSONObject) {
                JSONObject baseObj = (JSONObject) jsonBaseObj.get(key);
                JSONObject dumpObj = (JSONObject) jsonDumpObj.get(key);
                if(!jsonCompareObject(baseObj, dumpObj)) {
                    return false;
                }
            } else {
                Object baseVal = jsonBaseObj.get(key);
                Object newVal = jsonDumpObj.get(key);
                if(!baseVal.equals(newVal))
                    return false;
            }
          
        }
        if(!itr.hasNext()) {
            Set<String> keys1 = jsonDumpObj.keySet();
            List<String> keyList = new ArrayList<String>();
            keyList.addAll(keys1);
            for (int k = 0; k < keyList.size(); k++) {
                jsonDumpObj.remove(keyList.get(k));
            }
        }

        if(jsonDumpObj.length()!=0)
            return false;
        return true;

    }

}
