package com.lytuan99.projectIwarehouse.converter;

import com.lytuan99.projectIwarehouse.dto.PaymentDTO;
import com.lytuan99.projectIwarehouse.entity.PaymentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter {
	public PaymentDTO convertToDTO(PaymentEntity entity) {
		ModelMapper modelMapper = new ModelMapper();
		PaymentDTO dto=modelMapper.map(entity, PaymentDTO.class);
		return dto;
	}
	public PaymentEntity convertToEntity(PaymentDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		PaymentEntity entity=modelMapper.map(dto, PaymentEntity.class);
		return entity;
	}
}
