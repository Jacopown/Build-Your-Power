package com.BYP.BYP;

import java.util.Random;
import java.util.List;

import com.BYP.entity.Battery;
import com.BYP.DAO.BatteryRepository;
import com.BYP.entity.Station;
import com.BYP.entity.User;

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
			int userIndex = performRandomAction(battery, users, stations);
			sleep(delay);
			if (!users.isEmpty() && userIndex != -1) {
				users.remove(userIndex);
			}
		}
	}

	private int performRandomAction(Battery battery, List<User> users, List<Station> stations) {

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
				if (battery.getUser() != null)
					batteryRepository.update(battery, new String[]{"UNREACHABLE"});
				break;
		}
		// performing random assignment
		int userIndex = performRandomAssignment(battery, users, stations);// assignment

		// send a message to the client which reloads the battery status
		simpMessagingTemplate.convertAndSend("/topic/batteryStatus", "reload");		

		return userIndex;
	}

	private void sleep(long delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private int performRandomAssignment(Battery battery, List<User> users, List<Station> stations) {// assignment
		//TODO maybe add the a check for all batteries in order to avoid the same user to be assigned to multiple batteries
		int typesOfAssigns = 1; //which are are station and user
		int randomAction = random.nextInt(typesOfAssigns);
		int randomUser = -1;
		switch(randomAction) {
			case 0:// assigning random user
			        if(!users.isEmpty()) {
				        int itemsInUsers = users.size();				
					randomUser = random.nextInt(itemsInUsers);
					if (battery.getUser() == null || battery.getUser() != users.get(randomUser)) {
						batteryRepository.switchAssignment(battery, users.get(randomUser));
						break;
					}
				}
				break;
			case 1:// assigning random station
				int itemsInStations = stations.size();
				int randomStation = random.nextInt(itemsInStations);
				batteryRepository.switchAssignment(battery, stations.get(randomStation));//assigning random station
				break;
		}
		return randomUser;
	}
}
