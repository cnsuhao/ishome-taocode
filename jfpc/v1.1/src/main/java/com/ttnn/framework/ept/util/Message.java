package com.ttnn.framework.ept.util;


import com.ttnn.framework.ept.SEPTConstants;
import com.ttnn.framework.ept.TicketService;
import com.ttnn.framework.support.CSPVOSupport;

public class Message extends CSPVOSupport implements SEPTConstants{

	/**
     * 
     */
    private static final long serialVersionUID = 2704681158449642263L;
    
	public TicketService ticketService_;

	public Message(String ticketId, TicketService ticketService) {

		this.ticketService_ = ticketService;
	}

	// 日志输出
	public void updateTicketMessage(String messageId, Object msgs) {

	}

	public void updateTicketMessage(String stepinfo, String message) {
//		DataImportTicket ticket = (DataImportTicket) ticketService_
//				.getTicketById(ticketId_);
//		ticket.setDescription(message);
//		if (ticket.getFailureTime() != null
//				&& ticket.getFailureTime() < System.currentTimeMillis()) {
//			ticket.setDescription("{msg:'请求超时'}");
//			ticket.setState(TicketState.TIMEOUT);
//		}
//		ticketService_.updateTicket(ticket,
//				TicketService.DEFAULT_EXPRIED_SECOND);
	}

	public void updateTicketErrorMsg(String message) {
		haveMessage = true;
//		DataImportTicket ticket = (DataImportTicket) ticketService_
//				.getTicketById(ticketId_);
//		ticket.setState(TicketState.EXCEPTIONSTOP);
//		if (ticket.getFailureTime() != null
//				&& ticket.getFailureTime() < System.currentTimeMillis()) {
//			ticket.setDescription("{msg:'请求超时'}");
//			ticket.setState(TicketState.TIMEOUT);
//		}
//		ArrayList<String> m = new ArrayList<String>();
//		m.add(message);
//		ticket.appendErrorMsgs(m);
//		ticketService_.updateTicket(ticket,
//				TicketService.DEFAULT_EXPRIED_SECOND);
	}

	public void updateTicketInfo(String ticketId, String description,
			int currentRow, int totalRow,Integer state) {
//		DataImportTicket ticket = (DataImportTicket) ticketService_
//				.getTicketById(ticketId);
//		ticket.setState(state);
//		ticket.setDescription(description);
//		if(ticket.getFailureTime() != null && ticket.getFailureTime() < System.currentTimeMillis()){
//			ticket.setDescription("{msg:'请求超时'}");
//			ticket.setState(TicketState.TIMEOUT);
//		}
//		ticket.setCurrentRowCount(currentRow);
//		ticket.setTotalRowCount(totalRow);
//		ticketService_
//				.updateTicket(ticket, TicketService.DEFAULT_EXPRIED_SECOND);
	}

	public void updateTicketErrorMsg(String stepinfo, String message) {
		haveMessage = true;
	}

	// 日志输出
	public void updateTicketErrorMsg(String messageId, Object msgs) {

	}

	// 日志输出
	public void setFormReaultMessage(String messageID) {
		//formReaultMessageID = messageID;
	}

	//private String formReaultMessageID = "";

//	// 日志输出
//	public String getFormReaultMessage() {
//		EPTConfig EPTConfig_ = EPTConfig.getInstance();
//		EPTMessageBean mb = EPTConfig_.getMessageBeans().get(formReaultMessageID);
//		return mb.getValue();
//	}

	public String getMessage(){
		return "";
	}
	
	private boolean haveMessage = false;

	public boolean isHaveMessage() {
		return haveMessage;
	}
}
