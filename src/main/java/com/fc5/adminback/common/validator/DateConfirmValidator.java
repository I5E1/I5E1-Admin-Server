//package com.fc5.adminback.common.validator;
//
//import com.fc5.adminback.common.validator.annotation.DateConfirm;
//import com.fc5.adminback.domain.annual.dto.UpdateAnnualRequestDto;
//import com.fc5.adminback.domain.duty.dto.UpdateDutyRequestDto;
//import com.fc5.adminback.domain.model.Status;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import java.time.LocalDate;
//
//public class DateConfirmValidator implements ConstraintValidator<DateConfirm, Object> {
//
//    @Override
//    public void initialize(DateConfirm constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(Object value, ConstraintValidatorContext context) {
//        if (value instanceof UpdateAnnualRequestDto) {
//            UpdateAnnualRequestDto dto = (UpdateAnnualRequestDto) value;
//            LocalDate startDate = dto.getStartDate();
//            LocalDate endDate = dto.getEndDate();
//            Status status = dto.getStatus();
//
//            if (startDate.isAfter(endDate)) {
//                return false;
//            }
//
//            if (startDate.isBefore(LocalDate.now()) && endDate.isAfter(LocalDate.now()) && (status.equals(Status.COMPLETED) || status.equals(Status.REQUESTED))) {
//                return false;
//            }
//
//
//            if (startDate.isAfter(LocalDate.now()) && endDate.isAfter(startDate) && status.equals(Status.COMPLETED)) {
//                return false;
//            }
//
//            if (startDate.isBefore(endDate) && endDate.isBefore(LocalDate.now()) && (status.equals(Status.APPROVED) || status.equals(Status.REQUESTED))) {
//                return false;
//            }
//
//            if (startDate.isEqual(endDate) && endDate.isEqual(LocalDate.now()) && (status.equals(Status.COMPLETED) || status.equals(Status.REQUESTED))) {
//                return false;
//            }
//        }
//
//        if (value instanceof UpdateDutyRequestDto) {
//            UpdateDutyRequestDto dto = (UpdateDutyRequestDto) value;
//            LocalDate dutyDate = dto.getDutyDate();
//            Status status = dto.getStatus();
//
//            if (LocalDate.now().isBefore(dutyDate) && status.equals(Status.COMPLETED)) {
//                return false;
//            }
//
//            if (dutyDate.isBefore(LocalDate.now()) && (status.equals(Status.APPROVED) || status.equals(Status.REQUESTED))) {
//                return false;
//            }
//
//            if (dutyDate.equals(LocalDate.now()) && (status.equals(Status.COMPLETED) || status.equals(Status.REQUESTED))) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//}