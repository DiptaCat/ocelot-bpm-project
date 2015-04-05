package example

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate

class EmailService implements JavaDelegate {

    static transactional = false

    public void execute(DelegateExecution ex) throws Exception {

        println "Sending email..."

        //TODO: Implement this
    }

 }