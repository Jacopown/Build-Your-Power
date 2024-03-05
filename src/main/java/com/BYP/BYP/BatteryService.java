package com.BYP.BYP;

import java.util.Random;

import com.BYP.entity.Battery;
import com.BYP.DAO.BatteryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatteryService {

	//TODO add messaging template for convert and send later
	private final BatteryRepository batteryRepository;

	private final Random random = new Random();

	@Autowired
	public BatteryService(BatteryRepository batteryRepository) {
		this.batteryRepository = batteryRepository;
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

		//TODO must be added messaging system (convert and send)
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
	}

	private void sleep(long delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
