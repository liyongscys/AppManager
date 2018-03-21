package com.cabletech.common.util;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BeanValidator {
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static <T> BeanValidatorResult validate(T t) {
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

        List<String> messageList = new ArrayList<String>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }
        BeanValidatorResult beanValidatorResult = new BeanValidatorResult(messageList);
        return beanValidatorResult;
    }

    public static class BeanValidatorResult {
        /**
         * 验证结果
         */
        private boolean success;
        /**
         * 错误消息
         */
        private List<String> errorMessages;

        public BeanValidatorResult() {
        }

        public BeanValidatorResult(List<String> errorMessages) {
            this.errorMessages = errorMessages;
            this.success = CollectionUtils.isEmpty(errorMessages);
        }

        public List<String> getErrorMessages() {
            return errorMessages;
        }

        public void setErrorMessages(List<String> errorMessages) {
            this.errorMessages = errorMessages;
        }

        public boolean success() {
            return success;
        }

        public void setSuccess(boolean result) {
            this.success = result;
        }
    }
}
