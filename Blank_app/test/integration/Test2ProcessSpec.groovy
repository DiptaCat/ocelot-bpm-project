import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.TaskService
import org.camunda.bpm.engine.test.mock.Mocks
import spock.lang.Specification

/**
 * Integration Test for camunda Test2Process 
 */
class Test2ProcessSpec extends Specification {

    /**
     * 1) Inject camunda process engine API service beans
     */
    RuntimeService runtimeService
    TaskService taskService

    /**
     * 2) Mock your Grail(s) services called from Test2Process
     */
    def sampleTest2ProcessService = Mock(SampleTest2ProcessService)

    /*
     * Sample service to get started quickly. For real testing, mock your actual
     * Grails Service(s) called from Test2Process, then delete this!
     */
    class SampleTest2ProcessService {
        void serviceMethod() {}
    }

    /**
     * 3) Register your service mocks to make them accessible via Test2Process
     */
    def setup() {
        Mocks.register("sampleTest2ProcessService", sampleTest2ProcessService)
    }

    def cleanup() {
        Mocks.reset()
    }

    /**
     * 4) Test the various aspects and behaviour of Test2Process
     */
    void "Testing a happy walk through Test2Process"() {

        given: "a new instance of Test2Process"
        runtimeService.startProcessInstanceByKey("Test2Process")

        when: "completing the user task"
        def task = taskService.createTaskQuery().singleResult()
        taskService.complete(task.id)

        then: "the service method defined for the subsequent service task was called exactly once"
        1 * sampleTest2ProcessService.serviceMethod()

        and: "nothing else was called"
        0 * _

        and: "the process instance finished"
        !runtimeService.createProcessInstanceQuery().singleResult()

    }

}
