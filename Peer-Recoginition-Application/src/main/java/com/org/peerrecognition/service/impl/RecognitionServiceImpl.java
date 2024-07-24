package com.org.peerrecognition.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.peerrecognition.dto.RecognitionDto;
import com.org.peerrecognition.dto.RecognizeDto;
import com.org.peerrecognition.model.Badges;
import com.org.peerrecognition.model.Employee;
import com.org.peerrecognition.model.Recognition;
import com.org.peerrecognition.repository.BadgeRepository;
import com.org.peerrecognition.repository.EmployeeRepository;
import com.org.peerrecognition.repository.RecognitionRepository;
import com.org.peerrecognition.service.RecognitionService;

@Service
public class RecognitionServiceImpl implements RecognitionService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BadgeRepository badgeRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo; 
	
	@Autowired
	private RecognitionRepository recognizeRepo;

	@Override
	public RecognitionDto recognize(int giverId, int receiverId, RecognizeDto recognizeDto) {
		
		Badges badge = this.badgeRepo.findByBadgeName(recognizeDto.getBadgeName());
		Recognition recognition = this.dtoToRecognition(recognizeDto);
		recognition.setBadges(badge);
		
		Employee giver = this.employeeRepo.findById(giverId).get();
		Employee receiver = this.employeeRepo.findById(receiverId).get();
		recognition.setGiver(giver);
		recognition.setReceiver(receiver);
		
		Recognition recognized = this.recognizeRepo.save(recognition);
		
		RecognitionDto recognizedDto = new RecognitionDto();
		recognizedDto.setBadgeName(recognized.getBadges().getBadgeName());
		recognizedDto.setGiverId(recognized.getGiver().getEmployeeId());
		recognizedDto.setReceiverId(recognized.getReceiver().getEmployeeId());
		recognizedDto.setComment(recognized.getComment());
		
		
		return recognizedDto;
	}
	
	public Recognition dtoToRecognition(RecognizeDto recognizeDto)
	{
		return this.modelMapper.map(recognizeDto, Recognition.class);
	}
	
	public RecognitionDto recognitionToDto(Recognition recognition)
	{
		return this.modelMapper.map(recognition, RecognitionDto.class);
	}

}
