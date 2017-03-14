package com.studymp.presentation.interfaces;

import com.studymp.presentation.dto.ResponseDto;

/**
 * Created by qwerty on 14.03.2017.
 */
public interface ResponseDtoFactory {
    <T> ResponseDto<T> success(T body);
    ResponseDto<?> success();
    ResponseDto<?> failure(String error);
}
