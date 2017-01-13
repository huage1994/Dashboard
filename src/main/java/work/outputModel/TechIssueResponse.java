package work.outputModel;

import work.model.TechIssue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by I320997 on 1/13/2017.
 */
public class TechIssueResponse {
    public String moduleName;
    public String priority;
    public int issues =0;

    public TechIssueResponse(String moduleName, String priority, TechIssue[] techIssues) {
        Map<String,String[]> map= new HashMap<String,String[]>();
        String[] pri1 = {"BLOCKER","CRITICAL"};
        String[] pri2 = {"MAJOR"};
        String[] pri3 = {"MINOR","INFO"};
        map.put("high",pri1);
        map.put("medium",pri2);
        map.put("low",pri3);

        String[]  comp2 = map.get(priority);

        this.moduleName = moduleName;
        this.priority = priority;
        for (int i=0;i<techIssues.length;i++)
        {
            for(int j = 0;j < techIssues[i].issues.length;j++)
            {
                for(int k=0;k<comp2.length;k++) {
                    if (techIssues[i].issues[j].severity.compareTo(comp2[k])==0)
                    {
                        this.issues++;
                    }

                }
            }
        }
    }
}
