package com.BYP.BYP;

import java.util.Random;

import com.BYP.entity.Battery;
import com.BYP.DAO.BatteryRepository;

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
	public void performRandomActionsWithDelay(Battery battery, int maxIterations, long delay) { // delay is a value expressed in milliseconds
		for (int i = 0; i < maxIterations; i++) {
			performRandomAction(battery);
			sleep(delay);
		}
	}

	private void performRandomAction(Battery battery) {

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
				batteryRepository.update(battery, new String[]{"UNREACHABLE"});
				break;
		}
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
}
