package bcccp.carpark.exit;

import bcccp.carpark.Carpark;
import bcccp.carpark.ICarSensor;
import bcccp.carpark.ICarSensorResponder;
import bcccp.carpark.ICarpark;
import bcccp.carpark.IGate;
import bcccp.tickets.adhoc.IAdhocTicket;

public class ExitController 
		implements ICarSensorResponder,
		           IExitController {
	
	private IGate exitGate;
	private ICarSensor insideSensor;
	private ICarSensor outsideSensor; 
	private IExitUI ui;
	
	private ICarpark carpark;
	private IAdhocTicket  adhocTicket = null;
	private long exitTime;
	private String seasonTicketId = null;
	
	

	public ExitController(Carpark carpark, IGate exitGate, 
			ICarSensor is,
			ICarSensor os, 
			IExitUI ui) {

 this.carpark = carpark;
             this.exitGate = exitGate;
             this.is = is;
             this.os = os;
             this.ui = ui;
             
             
             os.resgisterResponder(this);
             is.resgisterResponder(this);
             ui.resgisterResponder(this);
          
             prevState = STATE.IDLE;
             setState( STATE.IDLE);
             
		//TODO Implement constructor
	}



	@Override
	public void ticketInserted(String ticketStr) {
			 If (state == STATE.WAITING){
             if (isADhocTicket(ticketStr)){
             adhocTicket = carpark.get.ADhocTicket(ticketStr);
             exitime = System.currentTimeMillies ();
             if (adhocTicket != null && adhocTicket.ispaid()){
                 setState (STATE.Processed );
}
             else {
                 ui.beep();
                 setState(STATE.REJECTED);
             }
                 
         }
             else if (carpark.isSeasonTicketValid(ticketStr) &&
                      carpark.isSeasonTicketInuse(ticketStr)){
                 SeasonTicket = ticketStr;
    	          setState(STATE.PROCESSED); 
 }
             else {
                 ui.beep();
                 setState(STATE.REJECTED);
             }
             else {
                 ui.beep();
                     ui.discardTicket();
                  Log("ticketInserted:calledd ehile an incoorect state");
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ticketTaken() {


 
             If (state == STATE.PROCESSED) {
             exitGate.raise ();
             setState( STATE.TAKEN );
         }
                    
		// TODO Auto-generated method stub
		
	}



	@Override
	public void carEventDetected(String detectorId, boolean detected) {
		// TODO Auto-generated method stub
		
	}

	
	
}
