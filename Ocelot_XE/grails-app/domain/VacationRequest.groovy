class VacationRequest {

	String employeeName
	Integer numberOfDays
	String vacationDescription
	String approvalStatus = "PENDING"
	String approvalRemark 
	Boolean resendRequest
    Date dateCreated
    Date lastUpdated
	String processInstanceId
  	
    static constraints = {
		employeeName blank:false, size:5..50
		numberOfDays range:1..14
		vacationDescription blank:false, size:5..255
		approvalStatus nullable:false
		approvalRemark nullable:true
		resendRequest nullable:true
		dateCreated blank:false
        lastUpdated nullable:true
		processInstanceId nullable:true
    }
}
