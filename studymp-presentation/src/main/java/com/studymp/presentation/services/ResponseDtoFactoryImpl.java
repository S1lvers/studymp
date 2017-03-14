package com.studymp.presentation.services;

import com.studymp.presentation.dto.ResponseDto;
import com.studymp.presentation.interfaces.ResponseDtoFactory;
import org.springframework.stereotype.Component;

/**
 * Created by qwerty on 14.03.2017.
 */

@Component
public class ResponseDtoFactoryImpl implements ResponseDtoFactory {
    @Override
    public <T> ResponseDto<T> success(T body) {
        ResponseDto<T> result = new ResponseDto<>();
        result.body = body;
        result.status = true;
        return result;
    }

    @Override
    public ResponseDto<?> success() {
        ResponseDto<Object> result = new ResponseDto<>();
        result.status = true;
        return result;
    }

    @Override
    public ResponseDto<?> failure(String error) {
        ResponseDto<Object> result = new ResponseDto<>();
        result.status = false;
        result.error = error;
        return result;
    }
}
