package com.BYP.BYP;

import java.util.Random;
import java.util.List;

import com.BYP.model.Battery;
import com.BYP.DAO.BatteryRepository;
import com.BYP.model.Station;
import com.BYP.model.User;
import com.BYP.BYP.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
public class BatteryService {

	private final BatteryRepository batteryRepository;
	private final SimpMessagingTemplate simpMessagingTemplate;

	private final Random random = new Random();

	@Autowired
	public BatteryService(BatteryRepository batteryRepository, SimpMessagingTemplate simpMessagingTemplate) {
		this.batteryRepository = batteryRepository;
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	// creating random behaviour for batteries
	public void performRandomActionsWithDelay(Battery battery, int maxIterations, long delay, List<User> users, List<Station> stations) { // delay is a value expressed in milliseconds
		for (int i = 0; i < maxIterations; i++) {
			performRandomAction(battery, users, stations);
			sleep(delay);
		}
	}

	private void performRandomAction(Battery battery, List<User> users, List<Station> stations) {

		int amountOfStatuses = 3;
		int randomAction = random.nextInt(amountOfStatuses);

		switch(randomAction) {
			case 0:
				batteryRepository.update(battery, new String[]{"AVAILABLE"});	
				break;
			case 1:
				batteryRepository.update(battery, new String[]{"UNAVAILABLE"});	
				break;
			case 2:
				batteryRepository.update(battery, new String[]{"DAMAGED"});
				break;
			case 3:
				// the battery can become unreachable only if it has a user
				if (battery.getUser() != null){
					batteryRepository.update(battery, new String[]{"UNREACHABLE"});
					EmailService emailService = new EmailService();
					emailService.sendEmail("aligiassioma72@gmail.com", battery.getId() + " is unreachable", "The battery is unreachable");
				}
				break;
		}
		// performing random assignment
		performRandomAssignment(battery, users, stations);// assignment

		// send a message to the client which reloads the battery status
		simpMessagingTemplate.convertAndSend("/topic/batteryStatus", "reload");		
	}

	private void sleep(long delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void performRandomAssignment(Battery battery, List<User> users, List<Station> stations) {// assignment
		int typesOfAssigns = 1; //which are are station and user
		int randomAction = random.nextInt(typesOfAssigns);
		switch(randomAction) {
			case 0:// assigning random user
			        if(!users.isEmpty()) {
				        int itemsInUsers = users.size();				
					int randomUser = random.nextInt(itemsInUsers);
					if (!isAlreadyAssignedToUser(batteryRepository.getAll(), users.get(randomUser))) {
						batteryRepository.switchAssignment(battery, users.get(randomUser));
						break;
					}
					break;
				}
				break;
			case 1:// assigning random station
				int itemsInStations = stations.size();
				int randomStation = random.nextInt(itemsInStations);
				batteryRepository.switchAssignment(battery, stations.get(randomStation));//assigning random station
				break;
		}
	}

	// defining a method that check if a battery is already assigned to a user
	public boolean isAlreadyAssignedToUser(List<Battery> batteries, User user) {
		for (Battery battery : batteries) {
			//System.out.println(battery.getUser());
			if (battery.getUser() != null && battery.getUser().getId() == user.getId()) {
				return true;
			}
		}
		return false;
	}
}
