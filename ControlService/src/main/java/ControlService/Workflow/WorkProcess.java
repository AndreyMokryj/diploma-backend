package ControlService.Workflow;

import java.util.Date;

public class WorkProcess {
    public int i = 0;
    private static WorkProcess workProcess;

    private WorkProcess(){}

    public static WorkProcess getInstance(){
        if(workProcess == null){
            workProcess = new WorkProcess();
        }
        return workProcess;
    }

    public void execute(){
        System.out.println("Task executed on " + new Date());
        i++;
    }
}
